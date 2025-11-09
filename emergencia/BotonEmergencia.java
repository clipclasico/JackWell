package emergencia;

import java.io.Serializable;

public class BotonEmergencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private boolean activado;

    public BotonEmergencia() {
        this.activado = false;
    }

    public void activar() {
        this.activado = true;
    }

    public void desactivar() {
        this.activado = false;
    }

    public boolean estaActivado() {
        return activado;
    }

    public String obtenerEstado() {
        return activado ? "Botón activado" : "Botón desactivado";
    }
}
