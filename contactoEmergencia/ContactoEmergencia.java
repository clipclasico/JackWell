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

    public void activar()
    {
        this.activo = true;
        System.out.println("Contacto de emergencia activado: " + nombre)
    }

    public void desactivar()
    {
        this.activo = false;
        System.out.println("Contacto de emergencia desactivado: " + nombre);
    }

    public String obtenerInformacion()
    {
        String estado = activo ? "Activo" : "Inactivo";
        return "Nombre: " + nombre + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Relación: " + relacion + "\n" +
               "Email: " + email + "\n" +
               "Estado: " + estado;
    }

    public void actualizarContacto(String telefono, String email)
    {
        if (telefono != null && !telefono.isEmpty()) {
            this.telefono = telefono;
        }

        if (email != null && !email.isEmpty()) {
            this.email = email;
        }

        System.out.println("Contacto de emergencia actualizado: " + nombre);
    }
}