package arbori.AfisareArbori.RedBlack;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import arbori.AfisareArbori.BST.PanouBST;
import arbori.ImplementareArbori.Nod;
import arbori.ImplementareArbori.RedBlack.RedBlack;

/**
 * Clasa PanouRB extinde clasa PanouB și este utilizată pentru afișarea
 * unui arbore Roșu-Negru (Red-Black).
 */
public class PanouRB extends PanouBST {
    private RedBlack<Integer> tree;
    private double radius = 30;
    private double vGap = 75;

    /**
     * Constructor care primește un arbore Roșu-Negru și îl asociază
     * cu instanța curentă de PanouRB.
     * @param tree Arborele Roșu-Negru pentru a fi vizualizat.
     */
    PanouRB(RedBlack<Integer> tree){
        this.tree = tree;
    }

    /**
     * Metoda afișează arborele Roșu-Negru în panou,
     * eliminând orice elemente preexistente și
     * apoi apelând metoda de afișare a arborelui.
     */
    @Override
    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    /**
     * Metoda protejată afișează recursiv arborele Roșu-Negru în panou,
     * utilizând culori specifice pentru noduri.
     * @param root Nodul curent pentru afișare.
     * @param x Coordonata x a centrului cercului care reprezintă nodul.
     * @param y Coordonata y a centrului cercului care reprezintă nodul.
     * @param hGap Spațiu orizontal între noduri.
     */
    private void displayTree(Nod<Integer> root, double x, double y, double hGap){
        if(root.left != null){
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null){
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        tree.getRed(root);

        Circle circle = new Circle(x, y, radius);
        circle.setStroke(Color.BLACK);
        if(tree.getRed(root))
            circle.setFill(Color.INDIANRED);
        else circle.setFill(Color.GRAY);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}