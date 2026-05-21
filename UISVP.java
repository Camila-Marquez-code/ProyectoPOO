//Tomás Meza

package vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import controlador.*;
import modelo.*;
import utilidades.*;

public class UISVP {
    private static UISVP instancia;

    private Scanner sc;
    private SistemaVentaPasajes sistema;

    private UISVP() {
        sc = new Scanner(System.in);
        sistema = SistemaVentaPasajes.getInstancia();
    }

    public static UISVP getInstancia() {
        if (instancia == null) {
            instancia = new UISVP();
        }
        return instancia;
    }

    public void menu() {

        int opcion;

        do {

            System.out.println("");
            System.out.println("========================================");
            System.out.println( ". . . : : : MENU PRINCIPAL : : : . . .");
            System.out.println("========================================");

            System.out.println("1) Crear empresa");
            System.out.println("2) Contratar tripulante");
            System.out.println("3) Crear terminal");
            System.out.println("4) Crear cliente");
            System.out.println("5) Crear bus");
            System.out.println("6) Crear viaje");
            System.out.println("7) Vender pasajes");
            System.out.println("8) Pagar venta pasajes");
            System.out.println("9) Listar ventas");
            System.out.println("10) Listar viajes");
            System.out.println("11) Listar pasajeros de viaje");
            System.out.println("12) Listar empresas");
            System.out.println("13) Listar llegadas/salidas terminal");
            System.out.println("14) Listar ventas empresa");
            System.out.println("15) Salir");

            System.out.println("----------------------------------------");

            System.out.print("..::: Ingrese número de opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    createEmpresa();
                    break;
                case 2:
                    contrataTripulante();
                    break;
                case 3:
                    createTerminal();
                    break;
                case 4:
                    createCliente();
                    break;
                case 5:
                    createBus();
                    break;
                case 6:
                    createViaje();
                    break;
                case 7:
                    vendePasajes();
                    break;
                case 8:
                    pagaVentaPasajes();
                    break;
                case 9:
                    listVentas();
                    break;
                case 10:
                    listViajes();
                    break;
                case 11:
                    listPasajerosViaje();
                    break;
                case 12:
                    listEmpresas();
                    break;
                case 13:
                    listLlegadasSalidasTerminal();
                    break;
                case 14:
                    listVentasEmpresa();
                    break;
                case 15:
                    System.out.println("...::: Saliendo :::...");
                    break;
                default:
                    System.out.println("ERROR! La opcion ingresada no es valida");
                    break;
            }
        } while (opcion != 15);
    }
    private void createEmpresa() {

        System.out.println("");
        System.out.println("...:::: Creando una nueva Empresa ::::....");

        System.out.print("R.U.T [11.111.111-1] : ");
        String rut = sc.nextLine();

        System.out.print("Nombre : ");
        String nombre = sc.nextLine();

        System.out.print("URL : ");
        String url = sc.nextLine();

        boolean creado =
                sistema.createEmpresa(rut, nombre, url);

        if (creado) {
            System.out.println("...:::: Empresa guardada exitosamente ::::....");
        } else {
            System.out.println("...::: Ya existe otra empresa con el mismo R.U.T :::...");
        }
    }
    private void contrataTripulante() {

        System.out.println("");
        System.out.println("...:::: Contratando un nuevo Tripulante ::::....");

        System.out.print(":::: Dato de la Empresa ");
        System.out.print("...");
        Rut rutEmpresa = Rut.of(sc.nextLine());

        System.out.print(":::: Datos tripulante");
        System.out.print("...");

        System.out.print("Auxiliar[1] o Conductor[2] : ");
        int tipo = sc.nextInt();
        sc.nextLine();

        boolean creado;

        if (tipo == 1) {
            creado = controladorEmpresas.addConductor(rutTrip, nom, dir, rutEmpresa);
        } else {
            creado = controladorEmpresas.addAuxiliar(rutTrip, nom, dir, rutEmpresa);
        }

        System.out.print("Rut[1] o Pasaporte[2] : ");
        int tipoId = sc.nextInt();
        sc.nextLine();

        IdPersona idTrip;

        if (tipoId == 1) {
            System.out.print("R.U.T [11.111.111-1]: ");
            String rutTexto = sc.nextLine();

            idTrip = Rut.of(rutTexto);
        } else {
            System.out.print("Pasaporte: ");
            String pasaporteTexto = sc.nextLine();

            idTrip = new Pasaporte(pasaporteTexto);
        }

        System.out.print("Sro.[1] o Sra.[2] : ");
        int opcionTrat = sc.nextInt();
        sc.nextLine();

        Tratamiento tratamiento;

        if (opcionTrat == 1) {
            tratamiento = Tratamiento.SR;
        } else {
            tratamiento = Tratamiento.SRA;
        }

        System.out.print("Nombres : ");
        String nombres = sc.nextLine();

        System.out.print("Apellido Paterno: ");
        String apPat = sc.nextLine();

        System.out.print("Apellido Materno: ");
        String apMat = sc.nextLine();

        Nombre nom = new Nombre(tratamiento, nombres, apPat, apMat);

        System.out.print("Calle : ");
        String calle = sc.nextLine();

        System.out.print("Numero : ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Comuna : ");
        String comuna = sc.nextLine();

        Direccion dir = new Direccion(calle, numero, comuna);

        if (creado) {
            System.out.println("...::: Auxiliar contratado exitosamente :::...");
        } else {
            System.out.println("...::: No fue posible contratar :::...");
        }
    }
    private void createTerminal() {

        System.out.println("");
        System.out.println("...:::: Creando un nuevo Terminal ::::....");

        System.out.print("Nombre : ");
        String nombre = sc.nextLine();

        System.out.print("Calle : ");
        String calle = sc.nextLine();

        System.out.print("Numero : ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Comuna : ");
        String comuna = sc.nextLine();

        Direccion dir = new Direccion(calle, numero, comuna);
        controladorEmpresas.createTerminal(nombre, dir);

        System.out.println("...:::: Terminal guardado exitosamente ::::....");
    }
    private void createCliente() {

        IdPersona idPersona;

        System.out.println("");
        System.out.println("...:::: Creando un nuevo Cliente ::::....");

        System.out.print("Rut [1] o Pasaporte [2]: ");
        int tipoId = sc.nextInt();
        sc.nextLine();

        if (tipoId == 1) {
            System.out.print("R.U.T [11.111.111-1] : ");
            String rutTexto = sc.nextLine();
            rutTexto = rutTexto.replace(".", "");
            idPersona = Rut.of(rutTexto);
        } else {
            System.out.print("Pasaporte [Solo texto]: ");
            String pasaporteTexto = sc.nextLine();
            idPersona = new Pasaporte(pasaporteTexto, "Chilena");
        }

        System.out.print("Tratamiento: Sr [1] o Sra [2]: ");
        int num = sc.nextInt();
        sc.nextLine();

        Tratamiento tratamiento;
        if (num == 1) {
            tratamiento = Tratamiento.SR;
        } else {
            tratamiento = Tratamiento.SRA;
        }

        System.out.print("Nombres : ");
        String nombres = sc.nextLine();

        System.out.print("Apellido Paterno : ");
        String apPaterno = sc.nextLine();

        System.out.print("Apellido Materno : ");
        String apMaterno = sc.nextLine();

        Nombre nombre = new Nombre(tratamiento, nombres, apPaterno, apMaterno);

        System.out.print("Telefono movil : ");
        String fono = sc.nextLine();

        System.out.print("Email : ");
        String email = sc.nextLine();

        boolean creado = sistema.createCliente(idPersona, nombre, fono, email);
        if (creado) {
            System.out.println("...:::: Creado exitosamente ::::....");
        } else {
            System.out.println("...:::: No se ha podido crear el cliente ::::....");
        }
    }
    private void createBus() {

        System.out.println(" ");
        System.out.println("...::: Creando un nuevo Bus ::::....");

        System.out.print("Patente : ");
        String patente = sc.nextLine();

        System.out.print("Marca : ");
        String marca = sc.nextLine();

        System.out.print("Modelo : ");
        String modelo = sc.nextLine();

        System.out.print("Numero asientos : ");
        int nro = Integer.parseInt(sc.nextLine());

        System.out.print(":::: Dato de la empresa");

        System.out.print("R.U.T [11.111.111-1]: ");
        Rut rutEmpresa = Rut.of(sc.nextLine());

        boolean creado = controladorEmpresas.createBus(patente, marca, modelo, nro, rutEmpresa);

        if (creado) {
            System.out.println("...:::: Bus guardado exitosamente ::::....");
        } else {
            System.out.println("...:::: No se pudo crear Bus ::::....");
        }
    }
    private void createViaje() {

        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("");
        System.out.println("...:::: Creando un nuevo Viaje ::::....");

        System.out.print("Fecha[dd/MM/yyyy] : ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formatterFecha);

        System.out.print("Hora [HH:mm] : ");
        LocalTime hora = LocalTime.parse(sc.nextLine(), formatterHora);

        System.out.print("Precio : ");
        int precio = Integer.parseInt(sc.nextLine());

        System.out.print("Duracion : ");
        int duracion = Integer.parseInt(sc.nextLine());

        System.out.print("Patente bus : ");
        String patente = sc.nextLine();

        boolean creado = sistema.createViaje(fecha, hora, precio, duracion, patente);

        if (creado) {
            System.out.println("...::::Viaje guardado exitosamente::::....");
        } else {
            System.out.println("...::::No se pudo crear Viaje::::....");
        }
    }
    private void vendePasajes() {

        SDateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("...:::: Venta de Pasajes ::::....");

        System.out.println("");
        System.out.println(":::: Datos de la venta");

        System.out.print("-> ID documento: ");
        String idDoc = sc.nextLine();

        System.out.print("-> Tipo documento: [1] Boleta [2] Factura: ");
        int tipoDoc = sc.nextInt();
        sc.nextLine();

        TipoDocumento tipo;
        if (tipoDoc == 1) {
            tipo = TipoDocumento.BOLETA;
        } else {
            tipo = TipoDocumento.FACTURA;
        }

        System.out.print("-> Fecha de venta [dd/MM/yyyy]: ");
        String fechaVentaTexto = sc.nextLine();
        LocalDate fechaVenta = LocalDate.parse(fechaVentaTexto, formatterFecha);

        System.out.println("");
        System.out.println(":::: Datos del cliente");

        System.out.print("-->  Rut [1] o Pasaporte [2]: ");
        int tipoId = sc.nextInt();
        sc.nextLine();

        IdPersona idPersona;
        if (tipoId == 1) {
            System.out.print("R.U.T [11.111.111-1]: ");
            String rutTexto = sc.nextLine();
            rutTexto = rutTexto.replace(".", "");
            idPersona = Rut.of(rutTexto);
        } else {
            System.out.print("Pasaporte [Solo texto]: ");
            String pasaporteTexto = sc.nextLine();
            idPersona = new Pasaporte(pasaporteTexto, "Chilena");
        }

        boolean creado = sistema.iniciaVenta(idDoc, tipo, fechaVenta, idPersona);
        if (creado) {
            System.out.println("...:::: Creado exitosamente ::::....");
        } else {
            System.out.println("*** No se ha podido realizar la venta ***");
        }

        System.out.println("");
        System.out.println(":::: Pasajes a vender");

        System.out.print("--> Cantidad de pasajes: ");
        int cantPasajes = sc.nextInt();
        sc.nextLine();

        System.out.print("--> Fecha de viaje [dd/MM/yyyy]: ");
        String fechaViajeTexto = sc.nextLine();

        LocalDate fechaViaje = LocalDate.parse(fechaViajeTexto, formatterFecha);
        System.out.println("");

        System.out.println(":::: Listado de horarios disponibles");

        String[][] horario = sistema.getHorariosDisponibles(fechaViaje);
        if (horario.length == 0) {
            System.out.println("*** No hay viajes disponibles para esa fecha ***");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-10s | %-10s |\n",
                "BUS", "SALIDA", "VALOR", "ASIENTOS");
        System.out.println("---------------------------------------------------------------------------------");
        for (int i = 0; i < horario.length; i++) {
            System.out.printf("| %-10s | %-10s | %-10s | %-10s |\n",
                    horario[i][0],
                    horario[i][1],
                    horario[i][2],
                    horario[i][3]);
            if (i < horario.length - 1) {
                System.out.println("---------------------------------------------------------------------------------");
            }
        }
        System.out.println("---------------------------------------------------------------------------------");

        System.out.print("Seleccione viaje en [" + 1 + "..." + horario.length + "]: ");
        int viaje = sc.nextInt();
        sc.nextLine();

        String patBus = horario[viaje - 1][0];
        LocalTime hora = LocalTime.parse(horario[viaje - 1][1], formatterHora);
        String[][] asientosB = sistema.listAsientosDeViaje(fechaViaje, hora, patBus);

        System.out.println(" ");
        System.out.println(":::: Asientos disponibles para el viaje seleccionado");

        System.out.println("------------------------");
        for (int i = 0; i < asientosB.length; i++) {
            int mitad = asientosB[i].length / 2;
            for (int j = 0; j < asientosB[i].length; j++) {
                if (j == mitad) {
                    System.out.print("   ");
                }
                System.out.printf("[%2s]", asientosB[i][j]);
            }
            System.out.println(" ");
        }

        System.out.print("Seleccione sus asientos [separado por ,]: ");
        String ingreso = sc.nextLine();
        String[] asientosArreglo = ingreso.split(",");

        if (asientosArreglo.length != cantPasajes) {
            System.out.println("*** La cantidad de asientos no coincide con la cantidad de pasajes ***");
            return;
        }

        int[] asientos = new int[asientosArreglo.length];
        for (int i = 0; i < asientosArreglo.length; i++) {
            asientos[i] = Integer.parseInt(asientosArreglo[i].trim());
        }

        IdPersona[] ids = new IdPersona[asientos.length];
        int[] npasaje = new int[asientos.length];

        for (int i = 0; i < asientos.length; i++) {
            System.out.println(":::: Datos pasajero " + (i + 1));
            int asiento = asientos[i];
            System.out.println("--> Asiento: " + asiento);
            System.out.print(" ");
            System.out.print("-->  Rut[1] o Pasaporte[2]: ");
            tipoId = sc.nextInt();
            sc.nextLine();

            if (tipoId == 1) {
                System.out.print("R.U.T [11.111.111-1] : ");
                String rutTexto = sc.nextLine();
                rutTexto = rutTexto.replace(".", "");
                idPersona = Rut.of(rutTexto);
            } else {
                System.out.print("Pasaporte : ");
                String pasaporteTexto = sc.nextLine();
                idPersona = new Pasaporte(pasaporteTexto, "Chilena");
            }

            ids[i] = idPersona;

            System.out.print("Tratamiento: Sr [1] o Sra [2]: ");
            int num = sc.nextInt();
            sc.nextLine();

            Tratamiento tratamiento;
            if (num == 1) {
                tratamiento = Tratamiento.SR;
            } else {
                tratamiento = Tratamiento.SRA;
            }

            System.out.print("Nombres: ");
            String nombres = sc.nextLine();

            System.out.print("Apellido Paterno: ");
            String apPaterno = sc.nextLine();

            System.out.print("Apellido Materno: ");
            String apMaterno = sc.nextLine();

            Nombre nombre = new Nombre(tratamiento, nombres, apPaterno, apMaterno);

            System.out.print("Telefono movil: ");
            String fono = sc.nextLine();

            boolean creadoPasajero = sistema.createPasajero(idPersona, nombre, fono, nombre, fono);

            if (creadoPasajero) {
                System.out.println("...::::Pasajero creado exitosamente::::....");
            } else {
                System.out.println("...::::Pasajero ya existe::::....");
            }

            creado = sistema.vendePasaje(idDoc, fechaViaje, hora, patBus, asiento, idPersona);
            if (creado) {
                System.out.println("...::::Creado exitosamente::::....");
            } else {
                System.out.println("*** No se ha podido crear el pasaje ***");
                return;
            }
            npasaje[i] = sistema.listVentas().length + 1;
        }

        for (int i = 0; i < asientos.length; i++) {
            System.out.println(". . . : : : PASAJE : : : . . .");
            System.out.println("Numero del pasaje : " + npasaje[i]);
            System.out.println("Fecha de viaje : " + fechaViaje);
            System.out.println("Hora de viaje : " + horario[viaje - 1][1]);
            System.out.println("Patente bus : " + patBus);
            System.out.println("Asiento : " + asientos[i]);
            System.out.println("R.U.T/PASAPORTE : " + ids[i]);
            System.out.println("Nombre Pasajero : " + sistema.getNombrePasajero(ids[i]));
        }
    }
    private void pagaVentaPasajes() {

        System.out.println(" ");
        System.out.println("...:::: Pagar venta Pasajes ::::....");

        System.out.print("::::ID documento venta ");
        String idDocumento = sc.nextLine();

        System.out.println("...:::: Tipo pago ::::....");
        System.out.println("1) Efectivo");
        System.out.println("2) Transferencia");
        System.out.println("3) Tarjeta");
        int tipoPago = sc.nextInt();
        sc.nextLine();

        boolean pagado = false;

        switch (tipoPago) {
            case 1:
                pagado = sistema.pagarVentaEfectivo(idDocumento);
                break;
            case 2:
                System.out.print("Banco : ");
                String banco = sc.nextLine();
                System.out.print("Numero transferencia : ");
                String nroTransferencia = sc.nextLine();
                pagado = sistema.pagarVentaTransferencia(idDocumento, banco, nroTransferencia);
                break;
            case 3:
                System.out.print("Numero tarjeta : ");
                String nroTarjeta = sc.nextLine();
                System.out.print("Titular: ");
                String titular = sc.nextLine();
                pagado = sistema.pagarVentaTarjeta(idDocumento, nroTarjeta, titular);
                break;
            default:
                System.out.println("*** Tipo invalido ***");
                return;
        }

        if (pagado) {
            System.out.println("...:::: Pago realizado ::::....");
        } else {
            System.out.println("*** No se pudo pagar ***");
        }
    }
    private void listVentas() {
        String[][] ventas = sistema.listVentas();

        if (ventas.length == 0) {
            System.out.println("*** No existen ventas ***");
            return;
        }

        System.out.println("...:::: LISTADO DE VENTAS ::::....");
        System.out.printf("| %-10s | %-13s | %-10s | %-16s | %-28s | %-13s | %-13s |\n",
                "ID DOC", "TIPO DOC", "FECHA", "RUT/PASS", "CLIENTE", "CANT", "TOTAL");

        for (int i = 0; i < ventas.length; i++) {
            System.out.printf("| %-10s | %-13s | %-10s | %-16s | %-28s | %-13s | %-13s |\n",
                    ventas[i][0],
                    ventas[i][1],
                    ventas[i][2],
                    ventas[i][3],
                    ventas[i][4],
                    ventas[i][5],
                    ventas[i][6]);
        }
    }
    private void listViajes() {
        String[][] viajes = sistema.listViajes();

        if (viajes.length == 0) {
            System.out.println("*** No existen viajes ***");
            return;
        }

        System.out.println(" ");
        System.out.println("...:::: Listado de Viajes ::::....");

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        System.out.printf("| %-12s | %-12s | %-12s | %-10s | %-15s | %-10s | %-15s | %-15s |\n",
                "FECHA",
                "HORA SALE",
                "HORA LLEGA",
                "PRECIO",
                "ASIENTO DISP",
                "PATENTE",
                "ORIGEN",
                "DESTINO"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < viajes.length; i++) {
            System.out.printf("| %-12s | %-12s | %-12s | %-10s | %-15s | %-10s | %-15s | %-15s |\n",
                    viajes[i][0],
                    viajes[i][1],
                    viajes[i][2],
                    viajes[i][3],
                    viajes[i][4],
                    viajes[i][5],
                    viajes[i][6],
                    viajes[i][7]
            );
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
    private void listPasajerosViaje() {
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("hh:mm");

        System.out.println(" ");
        System.out.println("...:::: Listado de Pasajeros Viaje ::::....");

        System.out.print("Fecha viaje [dd/MM/yyyy]: ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formatterFecha);

        System.out.print("Hora viaje[hh:mm] : ");
        LocalTime hora = LocalTime.parse(sc.nextLine(), formatterHora);

        System.out.print("Patente bus : ");
        String patente = sc.nextLine();

        String[][] pasajeros = sistema.listPasajeros(fecha, hora, patente);

        if (pasajeros.length == 0) {
            System.out.println("*** No existen pasajeros ***");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.printf("| %-10s | %-18s | %-30s | %-20s |\n",
                "ASIENTO",
                "RUT/PASAPORTE",
                "PASAJERO",
                "TELEFONO"
        );

        System.out.println("----------------------------------------------------------------------------------------------");

        for (int i = 0; i < pasajeros.length; i++) {

            System.out.printf(
                    "| %-10s | %-18s | %-30s | %-20s |\n",
                    pasajeros[i][0],
                    pasajeros[i][1],
                    pasajeros[i][2],
                    pasajeros[i][3]
            );
        }

        System.out.println("----------------------------------------------------------------------------------------------");
    }
    private void listEmpresas() {

        System.out.println(" ");
        System.out.println("...:::: Listado de Empresas ::::....");

        String[][] empresas =
                controladorEmpresas.listEmpresas();

        if (empresas.length == 0) {

            System.out.println("*** No existen empresas ***");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        System.out.printf("| %-15s | %-20s | %-25s | %-18s | %-15s | %-15s |\n",
                "RUT EMPRESA",
                "NOMBRE",
                "URL",
                "NRO TRIPULANTES",
                "NRO BUSES",
                "NRO VENTAS"
        );

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < empresas.length; i++) {

            System.out.printf("| %-15s | %-20s | %-25s | %-18s | %-15s | %-15s |\n",
                    empresas[i][0],
                    empresas[i][1],
                    empresas[i][2],
                    empresas[i][3],
                    empresas[i][4],
                    empresas[i][5]
            );

            System.out.println("----------------------------------------------------------------------------------------------------------------");
        }
    }
    private void listLlegadasSalidasTerminal() {
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println(" ");
        System.out.println("...:::: Llegada y Salida del terminal ::::....");

        System.out.print("Nombre terminal: ");
        String terminal = sc.nextLine();

        System.out.print("Fecha[dd/MM/yyyy] : ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formatterFecha);

        String[][] datos = sistema.listLlegadasSalidasTerminal(terminal, fecha);

        if (datos.length == 0) {
            System.out.println("*** Error. No existe un terminal con el nombre dado ***");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------");

        System.out.printf("| %-18s | %-10s | %-15s | %-20s | %-15s |\n",
                "LLEGADA/SALIDA",
                "HORA",
                "PATENTE BUS",
                "NOMBRE EMPRESA",
                "NRO PASAJEROS"
        );

        System.out.println("-----------------------------------------------------------------------------------------------");

        for (int i = 0; i < datos.length; i++) {
            System.out.printf("| %-18s | %-10s | %-15s | %-20s | %-15s |\n",
                    datos[i][0],
                    datos[i][1],
                    datos[i][2],
                    datos[i][3],
                    datos[i][4]
            );
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
    }
    private void listVentasEmpresa() {

        System.out.println(" ");
        System.out.println("...:::: Listado de ventas de una empresa ::::....");

        System.out.print("R.U.T [11.111.111-1] : ");
        String rutEmpresa = sc.nextLine();

        String[][] ventas = sistema.listVentaEmpresa(rutEmpresa);

        if (ventas.length == 0) {
            System.out.println("*** No existen ventas de Empresa ***");
            return;
        }

        System.out.println("---------------------------------------------------------------------");

        System.out.printf("| %-12s | %-10s | %-15s | %-15s |\n",
                "FECHA",
                "TIPO",
                "MONTO PAGADO",
                "TIPO PAGO"
        );
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < ventas.length; i++) {

            System.out.printf("| %-12s | %-10s | %-15s | %-15s |\n",
                    ventas[i][0],
                    ventas[i][1],
                    ventas[i][2],
                    ventas[i][3]
            );
        }

        System.out.println("---------------------------------------------------------------------");
    }
}