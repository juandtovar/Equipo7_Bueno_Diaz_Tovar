package Equipo7_Bueno_Diaz_Tovar.ui;

import javafx.stage.Stage;

public class Singleton {

    private static Singleton singleton;
    private Stage stage;

    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
