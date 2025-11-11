/// ----------------------------------------------
/// TipoCuenta.java
/// Enum para definir los tipos de cuentas en el sistema.
/// ----------------------------------------------

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