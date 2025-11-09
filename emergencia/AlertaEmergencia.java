package emergencia;

public class AlertaEmergencia {
        private String tipoCuentaDestino;
    private String mensaje;

    public AlertaEmergencia(String tipoCuentaDestino, String mensaje) {
        this.tipoCuentaDestino = tipoCuentaDestino;
        this.mensaje = mensaje;
    }

    public String generarAlerta() {
        return "Alerta enviada a " + tipoCuentaDestino + ": " + mensaje;
    }

    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    public String getMensaje() {
        return mensaje;
    }

}
