package arbori.AfisareArbori.BST;

import arbori.ImplementareArbori.BST.BST;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Clasa AfisareB reprezintă o aplicație JavaFX pentru afișarea unui
 * arbore binar de căutare (BST).
 */
public class AfisareBST extends Application {
    private static ArrayList<Integer> nodes = new ArrayList<>();

    /**
     * Metoda start este apelată la lansarea aplicației
     * și inițializează interfața grafică.
     * @param primaryStage Stadiul principal al aplicației.
     */
    @Override
    public void start(Stage primaryStage){
        BST<Integer> tree = new BST<>();
        BorderPane pane = new BorderPane();
        PanouBST view = new PanouBST(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "BST");
    }

    /**
     * Metoda setStage configurează stadiul principal al aplicației.
     * @param pane Layout-ul principal al aplicației.
     * @param primaryStage Stadiul principal al aplicației.
     * @param title Titlul stadiului.
     */
    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane, 900,700);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metoda setPane configurează structura panoului principal al aplicației.
     * @param pane Layout-ul principal al aplicației.
     * @param view Panoul pentru vizualizarea arborelui.
     * @param tree Arborele binar de căutare.
     */
    public void setPane(BorderPane pane, PanouBST view, BST<Integer> tree){
        pane.setCenter(view);
        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Introduceti");
        Button delete = new Button("Stergeti");
        addNod(textField, insert, delete, tree, view);
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Introduceti un numar"), textField, insert, delete);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(hBox);
    }

    /**
     * Metoda addNod adaugă funcționalități butoanelor
     * "Introdu" și "Șterge".
     * @param textField Câmpul de text pentru introducerea valorilor.
     * @param insert Butonul pentru introducerea valorilor în arbore.
     * @param delete Butonul pentru ștergerea valorilor din arbore.
     * @param tree Arborele binar de căutare.
     * @param view Panoul pentru vizualizarea arborelui.
     */
    public void addNod(TextField textField, Button insert, Button delete, BST<Integer> tree, PanouBST view){
        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nu ai introdus un numar", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                nodes.add(key);
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " deja exista");
                } else {
                    tree.insert(key);
                    view.displayTree();
                    view.setStatus(key + " a fost adaugat");
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
            int key = Integer.parseInt(textField.getText());
            if(!tree.search(key)){
                view.displayTree();
                view.setStatus(key +" nu exista");
            }
            else{
                tree.delete(key);
                view.displayTree();
                view.setStatus(key+" este inlocuit de predecesorul lui si este sters");
            }
            textField.clear();
        });
    }
}
