import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FraseDia
{
    private List<String> frases;
    private LocalDate fechaActual;
    private Random random;

    public FraseDia()
    {
        this.frases = new ArrayList<>();
        this.random = new Random();
        this.fechaActual = LocalDate.now();
        inicializarFrases();
    }
}