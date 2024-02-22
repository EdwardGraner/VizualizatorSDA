package arbori.afisare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import arbori.AfisareArbori.BST.AfisareBST;
import arbori.AfisareArbori.RedBlack.AfisareRB;

/**
 * Clasa Afisare reprezintă un controler JavaFX utilizat pentru afișarea
 * meniului principal.
 */
public class Afisare {
    @FXML
    private Button rb;
    @FXML
    private Button bst;

    /**
     * Metoda este apelată atunci când utilizatorul apasă butonul
     * pentru a afișa un arbore binar de căutare (BST).
     */
    @FXML
    private void bstAction(){
        bst.setOnAction(e-> setStage(new AfisareBST()));
    }

    /**
     * Metoda este apelată atunci când utilizatorul apasă butonul
     * pentru a afișa un arbore Roșu-Negru (Red-Black).
     */
    @FXML
    private void rbAction(){
        rb.setOnAction(e-> setStage(new AfisareRB()));
    }

    /**
     * Metoda privată care inițializează și afișează o nouă fereastră
     * pentru afișarea unui arbore specific.
     * @param menu Meniul (arborele) pe care dorim să îl afișăm.
     */
    private void setStage(AfisareBST menu){
        Stage menuStage = new Stage();
        menu.start(menuStage);
        menuStage.show();
    }
}
