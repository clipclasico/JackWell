
import tipos_de_cuentas.Estudiante;

public class GestorEmergencia {

    public static AlertaEmergencia generarAlertaPorEstado(Estudiante estudiante, String estadoEmocional) {
        String tipoCuenta = estudiante.getTipoCuenta().toString();
        String mensaje;

        switch (estadoEmocional.toLowerCase()) {
            case "ansiedad":
            case "estrés":
                mensaje = "El estudiante presenta signos de ansiedad. Se recomienda contacto inmediato.";
                break;
            case "tristeza":
                mensaje = "El estudiante se siente triste. Se sugiere seguimiento emocional.";
                break;
            default:
                mensaje = "El estudiante ha activado el botón de ayuda.";
        }

        return new AlertaEmergencia(tipoCuenta, mensaje);
    }
