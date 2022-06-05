package utn.cursojava.sistemabancario.modelo;

public class CajaDeAhorro extends Cuenta {
	// Atributos
	private String tipoMoneda;
        private String tipoCuenta;
	// COSNTRUCTOR
	public CajaDeAhorro() {
	}

	/**
	 * 
	 * @param numCuenta
	 * @param saldo
     * @param cbu
	 * @param tipoMoneda: pesos 'P', dolares 'D'
	 */
	public CajaDeAhorro(Integer numCuenta, Double saldo, String cbu, Cliente cliente, String tipoCuenta, String tipoMoneda) {
		super(numCuenta, saldo, cbu, cliente, tipoCuenta, tipoMoneda);
	}


}
