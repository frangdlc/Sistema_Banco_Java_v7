package utn.cursojava.sistemabancario.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Banco {
	// Atributos
	private Integer id;
	private String nombreBanco;
	private List<Sucursal> sucursales;
	private List<Cuenta> cuentas;

	public Banco(Integer id, String nombreBanco) {
		super();
		this.id = id;
		this.nombreBanco = nombreBanco;
		this.sucursales = new ArrayList<>();
		this.cuentas = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	

}
