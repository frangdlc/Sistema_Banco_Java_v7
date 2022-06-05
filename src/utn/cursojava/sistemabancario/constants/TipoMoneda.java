/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utn.cursojava.sistemabancario.constants;

/**
 * Valores definidos para opciones
 * Clase enum de Tipos de Monedas
 * @author Francisco de la Cruz v1.7
 */
public enum TipoMoneda {
    	MONEDA_PESO("P", 1), MONEDA_DOLAR("D", 2);

	private Integer nroMoneda;
	private String descripcion;

	TipoMoneda(String descripcion, Integer nroCuenta) {
		this.nroMoneda = nroCuenta;
		this.descripcion = descripcion;
	}

	public Integer getNroCuenta() {
		return nroMoneda;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
