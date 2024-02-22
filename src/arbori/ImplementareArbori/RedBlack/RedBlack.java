package arbori.ImplementareArbori.RedBlack;

import arbori.ImplementareArbori.Nod;
import arbori.ImplementareArbori.BST.BST;

import java.util.ArrayList;

/**
 * Clasa RedBlack extinde clasa BST și implementează un arbore binar Roșu-Negru.
 *
 * @param <E> Tipul elementelor stocate în arbore, trebuie să implementeze Comparable.
 */
public class RedBlack<E extends Comparable<E>> extends BST<E> {

    /**
     * Constructor implicit care nu face nimic specific pentru clasa RedBlack.
     */
    public RedBlack() {
    }

    /**
     * Constructor care primește un șir de elemente și
     * le inserează în arbore Roșu-Negru.
     *
     * @param elements Șirul de elemente de inserat în arbore.
     */
    public RedBlack(E[] elements) {
        super(elements);
    }

    /**
     * Suprascrie metoda createNewNode pentru a crea un nod Roșu-Negru.
     *
     * @param e Elementul care va fi stocat în noul nod.
     * @return Noul nod creat.
     */
    @Override
    public Nod<E> createNewNode(E e) {
        return new Nod<>(e);
    }

    /**
     * Suprascrie metoda insert pentru a insera un element în arbore
     * și a menține proprietățile arborelui Roșu-Negru.
     *
     * @param e Elementul de inserat în arbore.
     * @return True dacă inserarea a avut succes, false altfel.
     */
    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            ensureRBTree(e);
        }

        return true;
    }

    /**
     * Asigură că arborele Roșu-Negru rămâne într-o stare validă
     * după operația de inserare.
     *
     * @param e Elementul inserat.
     */
    private void ensureRBTree(E e) {
        ArrayList<Nod<E>> path = path(e);
        int i = path.size() - 1;
        Nod<E> u = (path.get(i));
        Nod<E> v = (u == root) ? null : (path.get(i - 1));
        u.setRed();
        if (u == root)
            u.setBlack();
        else if (v != null & v.isRed())
            fixDoubleRed(u, v, path, i);
    }

    /**
     * Tratează cazurile în care apare o situație de dublu roșu în arbore.
     *
     * @param u    Nodul roșu.
     * @param v    Părintele lui u.
     * @param path Calea către nodul inserat.
     * @param i    Indicele în calea către nodul inserat.
     */
    private void fixDoubleRed(Nod<E> u, Nod<E> v,
                              ArrayList<Nod<E>> path, int i) {
        Nod<E> w = (path.get(i - 2));
        Nod<E> parentW = (w == root) ? null : path.get(i - 3);

        Nod<E> x = (w.left == v) ? (w.right) :(w.left);

        if (x == null || x.isBlack()) {
            if (w.left == v && v.left == u) {
                restructureRecolor(u, v, w, w, parentW);

                w.left = v.right;
                v.right = w;
            }
            else if (w.left == v && v.right == u) {
                restructureRecolor(v, u, w, w, parentW);
                v.right = u.left;
                w.left = u.right;
                u.left = v;
                u.right = w;
            }
            else if (w.right == v && v.right == u) {
                restructureRecolor(w, v, u, w, parentW);
                w.right = v.left;
                v.left = w;
            }
            else {
                restructureRecolor(w, u, v, w, parentW);
                w.right = u.left;
                v.left = u.right;
                u.left = w;
                u.right = v;
            }
        }
        else {
            w.setRed();
            u.setRed();
            ((w.left)).setBlack();
            ((w.right)).setBlack();

            if (w == root) {
                w.setBlack();
            }
            else if (parentW != null && (parentW).isRed()) {
                u = w;
                v = parentW;
                fixDoubleRed(u, v, path, i - 2);
            }
        }
    }

    /**
     * Restructurează și recolorează nodurile pentru rezolvarea unei situații de dublu roșu.
     *
     * @param a        Nodul A.
     * @param b        Nodul B.
     * @param c        Nodul C.
     * @param w        Nodul W.
     * @param parentW Părintele lui W.
     */
    private void restructureRecolor(Nod<E> a, Nod<E> b,
                                    Nod<E> c, Nod<E> w, Nod<E> parentW) {
        if (parentW == null)
            root = b;
        else if (parentW.left == w)
            parentW.left = b;
        else
            parentW.right = b;

        b.setBlack();
        a.setRed();
        c.setRed();
    }

    /**
     * Suprascrie metoda delete pentru a permite ștergerea unui nod
     * și pentru a menține proprietățile arborelui Roșu-Negru.
     *
     * @param e Elementul de șters.
     * @return True dacă ștergerea a avut succes, false altfel.
     */
    @Override
    public boolean delete(E e) {
        Nod<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        if (current == null)
            return false;

        java.util.ArrayList<Nod<E>> path;
        if (current.left != null && current.right != null) {
            Nod<E> rightMost = current.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            path = path(rightMost.element);
            current.element = rightMost.element;
        }
        else
            path = path(e);
        deleteLastNodeInPath(path);

        size--;
        return true;
    }

    /**
     * Șterge ultimul nod în calea specificată și menține proprietățile
     * arborelui Roșu-Negru.
     *
     * @param path Calea către nodul șters.
     */
    public void deleteLastNodeInPath(ArrayList<Nod<E>> path) {
        int i = path.size() - 1;
        Nod<E> u = (path.get(i));
        Nod<E> parentU = (u == root) ? null : (path.get(i - 1));
        Nod<E> grandparentU = (parentU == null ||
                parentU == root) ? null : (path.get(i - 2));
        Nod<E> childOfu = (u.left == null) ? (u.right) :(u.left);

        connectNewParent(parentU, u, childOfu);


        if (childOfu == root || u.isRed())
            return;
        else if (childOfu != null && childOfu.isRed())
            childOfu.setBlack();
        else
            fixDoubleBlack(grandparentU, parentU, childOfu, path, i);
    }

    /**
     * Tratează cazurile în care apare dublu negru în arbore.
     *
     * @param grandparent Bunicul nodului cu dublu negru.
     * @param parent      Părintele nodului cu dublu negru.
     * @param db          Nodul cu dublu negru.
     * @param path        Calea către nodul cu dublu negru.
     * @param i           Indicele în calea către nodul cu dublu negru.
     */
    private void fixDoubleBlack(
            Nod<E> grandparent, Nod<E> parent,
            Nod<E> db, ArrayList<Nod<E>> path, int i) {
        Nod<E> y = (parent.right == db) ? (parent.left) : (parent.right);
        Nod<E> y1 = (y.left);
        Nod<E> y2 = (y.right);

        if (y.isBlack() && y1 != null && y1.isRed()) {
            if (parent.right == db) {

                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y1);

                parent.left = y.right;
                y.right = parent;
            }
            else {
                connectNewParent(grandparent, parent, y1);
                recolor(parent, y1, y);

                parent.right = y1.left;
                y.left = y1.right;
                y1.left = parent;
                y1.right = y;
            }
        }
        else if (y.isBlack() && y2 != null && y2.isRed()) {
            if (parent.right == db) {

                connectNewParent(grandparent, parent, y2);
                recolor(parent, y2, y); // Adjust colors


                y.right = y2.left;
                parent.left = y2.right;
                y2.left = y;
                y2.right = parent;
            }
            else {

                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y2);


                y.left = parent;
                parent.right = y1;
            }
        }
        else if (y.isBlack()) {

            y.setRed();
            if (parent.isRed())
                parent.setBlack(); // Done
            else if (parent != root) {

                db = parent;
                parent = grandparent;
                grandparent =
                        (i >= 3) ? (Nod<E>)(path.get(i - 3)) : null;
                fixDoubleBlack(grandparent, parent, db, path, i - 1);
            }
        }
        else {
            if (parent.right == db) {
                parent.left = y2;
                y.right = parent;
            }
            else {
                parent.right = y.left;
                y.left = parent;
            }

            parent.setRed();
            y.setBlack();
            connectNewParent(grandparent, parent, y);
            fixDoubleBlack(y, parent, db, path, i - 1);
        }
    }


    /**
     * Obține culoarea unui nod.
     *
     * @param e Nodul pentru care se verifică culoarea.
     * @return True dacă nodul este roșu, false altfel.
     */
    public boolean getRed(Nod<E> e) {
        Nod<E> aNode = e;
        boolean b = true;
        if(aNode.isBlack())
        {
            b = false;
        }
        return b;
    }

    /**
     * Recolorează nodurile pentru a menține proprietățile arborelui Roșu-Negru.
     *
     * @param parent      Nodul părinte.
     * @param newParent   Nodul nou părinte.
     * @param c           Nodul C.
     */
    private void recolor(Nod<E> parent,
                         Nod<E> newParent, Nod<E> c) {
        if (parent.isRed())
            newParent.setRed();
        else
            newParent.setBlack();

        parent.setBlack();
        c.setBlack();
    }

    /**
     * Conectează noul părinte la arbore.
     *
     * @param grandparent Bunicul nodului.
     * @param parent      Părintele nodului.
     * @param newParent   Noul părinte.
     */
    private void connectNewParent(Nod<E> grandparent,
                                  Nod<E> parent, Nod<E> newParent) {
        if (parent == root) {
            root = newParent;
            if (root != null)
                newParent.setBlack();
        }
        else if (grandparent.left == parent)
            grandparent.left = newParent;
        else
            grandparent.right = newParent;

    }
}