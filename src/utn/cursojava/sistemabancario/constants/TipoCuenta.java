package utn.cursojava.sistemabancario.constants;
/**
 * Valores definidos para opciones
 * Clase enum de Tipos de Cuentas
 * @author Francisco de la Cruz v1.7
 */
public enum TipoCuenta {

	CAJA_AHORRO("CA", 1), CUENTA_CORRIENTE("CC", 2);

	private Integer nroCuenta;
	private String descripcion;

	TipoCuenta(String descripcion, Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
		this.descripcion = descripcion;
	}

	public Integer getNroCuenta() {
		return nroCuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
