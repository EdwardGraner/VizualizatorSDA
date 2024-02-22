package arbori.AfisareArbori.RedBlack;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import arbori.AfisareArbori.BST.AfisareBST;
import arbori.ImplementareArbori.RedBlack.RedBlack;

/**
 * Clasa AfisareRB extinde clasa AfisareB și este utilizată pentru
 * afișarea unui arbore Roșu-Negru (Red-Black).
 */
public class AfisareRB extends AfisareBST {

    /**
     * Metoda start este apelată la inițializarea aplicației și
     * creează un obiect RedBlack, un panou specific pentru arborele
     * Roșu-Negru și inițializează interfața grafică.
     * @param primaryStage Stadiul principal al aplicației JavaFX.
     */
    @Override
    public void start(Stage primaryStage){
        RedBlack<Integer> tree = new RedBlack<>();
        BorderPane pane = new BorderPane();
        PanouRB view = new PanouRB(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "Arbore RedBlack");
    }
}
