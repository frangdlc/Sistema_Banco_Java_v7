package utn.cursojava.sistemabancario.dao;

import java.sql.SQLException;
import java.util.List;

import utn.cursojava.sistemabancario.modelo.Cliente;
import utn.cursojava.sistemabancario.modelo.Cuenta;
/**
 * 
 * @author Francisco de la Cruz v1.7
 */
public interface IClienteDAO {
    
    /**
     * Conecto a la DB para agregar un cliente<br>
     * datos enviados por parametro<br>
     * genero y ejecuto la consulta SQL <br>
     *
     * @param cliente
     */
    public void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
    
    /**
     * Conecto a la DB para agregar una Cuenta nueva <br>
     * genero y ejecuto la consulta SQL <br>
     *
     * @param cuenta
     */
    public void crearCuenta(Cuenta cuenta)throws SQLException, ClassNotFoundException;
    
    /**
     * Conecto a la DB para buscar un cliente por su DNI<br>
     *
     * @param dni
     * @return un objeto tipo Cliente
     */
    public Cliente buscarCliente(String dni);
    
    /**
     * Conecto a la DB para traer los clientes de una sucursal determinada <br>
     * genero y ejecuto la consulta SQL <br>
     *
     * @param nroSucursal
     * @return una lista de clientes
     */
    public List<Cliente> listarClientes(int nroSucursal);
    
    /**
     * Conecto a la DB para traer todos los clientes <br>
     * genero y ejecuto la consulta SQL <br>
     *
     * @return una lista de clientes
     */
    public List<Cliente> listarClientes();
    
    /**
     * Conecto a la DB para buscar las cuentas de un DNI<br>
     *
     * @param dni
     * @return una lista de cuentas
     */
    public List<Cuenta> listarCuentas(String dni);
    
    /**
     * Conecto a la DB para pasar los clientes a otra sucursal distinta <br>
     *
     * @param idSucursal
     */
    public void cambiarSucursal(int idSucursal)throws SQLException, ClassNotFoundException ;
}
