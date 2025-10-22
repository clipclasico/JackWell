public class ContactoEmergencia
{
    private String nombre;
    private String telefono;
    private String relacion;
    private String email;
    private boolean activo;

    public ContactoEmergencia(String nombre, String telefono, String relacion, String email)
    {
        this.nombre = nombre;
        this.telefono = telefono;
        this.relacion = relacion;
        this.email = email;
        this.activo = true;
    }
}