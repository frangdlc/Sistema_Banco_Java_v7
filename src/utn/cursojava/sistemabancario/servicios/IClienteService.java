package utn.cursojava.sistemabancario.servicios;

import java.sql.SQLException;
import java.util.List;
import utn.cursojava.sistemabancario.modelo.Cliente;
import utn.cursojava.sistemabancario.modelo.Cuenta;

/**
 * Declaracion de metodos para luego implementar en la clase ClienteService
 *
 * @author Francisco de la Cruz v1.7
 */
public interface IClienteService {

    /**
     * Metodo para agregar un (Cliente) a la DB<br>
     * Me dirijo a la capa DAO<br>
     * @param cliente
     */
    public void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
    
    /**
     * Metodo para buscar en la DB a un cliente por un dato (dni)<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param dni
     * @return un objeto del tipo Cliente que tiene los datos
     */
    public Cliente buscarCliente(String dni);
    
    /**
     * Metodo para traer de la DB en una lista a todos los clientes<br>
     * Me dirijo a la capa DAO<br>
     *
     * @return una lista de clientes
     */
    public List<Cliente> listarClientes();
    
    /**
     * Metodo para traer de la DB en una lista a los clientes de una sucursal
     * determinada<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param nroSucursal numero de sucursal a buscar
     * @return una lista de clientes (o solo 1) o null cuando no tenga
     */
    public List<Cliente> listarClientes(int nroSucursal);
    
    /**
     * Metodo para traer de la DB una lista de cuentas determinada por DNI<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param dni
     * @return lista de cuentas
     */
    public List<Cuenta> listarCuentas(String dni);
    
    /**
     * Metodo para agregar a la DB una (Cuenta)<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param cuenta
     */
    public void crearCuenta(Cuenta cuenta) throws SQLException, ClassNotFoundException;
    
    /**
     * Metodo para pasar los clientes a otra sucursal que sea distinto a ese
     * (id)<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param idSucursal para saber a cual sucursal sea distinto
     */
    public void cambiarSucursal(int idSucursal) throws SQLException, ClassNotFoundException;

}
