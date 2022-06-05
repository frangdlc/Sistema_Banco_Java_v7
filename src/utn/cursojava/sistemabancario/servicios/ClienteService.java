package utn.cursojava.sistemabancario.servicios;


import java.sql.SQLException;
import java.util.List;
import utn.cursojava.sistemabancario.dao.ClienteDAO;
import utn.cursojava.sistemabancario.dao.IClienteDAO;
import utn.cursojava.sistemabancario.modelo.Cliente;
import utn.cursojava.sistemabancario.modelo.Cuenta;
/**
 * Clase ClienteService que implementa los metodos de IClienteService 
 * @author Francisco de la Cruz v1.7
 */
public class ClienteService implements IClienteService {

    private IClienteDAO clienteDao;

    public ClienteService() {
        this.clienteDao = ClienteDAO.getInstance();
    }

    @Override
    public void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        try {
            clienteDao.addCliente(cliente);
        } catch (SQLException e) {
            System.out.println("Error al ingresar Cliente " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al ingresar Cliente " + e);
        }
    }

    public List<Cliente> listarClientes(int nroSucursal) {
        return clienteDao.listarClientes(nroSucursal);
    }

    public List<Cliente> listarClientes() {
        return clienteDao.listarClientes();
    }

    @Override
    public List<Cuenta> listarCuentas(String dni) {
        return clienteDao.listarCuentas(dni);
    }

    public void crearCuenta(Cuenta cuenta) throws SQLException, ClassNotFoundException {
        try {
            clienteDao.crearCuenta(cuenta);
        } catch (SQLException e) {
            System.out.println("Error al crear cuenta " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear cuenta  " + e.getMessage());
        }

    }

    @Override
    public Cliente buscarCliente(String dni) {
        return clienteDao.buscarCliente(dni);

    }


    @Override
    public void cambiarSucursal(int idSucursal)throws SQLException, ClassNotFoundException  {
        try{
            clienteDao.cambiarSucursal(idSucursal);
        } catch (SQLException e) {
            System.out.println("Error al cambiar de Sucursal " + e);
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cambiar de Sucursal  " + e);
            throw new ClassNotFoundException();
        }
    }

}
