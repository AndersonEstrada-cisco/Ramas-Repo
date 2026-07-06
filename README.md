# Ramas-Repo


Estrada Anderson -- Boton Delete

Se estableció la conexion a la base de datos en la Nube y se desarrolló el botón de DELETE.


public class Conexion {

    private static final String URL =
            "jdbc:postgresql://bdyu262pdbqzfv5m5pst-postgresql.services.clever-cloud.com:5432/bdyu262pdbqzfv5m5pst?sslmode=require";

    private static final String USER = "umhv4lmzaovwvp1qsvat";
    private static final String PASSWORD = "Vcmlntpv2LIZvfJwUgsjpzLTCEdHMY";

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            System.out.println("BD no disponible: " + e.getMessage());
            return null; // importante para no romper app
        }
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
