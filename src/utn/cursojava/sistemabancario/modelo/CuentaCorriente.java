package utn.cursojava.sistemabancario.modelo;

public class CuentaCorriente extends Cuenta {
private String tipoMoneda;
private String tipoCuenta;
	public CuentaCorriente() {
	}
	public CuentaCorriente(Integer numCuenta, Double saldo, String cbu, Cliente cliente,String tipoCuenta, String tipoMoneda) {
		super(numCuenta, saldo, cbu, cliente, tipoCuenta, tipoMoneda);
	}

}
