/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utn.cursojava.sistemabancario.servicios;

import java.sql.SQLException;
import java.util.List;
import utn.cursojava.sistemabancario.dao.ISucursalDAO;
import utn.cursojava.sistemabancario.dao.SucursalDAO;
import utn.cursojava.sistemabancario.modelo.Sucursal;

/**
 *
 * @author Francisco de la Cruz v1.7
 */
public class SucursalService implements ISucursalService {

    private ISucursalDAO sucursalDao;

    public SucursalService() {
        this.sucursalDao = new SucursalDAO();

    }

    @Override
    public List<Sucursal> traerSucursales() {
        return sucursalDao.traerSucursales();
    }


    @Override
    public String traerSucursal(int sucursal) {
        return sucursalDao.traerSucursal(sucursal);
    }


    @Override
    public void eliminarSucursal(int sucursal) throws SQLException, ClassNotFoundException {
        try {
            sucursalDao.eliminarSucursal(sucursal);
        } catch (SQLException e) {
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }


}
