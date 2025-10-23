package tipos_de_cuentas;

import java.util.ArrayList;
import registrar_horarios.Horario;
import contactoEmergencia.ContactoEmergencia;
import RegistrarEntradas.EntradaDiario;

public class Estudiante extends Usuario 
{
    private ArrayList<EntradaDiario> entradasDiario;
    private Configuracion configuraciones;
    private Racha racha;
    private MascotaVirtual mascota;
    private Horario horario;
    private ArrayList<ContactoEmergencia> contactosEmergencia;

    public Estudiante(String id, String nombre, String correo, String contrasena, String telefono)
    {
        super(id, nombre, correo, contrasena, TipoCuenta.ESTUDIANTE, telefono);
        this.entradasDiario = new ArrayList<>();
        this.configuraciones = new Configuracion();
        this.racha = new Racha();
        this.mascota = new MascotaVirtual("Mascota");
        this.horario = new Horario();
        this.contactosEmergencia = new ArrayList<>();
    }

    @Override
    public String[] getPermisosEspeciales()
    {
        return new String[]
        {
            "crear_entrada_diario",
            "usar_boton_emergencia",
            "personalizar_mascota",
            "registrar_horario",
            "ver_ejercicios_bienestar",
        };
    }

    public void registrarEntrada(EntradaDiario entrada)
    {
        entradasDiario.add(entrada);
        racha.actualizarRacha();
    }

    public ArrayList<EntradaDiario> obtenerHistorial()
    {
        return new ArrayList<>(entradasDiario);
    }

    public void personalizarMascota(String nuevoNombre)
    {
        mascota.setNombre(nuevoNombre);
    }

    public void agregarContactoEmergencia(ContactoEmergencia contacto)
    {
        contactosEmergencia.add(contacto);
    }

    public Horario getHorario()
    {
        return horario;
    }

    public ArrayList<ContactoEmergencia> getContactosEmergencia()
    {
        return contactosEmergencia;
    }

    public Configuracion getConfiguraciones()
    {
        return configuraciones;
    }

    public Racha getRacha()
    {
        return racha;
    }

    public MascotaVirtual getMascota()
    {
        return mascota;
    }
}
