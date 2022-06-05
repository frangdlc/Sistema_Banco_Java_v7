/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utn.cursojava.sistemabancario.dao;

import connection.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.cursojava.sistemabancario.modelo.Sucursal;

/**
 *Clase que implementa la interfaz ISucursalDAO 
 *Desarrollando e implementando cada uno de sus metodos
 * @author Francisco de la Cruz v1.7
 */
public class SucursalDAO implements ISucursalDAO {

    private static SucursalDAO instance;


    @Override
    public List<Sucursal> traerSucursales() {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select NRO_SUCURSAL, NOMBRE_SUCURSAL from sucursales");
            ResultSet rs = stmt.executeQuery();
            List<Sucursal> sucursales = new LinkedList<>();
            Sucursal sucursal;
            while (rs.next()) {
                sucursal = new Sucursal();
                sucursal.setNumSucursal(rs.getInt(1));
                sucursal.setNombreSucursal(rs.getString(2));
                sucursales.add(sucursal);
            }

            stmt.close();
            rs.close();
            return sucursales;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SucursalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SucursalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }


    @Override
    public String traerSucursal(int sucursal) {

        Sucursal sucur = new Sucursal();
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select NOMBRE_SUCURSAL from sucursales where NRO_SUCURSAL = " + sucursal);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sucur.setNombreSucursal(rs.getString(1));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SucursalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SucursalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sucur.getNombreSucursal();
    }


    @Override
    public void eliminarSucursal(int sucursal) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("delete from sucursales where NRO_SUCURSAL = " + sucursal);
            stmt.executeUpdate();

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
