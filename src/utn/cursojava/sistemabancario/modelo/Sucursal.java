package utn.cursojava.sistemabancario.modelo;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	// Atributos
	private String nombreSucursal;
	private Integer numSucursal;
	
	private List<Cliente> listaClientes;
	private List<Empleado> listaEmpleados;

    public Sucursal() {
    }

	public Sucursal(String nombreSucursal, Integer numSucursal) {
		this.nombreSucursal = nombreSucursal;
		this.numSucursal = numSucursal;
	}

	// METODOS

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public void setNumSucursal(Integer numSucursal) {
		this.numSucursal = numSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public int getNumSucursal() {
		return numSucursal;
	}

	public void setNumSucursal(int numSucursal) {
		this.numSucursal = numSucursal;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> clientes) {
		this.listaClientes = clientes;
	}

	/**
	 * 
	 * @param dni
	 * @return
	 */
	public Empleado obtenerEmpleado(String dni) {
		for (Empleado empleado : listaEmpleados) {
			if (empleado.getDni().equals(dni)) {
				return empleado;
			}

		}
		return null;
	}

}
