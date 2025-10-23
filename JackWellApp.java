import tipos_de_cuentas.*;
import java.util.Scanner;

import login.SistemaLogin;

public class JackWellApp
{
    private static SistemaLogin sistema;
    private static Scanner scanner;

    public static void main(String[] args)
    {
        sistema = new SistemaLogin();
        scanner = new Scanner(System.in);

        mostrarBienvenida();

        do
        {
            if (!sistema.haySesionActiva())
            {
                if (!menuPrincipal()) break;
            } else {
                menuUsuario();
            }
        }
        while (sistema.haySesionActiva());

        scanner.close();
        System.out.println("Gracias por usar JackWell. ¡Hasta luego!");
    }

    private static void mostrarBienvenida()
    {
        System.out.println("===================================");
        System.out.println("   Bienvenido a JackWell App");
        System.out.println("===================================");
    }

    private static boolean menuPrincipal()
    {
        System.out.println("\nMenú Principal");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar Usuario");
        System.out.println("3. Ver usuarios (demo)");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                sistema.listarUsuarios();
                break;
            case 4:
                return false;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return true;
    }

    private static void iniciarSesion()
    {
        System.out.println("\nIniciar Sesión");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        sistema.iniciarSesion(correo, contrasena);
    }

    private static void registrarUsuario()
    {
        System.out.println("\nRegistrar Usuario");
        
        System.out.println("Tipo de cuenta: ");
        System.out.println("1. Estudiante");
        System.out.println("2. Padre");
        System.out.println("3. Catedrático");
        System.out.print("Seleccione una opción:");
        int tipoNum = leerEntero();
        scanner.nextLine();

        TipoCuenta tipo;
        switch (tipoNum)
        {
            case 1:
                tipo = TipoCuenta.ESTUDIANTE;
                break;
            case 2:
                tipo = TipoCuenta.PADRE;
                break;
            case 3:
                tipo = TipoCuenta.CATEDRATICO;
                break;
            default:
                System.out.println("Tipo de cuenta inválido.");
                return;
        }

        System.out.print("ID/Carnet: ");
        String id = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        String datosAdicionales = null;
        if (tipo == TipoCuenta.PADRE)
        {
            System.out.print("ID del estudiante vinculado: ");
            datosAdicionales = scanner.nextLine();
        }

        sistema.registrarUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);
    }

    private static boolean menuUsuario()
    {
        Usuario usuario = sistema.getUsuarioActual();
        System.out.println("\nMenú de Usuario - " + usuario.getNombre());

        if (usuario instanceof Estudiante)
        {
            return menuEstudiante();
        }
        else if (usuario instanceof Catedratico)
        {
            return menuCatedratico();
        }
        else if (usuario instanceof Padre)
        {
            return menuPadre();
        }

        return true;
    }

    private static boolean menuEstudiante()
    {
        System.out.println("1. Mi diario emocional");
        System.out.println("2. Ejercicios de relajación");
        System.out.println("3. Mi horario");
        System.out.println("4. Contactos de emergencia");
        System.out.println("5. Frase del día");
        System.out.println("6. Mi perfil");
        System.out.println("7. Cerrar sesión");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.println("...");
                break;
            case 2:
                System.out.println("...");
                break;
            case 3:
                System.out.println("...");
                break;
            case 4:
                System.out.println("...");
                break;
            case 5:
                mostrarFraseDelDia();
                break;
            case 6:
                sistema.mostrarInfoUsuario();
                break;
            case 7:
                sistema.cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return true;
    }   

    private static boolean menuCatedratico()
    {
        System.out.println("1. Alertas de estudiantes");
        System.out.println("2. Formulario de denuncias");
        System.out.println("3. Mi perfil");
        System.out.println("4. Cerrar sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.println("...");
                break;
            case 2:
                System.out.println("...");
                break;
            case 3:
                sistema.mostrarInfoUsuario();
                break;
            case 4:
                sistema.cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return true;
    }

    private static boolean menuPadre()
    {
        System.out.println("1. Ver estado del estudiante");
        System.out.println("2. Configurar notificaciones");
        System.out.println("3. Mi perfil");
        System.out.println("4. Cerrar sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.println("...");
                break;
            case 2:
                System.out.println("...");
                break;
            case 3:
                sistema.mostrarInfoUsuario();
                break;
            case 4:
                sistema.cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return true;
    }

    private static void mostrarFraseDelDia()
    {
        frase.FraseDia frase = new frase.FraseDia();
        System.out.println("\n" + frase.obtenerFraseDelDia() + "\n");
    }

    private static int leerEntero()
    {
        while (!scanner.hasNextInt())
        {
            System.out.print("Entrada inválida. Por favor ingrese un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}