package com.example.cafeteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class aplicacionController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Productos> tabla;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<String> cmbTipoCafe;

    @FXML
    private TableColumn<Productos, Integer> colId;

    @FXML
    private TableColumn<Productos, String> colNombre;

    @FXML
    private TableColumn<Productos, String> colTipo;

    @FXML
    private TableColumn<Productos, Double> colPrecio;

    private aplicacionDAO dao = new aplicacionDAO();
    private ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbTipoCafe.getItems().addAll(
                "Americano", "Expreso", "Capuccino",
                "Mocha", "Latte", "Frappe"
        );

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCafe"));

        // 🔥 PROTECCIÓN CRÍTICA
        try {
            cargarBD();
        } catch (Exception e) {
            System.out.println("Error cargando BD: " + e.getMessage());
            e.printStackTrace();

            // 👇 IMPORTANTE: no dejar tabla vacía sin control
            javafx.scene.control.Alert alert =
                    new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);

            alert.setTitle("Advertencia");
            alert.setHeaderText("Base de datos no disponible");
            alert.setContentText("La aplicación seguirá funcionando sin datos.");
            alert.show();
        }

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtPrecio.setText(String.valueOf(newSel.getPrecio()));
                cmbTipoCafe.setValue(newSel.getTipoCafe());
            }
        });
    }
    public void actualizar() {

        try {
            Productos p = tabla.getSelectionModel().getSelectedItem();

            if (p == null) {
                mostrarAlerta("Aviso", "Seleccione un producto", Alert.AlertType.WARNING);
                return;
            }

            p.setNombre(txtNombre.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setTipoCafe(cmbTipoCafe.getValue());

            dao.actualizar(p);
            cargarBD();

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo actualizar", Alert.AlertType.ERROR);
        }
    }

    public void guardar() {

        try {
            Productos p = new Productos(
                    txtNombre.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    0,
                    cmbTipoCafe.getValue()
            );

            dao.guardar(p);
            cargarBD();

            mostrarAlerta("OK", "Guardado correctamente", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            System.out.println("Error guardar: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo guardar", Alert.AlertType.ERROR);
        }
    }

    private void cargarBD() {

        try {
            listaProductos.clear();

            var datos = dao.listar();

            if (datos != null) {
                listaProductos.addAll(datos);
            }

            tabla.setItems(listaProductos);

        } catch (Exception e) {
            System.out.println("Error cargando BD: " + e.getMessage());
        }
    }

    public void limpiar() {

        txtNombre.clear();
        txtPrecio.clear();
        cmbTipoCafe.getSelectionModel().clearSelection();
    }

    public void btnEliminar() {

        try {
            Productos p = tabla.getSelectionModel().getSelectedItem();

            if (p == null) {
                mostrarAlerta("Aviso", "Seleccione un producto", Alert.AlertType.WARNING);
                return;
            }

            dao.eliminar(p.getId());
            cargarBD();

            mostrarAlerta("OK", "Eliminado correctamente", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo eliminar", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(
            String titulo,
            String mensaje,
            Alert.AlertType tipo
    ) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}