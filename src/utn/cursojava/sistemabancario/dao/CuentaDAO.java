/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utn.cursojava.sistemabancario.dao;

import connection.ConexionBD;
import exceptions.OptionNotAvailable;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que implementa la interfaz ICuentaDAO 
 *Desarrollando e implementando cada uno de sus metodos
 * @author Francisco de la Cruz v1.7
 */
public class CuentaDAO implements ICuentaDAO {
//private static CuentaDAO instance;


    @Override
    public void extraer(int idCuenta, double monto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("select SALDO from cuentas where NRO_CUENTA = " + idCuenta);
            ResultSet rs = stmt.executeQuery();
            Double saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente - monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE NRO_CUENTA = " + idCuenta);
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.executeUpdate();
            }
            con.commit();
            stmt.close();
            rs.close();
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
    public double consultarSaldo(int idCuenta) {

        double saldo = 0;
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select SALDO from cuentas where NRO_CUENTA = " + idCuenta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                saldo = rs.getDouble(1);
            }
            stmt.close();
            rs.close();

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return saldo;

    }


    @Override
    public void depositarCBU(String depositarCuenta, Double monto) throws SQLException, ClassNotFoundException, OptionNotAvailable {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("select SALDO from cuentas where CBU = ? ");
            stmt.setString(1, depositarCuenta);
            ResultSet rs = stmt.executeQuery();
            Double saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente + monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE CBU = ?");
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.setString(2, depositarCuenta);
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.executeUpdate();
            }
            stmt.close();
            rs.close();
            con.commit();
            if (saldoCliente == null) {
                throw new OptionNotAvailable();
            }
        } catch (OptionNotAvailable e) {
            System.out.println(e);
            con.rollback();
            throw new OptionNotAvailable();
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
    public void transferir(int cuentaDepositar, Double monto, int cuentaExtraer) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("select SALDO from cuentas where NRO_CUENTA = ? ");
            stmt.setInt(1, cuentaExtraer);
            ResultSet rs = stmt.executeQuery();
            Double saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente - monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE NRO_CUENTA = ?");
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.setInt(2, cuentaExtraer);
                stmt2.executeUpdate();
            }
            stmt = con.prepareStatement("select SALDO from cuentas where NRO_CUENTA = ? ");
            stmt.setInt(1, cuentaDepositar);
            rs = stmt.executeQuery();
            saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente + monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE NRO_CUENTA = ?");
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.setInt(2, cuentaDepositar);
                stmt2.executeUpdate();
            }
            stmt.close();
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

    @Override
    public void transferirOtro(int cuentaExtraer, Double monto, String depositarCuenta) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("select SALDO from cuentas where NRO_CUENTA = ? ");
            stmt.setInt(1, cuentaExtraer);
            ResultSet rs = stmt.executeQuery();
            Double saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente - monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE NRO_CUENTA = ?");
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.setInt(2, cuentaExtraer);
                stmt2.executeUpdate();
            }
            stmt = con.prepareStatement("select SALDO from cuentas where CBU = ? ");
            stmt.setString(1, depositarCuenta);
            rs = stmt.executeQuery();
            saldoCliente = null;
            while (rs.next()) {
                saldoCliente = rs.getDouble(1);
                double saldoClienteFinal = saldoCliente + monto;
                PreparedStatement stmt2 = con.prepareStatement("UPDATE cuentas SET SALDO = ? WHERE CBU = ?");
                stmt2.setDouble(1, saldoClienteFinal);
                stmt2.setString(2, depositarCuenta);
                stmt2.executeUpdate();
            }
            stmt.close();
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
