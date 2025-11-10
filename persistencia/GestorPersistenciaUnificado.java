package persistencia;

import java.io.*;

public class GestorPersistenciaUnificado
{
    
    private static final String DIRECTORIO_DATOS = "datos/";
    
    static
    {
        File dir = new File(DIRECTORIO_DATOS);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
    }
    
    public static void guardarDatos(String nombreArchivo, Object datos) throws IOException
    {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo + ".dat";
        
        try (FileOutputStream fos = new FileOutputStream(rutaCompleta);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
             {
            
            oos.writeObject(datos);
            System.out.println("✓ Datos guardados en: " + rutaCompleta);
        } catch (IOException e) {
            System.err.println("✗ Error al guardar " + nombreArchivo + ": " + e.getMessage());
            throw e;
        }
    }
    
    public static Object cargarDatos(String nombreArchivo) throws IOException, ClassNotFoundException
    {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo + ".dat";
        File archivo = new File(rutaCompleta);
        
        if (!archivo.exists())
        {
            System.out.println("No existe archivo: " + rutaCompleta);
            return null;
        }
        
        try (FileInputStream fis = new FileInputStream(rutaCompleta);
             ObjectInputStream ois = new ObjectInputStream(fis))
             {
            
            Object datos = ois.readObject();
            System.out.println("Datos cargados desde: " + rutaCompleta);
            return datos;
            
        } catch (IOException | ClassNotFoundException e)
        {
            System.err.println("Error al cargar " + nombreArchivo + ": " + e.getMessage());
            throw e;
        }
    }

    public static boolean existeArchivo(String nombreArchivo)
    {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo + ".dat";
        return new File(rutaCompleta).exists();
    }
    
    public static boolean eliminarArchivo(String nombreArchivo)
    {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo + ".dat";
        File archivo = new File(rutaCompleta);
        
        if (archivo.exists())
        {
            boolean eliminado = archivo.delete();
            if (eliminado)
            {
                System.out.println("✓ Archivo eliminado: " + rutaCompleta);
            }
            return eliminado;
        }
        return false;
    }
    
    public static void listarArchivos()
    {
        File dir = new File(DIRECTORIO_DATOS);
        File[] archivos = dir.listFiles((d, name) -> name.endsWith(".dat"));
        
        if (archivos == null || archivos.length == 0)
        {
            System.out.println("No hay archivos de datos guardados.");
            return;
        }
        
        System.out.println("\n=== Archivos de datos ===");
        for (File archivo : archivos)
        {
            long tamanoKB = archivo.length() / 1024;
            System.out.printf("- %s (%.2f KB)%n", archivo.getName(), tamanoKB > 0 ? tamanoKB : 0.01);
        }
        System.out.println("========================\n");
    }
}