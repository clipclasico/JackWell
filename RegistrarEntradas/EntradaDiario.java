package RegistrarEntradas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaDiario {
    private static int contadorId = 0;
    private int id;
    private LocalDateTime fecha;
    private String estadoAnimo;
    private String pensamientos;

    public EntradaDiario(String estadoAnimo, String pensamientos) {
        this.id = ++contadorId;
        this.fecha = LocalDateTime.now();
        this.estadoAnimo = estadoAnimo;
        this.pensamientos = pensamientos;
    }

    public int getId() {
        return this.id;
    }

    public String getEstadoAnimo() {
        return this.estadoAnimo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String cabecera = String.format("--- Entrada #%d | %s | Estado de Ánimo: %s ---",
                                        this.id, this.fecha.format(formato), this.estadoAnimo);
        
        return cabecera + "\n" + this.pensamientos + "\n";
    }
}