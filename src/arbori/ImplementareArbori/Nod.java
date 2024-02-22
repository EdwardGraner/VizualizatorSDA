package arbori.ImplementareArbori;

/**
 * Clasa Nod reprezinta un nod într-un arbore
 * @param <E> Tipul generic al elementului din nod,
 *            care trebuie să fie comparabil.
 */
public class Nod<E extends Comparable<E>> {
    public E element;      // Elementul stocat în nod.
    public Nod<E> left;     // Referință către subarborele stâng.
    public Nod<E> right;    // Referință către subarborele drept.
    public int height = 0;  // Înălțimea nodului.
    private boolean red = true;  // Variabilă ce indică dacă nodul este roșu sau negru.

    /**
     * Constructor care initializeaza un nod cu un element dat.
     * @param e Elementul asociat nodului.
     */
    public Nod(E e) {
        element = e;
    }

    /**
     * Verifică dacă nodul este roșu.
     * @return {@code true} dacă nodul este roșu, {@code false} altfel.
     */
    public boolean isRed() {
        return red;
    }

    /**
     * Verifică dacă nodul este negru.
     * @return {@code true} dacă nodul este negru, {@code false} altfel.
     */
    public boolean isBlack() {
        return !red;
    }

    /**
     * Setează culoarea nodului la negru.
     */
    public void setBlack() {
        red = false;
    }

    /**
     * Setează culoarea nodului la roșu.
     */
    public void setRed() {
        red = true;
    }

    int blackHeight;  // Înălțimea neagră a nodului.
}
