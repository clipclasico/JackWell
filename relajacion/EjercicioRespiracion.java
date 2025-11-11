/// ----------------------------------------------
/// EjercicioRespiracion.java
/// Clase para representar un ejercicio de respiración.
/// ----------------------------------------------

package relajacion;

public class EjercicioRespiracion
{
    private String nombre;
    private int duracionMinutos;
    private String instrucciones;

    public EjercicioRespiracion(String nombre, int duracionMinutos, String instrucciones)
    {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.instrucciones = instrucciones;
    }

    public String obtenerDescripcion()
    {
        return "Ejercicio: " + nombre +
               "\nDuración: " + duracionMinutos + " minutos" +
               "\nInstrucciones: " + instrucciones;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getDuracionMinutos()
    {
        return duracionMinutos;
    }

    public String getInstrucciones()
    {
        return instrucciones;
    }
}