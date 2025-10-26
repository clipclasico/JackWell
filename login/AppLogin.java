package login;

import tipos_de_cuentas.TipoCuenta;
import java.io.IOException;
import java.util.Scanner;

public class AppLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaLogin sistema = null;
        boolean salir = false;

        try {
            sistema = (SistemaLogin) GestorPersistencia.cargarDatos();
            System.out.println("Sistema de login cargado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontró archivo. Creando nuevo sistema de login.");
        }

        if (sistema == null) {
            sistema = new SistemaLogin();
        }

        System.out.println("Bienvenido al Sistema de Login");

        while (!salir) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Cerrar sesión");
            System.out.println("5. Salir y Guardar");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Tipo (ESTUDIANTE, CATEDRATICO, PADRE): ");
                    TipoCuenta tipo = TipoCuenta.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = scanner.nextLine();
                    String datosAd = null;
                    if (tipo == TipoCuenta.PADRE) {
                        System.out.print("ID del Estudiante a vincular: ");
                        datosAd = scanner.nextLine();
                    }
                    sistema.registrarUsuario(tipo, id, nombre, correo, pass, tel, datosAd);
                    break;
                
                case 2:
                    System.out.print("Correo: ");
                    String correoLogin = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String passLogin = scanner.nextLine();
                    sistema.iniciarSesion(correoLogin, passLogin);
                    break;
                
                case 3:
                    sistema.listarUsuarios();
                    break;

                case 4:
                    sistema.cerrarSesion();
                    break;
                
                case 5:
                    try {
                        GestorPersistencia.guardarDatos(sistema);
                        System.out.println("Sistema guardado. Adiós.");
                    } catch (IOException e) {
                        System.err.println("ERROR al guardar el sistema.");
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
