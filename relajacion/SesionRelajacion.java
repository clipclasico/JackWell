public class SesionRelajacion {
    private EjercicioRespiracion ejercicio;
    private boolean activa;

    public SesionRelajacion(EjercicioRespiracion ejercicio) {
        this.ejercicio = ejercicio;
        this.activa = false;
    }

    public void iniciarSesion() {
        activa = true;
    }

    public void finalizarSesion() {
        activa = false;
    }

    public boolean estaActiva() {
        return activa;
    }

    public String obtenerEstadoSesion() {
        if (activa) {
            return "🧘 Sesión activa:\n" + ejercicio.obtenerDescripcion();
        } else {
            return "⚠️ No hay ninguna sesión activa.";
        }
    }
}