package com.example.cafeteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class aplicacionDAO {

    private Connection getConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            return Conexion.conectar();
        } catch (Exception e) {
            System.out.println("BD no disponible: " + e.getMessage());
            return null;
        }
    }

    public void guardar(Productos p) {

        String sql = "INSERT INTO productos(nombre, tipo_cafe, precio) VALUES(?,?,?)";

        Connection con = getConexion();

        if (con == null) {
            System.out.println("Sin conexión a BD");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoCafe());
            ps.setDouble(3, p.getPrecio());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void actualizar(Productos p) {

        String sql = "UPDATE productos SET nombre=?, tipo_cafe=?, precio=? WHERE id=?";

        Connection con = getConexion();

        if (con == null) {
            System.out.println("Sin conexión a BD (actualizar)");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoCafe());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM productos WHERE id=?";

        Connection con = getConexion();

        if (con == null) {
            System.out.println("Sin conexión a BD (eliminar)");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
        }
    }

    public List<Productos> listar() {

        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        Connection con = getConexion();

        if (con == null) {
            System.out.println("Sin conexión a BD (listar)");
            return lista;
        }

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Productos(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("id"),
                        rs.getString("tipo_cafe")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }
}