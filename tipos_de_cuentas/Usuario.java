package tipos_de_cuentas;

public abstract class Usuario
{
    protected String id;
    protected String nombre;
    protected String correo;
    protected String contrasena;
    protected TipoCuenta tipoCuenta;
    protected String telefono;

    public Usuario(String id, String nombre, String correo, String contrasena, TipoCuenta tipoCuenta, String telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoCuenta = tipoCuenta;
        this.telefono = telefono;
    }

    public boolean autenticar(String correo, String contrasena)
    {
        return this.correo.equals(correo) && this.contrasena.equals(contrasena);
    }

    public void actualizarInformacion(String nombre, String telefono)
    {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public abstract String[] getPermisosEspeciales();

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public TipoCuenta getTipoCuenta()
    {
        return tipoCuenta;
    }

        public String getTelefono()
    {
        return telefono;
    }

    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }
}