package registrar_horarios;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.*;
import java.time.LocalTime;

public class Horario
{
    private ArrayList<Clase> clases;

    public Horario()
    {
        this.clases = new ArrayList<>();
    }

    public boolean agregarClase(Clase nuevaClase)
    {
        for (Clase claseExistente : clases)
        {
            if (claseExistente.tieneConflicto(nuevaClase))
            {
                System.out.println("Conflicto detectado con: " + claseExistente.obtenerInformacion())
                return false;
            }
        }

        clases.add(nuevaClase);
        return true;
    }

    public boolean eliminarClase(String nombreMateria, DayOfWeek dia)
    {
        return clases.removeIf(clase -> clase.getNombreMateria().equalsIgnoreCase(nombreMateria) && clase.getDia().equals(dia));
    }

    public void eliminarTodasLasClases()
    {
        clases.clear();
        System.out.println("Todas las clases han sido eliminadas del horario.");
    }

    public ArrayList<Clase> obtenerClasesPorDia(DayOfWeek dia)
    {
        ArrayList<Clase> clasesDia = new ArrayList<>();
        for (Clase clase : clases)
        {
            if (clase.getDia().equals(dia))
            {
                clasesDia.add(clase);
            }
        }

        clasesDia.sort((c1, c2) -> c1.getHoraInicio().compareTo(c2.getHoraInicio()));
        return clasesDia;
    }

    public void mostrarHorarioCompleto()
    {
        if (clases.isEmpty())
        {
            System.out.println("El horario está vacío. Agregue clases para comenzar.");
            return;
        }

        System.out.println("Horario Completo:");
        for (DayOfWeek dia : DayOfWeek.values())
        {
            ArrayList<Clase> clasesDia = obtenerClasesPorDia(dia);
            if (!clasesDia.isEmpty())
            {
                System.out.println("\n" dia.name() + ":");
                for (Clase clase : clasesDia)
                {
                    System.out.println("  " + clase.obtenerInformacion());
                }
            }
        }
    }

    public Clase obtenerClaseActual(DayOfWeek diaActual, LocalTime horaActual)
    {
        for (Clase clase : clases)
        {
            if (clase.estaActivaEn(diaActual, horaActual))
            {
                return clase;
            }
        }
        return null;
    }

    public Clase obtenerProximaClase(DayOfWeek diaActual, LocalTime horaActual)
    {
        ArrayList<Clase> clasesHoy = obtenerClasesPorDia(diaActual);

        for (Clase clase : clasesHoy)
        {
            if (clase.getHoraInicio().isAfter(horaActual))
            {
                return clase;
            }
        }
        return null;
    }

}
