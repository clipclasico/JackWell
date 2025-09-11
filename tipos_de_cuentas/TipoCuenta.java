package tipos_de_cuentas;

public enum TipoCuenta
{
    ESTUDIANTE("Estudiante"),
    PADRE("Padre"),
    CATEDRATICO("Catedrático");

    private final String descripcion;

    TipoCuenta(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}