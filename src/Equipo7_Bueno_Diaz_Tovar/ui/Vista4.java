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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class Vista4 implements Vista {

    private final Scene escena;
    private final Plan plan;
    private final VBox layoutPrincipal;
    private final HBox layoutCreditosPAPA, layoutPieChart;
    private ObservableList<PieChart.Data> pieChartFundamentacion, pieChartDisciplinar, pieChartLibreEleccion;
    private Label PAPA, PA, creditosAprobados;
    private Button atras;

    public Vista4(Plan plan) {
        this.plan = plan;
        this.layoutPrincipal = new VBox();
        this.layoutCreditosPAPA = new HBox();
        this.layoutPieChart = new HBox();

        inicializarPieChart();
        inicializarAtras();
        inicializarLabels();
        this.layoutPrincipal.setSpacing(35);
        this.layoutPrincipal.getChildren().add(this.layoutPieChart);
        this.layoutPrincipal.getChildren().add(this.PAPA);
        this.layoutPrincipal.getChildren().add(this.PA);
        this.layoutPrincipal.getChildren().add(this.creditosAprobados);
        this.layoutPrincipal.getChildren().add(this.atras);
        this.escena = new Scene(this.layoutPrincipal);
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        stage.centerOnScreen();
    }

    public void inicializarPieChart() {
        ArrayList<Double> avance = MiAvance.calcularAvance(this.plan);
        this.pieChartFundamentacion = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(0)),
                new PieChart.Data("No aprobados", this.plan.getCreditosFund() - avance.get(0)));
        this.pieChartDisciplinar = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(1)),
                new PieChart.Data("No aprobados", this.plan.getCreditosDiscp() - avance.get(1)));
        this.pieChartLibreEleccion = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", avance.get(2)),
                new PieChart.Data("No aprobados", this.plan.getCreditosElect() - avance.get(2)));

        final DoughnutChart chart1 = new DoughnutChart(this.pieChartFundamentacion);
        final DoughnutChart chart2 = new DoughnutChart(this.pieChartDisciplinar);
        final DoughnutChart chart3 = new DoughnutChart(this.pieChartLibreEleccion);

        applyCustomColorSequence(this.pieChartFundamentacion, "#2ECC71", "#EAEDED");
        applyCustomColorSequence(this.pieChartDisciplinar, "#F5B041", "#EAEDED");
        applyCustomColorSequence(this.pieChartLibreEleccion, "#47D7E3", "#EAEDED");

        chart1.setTitle("Fundamentación");
        chart2.setTitle("Disciplinar");
        chart3.setTitle("Libre elección");
        chart1.setLegendVisible(false);
        chart2.setLegendVisible(false);
        chart3.setLegendVisible(false);

        this.layoutPieChart.getChildren().add(chart1);
        this.layoutPieChart.getChildren().add(chart2);
        this.layoutPieChart.getChildren().add(chart3);

        this.layoutPieChart.setSpacing(10);
    }

    public void inicializarAtras() {
        this.atras = new Button("Atrás");
        this.atras.setOnAction((ActionEvent event) -> {
            goBack();
        });
        this.atras.setPrefWidth(225);
        this.atras.setAlignment(Pos.CENTER);
        this.atras.setWrapText(true);
        this.atras.getStyleClass().add("button");
    }

    public void inicializarLabels() {
        this.PAPA = new Label("PAPA = " + String.valueOf(MiAvance.calcularPapaPaCreditosAprobados(this.plan).get(0)));
        this.PA = new Label("PA = " + String.valueOf(MiAvance.calcularPapaPaCreditosAprobados(this.plan).get(1)));
        int creditosAprobados = (int) Math.round(MiAvance.calcularPapaPaCreditosAprobados(this.plan).get(2));
        this.creditosAprobados = new Label("Creditos aprobados = "
                + String.valueOf(creditosAprobados)
                + " de " + this.plan.getCreditosTotales());
        this.PAPA.setFont(new Font("Arial", 39));
        this.PA.setFont(new Font("Arial", 39));
        this.creditosAprobados.setFont(new Font("Arial", 39));
    }

    @Override
    public void goBack() {
        Main.deletePestañas();
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        Controlador3 controlador = new Controlador3(Vista3.getPlanes(), this.plan.getNombre());
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.centerOnScreen();
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
