/// ----------------------------------------------
/// UsuarioModel.java
/// Modelo para gestionar usuarios y sesiones.
/// ----------------------------------------------

package model;

import tipos_de_cuentas.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioModel
{
    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    public UsuarioModel()
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
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion()
    {
        this.usuarioActual = null;
    }

    public boolean haySesionActiva()
    {
        return usuarioActual != null;
    }

    public boolean registrarUsuario(TipoCuenta tipo, String id, String nombre, String correo, String contrasena, String telefono, String datosAdicionales)
    {
        for (Usuario usuario : usuarios)
        {
            if (usuario.getCorreo().equals(correo))
            {
                return false;
            }
        }

        try {
            Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);
            usuarios.add(nuevoUsuario);

            if (nuevoUsuario instanceof Padre && datosAdicionales != null)
            {
                Padre padre = (Padre) nuevoUsuario;
                Estudiante estudiante = buscarEstudiantePorId(datosAdicionales);
                if (estudiante != null)
                {
                    padre.setEstudianteVinculado(estudiante);
                }
            }

            return true;
        } catch (Exception e)
        {
            return false;
        }
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

    public List<Usuario> getUsuarios()
    {
        return new ArrayList<>(usuarios);
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

    public boolean cambiarContrasena(String contrasenaActual, String nuevaContrasena)
    {
        if (usuarioActual == null)
        {
            return false;
        }

        if (usuarioActual.autenticar(usuarioActual.getCorreo(), contrasenaActual))
        {
            usuarioActual.setContrasena(nuevaContrasena);
            return true;
        }
        return false;
    }

    public void listarUsuarios()
    {
        System.out.println("\n=== Usuarios registrados ===");
        for (Usuario usuario : usuarios)
        {
            System.out.println("- " + usuario.getNombre() + " (" + usuario.getTipoCuenta().getDescripcion() + ") - " + usuario.getCorreo());
        }
        System.out.println("Total: " + usuarios.size() + " usuarios");
    }
}