package RegistrarEntradas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiarioEmocional implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<EntradaDiario> entradas;
    private int proximoId = 1;

    public DiarioEmocional()
    {
        this.entradas = new ArrayList<>();
        this.proximoId = 1;
    }

    public void agregarEntrada(String estadoAnimo, String pensamientos)
    {
        EntradaDiario nuevaEntrada = new EntradaDiario(this.proximoId, estadoAnimo, pensamientos);
        this.entradas.add(nuevaEntrada);
        this.proximoId++;
        System.out.println("Entrada guardada.");
    }

    public void verEntradas()
    {
        System.out.println("\n================ REGISTROS DEL DIARIO ================");
        if (entradas.isEmpty())
        {
            System.out.println("El diario está vacío. ¡Anímate a escribir algo!");
        } else {
            for (int i = this.entradas.size() - 1; i >= 0; i--)
            {
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