package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Subject;

import java.sql.*;

public class SubjectDBConnector {
    private static String dbURL = "jdbc:sqlite:subjectDB.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList getSubject() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from subject";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("subjectID");
                    String name = resultSet.getString("subjectName");
                    String year = resultSet.getString("year");
                    String term = resultSet.getString("term");
                    String previousSubject = resultSet.getString("previousSubject");
                    boolean status = resultSet.getBoolean("status");
                    String color = resultSet.getString("color");
                    subjects.add(new Subject(id, name, year, term, previousSubject, status, color));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjects;
    }

    public static void addSubject(String id, String name, String year, String term) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update subject set status = '" + 1 + "' where subject.subjectID=='" + id + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                Statement statement = connection.createStatement();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createSubject(String id, String name, String year, String term, String previousSubject, boolean status, String color) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Subject (subjectID, subjectName, year, term, previousSubject, status ,color) values ('" + id + "', '" + name + "', '" + year + "', '" + term + "', '" + previousSubject + "' , '" + status + "' , '" + color + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                Statement statement = connection.createStatement();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchSubject(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select * from subject where subject.subjectID=='" + id + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                Statement statement = connection.createStatement();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteSubject(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from subject where subject.subjectID=='" + id +"'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
