/// ----------------------------------------------
/// MascotaVirtual.java
/// Clase para gestionar una mascota virtual interactiva.
/// ----------------------------------------------

package extras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MascotaVirtual
{
    private String nombre;
    private int nivel;
    private int experiencia;
    private int felicidad;
    private int energia;
    private String tipo;
    private List<String> accesorios;
    private Random random;

    private static final int EXP_POR_NIVEL = 100;
    private static final int NIVEL_MAXIMO = 10;
    private static final int FELICIDAD_MAXIMA = 100;
    private static final int ENERGIA_MAXIMA = 100;

    public MascotaVirtual(String nombre)
    {
        this.nombre = nombre;
        this.nivel = 1;
        this.experiencia = 0;
        this.felicidad = 50;
        this.energia = 100;
        this.tipo = "Cachorro";
        this.accesorios = new ArrayList<>();
        this.random = new Random();
    }

    public void interactuar()
    {
        if (energia < 10)
        {
            System.out.println(nombre + " está muy cansado para jugar. Necesita descansar.");
            return;
        }

        String[] interacciones =
        {
            nombre + " salta de alegría al verte!",
            nombre + " mueve la cola felizmente!",
            nombre + " te da un abrazo virtual!",
            nombre + " está muy contento de pasar tiempo contigo!",
            nombre + " hace una pirueta para impresionarte!"
        };

        System.out.println(interacciones[random.nextInt(interacciones.length)]);
        
        aumentarFelicidad(10);
        gastarEnergia(5);
        ganarExperiencia(5);
    }

    public void alimentar()
    {
        if (energia >= ENERGIA_MAXIMA - 10)
        {
            System.out.println(nombre + " ya está lleno. No necesita comer ahora.");
            return;
        }

        System.out.println("Alimentando a " + nombre + "...");
        energia += 30;
        if (energia > ENERGIA_MAXIMA)
        {
            energia = ENERGIA_MAXIMA;
        }
        
        aumentarFelicidad(15);
        ganarExperiencia(10);
        System.out.println(nombre + " está feliz y lleno de energía!");
    }

    public void jugar()
    {
        if (energia < 20)
        {
            System.out.println(nombre + " está demasiado cansado para jugar. Dale de comer primero.");
            return;
        }

        String[] juegos =
        {
            nombre + " persigue la pelota con entusiasmo!",
            nombre + " juega a buscar y encuentra el juguete!",
            nombre + " hace trucos increíbles!",
            nombre + " corre en círculos de felicidad!"
        };

        System.out.println(juegos[random.nextInt(juegos.length)]);
        
        aumentarFelicidad(20);
        gastarEnergia(15);
        ganarExperiencia(15);
    }

    public void descansar()
    {
        System.out.println(nombre + " se acuesta a descansar...");
        energia += 40;
        if (energia > ENERGIA_MAXIMA)
        {
            energia = ENERGIA_MAXIMA;
        }
        System.out.println(nombre + " se siente renovado!");
    }

    public void entrenar()
    {
        if (energia < 30)
        {
            System.out.println(nombre + " necesita más energía para entrenar.");
            return;
        }

        System.out.println(nombre + " está entrenando duro...");
        gastarEnergia(25);
        ganarExperiencia(30);
        aumentarFelicidad(10);
        System.out.println(nombre + " está mejorando sus habilidades!");
    }

    public void agregarAccesorio(String accesorio)
    {
        if (!accesorios.contains(accesorio))
        {
            accesorios.add(accesorio);
            System.out.println("✨ " + nombre + " ahora tiene: " + accesorio);
            aumentarFelicidad(15);
        }
        else
        {
            System.out.println(nombre + " ya tiene ese accesorio.");
        }
    }

    private void ganarExperiencia(int cantidad)
    {
        if (nivel >= NIVEL_MAXIMO)
        {
            System.out.println(nombre + " ya alcanzó el nivel máximo!");
            return;
        }

        experiencia += cantidad;
        System.out.println(cantidad + " EXP");

        if (experiencia >= EXP_POR_NIVEL)
        {
            subirNivel();
        }
    }

    private void subirNivel()
    {
        nivel++;
        experiencia -= EXP_POR_NIVEL;
        
        System.out.println(nombre + " ahora es nivel " + nivel + "!");

        evolucionarMascota();

        energia = ENERGIA_MAXIMA;
        felicidad = FELICIDAD_MAXIMA;
    }

    private void evolucionarMascota()
    {
        String tipoAnterior = tipo;

        if (nivel >= 10)
        {
            tipo = "Leyenda";
        }
        else if (nivel >= 7)
        {
            tipo = "Maestro";
        }
        else if (nivel >= 5)
        {
            tipo = "Campeón";
        }
        else if (nivel >= 3)
        {
            tipo = "Adulto";
        }

        if (!tipo.equals(tipoAnterior))
        {
            System.out.println(nombre + " ha evolucionado a: " + tipo + "!");
        }
    }

    private void aumentarFelicidad(int cantidad)
    {
        felicidad += cantidad;
        if (felicidad > FELICIDAD_MAXIMA)
        {
            felicidad = FELICIDAD_MAXIMA;
        }
    }

    private void gastarEnergia(int cantidad)
    {
        energia -= cantidad;
        if (energia < 0)
        {
            energia = 0;
        }

        if (energia < 20)
        {
            System.out.println(nombre + " tiene poca energía. Considera darle de comer.");
        }
    }

    public String obtenerEstadoCompleto()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADO DE " + nombre.toUpperCase() + " 🐾\n");
        sb.append("Tipo: " + tipo + "\n");
        sb.append("Nivel: " + nivel + "/" + NIVEL_MAXIMO + "\n");
        sb.append("Experiencia: " + experiencia + "/" + EXP_POR_NIVEL + " EXP\n");
        sb.append(obtenerBarraProgreso("Felicidad", felicidad, FELICIDAD_MAXIMA) + "\n");
        sb.append(obtenerBarraProgreso("Energía", energia, ENERGIA_MAXIMA) + "\n");
        
        if (!accesorios.isEmpty())
        {
            sb.append("Accesorios:\n");
            for (String acc : accesorios)
            {
                sb.append(acc + "\n");
            }
        }
        else
        {
            sb.append("No tiene accesorios aún\n");
        }
        
        return sb.toString();
    }

    private String obtenerBarraProgreso(String nombre, int valor, int maximo)
    {
        int porcentaje = (valor * 100) / maximo;
        int barras = porcentaje / 10;
        
        StringBuilder barra = new StringBuilder(nombre + ": [");
        for (int i = 0; i < 10; i++)
        {
            if (i < barras)
            {
                barra.append("█");
            }
            else
            {
                barra.append("░");
            }
        }
        barra.append("] " + valor + "/" + maximo);
        return barra.toString();
    }

    public String obtenerEmojiEstado()
    {
        if (felicidad >= 80 && energia >= 70)
        {
            return ":D";
        }
        else if (felicidad >= 60 && energia >= 50)
        {
            return ":)";
        }
        else if (felicidad >= 40 || energia >= 30)
        {
            return ":(";
        }
        else if (energia < 20)
        {
            return "xl";
        }
        else
        {
            return "D:";
        }
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getNivel()
    {
        return nivel;
    }

    public int getExperiencia()
    {
        return experiencia;
    }

    public int getFelicidad()
    {
        return felicidad;
    }

    public int getEnergia()
    {
        return energia;
    }

    public String getTipo()
    {
        return tipo;
    }

    public List<String> getAccesorios()
    {
        return new ArrayList<>(accesorios);
    }

    public void actualizacionDiaria()
    {
        if (felicidad > 20)
        {
            felicidad -= 5;
        }

        if (energia < ENERGIA_MAXIMA)
        {
            energia += 10;
            if (energia > ENERGIA_MAXIMA)
            {
                energia = ENERGIA_MAXIMA;
            }
        }
    }
}