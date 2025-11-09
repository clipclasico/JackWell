package controller;

import model.UsuarioModel;
import view.MainView;
import tipos_de_cuentas.TipoCuenta;

public class LoginController
{
    private UsuarioModel usuarioModel;
    private MainView view;

    public LoginController(UsuarioModel usuarioModel, MainView view)
    {
        this.usuarioModel = usuarioModel;
        this.view = view;
    }

    public void iniciarSesion()
    {
        view.mostrarTitulo("Iniciar Sesión");
        
        String correo = view.solicitarTexto("Correo: ");
        String contrasena = view.solicitarTexto("Contraseña: ");

        if (usuarioModel.iniciarSesion(correo, contrasena))
        {
            view.mostrarExito("¡Sesión iniciada con éxito!");
            view.mostrarMensaje("Bienvenido/a, " + usuarioModel.getUsuarioActual().getNombre());
        } else {
            view.mostrarError("Credenciales incorrectas. Verifica tus datos.");
        }
        
        view.esperarEnter();
    }

    public void registrarUsuario()
    {
        view.mostrarTitulo("Registrar nuevo usuario");
        
        TipoCuenta tipo = solicitarTipoCuenta();
        if (tipo == null)
        {
            view.mostrarError("Tipo de cuenta inválido.");
            view.esperarEnter();
            return;
        }

        String id = view.solicitarTexto("ID/Carnet: ");
        String nombre = view.solicitarTexto("Nombre completo: ");
        String correo = view.solicitarTexto("Correo: ");
        String contrasena = view.solicitarTexto("Contraseña: ");
        String telefono = view.solicitarTexto("Teléfono: ");

        String datosAdicionales = null;
        if (tipo == TipoCuenta.PADRE)
        {
            datosAdicionales = view.solicitarTexto("ID del estudiante vinculado: ");
        }

        boolean registrado = usuarioModel.registrarUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);

        if (registrado)
        {
            view.mostrarExito("¡Usuario registrado con éxito!");
            view.mostrarMensaje("Ya puedes iniciar sesión con tu correo y contraseña.");
        } else {
            view.mostrarError("Error al registrar. El correo ya existe o datos inválidos.");
        }
        
        view.esperarEnter();
    }

    private TipoCuenta solicitarTipoCuenta()
    {
        view.mostrarMensaje("\nTipo de cuenta:");
        view.mostrarMensaje("1. Estudiante");
        view.mostrarMensaje("2. Padre");
        view.mostrarMensaje("3. Catedrático");
        
        int opcion = view.solicitarEntero("Seleccione una opción: ");

        switch (opcion)
        {
            case 1:
                return TipoCuenta.ESTUDIANTE;
            case 2:
                return TipoCuenta.PADRE;
            case 3:
                return TipoCuenta.CATEDRATICO;
            default:
                return null;
        }
    }

    public void cambiarContrasena()
    {
        view.mostrarTitulo("Cambiar Contraseña");
        
        String contrasenaActual = view.solicitarTexto("Contraseña actual: ");
        String nuevaContrasena = view.solicitarTexto("Nueva contraseña: ");
        String confirmar = view.solicitarTexto("Confirmar nueva contraseña: ");

        if (!nuevaContrasena.equals(confirmar))
        {
            view.mostrarError("Las contraseñas no coinciden.");
            view.esperarEnter();
            return;
        }

        if (usuarioModel.cambiarContrasena(contrasenaActual, nuevaContrasena))
        {
            view.mostrarExito("Contraseña cambiada exitosamente.");
        } else {
            view.mostrarError("Contraseña actual incorrecta.");
        }
        
        view.esperarEnter();
    }

    public void mostrarPerfil()
    {
        if (!usuarioModel.haySesionActiva())
        {
            view.mostrarError("No hay sesión activa.");
            view.esperarEnter();
            return;
        }

        var usuario = usuarioModel.getUsuarioActual();
        
        view.mostrarTitulo("Mi Perfil");
        view.mostrarMensaje("ID: " + usuario.getId());
        view.mostrarMensaje("Nombre: " + usuario.getNombre());
        view.mostrarMensaje("Correo: " + usuario.getCorreo());
        view.mostrarMensaje("Teléfono: " + usuario.getTelefono());
        view.mostrarMensaje("Tipo: " + usuario.getTipoCuenta().getDescripcion());
        
        view.mostrarMensaje("\nPermisos especiales:");
        for (String permiso : usuario.getPermisosEspeciales())
        {
            view.mostrarMensaje("  • " + permiso);
        }
        
        view.esperarEnter();
    }
}