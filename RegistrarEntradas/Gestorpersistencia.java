package RegistrarEntradas;

import java.io.*;

public class GestorPersistencia {

public static final String RUTA_ARCHIVO = "diario.dat";

    public static void guardarDatos(Object datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(RUTA_ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
          
          oos.writeObject(datos);
        }
    }
