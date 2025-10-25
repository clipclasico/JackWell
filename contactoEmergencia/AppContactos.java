package contactoEmergencia;

import java.io.IOException;
import java.util.Scanner;

public class AppContactos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorContactos gestor = null;
        boolean salir = false;

        try {
            gestor = (GestorContactos) GestorPersistencia.cargarDatos();
            System.out.println("Datos de contactos cargados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar el archivo. Iniciando nuevo gestor.");
        }

        if (gestor == null) {
            gestor = new GestorContactos();
        }

        System.out.println("Bienvenido al Gestor de Contactos de Emergencia");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Actualizar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Notificar emergencia a un contacto");
            System.out.println("6. Salir y Guardar");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = scanner.nextLine();
                    System.out.print("Relación: ");
                    String rel = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    gestor.agregarContacto(new ContactoEmergencia(nombre, tel, rel, email));
                    break;
                
                case 2:
                    gestor.listarContactos();
                    break;
                
                case 3:
                    System.out.print("Nombre del contacto a actualizar: ");
                    String nombreAct = scanner.nextLine();
                    ContactoEmergencia cAct = gestor.buscarContacto(nombreAct);
                    if (cAct != null) {
                        System.out.print("Nuevo teléfono (dejar en blanco para no cambiar): ");
                        String nuevoTel = scanner.nextLine();
                        System.out.print("Nuevo email (dejar en blanco para no cambiar): ");
                        String nuevoEmail = scanner.nextLine();
                        cAct.actualizarContacto(nuevoTel, nuevoEmail);
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombreElim = scanner.nextLine();
                    gestor.eliminarContacto(nombreElim);
                    break;
                
                case 5:
                    System.out.print("Nombre del contacto a notificar: ");
                    String nombreNotif = scanner.nextLine();
                    ContactoEmergencia cNotif = gestor.buscarContacto(nombreNotif);
                    if (cNotif != null) {
                        System.out.print("Mensaje de emergencia: ");
                        String msg = scanner.nextLine();
                        cNotif.notificarEmergencia(msg);
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;

                case 6:
                    try {
                        GestorPersistencia.guardarDatos(gestor);
                        System.out.println("Datos guardados. Adiós.");
                    } catch (IOException e) {
                        System.err.println("ERROR al guardar datos.");
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
