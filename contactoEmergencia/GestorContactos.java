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
