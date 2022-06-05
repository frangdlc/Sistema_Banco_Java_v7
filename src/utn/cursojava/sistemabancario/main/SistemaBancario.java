package utn.cursojava.sistemabancario.main;

import exceptions.InvalidDocument;
import exceptions.OptionNotAvailable;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.cursojava.sistemabancario.constants.TipoCuenta;
import utn.cursojava.sistemabancario.constants.TipoMoneda;
import utn.cursojava.sistemabancario.modelo.CajaDeAhorro;
import utn.cursojava.sistemabancario.modelo.Cliente;
import utn.cursojava.sistemabancario.modelo.Cuenta;
import utn.cursojava.sistemabancario.modelo.CuentaCorriente;
import utn.cursojava.sistemabancario.modelo.Sucursal;
import utn.cursojava.sistemabancario.servicios.ClienteService;
import utn.cursojava.sistemabancario.servicios.CuentaService;
import utn.cursojava.sistemabancario.servicios.IClienteService;
import utn.cursojava.sistemabancario.servicios.ICuentaService;
import utn.cursojava.sistemabancario.servicios.ISucursalService;
import utn.cursojava.sistemabancario.servicios.SucursalService;

/**
 * Tomando como punto de partida el diagrama de Clases del sistema de Gesti�n
 * Bancaria construya una aplicaci�n que permita realizar las siguientes
 * operaciones:
 *
 *
 * **********MENU PRINCIPAL********** <br>
 * 1) Agregar Cliente<br>
 * 2) Agregar cuenta a Cliente<br>
 * 3) Listar Clientes por sucursal<br>
 * 4) Listar Clientes de una sucursal<br>
 * 5) Extraer dinero<br>
 * 6) Consultar Saldo<br>
 * 7) Realizar Deposito<br>
 * 8) Realizar transferencias<br>
 * 9) Eliminar una sucursal<br>
 *
 * @author Francisco de la Cruz v1.7
 *
 */
public class SistemaBancario {

    public static void main(String[] args) throws InvalidDocument {

        Scanner sc = new Scanner(System.in);
        ISucursalService sucursal = new SucursalService();
        List<Sucursal> sucursales;
        IClienteService clienteService = new ClienteService();
        ICuentaService cuentaService = new CuentaService();
        Cliente altaCliente = null;
        Cuenta altaCuenta = null;
        String dni;
        int num;
        int opcion;
        do {
            opcion = 0;
            opcion = validarMenu();
            switch (opcion) {
                case 1:

                    System.out.println("Nombre/s");
                    String nombre = sc.next();
                    sc.nextLine();
                    System.out.println("Apellido/s");
                    String apellido = sc.nextLine();
                    String nombreApellido = nombre + " " + apellido;
                    System.out.println("DNI");
                    dni = ingresarDNI();
                    System.out.println("Elija sucursal mas cercana: ");
                    int numSucursal = seleccionarSucursal();
                    boolean valido;
                    try {
                        altaCliente = new Cliente(dni, nombreApellido, numSucursal);
                        clienteService.addCliente(altaCliente);
                        valido = true;
                        opcion = 2;
                    } catch (SQLException e) {
                        System.out.println("Error, Vuelva a ingresar valor");
                        valido = false;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error");
                    }
                case 2:
                    if (altaCliente != null) {
                        System.out.println("Tipo de Cuenta para el nuevo Cliente " + altaCliente.getNombreApellido());
                        int opcionCuenta = validarMenuCuentas();
                        double saldo = 0.0;
                        String CBU = cadenaAleatoriaCBU(22);
                        switch (opcionCuenta) {
                            case 1:
                                altaCuenta = new CajaDeAhorro(null, saldo, CBU, altaCliente, TipoCuenta.CAJA_AHORRO.getDescripcion(), TipoMoneda.MONEDA_PESO.getDescripcion());
                                break;
                            case 2:
                                altaCuenta = new CajaDeAhorro(null, saldo, CBU, altaCliente, TipoCuenta.CAJA_AHORRO.getDescripcion(), TipoMoneda.MONEDA_DOLAR.getDescripcion());
                                break;
                            case 3:
                                altaCuenta = new CuentaCorriente(null, saldo, CBU, altaCliente, TipoCuenta.CUENTA_CORRIENTE.getDescripcion(), TipoMoneda.MONEDA_PESO.getDescripcion());
                                break;
                            case 4:
                                altaCuenta = new CuentaCorriente(null, saldo, CBU, altaCliente, TipoCuenta.CUENTA_CORRIENTE.getDescripcion(), TipoMoneda.MONEDA_DOLAR.getDescripcion());
                                break;
                        }
                        try {
                            clienteService.crearCuenta(altaCuenta);
                        } catch (SQLException e) {
                            System.out.println("Error al ingresar datos de Usuario");
                        } catch (ClassNotFoundException e) {
                            System.out.println("Error al asignar datos en las columnas de la BD");
                        }
                        altaCliente = null;
                        break;
                    } else if (altaCliente == null) {
                        System.out.println("Ingrese DNI del Cliente a agregar cuanta");
                        dni = ingresarDNI();
                        altaCliente = clienteService.buscarCliente(dni);
                        if (altaCliente.getNombreApellido() != null) {
                            System.out.println("Tipo de Cuenta para el nuevo Cliente " + altaCliente.getNombreApellido());
                            int opcionCuenta = validarMenuCuentas();
                            boolean validar = false;
                            do {
                                validar = false;
                                try {
                                    double saldo = validarMonto();
                                    String CBU = cadenaAleatoriaCBU(22);
                                    switch (opcionCuenta) {
                                        case 1:
                                            altaCuenta = new CajaDeAhorro(null, saldo, CBU, altaCliente, TipoCuenta.CAJA_AHORRO.getDescripcion(), TipoMoneda.MONEDA_PESO.getDescripcion());
                                            validar = true;
                                            break;
                                        case 2:
                                            altaCuenta = new CajaDeAhorro(null, saldo, CBU, altaCliente, TipoCuenta.CAJA_AHORRO.getDescripcion(), TipoMoneda.MONEDA_DOLAR.getDescripcion());
                                            validar = true;
                                            break;
                                        case 3:
                                            altaCuenta = new CuentaCorriente(null, saldo, CBU, altaCliente, TipoCuenta.CUENTA_CORRIENTE.getDescripcion(), TipoMoneda.MONEDA_PESO.getDescripcion());
                                            validar = true;
                                            break;
                                        case 4:
                                            altaCuenta = new CuentaCorriente(null, saldo, CBU, altaCliente, TipoCuenta.CUENTA_CORRIENTE.getDescripcion(), TipoMoneda.MONEDA_DOLAR.getDescripcion());
                                            validar = true;
                                            break;
                                    }
                                    clienteService.crearCuenta(altaCuenta);
                                    altaCliente = null;
                                } catch (Exception e) {
                                    System.out.println("Debe ingresar un monto numerico");
                                }
                            } while (validar == false);
                        } else {
                            System.out.println("No existe cliente");
                            altaCliente = null;
                            break;
                        }
                        break;
                    }
                    break;
                case 3:
                    List<Cliente> clientesTotales = clienteService.listarClientes();
                    System.out.println("\nSUCURSAL  Nombre y Apellido   DNI\n");
                    for (Cliente clientes : clientesTotales) {
                        System.out.print(sucursal.traerSucursal(clientes.getSucursalId()));
                        System.out.print("  ");
                        System.out.print(clientes.getNombreApellido());
                        System.out.print("  ");
                        System.out.print(clientes.getDni());
                        System.out.println("\n");
                    }
                    break;
                case 4:
                    System.out.println("Elija sucursal a listar Clientes: ");
                    do {
                        valido = false;
                        try {
                            numSucursal = seleccionarSucursal();
                            List<Cliente> clientesSucursal = clienteService.listarClientes(numSucursal);
                            valido = true;
                            for (Cliente clientesS : clientesSucursal) {
                                System.out.print(clientesS.getNombreApellido());
                                System.out.print("  ");
                                System.out.print(clientesS.getDni());
                                System.out.println("\n");
                            }
                            valido = true;
                        } catch (Exception e) {
                            System.out.println("Error al intentar traer clientes");
                        }
                    } while (valido == false);
                    break;
                case 5:
                    System.out.println("Extraccion");
                    System.out.println("Ingrese el DNI del Cliente");
                    dni = ingresarDNI();
                    List<Cuenta> listarCuentas = clienteService.listarCuentas(dni);
                    if (listarCuentas.isEmpty()) {
                        System.out.println("DNI Sin Cuenta/s");
                    } else {
                        num = 1;
                        for (Cuenta cuentas : listarCuentas) {
                            System.out.print("Nº " + cuentas.getNumCuenta());
                            System.out.print(" ");
                            System.out.print("CBU: ");
                            System.out.print(" ");
                            System.out.print(cuentas.getCbu());
                            System.out.print("  ");
                            if (cuentas.getTipoCuenta().equals("CA")) {
                                System.out.print("Caja de Ahorro");
                            } else {
                                System.out.print("Cuenta Corriente");
                            }
                            System.out.print("  ");
                            if (cuentas.getTipoMoneda().equals("P")) {
                                System.out.print("Pesos");
                            } else {
                                System.out.print("Dolares");
                            }
                            System.out.print("  ");
                            num++;
                            System.out.println("\n");
                        }
                        System.out.println("Nº de cuenta a extraer :");
                        do {
                            valido = false;
                            try {
                                int cuentaElegida = sc.nextInt();
                                for (Cuenta cuentas : listarCuentas) {
                                    if (cuentas.getNumCuenta().equals(cuentaElegida)) {
                                        System.out.println("Monto a extraer :");
                                        Double monto = validarMonto();
                                        cuentaService.extraer(cuentaElegida, monto);
                                        valido = true;
                                        break;
                                    }
                                }
                                if (valido == false) {
                                    System.out.println("Vuelva a ingresar un valor de cuenta valido");
                                }
                            } catch (Exception e) {
                                System.out.println("Solo debe ingresar un digito");
                                sc.nextLine();
                            }
                        } while (valido == false);
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el DNI del Cliente");
                    dni = ingresarDNI();
                    listarCuentas = clienteService.listarCuentas(dni);
                    if (listarCuentas.isEmpty()) {
                        System.out.println("DNI Sin Cuenta/s");
                    } else {
                        System.out.println("Saldo de cuenta : ");
                        listarCuentas = clienteService.listarCuentas(dni);
                        num = 1;
                        for (Cuenta cuentas : listarCuentas) {
                            System.out.print(num + ")");
                            System.out.print("CBU: ");
                            System.out.print(" ");
                            System.out.print(cuentas.getCbu());
                            System.out.print("  ");
                            if (cuentas.getTipoCuenta().equals("CA")) {
                                System.out.print("Caja de Ahorro");
                            } else {
                                System.out.print("Cuenta Corriente");
                            }
                            System.out.print("  ");
                            if (cuentas.getTipoMoneda().equals("P")) {
                                System.out.print("Pesos");
                            } else {
                                System.out.print("Dolares");
                            }
                            System.out.print("  ");
                            System.out.print("Saldo: " + cuentas.getSaldo());
                            num++;
                            System.out.println("\n");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Deposito");
                    System.out.println("Ingrese CBU a depositar");
                    String cbuIngresado = sc.next();
                    System.out.println("Monto a depositar");
                    Double monto = validarMonto();
                    try {
                        cuentaService.depositarCBU(cbuIngresado, monto);
                    } catch (SQLException ex) {
                        Logger.getLogger(SistemaBancario.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SistemaBancario.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (OptionNotAvailable ex) {
                        Logger.getLogger(SistemaBancario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 8:
                    System.out.println("Transferencia");
                    System.out.println("Ingrese el DNI del Cliente");
                    dni = ingresarDNI();
                    listarCuentas = clienteService.listarCuentas(dni);
                    if (listarCuentas.isEmpty()) {
                        System.out.println("DNI Sin Cuenta/s");
                    } else {
                        num = 0;
                        for (Cuenta cuentas : listarCuentas) {
                            System.out.print("Nº " + cuentas.getNumCuenta());
                            System.out.print(" ");
                            System.out.print("CBU: ");
                            System.out.print(" ");
                            System.out.print(cuentas.getCbu());
                            System.out.print("  ");
                            if (cuentas.getTipoCuenta().equals("CA")) {
                                System.out.print("Caja de Ahorro");
                            } else {
                                System.out.print("Cuenta Corriente");
                            }
                            System.out.print("  ");
                            if (cuentas.getTipoMoneda().equals("P")) {
                                System.out.print("Pesos");
                            } else {
                                System.out.print("Dolares");
                            }
                            System.out.print("  ");
                            System.out.println("\n");
                            num++;
                        }
                        int cuentaExtraer = 0;
                        int cuentaDepositar = 0;
                        int cuentaElegida;
                        if (num == 1) {
                            try {
                                System.out.println("Nº de cuenta que Transfiere:");
                                do {
                                    valido = false;
                                    try {
                                        cuentaElegida = sc.nextInt();
                                        for (Cuenta cuentas : listarCuentas) {
                                            if (cuentas.getNumCuenta().equals(cuentaElegida)) {
                                                cuentaExtraer = cuentaElegida;
                                                valido = true;
                                                break;
                                            }
                                        }
                                        if (valido == false) {
                                            System.out.println("Vuelva a ingresar un valor de cuenta valido");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Solo debe ingresar un digito");
                                        sc.nextLine();
                                    }
                                } while (valido == false);
                                System.out.println("CBU de cuenta a Transferir:");
                                String cuentaDepositarCBU = sc.next();
                                System.out.println("Monto a transferir");
                                monto = sc.nextDouble();
                                cuentaService.transferirOtro(cuentaExtraer, monto, cuentaDepositarCBU);
                                valido = true;
                            } catch (SQLException ex) {
                                Logger.getLogger(SistemaBancario.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(SistemaBancario.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("Elija las opciones*******************");
                            System.out.println("1) Transferencia a una misma cuenta del cliente");
                            System.out.println("2) Transferencia a otra cuenta");
                            do {
                                valido = false;
                                try {
                                    int opcionTransferencia = sc.nextInt();
                                    if (opcionTransferencia == 1) {
                                        System.out.println("Nº de cuenta que Transfiere:");
                                        do {
                                            valido = false;
                                            try {
                                                cuentaElegida = sc.nextInt();
                                                for (Cuenta cuentas : listarCuentas) {
                                                    if (cuentas.getNumCuenta().equals(cuentaElegida)) {
                                                        cuentaExtraer = cuentaElegida;
                                                        valido = true;
                                                        break;
                                                    }
                                                }
                                                if (valido == false) {
                                                    System.out.println("Vuelva a ingresar un valor de cuenta valido");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Solo debe ingresar un digito");
                                                sc.nextLine();
                                            }
                                        } while (valido == false);
                                        System.out.println("Nº de cuenta a Transferir:");
                                        do {
                                            valido = false;
                                            try {
                                                cuentaElegida = sc.nextInt();
                                                for (Cuenta cuentas : listarCuentas) {
                                                    if (cuentas.getNumCuenta().equals(cuentaElegida)) {
                                                        cuentaDepositar = cuentaElegida;
                                                        valido = true;
                                                        break;
                                                    }
                                                }
                                                if (valido == false) {
                                                    System.out.println("Vuelva a ingresar un valor de cuenta valido");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Solo debe ingresar un digito");
                                                sc.nextLine();
                                            }
                                        } while (valido == false);
                                        System.out.println("Monto a transferir");
                                        monto = sc.nextDouble();
                                        cuentaService.transferir(cuentaDepositar, monto, cuentaExtraer);
                                        valido = true;
                                    } else if (opcionTransferencia == 2) {
                                        System.out.println("Nº de cuenta que Transfiere:");
                                        do {
                                            valido = false;
                                            try {
                                                cuentaElegida = sc.nextInt();
                                                for (Cuenta cuentas : listarCuentas) {
                                                    if (cuentas.getNumCuenta().equals(cuentaElegida)) {
                                                        cuentaExtraer = cuentaElegida;
                                                        valido = true;
                                                        break;
                                                    }
                                                }
                                                if (valido == false) {
                                                    System.out.println("Vuelva a ingresar un valor de cuenta valido");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Solo debe ingresar un digito");
                                                sc.nextLine();
                                            }
                                        } while (valido == false);
                                        System.out.println("CBU de cuenta a Transferir:");
                                        String cuentaDepositarCBU = sc.next();
                                        System.out.println("Monto a transferir");
                                        monto = sc.nextDouble();
                                        cuentaService.transferirOtro(cuentaExtraer, monto, cuentaDepositarCBU);
                                        valido = true;
                                    } else {
                                        System.out.println("Opcion no disponible");
                                        valido = false;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Solo debe ingresar un digito");
                                    sc.nextLine();
                                    valido = false;
                                }
                            } while (valido == false);
                        }
                    }
                    break;
                case 9:
                    System.out.println("Listar Sucursales");
                    sucursales = sucursal.traerSucursales();
                    int contarSucursales = 0;
                    for (Sucursal sucur : sucursales) {
                        contarSucursales++;
                        System.out.print("Opcion " + sucur.getNumSucursal() + " ");
                        System.out.println("Nombre :" + sucur.getNombreSucursal());
                    }
                    if (contarSucursales == 1) {
                        System.out.println("*****Debe quedar 1 sucursal*********");
                        System.out.println("***** No es posible eliminar *********");
                        break;
                    } else {
                        System.out.println("Elegir sucursal a Eliminar");
                        Boolean validar = false;
                        int sucursalElegida = 0;
                        do {
                             sucursalElegida = sc.nextInt();
                            try {
                                for (Sucursal sucur : sucursales) {
                                    contarSucursales++;
                                    if (sucur.getNumSucursal() == sucursalElegida) {
                                        clienteService.cambiarSucursal(sucursalElegida);
                                        sucursal.eliminarSucursal(sucursalElegida);
                                        validar = true;
                                    }
                                }
                                if (validar == false) {
                                    System.out.println("Eleccion no disponible de sucursal");
                                }
                            } catch (SQLException e) {
                                System.out.println("Error");
                                validar = false;
                                sc.nextLine();
                            } catch (ClassNotFoundException e) {
                                System.out.println("Error");
                                validar = false;
                                sc.nextLine();
                            }
                        } while (validar == false);
                    }
                    break;
                default:
                    System.out.println("***** Cerrando Programa *********");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        // Mensaje en caso de que falle
                    }
                    break;
            }

        } while (opcion != 10);
    }

    /**
     * generacion de CBU de 22 caracteres entregados en un String
     *
     * @param longitud de 22 digitos
     * @return cadena de 22 digitos (caracteres)
     */
    public static String cadenaAleatoriaCBU(int longitud) {
        String banco = "1234567890";
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    /**
     * rango de valores a tomar para el CBU
     *
     * @param minimo
     * @param maximo
     * @return
     */
    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    /**
     * Mostrar Menu recurrente y devuelve una opcion entre 1 al 10
     *
     * @return valor entero elegido
     */
    private static int validarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        System.out.println("    Menu Inicial    ");
        System.out.println("Opcion 1) Agregar Cliente");
        System.out.println("Opcion 2) Agregar Cuenta");
        System.out.println("Opcion 3) Listar Clientes por sucursal");
        System.out.println("Opcion 4) Listar Clientes de una sucursal");
        System.out.println("Opcion 5) Extraer dinero");
        System.out.println("Opcion 6) Consultar Saldo");
        System.out.println("Opcion 7) Realizar Deposito");
        System.out.println("Opcion 8) Realizar Transferencia");
        System.out.println("Opcion 9) Eliminar Sucursal");
        System.out.println("Opcion 10) SALIR");
        do {
            try {
                opcion = sc.nextInt();
                if (opcion <= 0 || opcion > 10) {
                    System.out.println("Debe ingresar una Opcion posible");
                }
            } catch (Exception e) {
                System.out.println("Debe ingresar una Opcion posible");
                sc.nextLine();
                opcion = 0;
            }
        } while (opcion <= 0 || opcion > 10);
        sc.nextLine();
        return opcion;
    }

    /**
     * Mostrar menu de cuentas recurrente y devuelve una opcion elegida entre 1
     * al 4
     *
     * @return valor entero elegido
     */
    private static int validarMenuCuentas() {
        Scanner sc = new Scanner(System.in);
        int opcionCuenta;
        System.out.println("1) Alta de Caja de Ahorro en PESOS");
        System.out.println("2) Alta de Caja de Ahorro en DOLARES");
        System.out.println("3) Alta de Cuenta Corriente en PESOS");
        System.out.println("4) Alta de Cuenta Corriente en DOLARES");
        do {
            try {
                opcionCuenta = sc.nextInt();
                if (opcionCuenta <= 0 || opcionCuenta > 4) {
                    System.out.println("Debe ingresar una Opcion posible");
                    sc.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Debe ingresar una Opcion posible");
                opcionCuenta = 0;
                sc.nextLine();
            }
        } while (opcionCuenta <= 0 || opcionCuenta > 4);
        return opcionCuenta;
    }

    /**
     * Solicita un ingreso de valor y valida que sea 0 o mayor
     *
     * @return valor entero >= 0
     */
    private static double validarMonto() {
        Scanner sc = new Scanner(System.in);
        double monto = -1.0;
        boolean validar = false;
        do {
            try {
                System.out.println("Ingrese Monto");
                monto = sc.nextDouble();
                validar = true;
                if (monto < 0) {
                    System.out.println("debe ingresar un valor mayor o igual a 0");
                    validar = false;
                    sc.nextLine();
                    monto = -1.0;
                }
            } catch (Exception e) {
                System.out.println("Debe ingresar un monto numerico");
                validar = false;
                sc.nextLine();
                monto = -1.0;
            }
        } while (validar == false);
        sc.nextLine();
        return monto;
    }

    /**
     * Metodo para ingresar el DNI por consola
     *
     * @return dni validado
     * @throws InvalidDocument error que es invalido ese documento
     */
    private static String ingresarDNI() throws InvalidDocument {
        boolean validar = false; //para saber si se cumple la validacion
        String ingresoDNI = null;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                ingresoDNI = sc.next();
                if (!validarDNI(ingresoDNI)) {
                    throw new InvalidDocument();
                }
                validar = true; // quedara true si no hay error al ingresar el valor
            } catch (InvalidDocument e) {
                System.out.println(e);
                System.out.println("Vuelva a ingresar el DNI");
                validar = false;
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error");
                System.out.println("Debe ingresar un valor numerico referido al DNI");
                validar = false;
                sc.nextLine();
            }
        } while (validar != true);
        return ingresoDNI;

    }

    /**
     * Metodo para verificar la longitud de 8 y que sean numericos el DNI
     *
     * @param dni
     * @return
     */
    public static boolean validarDNI(String dni) {
        boolean validacion = true;
        int contador = 0;
        for (int i = 0; i < dni.length(); i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                validacion = false;
            }
            contador++;
        }
        if (contador != 8) {
            validacion = false;
        }
        return validacion;
    }

    /**
     * Metodo para traer, mostrar y elegir correctamente una sucursal
     *
     * @return el id de la sucursal elegida
     */
    private static int seleccionarSucursal() {
        int numSucursal = 0;
        boolean valido;
        Scanner sc = new Scanner(System.in);
        ISucursalService sucursal = new SucursalService();
        List<Sucursal> sucursales = sucursal.traerSucursales();
        for (Sucursal sucur : sucursales) {
            System.out.print("Opcion " + sucur.getNumSucursal() + " ");
            System.out.println("Nombre :" + sucur.getNombreSucursal());
        }
        do {
            valido = false;
            try {
                numSucursal = sc.nextInt();
                System.out.println(numSucursal);
                if (sucursal.traerSucursal(numSucursal).isEmpty()) {
                    System.out.println("Opcion Elegida incorrecta, vuelva a ingresar una opcion");
                    valido = false;
                } else {
                    valido = true;
                }
            } catch (NullPointerException x) {
                System.out.println("Opcion Elegida incorrecta, vuelva a ingresar una opcion");
                valido = false;

            } catch (Exception e) {
                System.out.println("Debe ingresar un digito");
            }
        } while (valido == false);
        return numSucursal;
    }

}
