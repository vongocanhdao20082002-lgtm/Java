import java.sql.*;

public class App {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Dao@20/08/2002";

        try {
            // 1. Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // 3. Create DB
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS student_management");

            // 4. Use DB
            stmt.execute("USE student_management");

            // 5. Create table
            String createTable =
                    "CREATE TABLE IF NOT EXISTS student (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "student_name VARCHAR(100)," +
                    "age INT," +
                    "email VARCHAR(100)" +
                    ")";
            stmt.executeUpdate(createTable);

            // 6. Insert (PreparedStatement)
            String insertSQL =
                    "INSERT INTO student (student_name, age, email) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(insertSQL);

            ps.setString(1, "An");
            ps.setInt(2, 20);
            ps.setString(3, "an@gmail.com");
            ps.executeUpdate();

            ps.setString(1, "Binh");
            ps.setInt(2, 22);
            ps.setString(3, "binh@gmail.com");
            ps.executeUpdate();

            // 7. Select
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            System.out.println("===== DANH SACH SINH VIEN =====");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                        rs.getString("student_name") + " - " +
                        rs.getInt("age") + " - " +
                        rs.getString("email")
                );
            }

            // 8. Close
            rs.close();
            ps.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}