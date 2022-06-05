package utn.cursojava.sistemabancario.servicios;

import exceptions.OptionNotAvailable;
import java.sql.SQLException;

/**
 * Declaracion de metodos para la clase CuentaService
 *
 * @author Francisco de la Cruz v1.7
 */
public interface ICuentaService {
    
    /**
     * Metodo para extraer a una cuenta de mi DB<br>
     * Me dirijo a la capa DAO<br>
     * Validaciones si el monto es menor al saldo disponible<br>
     *
     * @param idCuenta id de la cuenta a extraer
     * @param monto a restarle
     */
    public void extraer(int idCuenta, double monto) throws SQLException, ClassNotFoundException;
    
    /**
     * Metodo para sumar el monto a una cuenta de la DB <br>
     *
     * @param idCuenta cuenta a sumar
     * @param monto valor
     */
    public void depositarCBU(String idCuenta, Double monto) throws SQLException, ClassNotFoundException, OptionNotAvailable;
    
    /**
     * Metodo para consultar en mi DB el SALDO<br>
     * Me dirijo a la capa DAO<br>
     *
     * @param idCuenta id de la cuenta a consultar
     * @return un valor tipo double
     */
    public double consultarSaldo(int idCuenta);
    
    /**
     * Metodo Tansferir para cambiar el monto de una cuenta y sumarla a otr del
     * mismo<br>
     *
     * @param cuentaDepositar Cuenta que sumara a su saldo
     * @param monto valor
     * @param cuentaExtraer Cuenta que resta a su saldo
     */
    public void transferir(int cuentaDepositar, Double monto, int cuentaExtraer) throws SQLException, ClassNotFoundException;
    
    /**
     * Metodo Tansferir para cambiar el monto de una cuenta y sumarla a otro<br>
     *
     * @param cuentaExtraer Cuenta que resta a su saldo
     * @param monto
     * @param depositarCuenta Cuenta que sumara a su saldo
     */
    public void transferirOtro(int cuentaExtraer, Double monto, String cuentaDepositar) throws SQLException, ClassNotFoundException;

}
