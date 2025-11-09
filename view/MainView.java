package view;

import frase.FraseDia;
import formulario.FormularioDenuncias;
import java.util.Scanner;

public class MainView
{
    private Scanner scanner;
    private FraseDia fraseDia;

    public MainView()
    {
        this.scanner = new Scanner(System.in);
        this.fraseDia = new FraseDia();
    }

    public void mostrarBienvenida()
    {
        System.out.println("Bienvenido a JackWell App ");
        System.out.println("¡Bienestar que trasciende!");
    }

    public void mostrarDespedida()
    {
        System.out.println("\n¡Gracias por usar JackWell!");
        System.out.println("Cuida tu bienestar. ¡Hasta luego! \n");
    }

    public int mostrarMenuPrincipal()
    {
        System.out.println("Menú Principal");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar Usuario");
        System.out.println("3. Ver frase del día");
        System.out.println("4. Formulario de denuncias");
        System.out.println("5. Ver usuarios (demo)");
        System.out.println("0. Salir");

        System.out.print("\nSeleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();
        return opcion;
    }

    public void mostrarFraseDelDia()
    {
        System.out.println("\nFrase del día:");
        fraseDia.mostrarFraseDelDia();
        esperarEnter();
    }

    public void mostrarFormularioDenuncias()
    {
        FormularioDenuncias.mostrarInformacion();
        esperarEnter();
    }

    public String solicitarTexto(String mensaje)
    {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int solicitarEntero(String mensaje)
    {
        System.out.print(mensaje);
        int valor = leerEntero();
        scanner.nextLine();
        return valor;
    }

    public boolean solicitarConfirmacion(String mensaje)
    {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }

    public void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }

    public void mostrarExito(String mensaje)
    {
        System.out.println("Exito: " + mensaje);
    }

    public void mostrarError(String mensaje)
    {
        System.out.println("Error: " + mensaje);
    }

    public void mostrarAdvertencia(String mensaje)
    {
        System.out.println("Advertencia: " + mensaje);
    }

    public void mostrarTitulo(String titulo)
    {
        System.out.println("\n=================================");
        System.out.println("   " + titulo);
        System.out.println("=================================");
    }

    public void mostrarSeparador()
    {
        System.out.println("-------------------------");
    }

    public void limpiarPantalla()
    {
        System.out.println("\n\n\n");
    }

    public void esperarEnter()
    {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    private int leerEntero()
    {
        while (!scanner.hasNextInt())
        {
            System.out.print("Entrada inválida. Ingrese un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public void cerrar()
    {
        if (scanner != null)
        {
            scanner.close();
        }
    }
}