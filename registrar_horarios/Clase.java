package registrar_horarios;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.*;

public class Clase
{
    private String nombreMateria;
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String edificio;
    private String salon;
    private String catedratico;

    public Clase(String nombreMateria, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String edificio, String salon, String catedratico)
    {
        this.nombreMateria = nombreMateria;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.edificio = edificio;
        this.salon = salon;
        this.catedratico = catedratico;
    }
}
