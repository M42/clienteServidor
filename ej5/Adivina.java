//
// Adivina
// 

import java.util.Random;

public class Adivina {
    private Random rand = new Random();
    private int pensado = rand.nextInt(Protocol.MAXINT);

    public Adivina() {
	System.out.println("El servidor ha elegido el: " + pensado);
    }

    public int intento(int n) {
	if (n == pensado)
	    return Protocol.ACIERTO;
	else if (n < pensado)
	    return Protocol.ESMENOR;
	return Protocol.ESMAYOR;
    }

    public int estima() {
	return rand.nextInt(Protocol.MAXINT);
    }
}
