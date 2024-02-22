package arbori.ImplementareArbori.BST;

import arbori.ImplementareArbori.Arbore;
import arbori.ImplementareArbori.Nod;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clasa BST reprezintă o implementare a unui arbore binar de căutare (BST).
 * @param <E> Tipul elementelor stocate în arbore,
 *          care trebuie să implementeze interfața Comparable.
 */
public class BST<E extends Comparable<E>> implements Arbore<E> {

    public Nod<E> root;
    public int size = 0;

    /**
     * Constructorul fara parametri.
     */
    public BST() {
    }

    /**
     * Constructor care primește un șir de obiecte și le inserează în arbore.
     * @param objects Șirul de obiecte de inserat în arbore.
     */
    public BST(E[] objects) {
        for (E o: objects)
            insert(o);
    }
    /**
     * Realizează căutarea unui element în arbore.
     * @param e Elementul căutat.
     * @return true dacă elementul a fost găsit, false în caz contrar.
     */
    private boolean search(Nod<E> root, E e){
        if(root == null)
            return false;
        else if(e.compareTo(root.element) == 0)
            return true;
        else{
            if(e.compareTo(root.element) > 0)
                return search(root.right, e);
            else
                return search(root.left, e);
        }
    }
    /**
     * Returnează înălțimea unui nod sau 0 dacă nodul este null.
     * @param N Nodul pentru care se calculează înălțimea.
     * @return Înălțimea nodului sau 0 dacă nodul este null.
     */
    public int height(Nod<E> N) {
        if (N == null)
            return 0;
        return N.height;
    }

    /**
     * Realizează căutarea unui element în arbore.
     * @param e Elementul căutat.
     * @return true dacă elementul a fost găsit, false în caz contrar.
     */
    @Override
    public boolean search(E e) {
        return search(root, e);
    }
    /**
     * Inserează un element în arbore.
     * @param e Elementul de inserat.
     * @return true dacă inserarea a avut succes, false în caz contrar.
     */
    public Nod<E> insert(Nod<E> root, E e){
        if(root == null)
            root = createNewNode(e);
        else{
            if(e.compareTo(root.element) > 0)
                root.right = insert(root.right, e);
            else if(e.compareTo(root.element) < 0)
                root.left = insert(root.left, e);
            else
                return null;
        }
        return root;

    }
    /**
     * Inserează un element în arbore.
     * @param e Elementul de inserat.
     * @return true dacă inserarea a avut succes, false în caz contrar.
     */
    @Override
    public boolean insert(E e) {
        root = insert(root,e);
        if(root == null)
            return false;
        size++;
        return true;
    }

    /**
     * Creează un nou nod pe baza unui element dat.
     * @param e Elementul pentru care se creează un nod nou.
     * @return Noul nod creat.
     */
    public Nod<E> createNewNode(E e){
        return new Nod<>(e);
    }

    /**
     * Parcurge arborele în inordine și afișează elementele.
     */
    @Override
    public void inorder() {
        inorder(root);
    }

    /**
     * Parcurge un subarbore în inordine și afișează elementele.
     * @param root Nodul de la care începe parcurgerea.
     */
    private void inorder(Nod<E> root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.element+" ");
            inorder(root.right);
        }
    }

    /**
     * Parcurge arborele în preordine și afișează elementele.
     */
    @Override
    public void preorder() {
        preorder(root);
    }

    /**
     * Parcurge un subarbore în preordine și afișează elementele.
     * @param root Nodul de la care începe parcurgerea.
     */
    private void preorder(Nod<E> root){
        if(root != null){
            System.out.print(root.element+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    /**
     * Parcurge arborele în postordine și afișează elementele.
     */
    @Override
    public void postorder() {
        postorder(root);
    }

    /**
     * Parcurge un subarbore în postordine și afișează elementele.
     * @param root Nodul de la care începe parcurgerea.
     */
    private void postorder(Nod<E> root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.element +" ");
        }
    }

    /**
     * Returnează numărul total de elemente din arbore.
     * @return Numărul total de elemente din arbore.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Returnează rădăcina arborelui.
     * @return Rădăcina arborelui.
     */
    public Nod<E> getRoot(){
        return root;
    }

    /**
     * Returnează o listă de noduri reprezentând calea către
     * un anumit element.
     * @param e Elementul pentru care se construiește calea.
     * @return Listă de noduri de la rădăcină la nodul căutat.
     */
    public ArrayList<Nod<E>> path(E e){
        ArrayList<Nod<E>> list = new ArrayList<>();
        Nod<E> current = root;
        while(current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0)
                current = current.left;
            else if(e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        return list;
    }

    /**
     * Verifică dacă arborele este gol.
     * @return true dacă arborele este gol, false în caz contrar.
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Șterge un element din arbore.
     * @param e Elementul de șters.
     * @return true dacă ștergerea a avut succes,
     * false în caz contrar.
     */
    public Nod<E> delete(Nod<E> root, E e){
        if(root == null)
            return null;

        if(e.compareTo(root.element) > 0)
            root.right = delete(root.right, e);
        else if(e.compareTo(root.element) < 0)
            root.left = delete(root.left, e);

        else{
            if(root.left == null && root.right == null)
                root = null;
            else if(root.left == null)
                root = root.right;
            else if(root.right == null)
                root = root.left;
            else {
                root.element = findMax(root.left);
                root.left = delete(root.left, root.element);
            }
        }
        return root;
    }

    /**
     * Găsește valoarea maximă într-un subarbore.
     * @param root Nodul de la care începe căutarea.
     * @return Valoarea maximă din subarbore.
     */
    private E findMax(Nod<E> root){
        Nod<E> temp = root;
        while(temp.right != null)
            temp = temp.right;
        return temp.element;
    }

    /**
     * Șterge un element din arbore.
     * @param e Elementul de șters.
     * @return true dacă ștergerea a avut succes, false în caz contrar.
     */
    @Override
    public boolean delete(E e) {
        root = delete(root, e);
        if(root == null)
            return false;
        size--;
        return true;
    }

    /**
     * Metoda iterator nu este implementată în acest exemplu și întoarce mereu null.
     * @return O referință null.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }
    
}

