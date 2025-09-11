package registrar_horarios;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioBuilder
{
    private Horario horario;

    public HorarioBuilder()
    {
        this.horario = new Horario();
    }    

    public HorarioBuilder agregarClase(String materia, DayOfWeek dia, String horaInicio, String horaFin, String edificio, String salon, String catedratico)
    {
        try 
        {
            LocalTime inicio = LocalTime.parse(horaInicio);
            LocalTime fin = LocalTime.parse(horaFin);

            Clase clase = new Clase(materia, dia, inicio, fin, edificio, salon, catedratico);
            horario.agregarClase(clase);
        } catch (Exception e) 
        {
            System.out.println("Error al agregar clase: " + e.getMessage());
        }

        return this;
    }

    public Horario build()
    {
        return horario;
    }
}
