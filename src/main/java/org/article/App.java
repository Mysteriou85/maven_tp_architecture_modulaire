package org.article;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.article.bll.BLLException;
import org.article.bll.ProduitManager;

public class App extends Application {

    private Label label_titreProduit = new Label("Produit");
    private Label label_reference = new Label("Référence :");
    private Label label_libelle = new Label("Libellé :");
    private Label label_marque = new Label("Marque :");
    private Label label_prix = new Label("Prix :");
    private Label label_quantite = new Label("Quantité :");
    private Label label_typeDeProduit = new Label("Type de produit :");

    private TextField textField_reference = new TextField();
    private TextField textField_libelle = new TextField();
    private TextField textField_marque = new TextField();
    private TextField textField_prix = new TextField();
    private TextField textField_quantite = new TextField();
    private TextField textField_typeDeProduit = new TextField();

    private ChoiceBox choiceBox_typeDeProduit = new ChoiceBox();

    private Separator separator1 = new Separator(Orientation.HORIZONTAL);
    private Separator separator2 = new Separator(Orientation.HORIZONTAL);

    // ***** Element : Bouton en bas de page *****
    private Button button_precedent = new Button("< Précédent");
    private Button button_nouveau = new Button("Nouveau");
    private Button button_enregistrer = new Button("Enregistrer");
    private Button button_suprimer = new Button("Suprimer");
    private Button button_suivant = new Button("Suivant >");

    // ***** Element : Stylo *****
    private Label label_stylo_couleur = new Label("Couleur :");
    private Label label_stylo_typeMine = new Label("Type de mine :");

    private ChoiceBox choiceBox_stylo_couleur = new ChoiceBox();

    private ToggleGroup radioGroupe_styloe_typeMine = new ToggleGroup();
    private RadioButton radioButton_stylo_crayonPapier = new RadioButton("Crayon à papier");
    private RadioButton radioButton_stylo_crayonBille = new RadioButton("Crayon à bille");
    private RadioButton radioButton_stylo_feutre = new RadioButton("Feutre");

    // ***** Element : Pain *****
    private Label label_pain_poids = new Label("Poids :");
    private Label label_pain_datePeremption = new Label("Date de péremption :");

    private TextField textField_pain_poids = new TextField();

    private DatePicker datePicker_pain_datePeremption = new DatePicker();

    // ***** Element : Glace *****
    private Label label_glace_parfum = new Label("Parfum :");
    private Label label_glace_datePeremption = new Label("Date de péremption :");
    private Label label_glace_temperatureConservation = new Label("Température de conservation :");

    private TextField textField_glace_parfum = new TextField();
    private TextField textField_glace_temperatureConservation = new TextField();

    private DatePicker datePicker_glace_datePeremption = new DatePicker();

    @Override
    public void start(Stage stage) {

        ProduitManager pm = ProduitManager.getInstance();
        try {
            pm.getLesProduits();
        } catch (BLLException e) {
            throw new RuntimeException(e);
        }

        GridPane root = new GridPane();

        // ************************************************
        // *****       Zone information général       *****
        // ************************************************

        // ***** Titre *****
        GridPane.setColumnIndex(label_titreProduit, 0);
        GridPane.setRowIndex(label_titreProduit, 0);

        root.getChildren().add(label_titreProduit);
        //root.add(label_titreProduit, 0, 0, 1, 2);

        // ***** Zone reference *****
        GridPane.setColumnIndex(label_reference, 0);
        GridPane.setRowIndex(label_reference, 1);

        GridPane.setColumnIndex(textField_reference, 1);
        GridPane.setRowIndex(textField_reference, 1);

        root.getChildren().add(label_reference);
        root.getChildren().add(textField_reference);

        // ***** Zone libelle *****
        GridPane.setColumnIndex(label_libelle, 0);
        GridPane.setRowIndex(label_libelle, 2);

        GridPane.setColumnIndex(textField_libelle, 1);
        GridPane.setRowIndex(textField_libelle, 2);

        root.getChildren().add(label_libelle);
        root.getChildren().add(textField_libelle);

        // ***** Zone marque *****
        GridPane.setColumnIndex(label_marque, 0);
        GridPane.setRowIndex(label_marque, 3);

        GridPane.setColumnIndex(textField_marque, 1);
        GridPane.setRowIndex(textField_marque, 3);

        root.getChildren().add(label_marque);
        root.getChildren().add(textField_marque);

        // ***** Zone prix *****
        GridPane.setColumnIndex(label_prix, 0);
        GridPane.setRowIndex(label_prix, 4);

        GridPane.setColumnIndex(textField_prix, 1);
        GridPane.setRowIndex(textField_prix, 4);

        root.getChildren().add(label_prix);
        root.getChildren().add(textField_prix);

        // ***** Zone quantité *****
        GridPane.setColumnIndex(label_quantite, 0);
        GridPane.setRowIndex(label_quantite, 5);

        GridPane.setColumnIndex(textField_quantite, 1);
        GridPane.setRowIndex(textField_quantite, 5);

        root.getChildren().add(label_quantite);
        root.getChildren().add(textField_quantite);

        // ***** Zone type de produit *****
        GridPane.setColumnIndex(label_typeDeProduit, 0);
        GridPane.setRowIndex(label_typeDeProduit, 6);

        choiceBox_typeDeProduit.getItems().add("Stylo");
        choiceBox_typeDeProduit.getItems().add("Pain");
        choiceBox_typeDeProduit.getItems().add("Glace");
        // choiceBox_typeDeProduit.getItems().add("Carte postale");

        GridPane.setColumnIndex(choiceBox_typeDeProduit, 1);
        GridPane.setRowIndex(choiceBox_typeDeProduit, 6);

        root.getChildren().add(label_typeDeProduit);
        root.getChildren().add(choiceBox_typeDeProduit);

        // ***** Zone separator *****
        //GridPane.setColumnIndex(separator1, 0);
        //GridPane.setRowIndex(separator1, 7);

        //GridPane.setColumnIndex(separator2, 1);
        //GridPane.setRowIndex(separator2, 8);

        //root.getChildren().add(separator1);
        //root.getChildren().add(separator1);

        // ************************************************
        // *****       Zone produit particulier       *****
        // ************************************************



        // ************************************************
        // *****       Zone boutton bas de page       *****
        // ************************************************

        // ***** Bouton précédent *****
        GridPane.setColumnIndex(button_precedent,0);
        GridPane.setRowIndex(button_precedent, 10);

        root.getChildren().add(button_precedent);

        // ***** Bouton nouveau *****
        GridPane.setColumnIndex(button_nouveau,1);
        GridPane.setRowIndex(button_nouveau, 10);

        root.getChildren().add(button_nouveau);

        // ***** Bouton enregistrer *****
        button_enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                App.this.label_titreProduit.setText("toto");
            }
        });

        GridPane.setColumnIndex(button_enregistrer,2);
        GridPane.setRowIndex(button_enregistrer, 10);

        root.getChildren().add(button_enregistrer);

        // ***** Bouton Suprimer *****
        GridPane.setColumnIndex(button_suprimer,3);
        GridPane.setRowIndex(button_suprimer, 10);

        root.getChildren().add(button_suprimer);

        // ***** Bouton Suivant *****
        GridPane.setColumnIndex(button_suivant,4);
        GridPane.setRowIndex(button_suivant, 10);

        root.getChildren().add(button_suivant);

        // **************************************
        // *****       Création scene       *****
        // **************************************
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void menu() {

    }

    public static void main(String[] args) {
        launch();
    }

}