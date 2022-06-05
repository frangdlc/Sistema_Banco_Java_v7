package utn.cursojava.sistemabancario.modelo;

public abstract class Cuenta {
	protected Integer numCuenta;
	protected Double saldo;
	protected String cbu;
        protected Cliente cliente;
        protected String tipoCuenta;
        protected String tipoMoneda;

    public Cuenta(Integer numCuenta, Double saldo, String cbu, Cliente cliente, String tipoCuenta, String tipoMoneda) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.cbu = cbu;
        this.cliente = cliente;
        this.tipoCuenta = tipoCuenta;
        this.tipoMoneda = tipoMoneda;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
	// Constructor
	public Cuenta() {
	}

	public Cuenta(Integer numCuenta, Double saldo, String cbu) {
		this.numCuenta = numCuenta;
		this.saldo = saldo;
		this.cbu = cbu;
	}

	public Integer getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta = numCuenta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

}
