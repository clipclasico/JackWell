package emergencia;

import java.io.IOException;
import java.util.Scanner;

public class AppBoton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BotonEmergencia miBoton = null;
        boolean salir = false;

        try {
            miBoton = (BotonEmergencia) GestorPersistencia.cargarDatos();
            System.out.println("Estado anterior del botón cargado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontró estado guardado. Creando botón nuevo.");
        }

        if (miBoton == null) {
            miBoton = new BotonEmergencia();
        }

        System.out.println("Bienvenido al gestor del botón de emergencia.");

        while (!salir) {
            System.out.println("\n--- ESTADO ACTUAL ---");
            System.out.println(miBoton.obtenerEstado());
            System.out.println("---------------------");
            System.out.println("1. Activar botón");
            System.out.println("2. Desactivar botón");
            System.out.println("3. Salir y Guardar");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    miBoton.activar();
                    System.out.println("Botón activado.");
                    break;
                
                case 2:
                    miBoton.desactivar();
                    System.out.println("Botón desactivado.");
                    break;
                
                case 3:
                    try {
                        GestorPersistencia.guardarDatos(miBoton);
                        System.out.println("Estado del botón guardado. Adiós.");
                    } catch (IOException e) {
                        System.err.println("ERROR al guardar el estado del botón.");
                        e.printStackTrace();
                    }
                    salir = true;
                    break;
                
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        scanner.close();
    }
}
