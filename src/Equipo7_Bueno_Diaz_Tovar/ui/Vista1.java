package Equipo7_Bueno_Diaz_Tovar.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Vista1 implements Vista {

    private Scene escena;
    private Label titulo;
    private Button btCrearPlan;
    private VBox layoutPrincipal;

    public Vista1() {
        layoutPrincipal = new VBox();
        titulo = new Label("Bienvenido a UNet");
        btCrearPlan = new Button("Siguiente");
        layoutPrincipal.getChildren().add(titulo);
        btCrearPlan = new Button("Crear plan");
        layoutPrincipal.getChildren().add(btCrearPlan);
        this.escena = new Scene(layoutPrincipal, 500, 500);
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
