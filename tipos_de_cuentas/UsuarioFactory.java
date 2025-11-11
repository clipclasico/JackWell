/// ----------------------------------------------
/// UsuarioFactory.java
/// Clase fábrica para crear instancias de Usuario según el tipo de cuenta.
/// ----------------------------------------------

package tipos_de_cuentas;

public class UsuarioFactory
{
    public static Usuario crearUsuario(TipoCuenta tipo, String id, String nombre, String correo, String contrasena, String telefono, String datosAdicionales)
    {
        switch (tipo)
        {
            case ESTUDIANTE:
                return new Estudiante(id, nombre, correo, contrasena, telefono);
            case PADRE:
                return new Padre(id, nombre, correo, contrasena, telefono, datosAdicionales);
            case CATEDRATICO:
                return new Catedratico(id, nombre, correo, contrasena, telefono);
            default:
                throw new IllegalArgumentException("Tipo de cuenta no válido: " + tipo);
        }
    }
}
