package utn.cursojava.sistemabancario.dao;

import connection.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.cursojava.sistemabancario.modelo.Cliente;
import utn.cursojava.sistemabancario.modelo.Cuenta;
import utn.cursojava.sistemabancario.modelo.CuentaCorriente;

/**
 *Clase que implementa la interfaz ICulienteDAO 
 *Desarrollando e implementando cada uno de sus metodos
 * @author Francisco de la Cruz v1.7
 */
public class ClienteDAO implements IClienteDAO {

    private static ClienteDAO instance;

    private ClienteDAO() {
    }

    public synchronized static ClienteDAO getInstance() {
        if (instance == null) {

            instance = new ClienteDAO();
        }

        return instance;
    }

    @Override
    public void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO clientes(DNI, NOMBRE_APELLIDO, NRO_SUCURSAL) VALUES (?,?,?)");
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombreApellido());
            stmt.setInt(3, cliente.getSucursalId());
            stmt.executeUpdate();

            stmt.close();
            con.commit();

        } catch (SQLException e) {
            System.out.println(e);
            con.rollback();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            con.rollback();
            throw new ClassNotFoundException();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }

    }


    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new LinkedList<>();
        Connection con = null;

        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select DNI, NOMBRE_APELLIDO, sucursales.NRO_SUCURSAL from clientes join sucursales on clientes.NRO_SUCURSAL = sucursales.NRO_SUCURSAL ");
            ResultSet rs = stmt.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString(1));
                cliente.setNombreApellido(rs.getString(2));
                cliente.setSucursalId(rs.getInt(3));
                clientes.add(cliente);
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clientes;
    }


    public List<Cliente> listarClientes(int nroSucursal) { 
        Connection con = null;

        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select DNI, NOMBRE_APELLIDO from clientes where NRO_SUCURSAL = ?");
            stmt.setInt(1, nroSucursal);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = null;
            List<Cliente> clientes = new LinkedList<>();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString(1));
                cliente.setNombreApellido(rs.getString(2));
                clientes.add(cliente);
            }

            stmt.close();
            rs.close();
            return clientes;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    public void crearCuenta(Cuenta cuenta) throws SQLException, ClassNotFoundException {

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("select NRO_CLIENTE from clientes where DNI = ?");
            stmt.setString(1, cuenta.getCliente().getDni());
            ResultSet rs = stmt.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                Integer nroCliente = rs.getInt(1);
                PreparedStatement stmt2 = con.prepareStatement("INSERT INTO cuentas (CBU, SALDO,NRO_CLIENTE, TIPO_CUENTA, TIPO_MONEDA) VALUES (?,?,?,?,?)");
                stmt2.setString(1, cuenta.getCbu());
                stmt2.setDouble(2, cuenta.getSaldo());
                stmt2.setDouble(3, nroCliente);
                stmt2.setString(4, cuenta.getTipoCuenta());
                stmt2.setString(5, cuenta.getTipoMoneda());
                stmt2.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            System.out.println(e);
            con.rollback();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            con.rollback();
            throw new ClassNotFoundException();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }
    }


    @Override
    public Cliente buscarCliente(String dni) {
        Connection con = null;
        Cliente cliente = new Cliente();
        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select DNI, NOMBRE_APELLIDO, NRO_SUCURSAL from clientes where DNI = ?");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cliente.setDni(rs.getString(1));
                cliente.setNombreApellido(rs.getString(2));
                cliente.setSucursalId(rs.getInt(3));
            }
            stmt.close();
            rs.close();
            return cliente;

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    @Override
    public List<Cuenta> listarCuentas(String dni) {
        List<Cuenta> cuentas = new LinkedList<>();
        Cliente cliente = new Cliente();
        int nroCliente = 0;
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select NRO_CLIENTE from clientes where DNI = ?");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nroCliente = rs.getInt(1);
            }
            PreparedStatement stmt2 = con.prepareStatement("Select NRO_CUENTA, CBU, SALDO, TIPO_CUENTA, TIPO_MONEDA from cuentas where NRO_CLIENTE = " + nroCliente);
            rs = stmt2.executeQuery();
            Cuenta cuenta;
            while (rs.next()) {
                cuenta = new CuentaCorriente();
                cuenta.setNumCuenta(rs.getInt(1));
                cuenta.setCbu(rs.getString(2));
                cuenta.setSaldo(rs.getDouble(3));
                cuenta.setTipoCuenta(rs.getString(4));
                cuenta.setTipoMoneda(rs.getString(5));
                cuentas.add(cuenta);
            }
            stmt.close();
            stmt2.close();
            rs.close();
            return cuentas;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    @Override
    public void cambiarSucursal(int idSucursal) throws SQLException, ClassNotFoundException {
        Cliente cliente = new Cliente();
        int nroSucursalNuevo = 0;
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("Select NRO_SUCURSAL from sucursales where NRO_SUCURSAL != ? limit 1");
            stmt.setInt(1, idSucursal);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nroSucursalNuevo = rs.getInt(1);
            }
            PreparedStatement stmt2 = con.prepareStatement("UPDATE clientes SET NRO_SUCURSAL = ? WHERE NRO_SUCURSAL = ? ");
            stmt2.setInt(1, nroSucursalNuevo);
            stmt2.setInt(2, idSucursal);
            stmt2.executeUpdate();
            
            stmt.close();
            stmt2.close();
            rs.close();
            con.commit();
        } catch (SQLException e) {
            System.out.println(e);
            con.rollback();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            con.rollback();
            throw new ClassNotFoundException();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }
    }
}
