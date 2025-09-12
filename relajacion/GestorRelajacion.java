import registrar_horarios.Horario;
import registrar_horarios.Clase;
import tipos_de_cuentas.Estudiante;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class GestorRelajacion {

    public static EjercicioRespiracion sugerirEjercicioPorHorario(Estudiante estudiante, DayOfWeek dia, LocalTime hora) {
        Horario horario = estudiante.getHorario();
        Clase claseActual = horario.obtenerClaseActual(dia, hora);

        if (claseActual == null) {
            return new EjercicioRespiracion(
                "Respiración profunda", 5, "Inhala por 4 segundos, exhala por 6 segundos. Repite lentamente."
            );
        } else {
            return null;
        }
    }

    public static EjercicioRespiracion sugerirEjercicioPorEmocion(String emocion) {
        String emocionLower = emocion.toLowerCase();

        if (emocionLower.contains("ansiedad") || emocionLower.contains("estrés")) {
            return new EjercicioRespiracion(
                "Respiración guiada", 7, "Inhala lentamente, cuenta hasta 4, mantén el aire, exhala contando hasta 6."
            );
        } else if (emocionLower.contains("tristeza")) {
            return new EjercicioRespiracion(
                "Respiración reconfortante", 6, "Respira profundo y visualiza un lugar que te haga sentir paz."
            );
        } else {
            return new EjercicioRespiracion(
                "Respiración consciente", 4, "Respira normalmente, enfocándote en el aire que entra y sale."
            );
        }
    }
}