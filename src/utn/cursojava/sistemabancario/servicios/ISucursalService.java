

package utn.cursojava.sistemabancario.servicios;

import java.sql.SQLException;
import java.util.List;
import utn.cursojava.sistemabancario.modelo.Sucursal;

/**
 * Declaracion de metodos para la clase SucursalService
 *
 * @author Francisco de la Cruz v1.7
 */
public interface ISucursalService {
    
    /**
     * Metodo para traer una sucursal determinada de la DB
     *
     * @param sucursal sucursal a buscar y traer
     * @return una sucursal id
     */
    public String traerSucursal(int sucursal);
    
    /**
     * Metodo para traer todas las sucursales en forma de lista<br>
     * Me dirijo a la capa DAO<br>
     *
     * @return una lista de sucursales
     */
    public List<Sucursal> traerSucursales();
    
    /**
     * Metodo para eliminar una sucursal determinada de la DB
     *
     * @param sucursal a eliminar
     */
    public void eliminarSucursal(int idSucursal)throws SQLException, ClassNotFoundException ;
}
