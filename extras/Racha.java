package extras;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Racha
{
    private int diasConsecutivos;
    private int mejorRacha;
    private LocalDate ultimaFecha;
    private int totalEntradas;
    private List<Recompensa> recompensasObtenidas;
    private List<Recompensa> recompensasDisponibles;

    public static class Recompensa
    {
        private String nombre;
        private String descripcion;
        private int diasRequeridos;
        private String icono;
        private boolean desbloqueada;

        public Recompensa(String nombre, String descripcion, int diasRequeridos, String icono)
        {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.diasRequeridos = diasRequeridos;
            this.icono = icono;
            this.desbloqueada = false;
        }

        public String getNombre() { return nombre; }
        public String getDescripcion() { return descripcion; }
        public int getDiasRequeridos() { return diasRequeridos; }
        public String getIcono() { return icono; }
        public boolean isDesbloqueada() { return desbloqueada; }
        public void desbloquear() { this.desbloqueada = true; }

        @Override
        public String toString()
        {
            String estado = desbloqueada ? : "🔒";
            return estado + " " + icono + " " + nombre + " - " + descripcion + " (" + diasRequeridos + " días)";
        }
    }

    public Racha()
    {
        this.diasConsecutivos = 0;
        this.mejorRacha = 0;
        this.ultimaFecha = null;
        this.totalEntradas = 0;
        this.recompensasObtenidas = new ArrayList<>();
        this.recompensasDisponibles = new ArrayList<>();
        inicializarRecompensas();
    }

    private void inicializarRecompensas()
    {
        recompensasDisponibles.add(new Recompensa(
            "Primer Paso", 
            "Completaste tu primera entrada", 
            1, 
            "🌱"
        ));

        recompensasDisponibles.add(new Recompensa(
            "Construyendo Hábitos", 
            "3 días consecutivos de registro", 
            3, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Una Semana Fuerte", 
            "7 días consecutivos", 
            7, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Dedicación Total", 
            "14 días consecutivos", 
            14, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Mes de Campeón", 
            "30 días consecutivos", 
            30,
        ));

        recompensasDisponibles.add(new Recompensa(
            "Maestro del Bienestar", 
            "50 días consecutivos", 
            50, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Leyenda Viviente", 
            "100 días consecutivos", 
            100, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Escritor Novato", 
            "10 entradas totales", 
            10, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Cronista", 
            "50 entradas totales", 
            50, 
        ));

        recompensasDisponibles.add(new Recompensa(
            "Autobiógrafo", 
            "100 entradas totales", 
            100, 
        ));
    }

    public void actualizarRacha()
    {
        LocalDate hoy = LocalDate.now();
        totalEntradas++;

        if (ultimaFecha == null)
        {
            diasConsecutivos = 1;
            ultimaFecha = hoy;
            System.out.println("¡Comenzaste tu racha!");
        }
        else
        {
            long diasTranscurridos = ChronoUnit.DAYS.between(ultimaFecha, hoy);

            if (diasTranscurridos == 0)
            {
                System.out.println("Ya registraste tu entrada de hoy.");
                return;
            }
            else if (diasTranscurridos == 1)
            {
                diasConsecutivos++;
                ultimaFecha = hoy;
                System.out.println("¡Racha actualizada! Días consecutivos: " + diasConsecutivos);
            }
            else
            {
                System.out.println("Se rompió tu racha de " + diasConsecutivos + " días.");
                if (diasConsecutivos > mejorRacha)
                {
                    mejorRacha = diasConsecutivos;
                    System.out.println("¡Pero fue tu mejor racha hasta ahora!");
                }
                diasConsecutivos = 1;
                ultimaFecha = hoy;
                System.out.println("Empezando una nueva racha...");
            }
        }

        if (diasConsecutivos > mejorRacha)
        {
            mejorRacha = diasConsecutivos;
        }

        verificarRecompensas();
    }

    private void verificarRecompensas()
    {
        for (Recompensa recompensa : recompensasDisponibles)
        {
            if (!recompensa.isDesbloqueada())
            {
                if (recompensa.getDiasRequeridos() <= 100 && diasConsecutivos >= recompensa.getDiasRequeridos())
                {
                    desbloquearRecompensa(recompensa);
                }
                else if (recompensa.getDiasRequeridos() > 100 && totalEntradas >= recompensa.getDiasRequeridos())
                {
                    desbloquearRecompensa(recompensa);
                }
            }
        }
    }

    private void desbloquearRecompensa(Recompensa recompensa)
    {
        recompensa.desbloquear();
        recompensasObtenidas.add(recompensa);
        
        System.out.println("¡RECOMPENSA DESBLOQUEADA!\n");
        System.out.println(recompensa.getIcono() + " " + recompensa.getNombre());
        System.out.println(recompensa.getDescripcion());
    }

    public void reiniciarRacha()
    {
        if (diasConsecutivos > mejorRacha)
        {
            mejorRacha = diasConsecutivos;
        }
        diasConsecutivos = 0;
        ultimaFecha = null;
        System.out.println("Racha reiniciada.");
    }

    public void mostrarEstadisticas()
    {
        System.out.println("\nESTADÍSTICAS");
        System.out.println("Racha actual: " + diasConsecutivos + " días");
        System.out.println("Mejor racha: " + mejorRacha + " días");
        System.out.println("Total de entradas: " + totalEntradas);
        System.out.println("Recompensas obtenidas: " + recompensasObtenidas.size() + "/" + recompensasDisponibles.size());

        mostrarNivelMotivacion();
    }

    private void mostrarNivelMotivacion()
    {
        System.out.println("\nMensaje motivacional:");
        
        if (diasConsecutivos == 0)
        {
            System.out.println("   ¡Empieza hoy tu viaje hacia el bienestar!");
        }
        else if (diasConsecutivos < 3)
        {
            System.out.println("   ¡Buen comienzo! Sigue así.");
        }
        else if (diasConsecutivos < 7)
        {
            System.out.println("   ¡Excelente! Estás construyendo un hábito sólido.");
        }
        else if (diasConsecutivos < 14)
        {
            System.out.println("   ¡Increíble! Una semana completa de constancia.");
        }
        else if (diasConsecutivos < 30)
        {
            System.out.println("   ¡Impresionante! Eres un ejemplo de dedicación.");
        }
        else if (diasConsecutivos < 50)
        {
            System.out.println("   ¡UN MES! Eres un verdadero campeón del bienestar.");
        }
        else if (diasConsecutivos < 100)
        {
            System.out.println("   ¡ASOMBROSO! Eres un maestro del autocuidado.");
        }
        else
        {
            System.out.println("   ¡LEGENDARIO! Eres una inspiración para todos.");
        }
    }

    public void mostrarRecompensas()
    {
        System.out.println("RECOMPENSAS\n");
        
        System.out.println("║  Recompensas Obtenidas:");
        if (recompensasObtenidas.isEmpty())
        {
            System.out.println("║    Aún no has desbloqueado ninguna");
        }
        else
        {
            for (Recompensa r : recompensasObtenidas)
            {
                System.out.println(r.getIcono() + " " + r.getNombre());
            }
        }
        
        System.out.println("Próximas Recompensas:");
        
        boolean hayProximas = false;
        for (Recompensa r : recompensasDisponibles)
        {
            if (!r.isDesbloqueada())
            {
                System.out.println(r.getNombre() + " (" + r.getDiasRequeridos() + " días)");
                hayProximas = true;
            }
        }
        
        if (!hayProximas)
        {
            System.out.println("║    ¡Has desbloqueado todas!");
        }
    }

    public String obtenerProgresoProximaRecompensa()
    {
        Recompensa proxima = null;
        int menorDiferencia = Integer.MAX_VALUE;

        for (Recompensa r : recompensasDisponibles)
        {
            if (!r.isDesbloqueada())
            {
                int diferencia = r.getDiasRequeridos() - diasConsecutivos;
                if (diferencia > 0 && diferencia < menorDiferencia)
                {
                    menorDiferencia = diferencia;
                    proxima = r;
                }
            }
        }

        if (proxima != null)
        {
            return "Faltan " + menorDiferencia + " días para: " + proxima.getNombre() + " " + proxima.getIcono();
        }
        else
        {
            return "¡Has completado todas las recompensas!";
        }
    }

    public int getDiasConsecutivos()
    {
        return diasConsecutivos;
    }

    public int getMejorRacha()
    {
        return mejorRacha;
    }

    public int getTotalEntradas()
    {
        return totalEntradas;
    }

    public List<Recompensa> getRecompensasObtenidas()
    {
        return new ArrayList<>(recompensasObtenidas);
    }

    public LocalDate getUltimaFecha()
    {
        return ultimaFecha;
    }
}