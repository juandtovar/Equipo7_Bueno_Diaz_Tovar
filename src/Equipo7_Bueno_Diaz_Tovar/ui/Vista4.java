package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;
import Equipo7_Bueno_Diaz_Tovar.logic.MiAvance;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class Vista4 implements Vista {

    private final Scene escena;
    private final Plan plan;
    private final ObservableList<PieChart.Data> pieChartFundamentacion, pieChartDisciplinar, pieChartLibreEleccion;
    private Label PAPA, PA;
    private final Button atras;

    public Vista4(Plan plan) {

        this.plan = plan;
        VBox lh = new VBox();
        HBox layout = new HBox();
        ArrayList<Double> avance = MiAvance.calcularAvance(this.plan);
        pieChartFundamentacion = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(0)),
                new PieChart.Data("No aprobados", this.plan.getCreditosFund() - avance.get(0)));
        pieChartDisciplinar = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(1)),
                new PieChart.Data("No aprobados", this.plan.getCreditosDiscp() - avance.get(1)));
        pieChartLibreEleccion = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(2)),
                new PieChart.Data("No aprobados", this.plan.getCreditosElect() - avance.get(2)));

        final DoughnutChart chart1 = new DoughnutChart(pieChartFundamentacion);
        final DoughnutChart chart2 = new DoughnutChart(pieChartDisciplinar);
        final DoughnutChart chart3 = new DoughnutChart(pieChartLibreEleccion);

        applyCustomColorSequence(pieChartFundamentacion, "#2ECC71", "#EAEDED");
        applyCustomColorSequence(pieChartDisciplinar, "#F5B041", "#EAEDED");
        applyCustomColorSequence(pieChartLibreEleccion, "#47D7E3", "#EAEDED");

        chart1.setTitle("Fundamentación");
        chart2.setTitle("Disciplinar");
        chart3.setTitle("Libre elección");
        chart1.setLegendVisible(false);
        chart2.setLegendVisible(false);
        chart3.setLegendVisible(false);

        atras = new Button("Atrás");
        atras.setOnAction((ActionEvent event) -> {
            goBack();
        });
        atras.setPrefWidth(225);
        atras.setAlignment(Pos.CENTER);
        atras.setWrapText(true);
        atras.getStyleClass().add("button");
        lh.setSpacing(35);
        layout.setSpacing(10);
        layout.getChildren().add(chart1);
        layout.getChildren().add(chart2);
        layout.getChildren().add(chart3);
        lh.getChildren().add(layout);
        lh.getChildren().add(atras);
        for (int i = 0; i < 7; i++) {
            lh.getChildren().add(new Button(String.valueOf(i)));
        }
        this.escena = new Scene(lh);

        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        stage.setScene(this.getScena());
        stage.setMaximized(true);
    }

    @Override
    public void goBack() {
        Main.deletePestañas();
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        Controlador3 controlador = new Controlador3(Vista3.getPlanes(), this.plan.getNombre());
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.show();
    }

    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

}
