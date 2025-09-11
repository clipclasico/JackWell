package tipos_de_cuentas;

public class Padre extends Usuario
{
    private String idEstudianteVinculado;
    private boolean recibirNotificacionesEmergencia;

    public Padre(String id, String nombre, String correo, String contasena, String telefono, String idEstudianteVinculado)
    {
        super(id, nombre, correo, contasena, TipoCuenta.PADRE, telefono);
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
}
