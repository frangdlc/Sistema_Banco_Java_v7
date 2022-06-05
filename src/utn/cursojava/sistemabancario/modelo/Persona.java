package utn.cursojava.sistemabancario.modelo;

public abstract class Persona {

	// Atributos
	protected String dni;
	protected String nombreApellido;
	protected String telefono;
	protected String email;

	// Constructor

	public Persona() {
	}

	public Persona(String dni, String nombreApellido) {
		this.dni = dni;
		this.nombreApellido = nombreApellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
