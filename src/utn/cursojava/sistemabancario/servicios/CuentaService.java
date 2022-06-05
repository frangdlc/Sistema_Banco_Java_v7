package utn.cursojava.sistemabancario.servicios;

import exceptions.OptionNotAvailable;
import java.sql.SQLException;
import utn.cursojava.sistemabancario.dao.CuentaDAO;
import utn.cursojava.sistemabancario.dao.ICuentaDAO;

/**
 * 
 * @author Francisco de la Cruz v1.7
 */
public class CuentaService implements ICuentaService {

    private ICuentaDAO cuentaDao;

    public CuentaService() {
        this.cuentaDao = new CuentaDAO();

    }

    @Override
    public double consultarSaldo(int idCuenta) {
        return cuentaDao.consultarSaldo(idCuenta);
    }


    @Override
    public void extraer(int idCuenta, double monto) throws SQLException, ClassNotFoundException {

        if (consultarSaldo(idCuenta) < monto) {
            System.out.println("No tiene saldo suficiente");
        } else if ((consultarSaldo(idCuenta) - monto) < 0) {
            System.out.println("No tiene saldo suficiente");
        } else if (monto == 0) {
            System.out.println("No puede extraer monto 0");
        } else {
            try {
                cuentaDao.extraer(idCuenta, monto);
            } catch (SQLException e) {
                throw new SQLException();
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException();
            }
        }
    }

    @Override
    public void transferir(int cuentaDepositar, Double monto, int cuentaExtraer) throws SQLException, ClassNotFoundException {
        if (consultarSaldo(cuentaExtraer) < monto) {
            System.out.println("No tiene saldo suficiente");
        } else if ((consultarSaldo(cuentaExtraer) - monto) < 0) {
            System.out.println("No tiene saldo suficiente");
        } else if (monto == 0) {
            System.out.println("Debe ingresar un valor mayor");
        } else {
            try {
                cuentaDao.transferir(cuentaDepositar, monto, cuentaExtraer);
            } catch (SQLException e) {
                System.out.println("Error en la ejecucion de la transferencia "+e);
            } catch (ClassNotFoundException e) {
                System.out.println("Error en la ejecucion de la transferencia "+e);
            }
        }
    }


    @Override
    public void transferirOtro(int cuentaExtraer, Double monto, String depositarCuenta)throws SQLException, ClassNotFoundException {
        if (consultarSaldo(cuentaExtraer) < monto) {
            System.out.println("No tiene saldo suficiente");
        } else if ((consultarSaldo(cuentaExtraer) - monto) < 0) {
            System.out.println("No tiene saldo suficiente");
        } else if (monto == 0) {
            System.out.println("Debe ingresar un valor mayor");
        } else {
            try {
                cuentaDao.transferirOtro(cuentaExtraer, monto, depositarCuenta);
            } catch (SQLException e) {
                System.out.println("Error en la ejecucion de la transferencia "+e);
            } catch (ClassNotFoundException e) {
                System.out.println("Error en la ejecucion de la transferencia "+e);
            } 
        }
    }

    @Override
    public void depositarCBU(String idCuenta, Double monto) throws SQLException, ClassNotFoundException, OptionNotAvailable {
        if (monto == 0) {
            System.out.println("Debe ingresar un valor mayor");
        } else {
            try {
                cuentaDao.depositarCBU(idCuenta, monto);
            } catch (SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (OptionNotAvailable e) {
                System.out.println("CBU No existente en esta BD");
            }
        }
    }
}
