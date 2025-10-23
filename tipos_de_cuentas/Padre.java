package tipos_de_cuentas;

public class Padre extends Usuario
{
    private String idEstudianteVinculado;
    private boolean recibirNotificacionesEmergencia;

    public Padre(String id, String nombre, String correo, String contrasena, String telefono, String idEstudianteVinculado)
    {
        super(id, nombre, correo, contrasena, TipoCuenta.PADRE, telefono);
        this.idEstudianteVinculado = idEstudianteVinculado;
        this.recibirNotificacionesEmergencia = true;
    }

    @Override
    public String[] getPermisosEspeciales()
    {
        return new String[]
        {
            "recibir_notificaciones_emergencia",
            "ver_resumen_bienestar_estudiante",
            "configurar_contacto_emergencia",
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
        return null;
    }

    public boolean isPuedeRecibirNotificaciones()
    {
        return recibirNotificacionesEmergencia;
    }
}
