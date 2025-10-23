package extras;

public class MascotaVirtual
{
    private String nombre;

    public MascotaVirtual(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void interactuar()
    {
        System.out.println(nombre + " está feliz de verte!");
    }
}
