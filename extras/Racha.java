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

    public void incrementarRacha()
    {
        diasConsecutivos++;
    }

    public void reiniciarRacha()
    {
        diasConsecutivos = 0;
    }
    
}
