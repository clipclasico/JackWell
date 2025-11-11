/// ----------------------------------------------
/// CatedraticoController.java
/// Controlador para gestionar las interacciones del catedrático.
/// ----------------------------------------------

package controller;

import model.UsuarioModel;
import view.CatedraticoView;
import tipos_de_cuentas.Catedratico;

public class CatedraticoController
{
    private Catedratico catedratico;
    private CatedraticoView view;
    private UsuarioModel usuarioModel;

    public CatedraticoController(Catedratico catedratico, CatedraticoView view, UsuarioModel usuarioModel)
    {
        this.catedratico = catedratico;
        this.view = view;
        this.usuarioModel = usuarioModel;
    }

    public boolean manejarMenu()
    {
        int opcion = view.mostrarMenuCatedratico(catedratico.getNombre());

        switch (opcion)
        {
            case 1:
                gestionarMaterias();
                break;
            case 2:
                configurarAlertas();
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

    private void gestionarMaterias()
    {
        int opcion = view.mostrarMenuMaterias();

        switch (opcion)
        {
            case 1:
                if (catedratico.getMaterias().isEmpty())
                {
                    view.mostrarMensaje("No tienes materias asignadas.");
                } else {
                    view.mostrarMensaje("\nMis materias:");
                    for (int i = 0; i < catedratico.getMaterias().size(); i++)
                    {
                        view.mostrarMensaje((i + 1) + ". " + catedratico.getMaterias().get(i));
                    }
                }
                view.esperarEnter();
                break;

            case 2:
                String materia = view.solicitarTexto("Nombre de la materia: ");
                catedratico.agregarMateria(materia);
                view.mostrarExito("Materia agregada exitosamente.");
                view.esperarEnter();
                break;

            case 0:
                return;
        }
    }

    private void configurarAlertas()
    {
        view.mostrarTitulo("Configurar alertas");
        view.mostrarMensaje("Estado actual: " + (catedratico.isPuedeRecibirAlertas() ? "Activado" : "Desactivado"));

        System.out.println("\n1. Activar alertas");
        System.out.println("2. Desactivar alertas");
        System.out.println("0. Volver");
        
        int opcion = view.solicitarEntero("Selecciona una opción: ");

        switch (opcion)
        {
            case 1:
                catedratico.setPuedeRecibirAlertas(true);
                view.mostrarExito("Alertas activadas. Recibirás notificaciones de emergencias.");
                view.esperarEnter();
                break;

            case 2:
                catedratico.setPuedeRecibirAlertas(false);
                view.mostrarMensaje("Alertas desactivadas.");
                view.esperarEnter();
                break;

            case 0:
                return;
        }
    }

    private void mostrarPerfil()
    {
        view.mostrarTitulo("Mi Perfil - Catedrático");
        view.mostrarMensaje("ID: " + catedratico.getId());
        view.mostrarMensaje("Nombre: " + catedratico.getNombre());
        view.mostrarMensaje("Correo: " + catedratico.getCorreo());
        view.mostrarMensaje("Teléfono: " + catedratico.getTelefono());
        view.mostrarMensaje("Materias: " + catedratico.getMaterias().size());
        view.esperarEnter();
    }
}