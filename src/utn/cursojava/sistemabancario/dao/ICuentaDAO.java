/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utn.cursojava.sistemabancario.dao;

import exceptions.OptionNotAvailable;
import java.sql.SQLException;
import utn.cursojava.sistemabancario.modelo.Cuenta;

/**
 *
 * @author Francisco de la Cruz v1.7
 */
public interface ICuentaDAO {
    
    /**
     * Conecto a la DB para buscar un saldo y descontar el monto requerido de
     * una cuenta determinada <br>
     *
     * @param idCuenta id de la cuenta a extraer
     * @param monto valor a descontar al saldo
     */
    public void extraer(int idCuenta, double monto) throws SQLException, ClassNotFoundException;
    
    /**
     * Conecto a la DB para consultar el Saldo de una cuenta determinada
     *
     * @param idCuenta id de la cuenta a consultar
     * @return valor
     */
    public double consultarSaldo(int idCuenta);
    
/**
 * Metodo para transferir MONTO desde una cuenta (donde descuento) hacia otra que esta o no dentro de la BD
 * @param cuentaExtraer
 * @param monto
 * @param depositarCuenta
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public void transferirOtro(int cuentaExtraer, Double monto, String depositarCuenta) throws SQLException, ClassNotFoundException;
    
    /**
     * Conecto a la DB para sumar el saldo a una cuenta por su CBU
     *
     * @param depositarCuenta CBU de la cuenta a depositar
     * @param monto valor
     */
    public void depositarCBU(String depositarCuenta, Double monto) throws SQLException, ClassNotFoundException, OptionNotAvailable;
    
    /**
     * Metodo para transferir a una misma cuenta
     *Descuento en una cuenta y le sumo a la otra cuenta
     * @param cuentaDepositar
     * @param monto
     * @param cuentaExtraer
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void transferir(int cuentaDepositar, Double monto, int cuentaExtraer) throws SQLException, ClassNotFoundException;
}
