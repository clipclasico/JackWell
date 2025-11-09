package view;

import extras.MascotaVirtual;
import frase.FraseDia;
import formulario.FormularioDenuncias;

public class EstudianteView extends MainView
{
    private FraseDia fraseDia;

    public EstudianteView()
    {
        super();
        this.fraseDia = new FraseDia();
    }

    public int mostrarMenuEstudiante(String nombre)
    {
        mostrarTitulo("Menú Estudiante - " + nombre);
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
        mostrarSeparador();

        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuDiario()
    {
        mostrarTitulo("Diario Emocional");
        System.out.println("1. Registrar nueva entrada");
        System.out.println("2. Ver todas mis entradas");
        System.out.println("3. Eliminar entrada");
        System.out.println("0. Volver");
        mostrarSeparador();

        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuRelajacion()
    {
        mostrarTitulo("Ejercicios de relajación");
        System.out.println("1. Ejercicio por estado emocional");
        System.out.println("2. Ejercicio según mi horario");
        System.out.println("3. Respiración rápida (3 min)");
        System.out.println("0. Volver");
        mostrarSeparador();
        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuHorario()
    {
        mostrarTitulo("Mi horario");
        System.out.println("1. Agregar clase");
        System.out.println("2. Ver horario completo");
        System.out.println("3. Ver clases de hoy");
        System.out.println("4. Eliminar clase");
        System.out.println("5. Buscar clase por materia");
        System.out.println("0. Volver");
        mostrarSeparador();
        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuContactosEmergencia()
    {
        mostrarTitulo("Contactos de emergencia");
        System.out.println("1. Ver mis contactos");
        System.out.println("2. Agregar contacto");
        System.out.println("3. Actualizar contacto");
        System.out.println("4. Activar/Desactivar contacto");
        System.out.println("0. Volver");
        mostrarSeparador();
        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuMascota(MascotaVirtual mascota)
    {
        mostrarTitulo("Mi mascota virtual");
        System.out.println(mascota.obtenerEstadoCompleto());
        System.out.println(mascota.obtenerEmojiEstado() + " Estado de " + mascota.getNombre());
        mostrarSeparador();
        System.out.println("1. Interactuar");
        System.out.println("2. Alimentar");
        System.out.println("3. Jugar");
        System.out.println("4. Descansar");
        System.out.println("5. Entrenar");
        System.out.println("6. Agregar accesorio");
        System.out.println("7. Cambiar nombre");
        System.out.println("0. Volver");
        mostrarSeparador();

        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuRacha()
    {
        System.out.println("\n1. Ver todas las recompensas");
        System.out.println("2. Ver recompensas obtenidas");
        System.out.println("0. Volver");
        return solicitarEntero("Selecciona una opción: ");
    }

    @Override
    public void mostrarFraseDelDia()
    {
        System.out.println("Frase del día:");
        fraseDia.mostrarFraseDelDia();
        esperarEnter();
    }

    public void mostrarFormularioDenuncias()
    {
        FormularioDenuncias.mostrarInformacion();
        esperarEnter();
    }
}