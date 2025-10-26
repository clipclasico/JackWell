package registrar_horarios;

import java.io.*;

public class GestorPersistencia {

    public static final String RUTA_ARCHIVO = "horario.dat";

    public static void guardarDatos(Object datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(RUTA_ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(datos);
        }
    }

    public static Object cargarDatos() throws IOException, ClassNotFoundException {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return null;
        }

        try (FileInputStream fis = new FileInputStream(RUTA_ARCHIVO);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Object datos = ois.readObject();
            return datos;
        }
    }
}
