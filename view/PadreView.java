/// ----------------------------------------------
/// PadreView.java
/// Vista para las interacciones del padre de familia.
/// ----------------------------------------------

package view;

import frase.FraseDia;
import formulario.FormularioDenuncias;

public class PadreView extends MainView
{
    private FraseDia fraseDia;

    public PadreView()
    {
        super();
        this.fraseDia = new FraseDia();
    }

    public int mostrarMenuPadre(String nombre)
    {
        mostrarTitulo("Menú Padre - Bienvenido " + nombre);
        System.out.println("1. Ver estudiante vinculado");
        System.out.println("2. Configurar notificaciones");
        System.out.println("3. Formulario de denuncias");
        System.out.println("4. Frase del día");
        System.out.println("5. Mi perfil");
        System.out.println("0. Cerrar sesión");
        mostrarSeparador();
        
        return solicitarEntero("Seleccione una opción: ");
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
        FormularioDenuncias.mostrarFormulario();
        esperarEnter();
    }
}