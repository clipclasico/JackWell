package login;

import tipos_de_cuentas.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaLogin
{
    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    public SistemaLogin()
    {
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
    }

    public boolean iniciarSesion(String correo, String contrasena)
    {
        for (Usuario usuario : usuarios)
        {
            if (usuario.autenticar(correo, contrasena))
            {
                this.usuarioActual = usuario;
                System.out.println("Inicio de sesión exitoso.");
                System.out.println("Bienvenido, " + usuario.getNombre());
                System.out.println("Tipo de cuenta: " + usuario.getTipoCuenta().getDescripcion());
                return true;
            }
        }
        System.out.println("Error: Credenciales inválidas.");
        return false;
    }

    public void cerrarSesion()
    {
        if (usuarioActual != null)
        {
            System.out.println("Cerrando sesión de " + usuarioActual.getNombre());
            usuarioActual = null;
        }
        else
        {
            System.out.println("No hay ningún usuario conectado.");
        }
    }

    public boolean registrarUsuario(TipoCuenta tipo, String id, String nombre, String correo, String contrasena, String telefono, String datosAdicionales)
    {
        for (Usuario usuario : usuarios)
        {
            if (usuario.getCorreo().equals(correo))
            {
                System.out.println("Error: Ya existe un usuario con este correo.");
                return false;
            }
        }

        try 
        {
            Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario registrado exitosamente: " + nombre);
            return true;
        } 
        catch (Exception e)
        {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean haySesionActiva()
    {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual()
    {
        return usuarioActual;
    }

    public Estudiante getEstudianteActual()
    {
        if (usuarioActual instanceof Estudiante)
        {
            return (Estudiante) usuarioActual;
        }
        return null;
    }

    public Catedratico getCatedraticoActual()
    {
        if (usuarioActual instanceof Catedratico)
        {
            return (Catedratico) usuarioActual;
        }
        return null;
    }

    public Padre getPadreActual()
    {
        if (usuarioActual instanceof Padre)
        {
            return (Padre) usuarioActual;
        }
        return null;
    }

    public boolean cambiarContrasena(String contrasenaActual, String nuevaContrasena)
    {
        if (usuarioActual == null)
        {
            System.out.println("No hay sesión activa.");
            return false;
        }

        if (usuarioActual.autenticar(usuarioActual.getCorreo(), contrasenaActual))
        {
            usuarioActual.setContrasena(nuevaContrasena);
            System.out.println("Contraseña cambiada exitosamente.");
            return true;
        }
        else
        {
            System.out.println("Error: La contraseña actual es incorrecta.");
            return false;
        }
    }

    public void mostrarInfoUsuario()
    {
        if (usuarioActual == null)
        {
            System.out.println("No hay sesión activa.");
            return;
        }

        System.out.println("Información del usuario:");
        System.out.println("ID: " + usuarioActual.getId());
        System.out.println("Nombre: " + usuarioActual.getNombre());
        System.out.println("Correo: " + usuarioActual.getCorreo());
        System.out.println("Teléfono: " + usuarioActual.getTelefono());
        System.out.println("Tipo de cuenta: " + usuarioActual.getTipoCuenta().getDescripcion());
        System.out.println("Permisos especiales: ");
        for (String permiso : usuarioActual.getPermisosEspeciales())
        {
            System.out.println("- " + permiso);
        }

        System.out.println("=============================");
    }

    public void listarUsuarios()
    {
        System.out.println("Listado de usuarios registrados:");
        for (Usuario usuario : usuarios)
        {
            System.out.println(usuario.getNombre() + " (" + usuario.getTipoCuenta().getDescripcion() + ") - " + usuario.getCorreo());
        }
        System.out.println("Total de usuarios: " + usuarios.size());
    }

    public boolean registrarUsuario(TipoCuenta tipo, String id, String nombre, String correo, String contrasena, String telefono, String datosAdicionales)
    {
        for (Usuario usuario : usuarios)
        {
            if (usuario.getCorreo().equals(correo))
            {
                System.out.println("Error: Ya existe un usuario con este correo");
                return false;
            }
        }

        try
        {
            Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);
            usuarios.add(nuevoUsuario);

            if (nuevoUsuario instanceof Padre && datosAdicionales != null)
            {
                Padre padre = (Padre) nuevoUsuario;
                Estudiante estudiante = buscarEstudiantePorId(datosAdicionales);
                if (estudiante != null)
                {
                    padre.setEstudianteVinculado(estudiante);
                    System.out.println("Estudiante vinculado exitosamente: " + estudiante.getNombre());
                }
                else
                {
                    System.out.println("Advertencia: No se encontró un estudiante con el ID proporcionado.");
                }
            }

            System.out.println("Usuario registrado exitosamente: " + nombre);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    
    private Estudiante buscarEstudiantePorId(String id)
    {
        for (Usuario usuario : usuarios)
        {
            if (usuario instanceof Estudiante && usuario.getId().equals(id))
            {
                return (Estudiante) usuario;
            }
        }
        return null;
    }
}