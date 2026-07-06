# Ramas-Repo
Chasi Alexis -Conexion con el table View
Establecer la conexion de la base de datos con el tableView



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
