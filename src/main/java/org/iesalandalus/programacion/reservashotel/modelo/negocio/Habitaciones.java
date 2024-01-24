package org.iesalandalus.programacion.reservashotel.modelo.negocio;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    //constructor
    public Habitaciones (int capacidad)
    {
        if (capacidad<=0)
        {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        else
        {
            this.capacidad=capacidad;
        }
        this.coleccionHabitaciones=new Habitacion [capacidad];
        this.tamano=0;
    }

    public Habitacion[] get()
    {
        return copiaProfundaHabitaciones();
    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion)
    {
        Habitacion[] copiaHabitaciones= new Habitacion[coleccionHabitaciones.length];
        int contador = 0;

        for (int i = 0; i < coleccionHabitaciones.length; i++)
        {
            if (coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion))
            {
                Habitacion original =coleccionHabitaciones[i];
                Habitacion copia= new Habitacion(original);
                copiaHabitaciones[contador] = copia;
                contador++;
            }
        }

        return copiaHabitaciones;
    }



    public int getTamano()
    {
        return tamano;
    }

    public int getCapacidad()
    {
       return capacidad;
    }

    private Habitacion[] copiaProfundaHabitaciones()
    {
        Habitacion  [] copiaHabitaciones= new Habitacion[coleccionHabitaciones.length];

        for (int i = 0; i < coleccionHabitaciones.length; i++)
        {
            if (coleccionHabitaciones[i] != null)
            {
                Habitacion original =coleccionHabitaciones[i];
                Habitacion copia= new Habitacion(original);
                copiaHabitaciones[i] = copia;
            }
        }

        return copiaHabitaciones;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException
    {
        if (habitacion == null)
        {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        else
        {
           boolean insertado= false;
           for (int i=0; i < coleccionHabitaciones.length; i++)
           {
               Habitacion original= coleccionHabitaciones[i];
               if(original == null)
               {
                   coleccionHabitaciones[i]= habitacion;
                   tamano++;
                   insertado= true;
                   break;
               }
               else
               {
                   if(original.equals(habitacion))
                   {
                       throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
                   }

               }
           }
           if (!insertado)
           {
               throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");
           }
        }
    }

    private int buscarIndice(Habitacion habitacion)
    {
        for (int i = 0; i < coleccionHabitaciones.length; i++)
        {
            Habitacion original = coleccionHabitaciones[i];
            if (habitacion.equals(original))
            {
                return i;
            }
        }
        return -1;
    }

    public Habitacion buscar (Habitacion habitacion)
    {
        if (habitacion != null)
        {
            for (int i = 0; i < coleccionHabitaciones.length; i++)
            {
                Habitacion original = coleccionHabitaciones[i];
                if (habitacion.equals(original))
                {
                    return new Habitacion(original);
                }
            }

            return null;
        }
        else
        {
            throw new NullPointerException("ERROR 4.");
        }
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException
    {
        if (habitacion != null) {
            int indiceABorrar = buscarIndice(habitacion);

            if (indiceABorrar == -1)
            {
                throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
            }

            tamano--;
            for (int i = indiceABorrar; i < coleccionHabitaciones.length; i++)
            {
                coleccionHabitaciones[i] = null;
                if (i + 1 < coleccionHabitaciones.length)
                {
                    desplazarUnaPosicionHaciaIzquierda(i + 1);
                }
            }
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
    }

    private Boolean tamanoSuperado(int indice)
    {
        return tamano >= capacidad;
    }

    private Boolean capacidadSuperada(int indice)
    {
        return indice + 1 > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice)
    {
        if(indice < 1)
        {
            throw new IllegalArgumentException("erro 5");
        }
        coleccionHabitaciones[indice-1] = coleccionHabitaciones[indice];
        coleccionHabitaciones[indice] = null;
    }

}
