import java.util.Scanner;

public class AplicacionDiario {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        DiarioEmocional miDiario = new DiarioEmocional();
        boolean salir = false;

        System.out.println("Bienvenido a tu diario de emociones portatil ");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nueva entrada");
            System.out.println("2. Ver todas mis entradas");
            System.out.println("3. Eliminar una entrada");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

        int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: {
                    System.out.print("¿Cómo te sientes hoy? (Ej: Feliz, Triste, Ansioso): ");
                    String animo = scanner.nextLine();
                    System.out.println("Escribe tus pensamientos o que te pasó hoy:");
                    String pensamientos = scanner.nextLine();
                    miDiario.agregarEntrada(animo, pensamientos);

                }
                case 2 -> {
                    miDiario.verEntradas();
                }
                case 3 -> {
                    System.out.print("Ingresa el ID de la entrada que quieres eliminar: ");
                    int idParaEliminar = scanner.nextInt();
                    if (miDiario.eliminarEntrada(idParaEliminar)) {
                        System.out.println("🗑Entrada #" + idParaEliminar + " eliminada.");
                    }
                }
                case 4 -> {
                    System.out.println("Gracias por usar tu diario, no platicamos luego");














