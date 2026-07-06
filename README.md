# Ramas-Repo
package org.example.mostraaerw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class aplicacionController implements Initializable {
    public void guardar() {

        try {

            String sql = "INSERT INTO productos(nombre, tipo_cafe, precio) VALUES(?,?,?)";

            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtNombre.getText());
            ps.setString(2, cmbTipoCafe.getValue());
            ps.setDouble(3, Double.parseDouble(txtPrecio.getText()));

            ps.executeUpdate();

            ps.close();
            con.close();

            cargarBD();

            JOptionPane.showMessageDialog(null, "Registro guardado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void cargarBD() {

        listaProductos.clear();

        String sql = "SELECT * FROM productos";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                listaProductos.add(new Productos(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("id"),
                        rs.getString("tipo_cafe")
                ));
            }

            tabla.setItems(listaProductos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void limpiar(){
        txtNombre.clear();
        txtPrecio.clear();
    }
}
