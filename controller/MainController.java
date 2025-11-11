/// ----------------------------------------------
/// MainController.java
/// Controlador principal que gestiona la navegación entre vistas y controladores específicos.
/// ----------------------------------------------

package controller;

import model.*;
import view.*;
import tipos_de_cuentas.*;

public class MainController
{
    private UsuarioModel usuarioModel;
    private MainView mainView;
    private LoginController loginController;
    private EstudianteController estudianteController;
    private CatedraticoController catedraticoController;
    private PadreController padreController;

    public MainController()
    {
        this.usuarioModel = new UsuarioModel();
        this.mainView = new MainView();
        this.loginController = new LoginController(usuarioModel, mainView);
    }

    public void iniciar()
    {
        mainView.mostrarBienvenida();
        ejecutarMenuPrincipal();
        mainView.mostrarDespedida();
    }

    private void ejecutarMenuPrincipal()
    {
        int opcion = mainView.mostrarMenuPrincipal();
        
        switch (opcion)
        {
            case 1:
                loginController.iniciarSesion();
                ejecutarProximoMenu();
                break;
            case 2:
                loginController.registrarUsuario();
                ejecutarProximoMenu();
                break;
            case 3:
                mainView.mostrarFraseDelDia();
                ejecutarProximoMenu();
                break;
            case 4:
                mainView.mostrarFormularioDenuncias();
                ejecutarProximoMenu();
                break;
            case 5:
                usuarioModel.listarUsuarios();
                mainView.esperarEnter();
                ejecutarProximoMenu();
                break;
            case 0:
                return;
            default:
                mainView.mostrarError("Opción inválida");
                ejecutarProximoMenu();
        }
    }

    private void ejecutarProximoMenu()
    {
        if (usuarioModel.haySesionActiva())
        {
            ejecutarMenuUsuario();
        } else {
            ejecutarMenuPrincipal();
        }
    }

    private void ejecutarMenuUsuario()
    {
        Usuario usuario = usuarioModel.getUsuarioActual();
        
        if (usuario instanceof Estudiante)
        {
            if (estudianteController == null)
            {
                estudianteController = new EstudianteController(
                    (Estudiante) usuario, 
                    new EstudianteView(),
                    usuarioModel
                );
            }
            estudianteController.manejarMenu();
            
        } else if (usuario instanceof Catedratico)
        {
            if (catedraticoController == null)
            {
                catedraticoController = new CatedraticoController(
                    (Catedratico) usuario,
                    new CatedraticoView(),
                    usuarioModel
                );
            }
            catedraticoController.manejarMenu();
            
        } else if (usuario instanceof Padre)
        {
            if (padreController == null)
            {
                padreController = new PadreController(
                    (Padre) usuario,
                    new PadreView(),
                    usuarioModel
                );
            }
            padreController.manejarMenu();
        }

        if (usuarioModel.haySesionActiva())
        {
            ejecutarMenuUsuario();
        } else {
            reiniciarControladores();
            ejecutarMenuPrincipal();
        }
    }

    public void reiniciarControladores()
    {
        this.estudianteController = null;
        this.catedraticoController = null;
        this.padreController = null;
    }
}