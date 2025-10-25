package contactoEmergencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<ContactoEmergencia> contactos;
    
public GestorContactos() {
        this.contactos = new ArrayList<>();
