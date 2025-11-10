package contactoEmergencia;

import java.io.Serializable;

public class ContactoEmergencia implements Serializable
{
    private static final long serialVersionUID = 1L;
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
        System.out.println("Contacto de emergencia activado: " + nombre);
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

    public void notificarEmergencia(String mensaje)
    {
        if(activo)
        {
            System.out.println("Notificación enviada a " + nombre + " (" + email + ")");
            System.out.println("Llamada automática al " + telefono);
            System.out.println("Mensaje: " + mensaje);
        } else {
            System.out.println("El contacto de emergencia " + nombre + " está inactivo. No se puede notificar.");
        }
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getRelacion()
    {
        return relacion;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean estaActivo()
    {
        return activo;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public void setRelacion(String relacion)
    {
        this.relacion = relacion;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }
}