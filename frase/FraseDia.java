package frase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FraseDia
{
    private List<String> frases;
    private LocalDate fechaActual;
    private Random random;
    private String rutaArchivoCSV;

    public FraseDia()
    {
        this("frases.csv");
    }

    public FraseDia(String rutaArchivoCSV)
    {
        this.frases = new ArrayList<>();
        this.random = new Random();
        this.fechaActual = LocalDate.now();
        this.rutaArchivoCSV = rutaArchivoCSV;
        
        cargarFrasesDesdeCSV();
        
        if (this.frases.isEmpty())
        {
            cargarFrasesPorDefecto();
        }
    }

    private void cargarFrasesDesdeCSV()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(this.rutaArchivoCSV)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                if (!linea.trim().isEmpty()) {
                    frases.add(linea.trim());
                }
            }
            System.out.println(frases.size() + " frases cargadas desde " + this.rutaArchivoCSV);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + this.rutaArchivoCSV + " no encontrado. Usando frases por defecto.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + this.rutaArchivoCSV + ": " + e.getMessage());
        }
    }

    private void cargarFrasesPorDefecto()
    {
        frases.add("La persistencia vence lo que la diligencia no alcanza.");
        frases.add("Cada día es una nueva oportunidad para mejorar.");
        frases.add("El bienestar empieza con pequeños pasos diarios.");
        frases.add("Tu salud mental es tan importante como tu salud física.");
        frases.add("No hay atajos para lugares que vale la pena visitar.");
        frases.add("El éxito es la suma de pequeños esfuerzos repetidos día tras día.");
        frases.add("Cuida tus pensamientos, se convertirán en palabras.");
        frases.add("La mejor inversión es la que haces en ti mismo.");
        frases.add("El cambio comienza cuando decides dar el primer paso.");
        frases.add("Cada pequeño progreso cuenta en el camino del bienestar.");
        
        System.out.println("✓ " + frases.size() + " frases por defecto cargadas");
    }

    public void agregarYGuardarFrase(String nuevaFrase) {
        if (nuevaFrase == null || nuevaFrase.trim().isEmpty()) {
            System.out.println("No se puede agregar una frase vacía.");
            return;
        }

        this.frases.add(nuevaFrase);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.rutaArchivoCSV, true))) {
            bw.write(nuevaFrase);
            bw.newLine();
            System.out.println("✓ Frase agregada: " + nuevaFrase);
        } catch (IOException e) {
            System.err.println("✗ Error al guardar la frase: " + e.getMessage());
            this.frases.remove(this.frases.size() - 1);
        }
    }

    public String obtenerFraseDelDia() {
        if (frases.isEmpty()) {
            return "No hay frases disponibles. ¡Agrega algunas!";
        }
        int dia = fechaActual.getDayOfYear();
        int indice = dia % frases.size();
        return frases.get(indice);
    }

    public String obtenerFraseAleatoria() {
        if (frases.isEmpty()) {
            return "No hay frases disponibles.";
        }
        int indice = random.nextInt(frases.size());
        return frases.get(indice);
    }

    public void mostrarFraseDelDia() {
        String frase = obtenerFraseDelDia();
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       💭 FRASE DEL DÍA 💭             ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ " + frase);
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public void listarFrases() {
        System.out.println("\n=== Listado de frases ===");
        for (int i = 0; i < frases.size(); i++) {
            System.out.println((i + 1) + ". " + frases.get(i));
        }
        System.out.println("Total: " + frases.size() + " frases\n");
    }

    public List<String> getFrases() {
        return new ArrayList<>(frases);
    }

    public int getCantidadFrases() {
        return frases.size();
    }
}