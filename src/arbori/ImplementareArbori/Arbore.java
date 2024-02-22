package arbori.ImplementareArbori;

/**
 * Interfață generică pentru un arbore.
 * @param <E> Tipul generic al elementelor din arbore.
 */
public interface Arbore<E> extends Iterable<E> {

        /**
         * Inserează un element în arbore.
         * @param e Elementul de inserat.
         * @return {@code true} dacă inserarea a avut succes, {@code false} altfel.
         */
        boolean insert(E e);

        /**
         * Șterge un element din arbore.
         * @param e Elementul de șters.
         * @return {@code true} dacă ștergerea a avut succes, {@code false} altfel.
         */
        boolean delete(E e);

        /**
         * Verifică dacă un element este prezent în arbore.
         * @param e Elementul de căutat.
         * @return {@code true} dacă elementul este găsit, {@code false} altfel.
         */
        boolean search(E e);


        /**
         * Realizează o parcurgere în inordine a arborelui și afișează elementele.
         */
        void inorder();

        /**
         * Realizează o parcurgere în postordine a arborelui și afișează elementele.
         */
        void postorder();

        /**
         * Realizează o parcurgere în preordine a arborelui și afișează elementele.
         */
        void preorder();

        /**
         * Returnează numărul total de elemente din arbore.
         *
         * @return Numărul total de elemente din arbore.
         */
        int getSize();
        /**
         * Verifică dacă arborele este gol.
         *
         * @return {@code true} dacă arborele este gol, {@code false} altfel.
         */
        boolean isEmpty();
}
