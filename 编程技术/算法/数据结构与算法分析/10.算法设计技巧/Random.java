import javax.xml.transform.Templates;

public class Random {
    private static final int A = 48271;
    private static final int M = 2147483647;
    private static final int Q = M / A;
    private static final int R = M % A;
    private int state;

    public Random() {
        state = System.currentTimeMillis() % Interger.MAX_VALUE;
    }

    /**
     * Return a pseudorandom(伪随机) int, and change the internal state.
     * @return the pseudorandom int.
     */
    public int randomInt() {
        int tmpState = A * (state % Q) - R * (state / Q);

        if (tmpState >= 0)
            state = tmpState;
        else
            state = tmpState + M;
        
        return state;
    }

    /**
     * Return a pseudorandom double in the open range (0,1) and change the internal state.
     * @return the pseudorandom double.
     */
    public double random0_1() {
        return (double) randomInt() / M;
    }
}

/**
 * Random number class, using a 48-bit linear congruential(同余的) generator/
 */
public class Random48 {
    private static final long A = 25_214_903_917L;
    private static final long B = 48;
    private static final long C = 11;
    private static final long M = (1L << B);
    private static final long MASK = M - 1;
    private long state;

    public Random48() {
        state = System.nanoTime() % MASK;
    }

    public int randomInt() {
        return next(32);
    }

    public double random0_1() {
        return ((long) (next(26)) << 27) + next(27) / (double) (1L << 53);
    }

    /**
     * Return specified number of random bits
     * @param bits number of bits to return
     * @return specified random bits
     * @throws IllegalArgumentException if bits is more than 32
     */
    private int next(int bits) {
        if (bits <= 0 || bits > 32)
            throw new IllegalArgumentException();
        
        state = (A * state + C) & MASK;

        return (int) (state >>> (B - bits));
    }
}