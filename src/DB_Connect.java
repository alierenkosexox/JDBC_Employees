import java.sql.*;

public class DB_Connect {
    public static final String URL = "jdbc:mysql://localhost/";
    public static final String USER = "root";
    public static final String PASSWORD = "mysql";
    public static final String DATABASE_NAME = "jdbcemployees";


    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String databaseCreation = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(databaseCreation);

            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            statement = connection.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS employees ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Name VARCHAR(250), "
                    + "Position VARCHAR(100), "
                    + "Salary DECIMAL(10,2)"
                    + ")";

            statement.executeUpdate(createTable);

            String employee1 = "INSERT INTO employees (Name, Position, Salary) VALUES ('Ali', 'CEO', 45000)";
            String employee2 = "INSERT INTO employees (Name, Position, Salary) VALUES ('Veli', 'Manager', 30000)";
            String employee3 = "INSERT INTO employees (Name, Position, Salary) VALUES ('Ahmet', 'Office Boy', 28000)";
            String employee4 = "INSERT INTO employees (Name, Position, Salary) VALUES ('Zeynep', 'Software Eng.', 30000)";
            String employee5 = "INSERT INTO employees (Name, Position, Salary) VALUES ('Ayse', 'Lawyer', 35000)";

            statement.executeUpdate(employee1);
            statement.executeUpdate(employee2);
            statement.executeUpdate(employee3);
            statement.executeUpdate(employee4);
            statement.executeUpdate(employee5);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Position: " + resultSet.getString("Position"));
                System.out.println("Salary: " + resultSet.getBigDecimal("Salary"));
                System.out.println("========================");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}