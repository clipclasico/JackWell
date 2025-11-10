package registrar_horarios;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Clase implements Serializable
{
    private static final long serialVersionUID = 1L;
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

    public String obtenerInformacion()
    {
        return String.format("%s - %s de %s a %s en %s %s (Catedrático: %s)",
        nombreMateria, dia.name(), horaInicio.toString(), horaFin.toString(), edificio, salon, catedratico);
    }

    public void modificarHorario(DayOfWeek nuevoDia, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin, String nuevoEdificio, String nuevoSalon)
    {
        this.dia = nuevoDia;
        this.horaInicio = nuevaHoraInicio;
        this.horaFin = nuevaHoraFin;
        this.edificio = nuevoEdificio;
        this.salon = nuevoSalon;
    }

    public boolean tieneConflicto(Clase otraClase)
    {
        if (!this.dia.equals(otraClase.dia))
        {
        return false;
        }

         return !(this.horaFin.isBefore(otraClase.horaInicio) || this.horaInicio.isAfter(otraClase.horaFin));
    }

    public boolean estaActivaEn(DayOfWeek dia, LocalTime hora)
    {
        return this.dia.equals(dia) && !hora.isBefore(horaInicio) && !hora.isAfter(horaFin);
    }

    public String getNombreMateria()
    {
        return nombreMateria;
    }

    public DayOfWeek getDia()
    {
        return dia;
    }

    public LocalTime getHoraInicio()
    {
        return horaInicio;
    }

    public LocalTime getHoraFin()
    {
        return horaFin;
    }

    public String getEdificio()
    {
        return edificio;
    }

    public String getSalon()
    {
        return salon;
    }

    public String getCatedratico()
    {
        return catedratico;
    }

    public void setNombreMateria(String nombreMateria)
    {
        this.nombreMateria = nombreMateria;
    }

    public void setCatedratico(String catedratico)
    {
        this.catedratico = catedratico;
    }
}
