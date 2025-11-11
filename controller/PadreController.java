/// ----------------------------------------------
/// PadreController.java
/// Controlador para gestionar las interacciones del padre.
/// ----------------------------------------------

package controller;

import model.UsuarioModel;
import view.PadreView;
import tipos_de_cuentas.Padre;
import tipos_de_cuentas.Estudiante;

public class PadreController
{
    private Padre padre;
    private PadreView view;
    private UsuarioModel usuarioModel;

    public PadreController(Padre padre, PadreView view, UsuarioModel usuarioModel)
    {
        this.padre = padre;
        this.view = view;
        this.usuarioModel = usuarioModel;
    }

    public boolean manejarMenu()
    {
        int opcion = view.mostrarMenuPadre(padre.getNombre());

        switch (opcion) {
            case 1:
                verEstudianteVinculado();
                break;
            case 2:
                configurarNotificaciones();
                break;
            case 3:
                view.mostrarFormularioDenuncias();
                break;
            case 4:
                view.mostrarFraseDelDia();
                break;
            case 5:
                mostrarPerfil();
                break;
            case 0:
                usuarioModel.cerrarSesion();
                view.mostrarMensaje("Sesión cerrada.");
                view.esperarEnter();
                break;
            default:
                view.mostrarError("Opción inválida.");
        }

        return false;
    }

    private void verEstudianteVinculado()
    {
        view.mostrarTitulo("Estudiante vinculado");

        Estudiante estudiante = padre.getEstudianteVinculado();
        
        if (estudiante == null)
        {
            view.mostrarAdvertencia("No se ha vinculado ningún estudiante a esta cuenta.");
            view.mostrarMensaje("ID del estudiante esperado: " + padre.getIdEstudianteVinculado());
            view.esperarEnter();
            return;
        }

        view.mostrarMensaje("Nombre: " + estudiante.getNombre());
        view.mostrarMensaje("Correo: " + estudiante.getCorreo());
        view.mostrarSeparador();
        view.mostrarMensaje("\nInformación disponible:");
        view.mostrarMensaje("Estado general: Activo en la plataforma");
        view.mostrarMensaje("Última actividad: Hoy");
        view.mostrarMensaje("Racha de uso: Información protegida por privacidad");
        
        view.mostrarSeparador();
        view.mostrarMensaje("\nNota:");
        view.mostrarMensaje("Para proteger la privacidad de tu hijo/a, solo se muestra");
        view.mostrarMensaje("información general. Si tienes preocupaciones específicas,");
        view.mostrarMensaje("considera hablar directamente con él/ella.");
        
        view.mostrarSeparador();
        view.mostrarMensaje("\nEn caso de emergencia:");
        view.mostrarMensaje("Si tu hijo/a activa el botón de emergencia,");
        view.mostrarMensaje("serás notificado inmediatamente.");
        
        view.esperarEnter();
    }

    private void configurarNotificaciones()
    {
        view.mostrarTitulo("Configurar Notificaciones");
        view.mostrarMensaje("Estado actual: " + (padre.isPuedeRecibirNotificaciones() ? "Activado" : "Desactivado"));

        view.mostrarMensaje("\nTipos de notificaciones:");
        view.mostrarMensaje("  • Emergencias activadas por tu hijo/a");
        view.mostrarMensaje("  • Alertas de seguridad del campus");

        System.out.println("\n1. Activar notificaciones");
        System.out.println("2. Desactivar notificaciones");
        System.out.println("0. Volver");
        
        int opcion = view.solicitarEntero("Selecciona una opción: ");

        switch (opcion)
        {
            case 1:
                padre.configurarNotificaciones(true);
                view.mostrarExito("Notificaciones activadas.");
                view.mostrarMensaje("Recibirás alertas sobre la actividad de tu hijo/a.");
                view.esperarEnter();
                break;

            case 2:
                padre.configurarNotificaciones(false);
                view.mostrarMensaje("Notificaciones desactivadas.");
                view.esperarEnter();
                break;

            case 0:
                return;
        }
    }

    private void mostrarPerfil()
    {
        view.mostrarTitulo("Mi Perfil - Padre");
        view.mostrarMensaje("ID: " + padre.getId());
        view.mostrarMensaje("Nombre: " + padre.getNombre());
        view.mostrarMensaje("Correo: " + padre.getCorreo());
        view.mostrarMensaje("Teléfono: " + padre.getTelefono());
        view.mostrarMensaje("Estudiante vinculado: " + (padre.getEstudianteVinculado() != null ? padre.getEstudianteVinculado().getNombre() : "No vinculado"));
        view.esperarEnter();
    }
}