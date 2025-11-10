package frase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FraseDia
{
    private List<String> frases;
    private LocalDate fechaActual;
    private Random random;
    private static final String ARCHIVO_FRASES = "frases.csv";

    public FraseDia()
    {
        this.frases = new ArrayList<>();
        this.random = new Random();
        this.fechaActual = LocalDate.now();
        cargarFrasesDesdeCSV();
        
        // Si no se pudieron cargar del CSV, usar frases por defecto
        if (frases.isEmpty())
        {
            inicializarFrasesDefecto();
        }
    }

    private void cargarFrasesDesdeCSV()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_FRASES)))
        {
            String linea;
            // Saltar la primera línea si es un encabezado
            br.readLine();
            
            while ((linea = br.readLine()) != null)
            {
                linea = linea.trim();
                if (!linea.isEmpty())
                {
                    frases.add(linea);
                }
            }
            
            System.out.println("✓ " + frases.size() + " frases cargadas desde " + ARCHIVO_FRASES);
        }
        catch (IOException e)
        {
            System.out.println("⚠ No se pudo cargar el archivo de frases. Usando frases por defecto.");
        }
    }

    private void inicializarFrasesDefecto()
    {
        frases.add("Tu salud mental es una prioridad. Cuídate.");
        frases.add("Cada día es una nueva oportunidad para ser feliz.");
        frases.add("La resiliencia es la clave para superar los desafíos.");
        frases.add("No estás solo. Busca apoyo cuando lo necesites.");
        frases.add("La meditación puede ayudarte a encontrar paz interior.");
        frases.add("Hablar sobre tus sentimientos es un acto de valentía.");
        frases.add("No pain, no gain.");
        frases.add("El éxito es la suma de pequeños esfuerzos repetidos día tras día.");
        frases.add("La perseverancia es la madre del éxito.");
        frases.add("Está bien no estar bien.");
        frases.add("El autocuidado no es egoísmo, es una necesidad.");
        frases.add("JackWell te cuida.");
        frases.add("La felicidad no es algo hecho. Viene de tus propias acciones.");
        frases.add("Tus errores no te definen. Aprende de ellos y sigue adelante.");
        frases.add("¿Alguien lee esto?");
        frases.add("Sé amable con todos. Nunca sabes si te puedes encontrar con alguien que cambie tu vida.");
        frases.add("Los días difíciles no duran para siempre.");
        frases.add("Peace. Power. JackWell.");
        frases.add("Más wellness, menos drama.");
        frases.add("Más que cuidado. Conexión.");
        frases.add("Cada día es un reinicio. Aprovecha la oportunidad para empezar de nuevo.");
        frases.add("El amor también se mide en bienestar.");
        frases.add("Tu historia no termina aquí. Cada día es una nueva página en blanco.");
        frases.add("Pequeños pasos conducen a grandes cambios.");
        frases.add("Eres más fuerte de lo que piensas.");
        frases.add("Ríe. Llora. Respira. Repite.");
        frases.add("Los días dificiles hacen que los buenos días sean aún mejores.");
        frases.add("Buenos días.");
        frases.add("Cada pequeño paso cuenta en tu camino hacia el bienestar.");
    }

    public String obtenerFraseDelDia()
    {
        int dia = fechaActual.getDayOfYear();
        int indice = dia % frases.size();
        return frases.get(indice);
    }

    public String obtenerFraseAleatoria()
    {
        int indice = random.nextInt(frases.size());
        return frases.get(indice);
    }

    public void mostrarFraseDelDia()
    {
        String frase = obtenerFraseDelDia();
        System.out.println("Frase del día: ");
        System.out.println("   \"" + frase + "\"");
    }

    public void listarFrases()
    {
        System.out.println("Listado de frases:");
        for (int i = 0; i < frases.size(); i++)
        {
            System.out.println((i + 1) + ". " + frases.get(i));
        }
        System.out.println("Total de frases: " + frases.size());
    }

    public List<String> getFrases()
    {
        return new ArrayList<>(frases);
    }

    public int getCantidadFrases()
    {
        return frases.size();
    }    
}