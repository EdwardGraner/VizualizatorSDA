package arbori.AfisareArbori.BST;

import arbori.ImplementareArbori.Nod;
import arbori.ImplementareArbori.BST.BST;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Clasa PanouB extinde clasa javafx.scene.layout.Pane
 * și este utilizată pentru vizualizarea unui arbore binar de căutare (BST).
 */
public class PanouBST extends Pane {
    private BST<Integer> tree;
    private double radius = 25;
    private double vGap = 75;

    /**
     * Constructor implicit. Nu realizează nicio inițializare specifică.
     * Este necesar pentru a asigura funcționalitățile de extindere a clasei.
     */
    protected PanouBST(){ }

    /**
     * Constructor care primește un arbore binar de căutare
     * și îl asociază cu instanța curentă de PanouB.
     * @param tree Arborele binar de căutare pentru a fi vizualizat.
     */
    PanouBST(BST<Integer> tree){
        this.tree = tree;
    }

    /**
     * Metoda setează un mesaj de stare în partea superioară a panoului.
     * @param msg Mesajul de stare de afișat.
     */
    public void setStatus(String msg){
        getChildren().add(new Text(20, 20, msg));
    }

    /**
     * Metoda șterge orice elemente preexistente din panou
     * și afișează arborele în cazul în care rădăcina nu este nulă.
     */
     public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.WHITE);
        }
    }

    /**
     * Metoda protejată afișează recursiv arborele binar de căutare în panou.
     * @param root Nodul curent pentru afișare.
     * @param x Coordonata x a centrului cercului care reprezintă nodul.
     * @param y Coordonata y a centrului cercului care reprezintă nodul.
     * @param hGap Spațiu orizontal între noduri.
     * @param color Culoarea pentru umplerea cercului care reprezintă nodul.
     */
    protected void displayTree(Nod<Integer> root, double x, double y, double hGap, Color color){
        if(root.left != null){
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2,color);
        }

        if (root.right != null){
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, color);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}