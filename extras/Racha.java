package extras;

public class Racha
{
    private int diasConsecutivos;

    public Racha()
    {
        this.diasConsecutivos = 0;
    }

    public int getDiasConsecutivos()
    {
        return diasConsecutivos;
    }

    public void actualizarRacha()
    {
        diasConsecutivos++;
        System.out.println("¡Racha actualizada! Días consecutivos: " + diasConsecutivos);
    }

    public void reiniciarRacha()
    {
        diasConsecutivos = 0;
    }
    
}
