package tipos_de_cuentas;

import java.util.ArrayList;

public class Catedratico extends Usuario
{
    private ArrayList<String> materias;
    private boolean puedeRecibirAlertas;

    public Catedratico(String id, String nombre, String correo, String contrasena, String telefono)
    {
        super(id, nombre, correo, contrasena, TipoCuenta.CATEDRATICO, telefono);
        this.materias = new ArrayList<>();
        this.puedeRecibirAlertas = true;
    }

    @Override
    public String[] getPermisosEspeciales()
    {
        return new String[]
        {
            "Recibir alertas de estudiantes",
            "Acceso arecursos institucionales",
            "Reportar incidentes",
        };
    }

    public void agregarMateria(String materia)
    {
        materias.add(materia);
    }

    public ArrayList<String> getMaterias()
    {
        return new ArrayList<>(materias);
    }

    public boolean isPuedeRecibirAlertas()
    {
        return puedeRecibirAlertas;
    }

    public void setPuedeRecibirAlertas(boolean puedeRecibirAlertas)
    {
        this.puedeRecibirAlertas = puedeRecibirAlertas;
    }
}
