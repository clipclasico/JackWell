package registrar_horarios;

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
                System.out.println("Conflicto detectado con: " + claseExistente.obtenerInformacion());
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
                System.out.println("\n" + dia.name() + ":");
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
        Clase proximaClase = null;

        for (Clase clase : clasesHoy)
        {
            if (clase.getHoraInicio().isAfter(horaActual))
            {
                if (proximaClase == null || clase.getHoraInicio().isBefore(proximaClase.getHoraInicio()))
                {
                    proximaClase = clase;
                }
            }
        }
        return proximaClase;
    }

    public String generarReporte()
    {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte del Horario:\n");
        reporte.append("======================\n");
        reporte.append("Total de clases: ").append(clases.size()).append("\n");

        for (DayOfWeek dia : DayOfWeek.values())
        {
            ArrayList<Clase> clasesDia = obtenerClasesPorDia(dia);
            if (!clasesDia.isEmpty())
            {
                reporte.append(dia.name()).append(" (").append(clasesDia.size()).append(" clases:\n");
                for (Clase clase : clasesDia)
                {
                    reporte.append("  - ").append(clase.obtenerInformacion()).append("\n");
                }
                reporte.append("\n");
            }
        }
        return reporte.toString();
    }

    public ArrayList<Clase> buscarPorMateria(String materia)
    {
        ArrayList<Clase> encontradas = new ArrayList<>();
        for (Clase clase : clases)
        {
            if (clase.getNombreMateria().toLowerCase().contains(materia.toLowerCase()))
            {
                encontradas.add(clase);
            }
        }
        return encontradas;
    }

    public boolean validarHorario()
    {
        for (int i = 0; i < clases.size(); i++)
        {
            for (int j = i + 1; j < clases.size(); j++)
            {
                if (clases.get(i).tieneConflicto(clases.get(j)))
                {
                    System.out.println("Conflicto detectado entre: " + clases.get(i).obtenerInformacion() + " y " + clases.get(j).obtenerInformacion());
                    return false;
                }
            }
        }
        System.out.println("No se detectaron conflictos en el horario.");
        return true;
    }

    public ArrayList<Clase> getClases()
    {
        return new ArrayList<>(clases);
    }
}
