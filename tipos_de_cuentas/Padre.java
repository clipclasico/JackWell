/// ----------------------------------------------
/// Padre.java
/// Clase que representa a un padre de familia en el sistema.
/// ----------------------------------------------

package tipos_de_cuentas;

public class Padre extends Usuario
{
    private String idEstudianteVinculado;
    private boolean recibirNotificacionesEmergencia;
    private Estudiante estudianteVinculado;

    public Padre(String id, String nombre, String correo, String contrasena, String telefono, String idEstudianteVinculado)
    {
        super(id, nombre, correo, contrasena, TipoCuenta.PADRE, telefono);
        this.idEstudianteVinculado = idEstudianteVinculado;
        this.recibirNotificacionesEmergencia = true;
        this.estudianteVinculado = null;
    }

    @Override
    public String[] getPermisosEspeciales()
    {
        return new String[]
        {
            "Recibir notificaciones de emergencia",
            "Ver resumen de bienestar del estudiante",
            "configurar contacto de emergencia",
        };
    }

    public void configurarNotificaciones(boolean activar)
    {
        this.recibirNotificacionesEmergencia = activar;
    }

    public String getIdEstudianteVinculado()
    {
        return idEstudianteVinculado;
    }

    public boolean isRecibirNotificacionesEmergencia()
    {
        return recibirNotificacionesEmergencia;
    }

    public Estudiante getEstudianteVinculado()
    {
        return estudianteVinculado;
    }

    public boolean isPuedeRecibirNotificaciones()
    {
        return recibirNotificacionesEmergencia;
    }

    public void setEstudianteVinculado(Estudiante estudiante)
    {
        this.estudianteVinculado = estudiante;
    }
}
