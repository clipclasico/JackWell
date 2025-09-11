package registrar_horarios;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Scanner;

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
}
