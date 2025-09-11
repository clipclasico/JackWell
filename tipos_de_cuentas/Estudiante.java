package tipos_de_cuentas;

import java.util.ArrayList;


public class Estudiante extends Usuario 
{
    private ArrayList<EntradasDiario> entradasDiario;
    private Configuracion configuraciones;
    private Racha racha;
    private MascotaVirtual mascota;
    private Horario horario;
    private ArrayList<ContactoEmergencia> contactosEmergencia;

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
}
