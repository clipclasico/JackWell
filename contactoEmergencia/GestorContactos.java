package contactoEmergencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<ContactoEmergencia> contactos;
    
public GestorContactos() {
        this.contactos = new ArrayList<>();
    }

    public void agregarContacto(ContactoEmergencia contacto) {
        this.contactos.add(contacto);
        System.out.println("Contacto agregado: " + contacto.getNombre());
    }

    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos de emergencia registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE CONTACTOS ---");
        for (ContactoEmergencia c : contactos) {
            System.out.println(c.obtenerInformacion());
            System.out.println("--------------------------");
        }
    }

    public ContactoEmergencia buscarContacto(String nombre) {
        for (ContactoEmergencia c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarContacto(String nombre) {
        ContactoEmergencia c = buscarContacto(nombre);
        if (c != null) {
            contactos.remove(c);
            System.out.println("Contacto eliminado: " + nombre);
            return true;
        } else {
            System.out.println("No se encontró el contacto: " + nombre);
            return false;
        }
    }
}
