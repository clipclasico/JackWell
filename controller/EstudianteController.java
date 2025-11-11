/// ----------------------------------------------
/// EstudianteController.java
/// Controlador para gestionar las interacciones del estudiante.
/// ----------------------------------------------

package controller;

import model.UsuarioModel;
import view.EstudianteView;
import tipos_de_cuentas.Estudiante;
import RegistrarEntradas.*;
import registrar_horarios.*;
import relajacion.*;
import emergencia.*;
import contactoEmergencia.ContactoEmergencia;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EstudianteController
{
    private Estudiante estudiante;
    private EstudianteView view;
    private UsuarioModel usuarioModel;

    public EstudianteController(Estudiante estudiante, EstudianteView view, UsuarioModel usuarioModel)
    {
        this.estudiante = estudiante;
        this.view = view;
        this.usuarioModel = usuarioModel;
    }

    public boolean manejarMenu()
    {
        int opcion = view.mostrarMenuEstudiante(estudiante.getNombre());

        switch (opcion)
        {
            case 1:
                gestionarDiarioEmocional();
                break;
            case 2:
                gestionarEjerciciosRelajacion();
                break;
            case 3:
                gestionarHorario();
                break;
            case 4:
                gestionarContactosEmergencia();
                break;
            case 5:
                activarBotonEmergencia();
                break;
            case 6:
                view.mostrarFraseDelDia();
                break;
            case 7:
                gestionarMascota();
                break;
            case 8:
                mostrarRacha();
                break;
            case 9:
                mostrarPerfil();
                break;
            case 10:
                view.mostrarFormularioDenuncias();
                break;
            case 0:
                usuarioModel.cerrarSesion();
                view.mostrarMensaje("Sesión cerrada.");
                view.esperarEnter();
                break;
            default:
                view.mostrarError("Opción inválida.");
        }

        return false;
    }

    private void gestionarDiarioEmocional()
    {
        DiarioEmocional diario = estudiante.getDiario();
        int opcion = view.mostrarMenuDiario();

        switch (opcion)
        {
            case 1:
                String animo = view.solicitarTexto("¿Cómo te sientes hoy? ");
                String pensamientos = view.solicitarTexto("Escribe tus pensamientos: ");
                
                diario.agregarEntrada(animo, pensamientos);
                estudiante.getRacha().actualizarRacha();
                estudiante.getMascota().actualizacionDiaria();
                
                EjercicioRespiracion ejercicio = GestorRelajacion.sugerirEjercicioPorEmocion(animo);
                if (ejercicio != null)
                {
                    view.mostrarMensaje("Ejercicio sugerido: " + ejercicio.obtenerDescripcion());
                }
                
                view.mostrarMensaje(estudiante.getRacha().obtenerProgresoProximaRecompensa());
                view.esperarEnter();
                break;

            case 2:
                diario.verEntradas();
                view.esperarEnter();
                break;

            case 3:
                diario.verEntradas();
                int id = view.solicitarEntero("ID de la entrada a eliminar: ");
                if (diario.eliminarEntrada(id))
                {
                    view.mostrarExito("Entrada eliminada.");
                } else {
                    view.mostrarError("No se encontró la entrada.");
                }
                view.esperarEnter();
                break;

            case 0:
                return;
        }
    }

    private void gestionarEjerciciosRelajacion()
    {
        int opcion = view.mostrarMenuRelajacion();

        switch (opcion)
        {
            case 1:
                String emocion = view.solicitarTexto("¿Cómo te sientes ahora? ");
                EjercicioRespiracion ejercicio = GestorRelajacion.sugerirEjercicioPorEmocion(emocion);
                view.mostrarMensaje(ejercicio.obtenerDescripcion());
                
                if (view.solicitarConfirmacion("¿Iniciar ejercicio?"))
                {
                    SesionRelajacion sesion = new SesionRelajacion(ejercicio);
                    sesion.iniciarSesion();
                    view.mostrarMensaje("\n🧘 Realizando ejercicio...");
                    esperarSegundos(3);
                    sesion.finalizarSesion();
                    view.mostrarExito("Ejercicio completado.");
                }
                view.esperarEnter();
                break;

            case 2:
                DayOfWeek dia = DayOfWeek.from(java.time.LocalDate.now());
                LocalTime hora = LocalTime.now();
                EjercicioRespiracion ejercicio2 = GestorRelajacion.sugerirEjercicioPorHorario(estudiante, dia, hora);
                
                if (ejercicio2 != null)
                {
                    view.mostrarMensaje(ejercicio2.obtenerDescripcion());
                } else {
                    view.mostrarMensaje("No hay ejercicios sugeridos ahora según tu horario.");
                }
                view.esperarEnter();
                break;

            case 3:
                EjercicioRespiracion rapido = new EjercicioRespiracion("Respiración rápida", 3, "Inhala profundamente por 3 segundos, exhala rápidamente. Repite.");
                view.mostrarMensaje(rapido.obtenerDescripcion());
                view.mostrarMensaje("Iniciando...");
                esperarSegundos(3);
                view.mostrarExito("Ejercicio completado.");
                view.esperarEnter();
                break;

            case 0:
                return;
        }
    }

    private void gestionarHorario()
    {
        Horario horario = estudiante.getHorario();
        int opcion = view.mostrarMenuHorario();

        switch (opcion)
        {
            case 1:
                agregarClase(horario);
                break;

            case 2:
                horario.mostrarHorarioCompleto();
                view.esperarEnter();
                break;

            case 3:
                DayOfWeek hoy = DayOfWeek.from(java.time.LocalDate.now());
                view.mostrarMensaje("\nClases de hoy (" + hoy + "):");
                for (Clase clase : horario.obtenerClasesPorDia(hoy))
                {
                    view.mostrarMensaje("  " + clase.obtenerInformacion());
                }
                view.esperarEnter();
                break;

            case 4:
                eliminarClase(horario);
                break;

            case 5:
                buscarClase(horario);
                break;

            case 0:
                return;
        }
    }

    private void agregarClase(Horario horario)
    {
        try {
            String materia = view.solicitarTexto("Nombre de la materia: ");
            String diaStr = view.solicitarTexto("Día (MONDAY, TUESDAY, etc.): ").toUpperCase();
            DayOfWeek dia = DayOfWeek.valueOf(diaStr);
            
            String horaInicio = view.solicitarTexto("Hora inicio (HH:MM): ");
            String horaFin = view.solicitarTexto("Hora fin (HH:MM): ");
            String edificio = view.solicitarTexto("Edificio: ");
            String aula = view.solicitarTexto("Aula: ");
            String catedratico = view.solicitarTexto("Catedrático: ");

            Clase nuevaClase = new Clase(materia, dia, LocalTime.parse(horaInicio), LocalTime.parse(horaFin), edificio, aula, catedratico);
            
            if (horario.agregarClase(nuevaClase))
            {
                view.mostrarExito("Clase agregada exitosamente.");
            } else {
                view.mostrarError("Conflicto de horario detectado.");
            }
        } catch (Exception e)
        {
            view.mostrarError("Error al agregar clase: " + e.getMessage());
        }
        view.esperarEnter();
    }

    private void eliminarClase(Horario horario)
    {
        String materia = view.solicitarTexto("Nombre de la materia: ");
        String diaStr = view.solicitarTexto("Día (MONDAY, TUESDAY, etc.): ").toUpperCase();
        
        try
        {
            DayOfWeek dia = DayOfWeek.valueOf(diaStr);
            if (horario.eliminarClase(materia, dia))
            {
                view.mostrarExito("Clase eliminada.");
            } else {
                view.mostrarError("No se encontró la clase.");
            }
        } catch (Exception e) {
            view.mostrarError("Día inválido.");
        }
        view.esperarEnter();
    }

    private void buscarClase(Horario horario)
    {
        String busqueda = view.solicitarTexto("Nombre de la materia a buscar: ");
        var encontradas = horario.buscarPorMateria(busqueda);

        if (encontradas.isEmpty()) {
            view.mostrarMensaje("No se encontraron clases.");
        } else {
            view.mostrarMensaje("Clases encontradas:");
            for (Clase clase : encontradas) {
                view.mostrarMensaje("  " + clase.obtenerInformacion());
            }
        }
        view.esperarEnter();
    }

    private void gestionarContactosEmergencia()
    {
        int opcion = view.mostrarMenuContactosEmergencia();

        switch (opcion)
        {
            case 1:
                mostrarContactos();
                break;
            case 2:
                agregarContacto();
                break;
            case 3:
                actualizarContacto();
                break;
            case 4:
                toggleContacto();
                break;
            case 0:
                return;
        }
    }

    private void mostrarContactos()
    {
        if (estudiante.getContactosEmergencia().isEmpty())
        {
            view.mostrarMensaje("No tienes contactos registrados.");
        } else {
            view.mostrarMensaje("\nTus contactos de emergencia:");
            for (int i = 0; i < estudiante.getContactosEmergencia().size(); i++)
            {
                view.mostrarMensaje("\nContacto #" + (i + 1));
                view.mostrarMensaje(estudiante.getContactosEmergencia().get(i).obtenerInformacion());
            }
        }
        view.esperarEnter();
    }

    private void agregarContacto()
    {
        if (estudiante.getContactosEmergencia().size() >= 5)
        {
            view.mostrarError("Límite de 5 contactos alcanzado.");
            view.esperarEnter();
            return;
        }

        String nombre = view.solicitarTexto("Nombre del contacto: ");
        String telefono = view.solicitarTexto("Teléfono: ");
        String relacion = view.solicitarTexto("Relación: ");
        String email = view.solicitarTexto("Email: ");

        ContactoEmergencia contacto = new ContactoEmergencia(nombre, telefono, relacion, email);
        estudiante.agregarContactoEmergencia(contacto);
        view.mostrarExito("Contacto agregado.");
        view.esperarEnter();
    }

    private void actualizarContacto()
    {
        if (estudiante.getContactosEmergencia().isEmpty())
        {
            view.mostrarError("No hay contactos para actualizar.");
            view.esperarEnter();
            return;
        }

        mostrarListaContactos();
        int indice = view.solicitarEntero("Número de contacto: ") - 1;

        if (indice >= 0 && indice < estudiante.getContactosEmergencia().size())
        {
            ContactoEmergencia contacto = estudiante.getContactosEmergencia().get(indice);
            String telefono = view.solicitarTexto("Nuevo teléfono (vacío = no cambiar): ");
            String email = view.solicitarTexto("Nuevo email (vacío = no cambiar): ");
            contacto.actualizarContacto(telefono, email);
            view.mostrarExito("Contacto actualizado.");
        } else {
            view.mostrarError("Índice inválido.");
        }
        view.esperarEnter();
    }

    private void toggleContacto()
    {
        if (estudiante.getContactosEmergencia().isEmpty())
        {
            view.mostrarError("No hay contactos.");
            view.esperarEnter();
            return;
        }

        mostrarListaContactos();
        int indice = view.solicitarEntero("Número de contacto: ") - 1;

        if (indice >= 0 && indice < estudiante.getContactosEmergencia().size())
        {
            ContactoEmergencia contacto = estudiante.getContactosEmergencia().get(indice);
            contacto.setActivo(!contacto.estaActivo());
            view.mostrarExito("Estado cambiado a: " + (contacto.estaActivo() ? "Activo" : "Inactivo"));
        } else {
            view.mostrarError("Índice inválido.");
        }
        view.esperarEnter();
    }

    private void mostrarListaContactos()
    {
        for (int i = 0; i < estudiante.getContactosEmergencia().size(); i++)
        {
            ContactoEmergencia c = estudiante.getContactosEmergencia().get(i);
            view.mostrarMensaje((i + 1) + ". " + c.getNombre() + " (" + (c.estaActivo() ? "Activo" : "Inactivo") + ")");
        }
    }

    private void activarBotonEmergencia()
    {
        view.mostrarTitulo("Activar Botón de Emergencia");
        view.mostrarAdvertencia("Esto notificará a tus contactos de emergencia.");

        if (!view.solicitarConfirmacion("¿Activar emergencia?"))
        {
            view.mostrarMensaje("Emergencia cancelada.");
            view.esperarEnter();
            return;
        }

        BotonEmergencia boton = new BotonEmergencia();
        boton.activar();
        view.mostrarMensaje(boton.obtenerEstado());
        view.mostrarMensaje("Enviando alertas...");
        esperarSegundos(1);

        if (!estudiante.getContactosEmergencia().isEmpty())
        {
            view.mostrarMensaje("Notificando contactos de emergencia...");
            for (ContactoEmergencia contacto : estudiante.getContactosEmergencia())
            {
                contacto.notificarEmergencia("Alerta: " + estudiante.getNombre() + " necesita ayuda.");
            }
        } else {
            view.mostrarAdvertencia("No tienes contactos de emergencia registrados.");
        }

        String estado = view.solicitarTexto("\n¿Cómo te sientes? ");
        AlertaEmergencia alerta = GestorEmergencia.generarAlertaPorEstado(estudiante, estado);
        view.mostrarMensaje(alerta.generarAlerta());
        view.mostrarMensaje("\nAyuda en camino. Mantente seguro.");
        
        boton.desactivar();
        view.esperarEnter();
    }

    private void gestionarMascota()
    {
        int opcion = view.mostrarMenuMascota(estudiante.getMascota());

        switch (opcion)
        {
            case 1:
                estudiante.getMascota().interactuar();
                break;
            case 2:
                estudiante.getMascota().alimentar();
                break;
            case 3:
                estudiante.getMascota().jugar();
                break;
            case 4:
                estudiante.getMascota().descansar();
                break;
            case 5:
                estudiante.getMascota().entrenar();
                break;
            case 6:
                agregarAccesorio();
                break;
            case 7:
                String nombre = view.solicitarTexto("Nuevo nombre: ");
                estudiante.getMascota().setNombre(nombre);
                view.mostrarExito("Nombre actualizado a " + nombre);
                break;
            case 0:
                return;
        }
        view.esperarEnter();
    }

    private void agregarAccesorio()
    {
        view.mostrarMensaje("\nAccesorios disponibles:");
        String[] accesorios =
        {
            "Collar Dorado", "Gorra Cool", "Bufanda de Invierno",
            "Gafas de Sol", "Capa de Superhéroe", "Corona Real"
        };
        
        for (int i = 0; i < accesorios.length; i++)
        {
            view.mostrarMensaje((i + 1) + ". " + accesorios[i]);
        }

        int opcion = view.solicitarEntero("Selecciona un accesorio: ") - 1;
        if (opcion >= 0 && opcion < accesorios.length)
        {
            estudiante.getMascota().agregarAccesorio(accesorios[opcion]);
        } else {
            view.mostrarError("Accesorio inválido.");
        }
    }

    private void mostrarRacha()
    {
        var racha = estudiante.getRacha();
        racha.mostrarEstadisticas();
        view.mostrarMensaje("\n" + racha.obtenerProgresoProximaRecompensa());

        int opcion = view.mostrarMenuRacha();
        if (opcion == 1)
        {
            racha.mostrarRecompensas();
        } else if (opcion == 2) {
            view.mostrarMensaje("\nRecompensas obtenidas:");
            if (racha.getRecompensasObtenidas().isEmpty())
            {
                view.mostrarMensaje("Aún no has obtenido recompensas.");
            } else {
                for (var r: racha.getRecompensasObtenidas())
                {
                    view.mostrarMensaje("  " + r.getIcono() + " " + r.getNombre());
                }
            }
        }
        view.esperarEnter();
    }

    private void mostrarPerfil()
    {
        view.mostrarTitulo("Mi Perfil - Estudiante");
        view.mostrarMensaje("ID: " + estudiante.getId());
        view.mostrarMensaje("Nombre: " + estudiante.getNombre());
        view.mostrarMensaje("Correo: " + estudiante.getCorreo());
        view.mostrarMensaje("Teléfono: " + estudiante.getTelefono());
        view.esperarEnter();
    }

    private void esperarSegundos(int segundos)
    {
        try
        {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}