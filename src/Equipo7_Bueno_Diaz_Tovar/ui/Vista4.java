package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Materia;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import Equipo7_Bueno_Diaz_Tovar.logic.MiAvance;
import Equipo7_Bueno_Diaz_Tovar.logic.MiPlan;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Vista4 implements Vista {

    private final Scene escena;
    private final Plan plan;
    private final ObservableList<PieChart.Data> pieChartFundamentacion, pieChartDisciplinar, pieChartLibreEleccion;
    private Label PAPA, PA;

    public Vista4(Plan plan) {
        this.plan = plan;
        HBox lh = new HBox();
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

        HBox layout = new HBox();
        lh.setSpacing(35);
        layout.setSpacing(10);
        layout.getChildren().add(chart1);
        layout.getChildren().add(chart2);
        layout.getChildren().add(chart3);
        lh.getChildren().add(layout);
        this.escena = new Scene(lh, 500, 500);
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
