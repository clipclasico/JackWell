/// ----------------------------------------------
/// DiarioEmocional.java
/// Clase para gestionar el diario emocional del usuario.
/// ----------------------------------------------

package RegistrarEntradas;

import java.util.ArrayList;
import java.util.List;

public class DiarioEmocional
{
    private List<EntradaDiario> entradas;

    public DiarioEmocional()
    {
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(String estadoAnimo, String pensamientos)
    {
        EntradaDiario nuevaEntrada = new EntradaDiario(estadoAnimo, pensamientos);
        this.entradas.add(nuevaEntrada);
        System.out.println("Se guardó tu entrada.");
    }

    public void verEntradas()
    {
        System.out.println("\n================ REGISTROS DEL DIARIO ================");
        if (entradas.isEmpty())
        {
            System.out.println("El diario está vacío. ¡Anímate a escribir algo!");
        } else {
            for (int i = this.entradas.size() - 1; i >= 0; i--) {
                System.out.println(this.entradas.get(i));
            }
        }
        System.out.println("======================================================");
    }
    
    public boolean eliminarEntrada(int id)
    {
        boolean eliminada = this.entradas.removeIf(entrada -> entrada.getId() == id);
        return eliminada;
    }
}