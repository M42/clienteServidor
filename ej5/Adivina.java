//
// Adivina
// 

import java.util.Random;

public class Adivina {
    private Random rand = new Random();
    private int pensado = rand.nextInt(100);

    public Adivina() {
    }

    public int intento(int n) {
	if (n == pensado)
	    return 0;
	else if (n <= pensado)
	    return -1;
	return 1;
    }

    public int estima() {
	return rand.nextInt(100);
    }
}
