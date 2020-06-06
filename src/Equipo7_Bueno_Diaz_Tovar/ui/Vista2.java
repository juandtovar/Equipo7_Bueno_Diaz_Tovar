package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Chain;
import Equipo7_Bueno_Diaz_Tovar.data.ChainNode;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Vista2 implements Vista {

    private Scene escena;
    private Label elegir;
    private Chain<Button> botones;
    private Chain<Plan> planes;

    public Vista2(Chain<Plan> planes) {
        this.botones = new Chain<>();
        this.planes = planes;
        this.elegir = new Label("                Eliga su plan");
        HBox lh = new HBox();
        VBox layout = new VBox();
        lh.setSpacing(35);
        layout.setSpacing(10);
        layout.getChildren().add(elegir);
        lh.getChildren().add(new Label(""));
        ChainNode<Plan> temp = planes.getHead();
        do {
            Button boton = new Button(temp.getElement().getNombre()
                    .replace("ingenieria_mecanica", "Ingeniería Mecánica")
                    .replace("ingenieria_mecatronica", "Ingeniería Mecatrónica"));
            boton.setPrefWidth(180);
            layout.getChildren().add(boton);
            this.botones.add(boton, this.botones.getSize());
            temp = temp.getNext();
        } while (temp != null); 
        lh.getChildren().add(layout);
        this.escena = new Scene(lh, 250, 200);
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Label getElegir() {
        return elegir;
    }

    public void setElegir(Label elegir) {
        this.elegir = elegir;
    }

    public Chain<Button> getBotones() {
        return botones;
    }

    public void setBotones(Chain<Button> botones) {
        this.botones = botones;
    }

    public Chain<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(Chain<Plan> planes) {
        this.planes = planes;
    }

}
