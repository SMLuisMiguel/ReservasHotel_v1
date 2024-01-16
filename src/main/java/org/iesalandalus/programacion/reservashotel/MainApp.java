package org.iesalandalus.programacion.reservashotel;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp
{
    public static final int CAPACIDAD = 6;

    private static Habitaciones habitaciones;

    private static Reservas reservas;

    private static Huespedes huespedes;

    public static void main(String[] args)
    {

        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
        huespedes = new Huespedes(CAPACIDAD);

        System.out.println("--------------------------------------------");
        System.out.println("Bienvenido al hotel IES Al-Ándalus ");

        Opcion opcion = null;

        while (opcion != Opcion.SALIR) {
            System.out.println("--------------------------------------------");
            Consola.mostrarMenu();
            System.out.println("--------------------------------------------");
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }

        System.out.println("Adios.");
    }

    private static void ejecutarOpcion(Opcion opcion)
    {
        switch (opcion)
        {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;

            case BUSCAR_HUESPED:
                buscarHuesped();
                break;

            case BORRAR_HUESPED:
                borrarHuesped();
                break;

            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;

            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;

            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;

            case BORRAR_HABITACION:
                borrarHabitacion();
                break;

            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;

            case INSERTAR_RESERVA:
                insertarReserva();
                break;

            case ANULAR_RESERVA:
                anularReserva();
                break;

            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;

            case CONSULTAR_DISPONIBILIDAD:
                TipoHabitacion tipo = Consola.leerTipoHabitacion();
                LocalDate fechaInicioReserva = Consola.leerFecha("Introduce la fecha de inicio de reserva");
                LocalDate fechaFinReserva = Consola.leerFecha("Introduce la fecha de fin de reserva");
                Habitacion habitacion = consultarDisponibilidad(tipo, fechaInicioReserva, fechaFinReserva);
                if (habitacion != null)
                {
                    System.out.println("Hay disponibilidad");
                }
                else
                {
                    System.out.println("No hay disponibilidad");
                }
                break;


        }
    }

    private static void insertarHuesped() {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.leerHuesped();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            huespedes.insertar(huesped);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }


    private static void buscarHuesped()
    {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Huesped huespedEncontrado = huespedes.buscar(huesped);
            if (huespedEncontrado != null)
            {
                System.out.println("Mostrando datos del huesped encontrado");
                System.out.println(huespedEncontrado);
            }
            else
            {
                System.out.println("Huesped no encontrado");
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void borrarHuesped()
    {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            huespedes.borrar(huesped);
            System.out.println("Se ha borrado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void mostrarHuespedes()
    {
        if (huespedes != null && huespedes.getTamano() > 0)
        {
            System.out.println("Mostrando listado de huespedes");
            for (int i = 0; i < huespedes.getTamano(); i++)
            {
                System.out.println(huespedes.get()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay huespedes registrados");
        }
    }

    private static void insertarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            habitaciones.insertar(habitacion);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void buscarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacionPorIdentificador();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Habitacion habitacionEncontrado = habitaciones.buscar(habitacion);
            if (habitacionEncontrado != null)
            {
                System.out.println("Mostrando datos de la habitación encontrada");
                System.out.println(habitacionEncontrado);
            }
            else
            {
                System.out.println("Habitación no encontrada");
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void borrarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacionPorIdentificador();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            habitaciones.borrar(habitacion);
            System.out.println("Se ha borrado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void mostrarHabitaciones()
    {
        if (habitaciones != null && habitaciones.getTamano() > 0)
        {
            System.out.println("Mostrando listado de habitaciones");
            for (int i=0; i<habitaciones.getTamano(); i++)
            {
                System.out.println(habitaciones.get()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay habitaciones registradas");
        }
    }

    private static void insertarReserva()
    {
        Reserva reserva = null;
        while (reserva == null) {
            try {
                reserva = Consola.leerReserva();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Habitacion habitacionLibre = consultarDisponibilidad(
                    reserva.getHabitacion().getTipoHabitacion(),
                    reserva.getFechaInicioReserva(),
                    reserva.getFechaInicioReserva()
            );

            if (habitacionLibre != null) {
                reservas.insertar(reserva);
                System.out.println("Reserva confirmada");
            }
            else
            {
                System.out.println("No se ha podido hacer la reserva. No hay disponibilidad.");
            }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void listarReserva(TipoHabitacion tipoHabitacion)
    {
        Reserva[] listaReservas = reservas.getReservas(tipoHabitacion);
        if (listaReservas != null && listaReservas.length > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.length; i++)
            {
                System.out.println(listaReservas[i]);
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private static void listarReserva(Huesped huesped)
    {
        Reserva[] listaReservas = reservas.getReservas(huesped);
        if (listaReservas != null && listaReservas.length > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.length; i++)
            {
                System.out.println(listaReservas[i]);
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private static void listarReserva(LocalDate fecha)
    {
        if (reservas != null && reservas.getTamano() > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<reservas.getTamano(); i++)
            {
                boolean fechaEntreReserva = fecha.isAfter(reservas.get()[i].getFechaInicioReserva()) &&
                        fecha.isBefore(reservas.get()[i].getFechaFinReserva());
                if (fechaEntreReserva ||
                        fecha.isEqual(reservas.get()[i].getFechaInicioReserva())  ||
                        fecha.isEqual(reservas.get()[i].getFechaFinReserva())
                )
                {
                    System.out.println(reservas.get()[i]);
                }
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private static Reserva[] getReservasAnulables(Reserva[] reservasAAnular)
    {
        Reserva[] reservasAnulables = new Reserva[reservasAAnular.length];
        int contador = 0;

        for (int i = 0; i < reservasAAnular.length; i++)
        {
            if (reservasAAnular[i] != null && reservasAAnular[i].getFechaInicioReserva().isAfter(LocalDate.now()))
            {
                reservasAnulables[contador] = new Reserva(reservasAAnular[i]);
                contador++;
            }
        }

        Reserva[] reservasAnulablesLimpio = new Reserva[contador];
        for (int i = 0; i < reservasAnulablesLimpio.length; i++)
        {
            reservasAnulablesLimpio[i] = new Reserva(reservasAnulables[i]);
        }

        return reservasAnulablesLimpio;
    }

    private static void anularReserva()
    {
        Huesped huesped = null;
        Reserva[] reservasAnulables = null;
        while (huesped == null)
        {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try
        {
            reservasAnulables = getReservasAnulables(reservas.get());
            if (reservasAnulables.length == 0)
            {
                System.out.println("No existen reservas anulables");
            }
            else
            {
                if (reservasAnulables.length == 1)
                {
                    System.out.println("1: Confirmar. \n2: Cancelar.");
                    System.out.println("Introduzca una opcion");
                    int menu= Entrada.entero();

                    while (menu<1 || menu>2)
                    {
                        System.out.println("Introduzca un número correcto");
                        menu=Entrada.entero();
                    }
                    if (menu == 1){
                        reservas.borrar(reservasAnulables[0]);
                        System.out.println("Reserva borrada");
                    }
                }
                else {
                    System.out.println("Selecciona la reserva que desea anular");
                    for (int i = 0; i < reservasAnulables.length; i++)
                    {
                        System.out.println((i + 1) + " - " + reservasAnulables[i]);
                        System.out.println("Introduzca una opcion");
                        int menu = Entrada.entero();

                        while (menu < 1 || menu > reservasAnulables.length)
                        {
                            System.out.println("Introduzca un número correcto");
                            menu = Entrada.entero();
                        }

                        reservas.borrar(reservasAnulables[menu - 1]);
                        System.out.println("Reserva borrada");
                    }
                }
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private static void mostrarReservas()
    {
        if (reservas != null && reservas.getTamano() > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<reservas.getTamano(); i++)
            {
                System.out.println(reservas.get()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
    }

    private static int getNumElementosNoNulos(Reserva[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private static int getNumElementosNoNulos(Habitacion[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private static int getNumElementosNoNulos(Huesped[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipohabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva)
    {
        Habitacion habitacion = null;

        for (int i = 0; i < habitaciones.getTamano(); i++)
        {
            if (habitaciones.get()[i].getTipoHabitacion().equals(tipohabitacion))
            {
                if (getNumElementosNoNulos(reservas.get()) == 0)
                {
                    return habitaciones.get()[i];
                }
                for (int j = 0; j < reservas.getTamano(); j++)
                {
                    if (reservas.get()[j].getHabitacion().equals(habitaciones.get()[i]))
                    {
                        // fechas nuevas entre la reserva
                        boolean isFechaInicioNuevaEntreLaReserva =
                                fechaInicioReserva.isAfter(reservas.get()[j].getFechaInicioReserva()) &&
                                        fechaInicioReserva.isBefore(reservas.get()[j].getFechaFinReserva());

                        boolean isFechaFinNuevaEntreLaReserva =
                                fechaFinReserva.isAfter(reservas.get()[j].getFechaInicioReserva()) &&
                                        fechaFinReserva.isBefore(reservas.get()[j].getFechaFinReserva());

                        // fechas antiguas entre las nuevas
                        boolean isFechaInicioAntiguaEntreLaReservaNueva =
                                reservas.get()[j].getFechaInicioReserva().isAfter(fechaInicioReserva) &&
                                        reservas.get()[j].getFechaInicioReserva().isBefore(fechaFinReserva);

                        boolean isFechaFinAntiguaEntreLaReservaNueva =
                                reservas.get()[j].getFechaFinReserva().isAfter(fechaInicioReserva) &&
                                        reservas.get()[j].getFechaFinReserva().isBefore(fechaFinReserva);



                        if (!isFechaInicioNuevaEntreLaReserva && !isFechaFinNuevaEntreLaReserva &&
                                !isFechaInicioAntiguaEntreLaReservaNueva && !isFechaFinAntiguaEntreLaReservaNueva )
                        {
                            habitacion = habitaciones.get()[i];
                            return habitacion;
                        }
                    }
                }
            }
        }


        return habitacion;
    }



}






