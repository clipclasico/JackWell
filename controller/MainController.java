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
        
        boolean salir = false;
        while (!salir) {
            if (!usuarioModel.haySesionActiva())
            {
                salir = mostrarMenuPrincipal();
            } else {
                salir = mostrarMenuUsuario();
            }
        }
        
        mainView.mostrarDespedida();
    }

    private boolean mostrarMenuPrincipal()
    {
        int opcion = mainView.mostrarMenuPrincipal();
        
        switch (opcion)
        {
            case 1:
                loginController.iniciarSesion();
                break;
            case 2:
                loginController.registrarUsuario();
                break;
            case 3:
                mainView.mostrarFraseDelDia();
                break;
            case 4:
                mainView.mostrarFormularioDenuncias();
                break;
            case 5:
                usuarioModel.listarUsuarios();
                mainView.esperarEnter();
                break;
            case 0:
                return true;
            default:
                mainView.mostrarError("Opción inválida");
        }
        
        return false;
    }

    private boolean mostrarMenuUsuario()
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
            return estudianteController.manejarMenu();
            
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
            return catedraticoController.manejarMenu();
            
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
            return padreController.manejarMenu();
        }
        
        return false;
    }

    public void reiniciarControladores()
    {
        this.estudianteController = null;
        this.catedraticoController = null;
        this.padreController = null;
    }
}