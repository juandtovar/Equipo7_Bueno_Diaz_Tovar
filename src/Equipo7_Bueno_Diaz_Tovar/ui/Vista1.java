package Equipo7_Bueno_Diaz_Tovar.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Vista1 implements Vista {

    private Scene escena;
    private Label titulo;
    private Button btCrearPlan;
    private VBox layoutPrincipal;

    public Vista1() {
        HBox lh = new HBox();
        lh.setSpacing(50);
        layoutPrincipal = new VBox();
        layoutPrincipal.setSpacing(10);
        titulo = new Label("Bienvenido a Unet");
        titulo.setStyle("-fx-text-size: 16" );
        layoutPrincipal.getChildren().addAll(new Label(""), titulo);
        btCrearPlan = new Button("Crear plan");    
        btCrearPlan.setPrefWidth(100);
        layoutPrincipal.getChildren().add(btCrearPlan);
        lh.getChildren().addAll(new Label(""), layoutPrincipal );
        this.escena = new Scene(lh, 200, 200);
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

    public Scene getEscena() {
        return escena;
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Label getTitulo() {
        return titulo;
    }

    public void setTitulo(Label titulo) {
        this.titulo = titulo;
    }

    public Button getBtCrearPlan() {
        return btCrearPlan;
    }

    public void setBtCrearPlan(Button btCrearPlan) {
        this.btCrearPlan = btCrearPlan;
    }

    public VBox getLayoutPrincipal() {
        return layoutPrincipal;
    }

    public void setLayoutPrincipal(VBox layoutPrincipal) {
        this.layoutPrincipal = layoutPrincipal;
    }

}
