package registrar_horarios;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppHorarios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Horario miHorario = null;
        boolean salir = false;

        try {
            miHorario = (Horario) GestorPersistencia.cargarDatos();
            System.out.println("Horario cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontró horario guardado. Se creará uno nuevo.");
        }

        if (miHorario == null) {
            miHorario = new Horario();
        }

        System.out.println("Bienvenido al Registrador de Horarios");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar Clase");
            System.out.println("2. Mostrar Horario Completo");
            System.out.println("3. Eliminar Clase");
            System.out.println("4. Salir y Guardar");
            System.out.print("Elige una opción: ");

            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Nombre de la materia: ");
                        String materia = scanner.nextLine();
                        System.out.print("Día (MONDAY, TUESDAY, ...): ");
                        DayOfWeek dia = DayOfWeek.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Hora Inicio (HH:mm): ");
                        LocalTime inicio = LocalTime.parse(scanner.nextLine());
                        System.out.print("Hora Fin (HH:mm): ");
                        LocalTime fin = LocalTime.parse(scanner.nextLine());
                        System.out.print("Edificio: ");
                        String edificio = scanner.nextLine();
                        System.out.print("Salón: ");
                        String salon = scanner.nextLine();
                        System.out.print("Catedrático: ");
                        String catedratico = scanner.nextLine();
                        
                        Clase nuevaClase = new Clase(materia, dia, inicio, fin, edificio, salon, catedratico);
                        if (miHorario.agregarClase(nuevaClase)) {
                            System.out.println("Clase agregada exitosamente.");
                        } else {
                            System.out.println("No se pudo agregar la clase (conflicto).");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Formato de hora incorrecto. Use HH:mm.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Día de la semana incorrecto.");
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;
                
                case 2:
                    miHorario.mostrarHorarioCompleto();
                    break;
                
                case 3:
                    try {
                        System.out.print("Nombre de la materia a eliminar: ");
                        String materiaElim = scanner.nextLine();
                        System.out.print("Día (MONDAY, TUESDAY, ...): ");
                        DayOfWeek diaElim = DayOfWeek.valueOf(scanner.nextLine().toUpperCase());
                        
                        if (miHorario.eliminarClase(materiaElim, diaElim)) {
                            System.out.println("Clase eliminada.");
                        } else {
                            System.out.println("No se encontró la clase especificada.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Día de la semana incorrecto.");
                    }
                    break;

                case 4:
                    try {
                        GestorPersistencia.guardarDatos(miHorario);
                        System.out.println("Horario guardado. Adiós.");
                    } catch (IOException e) {
                        System.err.println("ERROR al guardar el horario.");
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
