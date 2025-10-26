import tipos_de_cuentas.*;
import login.SistemaLogin;
import frase.FraseDia;
import formulario.FormularioDenuncias;
import contactoEmergencia.ContactoEmergencia;
import RegistrarEntradas.*;
import registrar_horarios.*;
import relajacion.*;
import emergencia.*;
import extras.MascotaVirtual;
import extras.Racha;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Scanner;

public class JackWellApp
{
    private static SistemaLogin sistema;
    private static Scanner scanner;
    private static FraseDia fraseDia;

    public static void main(String[] args)
    {
        sistema = new SistemaLogin();
        scanner = new Scanner(System.in);
        fraseDia = new FraseDia();

        mostrarBienvenida();

        int opcion = -1;

        while(opcion != 0)
        {
            if (!sistema.haySesionActiva())
            {
                opcion = menuPrincipal();
            }
            else
            {
                opcion = menuUsuario();
            }
        }

        scanner.close();
        System.out.println("Gracias por usar JackWell. ¡Hasta luego!");
    }


    private static void mostrarBienvenida()
    {
        System.out.println("===================================");
        System.out.println("   Bienvenido a JackWell App");
        System.out.println("   ¡Bienestar que trasciende!");
        System.out.println("===================================");
    }

    private static int menuPrincipal()
    {
        System.out.println("\nMenú Principal");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar Usuario");
        System.out.println("3. Ver frase del día");
        System.out.println("4. Formulario de denuncias");
        System.out.println("5. Ver usuarios (demo)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                fraseDia.mostrarFraseDelDia();
                presionarEnter();
                break;
            case 4:
                FormularioDenuncias.mostrarInformacion();
                presionarEnter();
                break;
            case 5:
                sistema.listarUsuarios();
                presionarEnter();
                break;
            case 0:
                return 0;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                break;
        }

        return -1;
    }

    private static void iniciarSesion()
    {
        System.out.println("\nIniciar Sesión");
        System.out.print("Correo: ");
        String correo = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine().trim();

        boolean exito = sistema.iniciarSesion(correo, contrasena);

        if (exito)
        {
            System.out.println("¡Sesión iniciada con éxito!");
            presionarEnter();
        }
        else
        {
            System.out.println("Error al iniciar sesión. Verifique sus credenciales.");
            presionarEnter();
        }
    }

    private static void registrarUsuario()
    {
        System.out.println("\nRegistrar Usuario");
        
        System.out.println("Tipo de cuenta: ");
        System.out.println("1. Estudiante");
        System.out.println("2. Padre");
        System.out.println("3. Catedrático");
        System.out.print("Seleccione una opción: ");
        int tipoNum = leerEntero();
        scanner.nextLine();

        TipoCuenta tipo;
        switch (tipoNum)
        {
            case 1:
                tipo = TipoCuenta.ESTUDIANTE;
                break;
            case 2:
                tipo = TipoCuenta.PADRE;
                break;
            case 3:
                tipo = TipoCuenta.CATEDRATICO;
                break;
            default:
                System.out.println("Tipo de cuenta inválido.");
                return;
        }

        System.out.print("ID/Carnet: ");
        String id = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        String datosAdicionales = null;
        if (tipo == TipoCuenta.PADRE)
        {
            System.out.print("ID del estudiante vinculado: ");
            datosAdicionales = scanner.nextLine().trim();
        }

        boolean registrado = sistema.registrarUsuario(tipo, id, nombre, correo, contrasena, telefono, datosAdicionales);

        if (registrado)
        {
            System.out.println("¡Usuario registrado con éxito!");
            presionarEnter();
        }
        else
        {
            System.out.println("Error al registrar usuario.");
            presionarEnter();
        }
    }

    private static int menuUsuario()
    {
        Usuario usuario = sistema.getUsuarioActual();
        System.out.println("===============================");
        System.out.println("Menú de Usuario - " + usuario.getNombre());
        System.out.println("===============================\n");

        if (usuario instanceof Estudiante)
        {
            return menuEstudiante((Estudiante) usuario);
        }
        else if (usuario instanceof Catedratico)
        {
            return menuCatedratico((Catedratico) usuario);
        }
        else if (usuario instanceof Padre)
        {
            return menuPadre((Padre) usuario);
        }

        return -1;
    }

    private static int menuEstudiante(Estudiante estudiante)
    {
        System.out.println("1. Mi diario emocional");
        System.out.println("2. Ejercicios de relajación");
        System.out.println("3. Mi horario");
        System.out.println("4. Contactos de emergencia");
        System.out.println("5. Botón de emergencia");
        System.out.println("6. Frase del día");
        System.out.println("7. Mi mascota virtual");
        System.out.println("8. Mi racha");
        System.out.println("9. Mi perfil");
        System.out.println("10. Formulario de denuncias");
        System.out.println("0. Cerrar sesión");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                gestionarDiarioEmocional(estudiante);
                break;
            case 2:
                gestionarEjerciciosRelajacion(estudiante);
                break;
            case 3:
                gestionarHorario(estudiante);
                break;
            case 4:
                gestionarContactosEmergencia(estudiante);
                break;
            case 5:
                activarBotonEmergencia(estudiante);
                break;
            case 6:
                fraseDia.mostrarFraseDelDia();
                presionarEnter();
                break;
            case 7:
                gestionarMascota(estudiante);
                break;
            case 8:
                mostrarRacha(estudiante);
                break;
            case 9:
                sistema.mostrarInfoUsuario();
                presionarEnter();
                break;
            case 10:
                FormularioDenuncias.mostrarInformacion();
                presionarEnter();
                break;
            case 0:
                sistema.cerrarSesion();
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return -1;
    }   

    private static int menuCatedratico(Catedratico catedratico)
    {
        System.out.println("1. Mis materias");
        System.out.println("2. Configurar alertas");
        System.out.println("3. Formulario de denuncias");
        System.out.println("4. Frase del día");
        System.out.println("5. Mi perfil");
        System.out.println("0. Cerrar sesión");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                gestionarMaterias(catedratico);
                break;
            case 2:
                configurarAlertas(catedratico);
                break;
            case 3:
                FormularioDenuncias.mostrarInformacion();
                presionarEnter();
                break;
            case 4:
                fraseDia.mostrarFraseDelDia();
                presionarEnter();
                break;
            case 5:
                sistema.mostrarInfoUsuario();
                presionarEnter();
                break;
            case 0:
                sistema.cerrarSesion();
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return -1;
    }

    private static int menuPadre(Padre padre)
    {
        System.out.println("1. Ver estudiante vinculado");
        System.out.println("2. Configurar notificaciones");
        System.out.println("3. Formulario de denuncias");
        System.out.println("4. Frase del día");
        System.out.println("5. Mi perfil");
        System.out.println("0. Cerrar sesión");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                verEstudianteVinculado(padre);
                break;
            case 2:
                configurarNotificacionesPadre(padre);
                break;
            case 3:
                FormularioDenuncias.mostrarInformacion();
                presionarEnter();
                break;
            case 4:
                fraseDia.mostrarFraseDelDia();
                presionarEnter();
                break;
            case 5:
                sistema.mostrarInfoUsuario();
                presionarEnter();
                break;
            case 0:
                sistema.cerrarSesion();
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return -1;
    }

    private static void gestionarDiarioEmocional(Estudiante estudiante)
    {
        DiarioEmocional diario = estudiante.getDiario();
        System.out.println("\n--- Diario Emocional ---");
        System.out.println("1. Registrar nueva entrada");
        System.out.println("2. Ver todas mis entradas");
        System.out.println("3. Eliminar entrada");
        System.out.println("0. Volver al menú principal");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.print("¿Cómo te sientes hoy? ");
                String animo = scanner.nextLine();
                System.out.print("Escribe tus pensamientos o lo que te pasó hoy: ");
                String pensamientos = scanner.nextLine();

                diario.agregarEntrada(animo, pensamientos);
                estudiante.getRacha().actualizarRacha();
                estudiante.getMascota().actualizacionDiaria();
                EjercicioRespiracion ejercicio = GestorRelajacion.sugerirEjercicioPorEmocion(animo);
                if (ejercicio != null)
                {
                    System.out.println("Se ha sugerido un ejercicio de relajación para ti: " + ejercicio.obtenerDescripcion());
                }

                System.out.println(estudiante.getRacha().obtenerProgresoProximaRecompensa());
                presionarEnter();
                gestionarDiarioEmocional(estudiante);
                break;

            case 2:
                diario.verEntradas();
                presionarEnter();
                gestionarDiarioEmocional(estudiante);
                break;

            case 3:
                diario.verEntradas();
                System.out.print("Ingresar el ID de la entrada que quieres eliminar: ");
                int idParaEliminar = leerEntero();
                scanner.nextLine();
                if (diario.eliminarEntrada(idParaEliminar))
                    {
                    System.out.println("Entrada #" + idParaEliminar + " eliminada.");
                }
                else
                {
                    System.out.println("No se encontró una entrada con ese ID.");
                }
                presionarEnter();
                gestionarDiarioEmocional(estudiante);
                break;
                    
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                gestionarDiarioEmocional(estudiante);
            
        }
    }

    private static void gestionarEjerciciosRelajacion(Estudiante estudiante)
    {
        System.out.println("Ejercicios de relajación");
        System.out.println("1. Ejercicio por estado emocional");
        System.out.println("2. Ejercicio según mi horario");
        System.out.println("3. Respiración profunda (rápida)");
        System.out.println("0. Volver al menú principal");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.print("¿Cómo te sientes ahora? ");
                String emocion = scanner.nextLine();
                EjercicioRespiracion ejercicio1 = GestorRelajacion.sugerirEjercicioPorEmocion(emocion);
                System.out.println("Ejercicio sugerido: " + ejercicio1.obtenerDescripcion());

                System.out.println("¿Deseas iniciar el ejercicio ahora? (s/n)");
                if (scanner.nextLine().equalsIgnoreCase("s"))
                {
                    SesionRelajacion sesion = new SesionRelajacion(ejercicio1);
                    sesion.iniciarSesion();
                    System.out.println(sesion.obtenerEstadoSesion());
                    System.out.println("\n Realizando ejercicio...");
                    try 
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) 
                    {}

                    sesion.finalizarSesion();
                    System.out.println("Ejercicio completado.");

                }
                presionarEnter();
                gestionarEjerciciosRelajacion(estudiante);
                break;

            case 2:
                DayOfWeek diaActual = DayOfWeek.from(java.time.LocalDate.now());
                LocalTime horaActual = LocalTime.now();
                EjercicioRespiracion ejercicio2 = GestorRelajacion.sugerirEjercicioPorHorario(estudiante, diaActual, horaActual);
                if (ejercicio2 != null)
                {
                    System.out.println(ejercicio2.obtenerDescripcion());
                }
                else
                {
                    System.out.println("No hay ejercicios sugeridos en este momento según tu horario.");
                }
                presionarEnter();
                gestionarEjerciciosRelajacion(estudiante);
                break;
            
            case 3:
                EjercicioRespiracion ejercicio3 = new EjercicioRespiracion(
                    "Respiración rápida", 3, "Inhala profundamente por 3 segundos, exhala rápidamente. Repite."
                );
                System.out.println(ejercicio3.obtenerDescripcion());
                System.out.println("Iniciando...");
                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {}
                System.out.println("Ejercicio completado.");
                presionarEnter();
                gestionarEjerciciosRelajacion(estudiante);
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                gestionarEjerciciosRelajacion(estudiante);
        }
    }

    private static void gestionarHorario(Estudiante estudiante)
    {
        Horario horario = estudiante.getHorario();

        System.out.println("\n--- Mi Horario ---");
        System.out.println("1. Agregar clase");
        System.out.println("2. Ver horario completo");
        System.out.println("3. Ver clases de hoy");
        System.out.println("4. Eliminar clase");
        System.out.println("5. Buscar clase por materia");
        System.out.println("0. Volver al menú principal");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.print("Nombre de la materia: ");
                String materia = scanner.nextLine();

                System.out.println("Dia de la semana (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY): ");
                String diaStr = scanner.nextLine().toUpperCase();
                DayOfWeek dia;
                try
                {
                    dia = DayOfWeek.valueOf(diaStr);
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Día inválido.");
                    presionarEnter();
                    gestionarHorario(estudiante);
                    return;
                }

                System.out.print("Hora de inicio (HH:MM): ");
                String horaInicio = scanner.nextLine();
                System.out.print("Hora de fin (HH:MM): ");
                String horaFin = scanner.nextLine();
                System.out.print("Edificio: ");
                String edificio = scanner.nextLine();
                System.out.print("Aula: ");
                String aula = scanner.nextLine();
                System.out.print("Catedrático: ");
                String catedratico = scanner.nextLine();

                try
                {
                    Clase nuevaClase = new Clase(materia, dia, LocalTime.parse(horaInicio), LocalTime.parse(horaFin), edificio, aula, catedratico);
                    if (horario.agregarClase(nuevaClase))
                    {
                        System.out.println("Clase agregada exitosamente.");
                    }
                } catch (Exception e)
                {
                    System.out.println("Error al agregar clase. Puede que haya un conflicto de horario.");
                }
                presionarEnter();
                gestionarHorario(estudiante);
                break;
            
            case 2:
                horario.mostrarHorarioCompleto();
                presionarEnter();
                gestionarHorario(estudiante);
                break;

            case 3:

                DayOfWeek hoy = DayOfWeek.from(java.time.LocalDate.now());
                System.out.println("\n Clases de hoy (" + hoy + "):");
                for (Clase clase : horario.obtenerClasesPorDia(hoy))
                {
                    System.out.println(clase.obtenerInformacion());
                }
                presionarEnter();
                gestionarHorario(estudiante);
                break;

            case 4:
                System.out.print("Ingrese el nombre de la materia a eliminar: ");
                String materiaEliminar = scanner.nextLine();
                System.out.print("Día (MONDAY, TUESDAY, etc.): ");
                String diaEliminarStr = scanner.nextLine().toUpperCase();

                try
                {
                    DayOfWeek diaEliminar = DayOfWeek.valueOf(diaEliminarStr);
                    if (horario.eliminarClase(materiaEliminar, diaEliminar))
                    {
                        System.out.println("Clase eliminada exitosamente.");
                    }
                    else
                    {
                        System.out.println("No se encontró la clase especificada.");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Día inválido.");
                }
                presionarEnter();
                gestionarHorario(estudiante);
                break;
            
            case 5:
                System.out.print("Ingrese el nombre de la materia a buscar: ");
                String busqueda = scanner.nextLine();
                var encontradas = horario.buscarPorMateria(busqueda);

                if (encontradas.isEmpty())
                {
                    System.out.println("No se encontraron clases con ese nombre.");
                }
                else
                {
                    System.out.println("Clases encontradas:");
                    for (Clase clase : encontradas)
                    {
                        System.out.println(clase.obtenerInformacion());
                    }
                }
                presionarEnter();
                gestionarHorario(estudiante);
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                gestionarHorario(estudiante);
        }
    }

    private static void gestionarContactosEmergencia(Estudiante estudiante)
    {
        System.out.println("\n--- Contactos de Emergencia ---");
        System.out.println("1. Ver mis contactos");
        System.out.println("2. Agregar contacto");
        System.out.println("3. Actualizar contacto");
        System.out.println("4. Activar/Desactivar contacto");
        System.out.println("0. Volver al menú principal");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch(opcion)
        {
            case 1:
                if (estudiante.getContactosEmergencia().isEmpty())
                {
                    System.out.println("No tienes contactos de emergencia registrados.");
                }
                else
                {
                    System.out.println("Tus contactos de emergencia:");
                    for (int i = 0; i < estudiante.getContactosEmergencia().size(); i++)
                    {
                        System.out.println("\nContacto #" + (i + 1));
                        System.out.println(estudiante.getContactosEmergencia().get(i).obtenerInformacion());
                    }
                }
                presionarEnter();
                gestionarContactosEmergencia(estudiante);
                break;

            case 2:
                if (estudiante.getContactosEmergencia().size() >= 5)
                {
                    System.out.println("Has alcanzado el límite de 5 contactos de emergencia.");
                    presionarEnter();
                    gestionarContactosEmergencia(estudiante);
                    break;
                }

                System.out.print("Nombre del contacto: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                System.out.print("Relación: ");
                String relacion = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();

                ContactoEmergencia contacto = new ContactoEmergencia(nombre, telefono, relacion, email);
                estudiante.agregarContactoEmergencia(contacto);
                System.out.println("Contacto agregado exitosamente.");
                presionarEnter();
                gestionarContactosEmergencia(estudiante);
                break;

            case 3:
                if (estudiante.getContactosEmergencia().isEmpty())
                {
                    System.out.println("No tienes contactos de emergencia para actualizar.");
                    presionarEnter();
                    gestionarContactosEmergencia(estudiante);
                    break;
                }

                System.out.println("Contactos:");
                for (int i = 0; i < estudiante.getContactosEmergencia().size(); i++)
                {
                    System.out.println((i + 1) + ". " + estudiante.getContactosEmergencia().get(i).getNombre());
                }
                System.out.print("Seleccione el número del contacto a actualizar: ");
                int indice = leerEntero() - 1;
                scanner.nextLine();

                if (indice >= 0 && indice < estudiante.getContactosEmergencia().size())
                {
                    ContactoEmergencia cont = estudiante.getContactosEmergencia().get(indice);
                    System.out.print("Nuevo teléfono(deje vacío para no cambiar): ");
                    String nuevoTelefono = scanner.nextLine();
                    System.out.print("Nuevo email(deje vacío para no cambiar): ");
                    String nuevoEmail = scanner.nextLine();
                    cont.actualizarContacto(nuevoTelefono, nuevoEmail);
                    System.out.println("Contacto actualizado exitosamente.");
                }
                else
                {
                    System.out.println("Índice inválido.");
                }
                presionarEnter();
                gestionarContactosEmergencia(estudiante);
                break;

            case 4:
                if (estudiante.getContactosEmergencia().isEmpty())
                {
                    System.out.println("No tienes contactos de emergencia para activar/desactivar.");
                    presionarEnter();
                    gestionarContactosEmergencia(estudiante);
                    break;
                }

                System.out.println("Contactos:");
                for (int i = 0; i < estudiante.getContactosEmergencia().size(); i++)
                {
                    ContactoEmergencia c = estudiante.getContactosEmergencia().get(i);
                    System.out.println((i + 1) + ". " + c.getNombre() + " (Estado: " + (c.estaActivo() ? "Activo" : "Inactivo") + ")");
                }
                System.out.print("Seleccione el número del contacto a activar/desactivar: ");
                int idx = leerEntero() - 1;
                scanner.nextLine();

                if (idx >= 0 && idx < estudiante.getContactosEmergencia().size())
                {
                    ContactoEmergencia cont = estudiante.getContactosEmergencia().get(idx);
                    cont.setActivo(!cont.estaActivo());
                    System.out.println("Contacto " + (cont.estaActivo() ? "activado" : "desactivado") + " exitosamente.");
                }
                else
                {
                    System.out.println("Índice inválido.");
                }

                presionarEnter();
                gestionarContactosEmergencia(estudiante);
                break;
            
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                gestionarContactosEmergencia(estudiante);
        }
    }

    private static void activarBotonEmergencia(Estudiante estudiante)
    {
        System.out.println("\n--- Botón de Emergencia ---");
        
        System.out.println("Este botón notificará a tus contactos de emergencia.");

        System.out.print("¿Estás seguro de activar la emergencia? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("s"))
        {
            BotonEmergencia boton = new BotonEmergencia();
            boton.activar();


            System.out.println(boton.obtenerEstado());

            System.out.println("Enviando alertas...");

            try 
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) 
            {}

            if (!estudiante.getContactosEmergencia().isEmpty())
            {
                System.out.println("Notificando a tus contactos de emergencia...");
                AlertaEmergencia alerta = GestorEmergencia.generarAlertaPorEstado(estudiante, "botón de ayuda");
                for (ContactoEmergencia contacto : estudiante.getContactosEmergencia())
                {
                    contacto.notificarEmergencia("Alerta: " + estudiante.getNombre() + " necesita ayuda inmediata.");
                }
            }
            else
            {
                System.out.println("No tienes contactos de emergencia registrados.");
            }

            System.out.println("Emergencia gestionada.");
            System.out.print("¿Cómo te sientes?");
            String estadoEmocional = scanner.nextLine();
            AlertaEmergencia alerta = GestorEmergencia.generarAlertaPorEstado(estudiante, estadoEmocional);
            System.out.println("Alerta generada: " + alerta.generarAlerta());

            System.out.println("Ayuda en camino. Mantente seguro.");
            boton.desactivar();
        }
        else
        {
            System.out.println("Emergencia cancelada.");
        }
        presionarEnter();
    }

    private static void gestionarMascota(Estudiante estudiante)
{
    MascotaVirtual mascota = estudiante.getMascota();
    
    System.out.println(mascota.obtenerEstadoCompleto());
    System.out.println(mascota.obtenerEmojiEstado() + " Estado actual de " + mascota.getNombre());
    
    System.out.println("\n1. Interactuar con " + mascota.getNombre());
    System.out.println("2. Alimentar");
    System.out.println("3. Jugar");
    System.out.println("4. Descansar");
    System.out.println("5. Entrenar");
    System.out.println("6. Agregar accesorio");
    System.out.println("7. Cambiar nombre");
    System.out.println("0. Volver");
    System.out.print("Seleccione una opción: ");
    
    int opcion = leerEntero();
    scanner.nextLine();
    
    switch (opcion)
    {
        case 1:
            mascota.interactuar();
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 2:
            mascota.alimentar();
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 3:
            mascota.jugar();
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 4:
            mascota.descansar();
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 5:
            mascota.entrenar();
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 6:
            System.out.println("\n🎁 ACCESORIOS DISPONIBLES:");
            System.out.println("1. Collar Dorado");
            System.out.println("2. Gorra Cool");
            System.out.println("3. Bufanda de Invierno");
            System.out.println("4. Gafas de Sol");
            System.out.println("5. Capa de Superhéroe");
            System.out.println("6. Corona Real");
            System.out.print("Seleccione un accesorio: ");
            
            int acc = leerEntero();
            scanner.nextLine();
            
            String accesorio = "";
            switch (acc)
            {
                case 1: accesorio = "Collar Dorado"; break;
                case 2: accesorio = "Gorra Cool"; break;
                case 3: accesorio = "Bufanda de Invierno"; break;
                case 4: accesorio = "Gafas de Sol"; break;
                case 5: accesorio = "Capa de Superhéroe"; break;
                case 6: accesorio = "Corona Real"; break;
                default:
                    System.out.println("Accesorio inválido.");
                    presionarEnter();
                    gestionarMascota(estudiante);
                    return;
            }
            
            mascota.agregarAccesorio(accesorio);
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 7:
            System.out.print("\nNuevo nombre para tu mascota: ");
            String nuevoNombre = scanner.nextLine();
            mascota.setNombre(nuevoNombre);
            System.out.println("¡Nombre actualizado a " + nuevoNombre + "!");
            presionarEnter();
            gestionarMascota(estudiante);
            break;
            
        case 0:
            break;
            
        default:
            System.out.println("Opción inválida.");
            gestionarMascota(estudiante);
    }
}

    private static void mostrarRacha(Estudiante estudiante)
{
    Racha racha = estudiante.getRacha();
    
    racha.mostrarEstadisticas();
    
    System.out.println("\n" + racha.obtenerProgresoProximaRecompensa());
    
    System.out.println("\n1. Ver todas las recompensas");
    System.out.println("2. Ver recompensas obtenidas");
    System.out.println("0. Volver");
    System.out.print("Seleccione una opción: ");
    
    int opcion = leerEntero();
    scanner.nextLine();
    
    switch (opcion)
    {
        case 1:
            racha.mostrarRecompensas();
            presionarEnter();
            mostrarRacha(estudiante);
            break;
            
        case 2:
            System.out.println("RECOMPENSAS OBTENIDAS");
            
            if (racha.getRecompensasObtenidas().isEmpty())
            {
                System.out.println("Aún no has obtenido recompensas");
                System.out.println("¡Sigue usando tu diario!");
            }
            else
            {
                for (Racha.Recompensa r : racha.getRecompensasObtenidas())
                {
                    System.out.println(r.getIcono() + " " + r.getNombre());
                }
            }
            
            presionarEnter();
            mostrarRacha(estudiante);
            break;
            
        case 0:
            break;
            
        default:
            System.out.println("Opción inválida.");
            mostrarRacha(estudiante);
    }
}

    // ========================== FUNCIONALIDADES CATEDRÁTICO ==========================

    private static void gestionarMaterias(Catedratico catedratico)
    {
        System.out.println("\n--- Mis Materias ---");
        System.out.println("1. Ver mis materias");
        System.out.println("2. Agregar materia");
        System.out.println("0. Volver al menú principal");

        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                if (catedratico.getMaterias().isEmpty())
                {
                    System.out.println("No tienes materias asignadas.");
                }
                else
                {
                    System.out.println("Mis materias:");
                    for (int i = 0; i < catedratico.getMaterias().size(); i++)
                    {
                        String materia = catedratico.getMaterias().get(i);
                        System.out.println((i + 1) + ". " + catedratico.getMaterias().get(i));
                    }
                }
                presionarEnter();
                gestionarMaterias(catedratico);
                break;

            case 2:
                System.out.print("Nombre de la materia: ");
                String materia = scanner.nextLine();
                catedratico.agregarMateria(materia);
                System.out.println("Materia agregada exitosamente.");
                presionarEnter();
                gestionarMaterias(catedratico);
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                gestionarMaterias(catedratico);
        }
    }

    private static void configurarAlertas(Catedratico catedratico)
    {
        System.out.println("\n--- Configurar Alertas ---");
        System.out.println("Estado actual: " + (catedratico.isPuedeRecibirAlertas() ? "Activado" : "Desactivado"));

        System.out.println("1. Activar alertas");
        System.out.println("2. Desactivar alertas");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.println("Alertas activadas. Recibiras notificaciones de emergencias.");
                catedratico.setPuedeRecibirAlertas(true);
                presionarEnter();
                configurarAlertas(catedratico);
                break;

            case 2:
                System.out.println("Alertas desactivadas. Ya no recibirás notificaciones de emergencias.");
                catedratico.setPuedeRecibirAlertas(false);
                presionarEnter();
                configurarAlertas(catedratico);
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                configurarAlertas(catedratico);
        }
    }

// ========================== FUNCIONALIDADES PADRE ==========================

    private static void verEstudianteVinculado(Padre padre)
    {
        System.out.println("\n--- Estudiante Vinculado ---");

        Estudiante estudiante = padre.getEstudianteVinculado();
        if (estudiante == null)
        {
            System.out.println("No se ha vinculado ningún estudiante a esta cuenta.");
            System.out.println("ID del estudiante a vincular: " + padre.getIdEstudianteVinculado());
            presionarEnter();
            return;
        }

        System.out.println("Nombre: " + padre.getEstudianteVinculado().getNombre());

        System.out.println("Información disponible");
        System.out.println("Estado general: Activo en la plataforma");
        System.out.println("Última actividad: Hoy");
        System.out.println("Racha de uso: Información protegida por privacidad");

        System.out.println("\nNota importante:");
        System.out.println("Para proteger la privacidad de tu hijo, solo se muestra información general.");
        System.out.println("Si tienes preocupaciones específicas, considera hablar directamente con él o ella.");

        System.out.println("\nEn caso de emergencia:");
        System.out.println("Si tu hijo/a activa el botón de emergencia, serás notificado inmediatamente.");
        presionarEnter();
    }

    private static void configurarNotificacionesPadre(Padre padre)
    {
        System.out.println("\n--- Configurar Notificaciones ---");
        System.out.println("Estado actual: " + (padre.isPuedeRecibirNotificaciones() ? "Activado" : "Desactivado"));

        System.out.println("\nTipos de notificaciones:");
        System.out.println("- Emergencias activadas por tu hijo/a");
        System.out.println("- Alertas de seguridad del campus");

        System.out.println("1. Activar notificaciones");
        System.out.println("2. Desactivar notificaciones");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();
        scanner.nextLine();

        switch (opcion)
        {
            case 1:
                padre.configurarNotificaciones(true);
                System.out.println("Notificaciones activadas. Recibirás alertas sobre la actividad de tu hijo/a.");
                presionarEnter();
                configurarNotificacionesPadre(padre);
                break;

            case 2:
                padre.configurarNotificaciones(false);
                System.out.println("Notificaciones desactivadas. No recibirás alertas sobre la actividad de tu hijo/a.");
                presionarEnter();
                configurarNotificacionesPadre(padre);
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                configurarNotificacionesPadre(padre);
        }
    }

// ========================== UTILIDADES ==========================


    private static void mostrarFraseDelDia()
    {
        frase.FraseDia frase = new frase.FraseDia();
        System.out.println("\n" + frase.obtenerFraseDelDia() + "\n");
    }

    private static int leerEntero()
    {
        while (!scanner.hasNextInt())
        {
            System.out.print("Entrada inválida. Por favor ingrese un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void presionarEnter()
    {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
}