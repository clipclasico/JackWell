/// ----------------------------------------------
/// CatedraticoView.java
/// Vista para las interacciones del catedrático.
/// ----------------------------------------------

package view;

import frase.FraseDia;
import formulario.FormularioDenuncias;

public class CatedraticoView extends MainView
{
    private FraseDia fraseDia;

    public CatedraticoView()
    {
        super();
        this.fraseDia = new FraseDia();
    }

    public int mostrarMenuCatedratico(String nombre)
    {
        mostrarTitulo("Menú Catedrático - " + nombre);
        System.out.println("1. Mis materias");
        System.out.println("2. Configurar alertas");
        System.out.println("3. Formulario de denuncias");
        System.out.println("4. Frase del día");
        System.out.println("5. Mi perfil");
        System.out.println("0. Cerrar sesión");
        mostrarSeparador();

        return solicitarEntero("Selecciona una opción: ");
    }

    public int mostrarMenuMaterias()
    {
        mostrarTitulo("Mis materias");
        System.out.println("1. Ver mis materias");
        System.out.println("2. Agregar materia");
        System.out.println("0. Volver");
        mostrarSeparador();

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