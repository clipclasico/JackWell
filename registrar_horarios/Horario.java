package registrar_horarios;

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
}
