/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utn.cursojava.sistemabancario.dao;

import java.sql.SQLException;
import java.util.List;
import utn.cursojava.sistemabancario.modelo.Sucursal;

/**
 *Interfaz con los metodos declarados
 * @author Francisco de la Cruz v1.7
 */
public interface ISucursalDAO {
    /**
     * Conecto a la DB para traer todas las Sucursales
     *
     * @return una lista de sucursales
     */
    public List<Sucursal> traerSucursales();
    
    /**
     * Conecto a la DB para traer una sucursal determinada
     *
     * @param sucursal id de la sucursal a traer
     * @return sucursal requerida o null
     */
    public String traerSucursal(int sucursal);
    
    /**
     * Conecto a la DB para eliminar sucursal requerida<br>
     *
     * @param sucursal id de la sucursal a eliminar
     */
    public void eliminarSucursal(int sucursal)throws SQLException, ClassNotFoundException;
}
