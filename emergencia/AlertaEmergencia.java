<<<<<<< HEAD
    public class AlertaEmergencia {
=======
package emergencia;

public class AlertaEmergencia {
>>>>>>> 82073b4d4ae74ab1fe356869b42d5deea6c33743
        private String tipoCuentaDestino;
    private String mensaje;

    public AlertaEmergencia(String tipoCuentaDestino, String mensaje) {
        this.tipoCuentaDestino = tipoCuentaDestino;
        this.mensaje = mensaje;
    }

    public String generarAlerta() {
        return "🔔 Alerta enviada a " + tipoCuentaDestino + ": " + mensaje;
    }

    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    public String getMensaje() {
        return mensaje;
    }

}
