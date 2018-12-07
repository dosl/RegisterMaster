package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Subject;

import java.sql.*;
import java.util.ArrayList;

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

    public static void resetSubject() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update subject set status = '" + 0 + "'";
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

    public static String searchSubject(String id) {
        //not done!!!!!////
        //try to get previousSubject
        //use this in addController
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select previousSubject from subject where subject.subjectID=='" + id + "'";
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
        return id;
    }

    public static void deleteSubject(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from subject where subject.subjectID=='" + id + "'";
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

    public static void editSubject(String id, String name, String year, String term, String previousSubject, String color) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update subject set subjectName = '" + name + "', term = '" + term + "', year = '" + year + "', color = '" + color + "', previousSubject = '" + previousSubject + "' where subjectID = '" + id + "'";
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

    public static ArrayList<String> getPrevious() {
        ArrayList<String> preList = new ArrayList<String>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select previousSubject from subject";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
//                    String id = resultSet.getString("subjectID");
//                    String name = resultSet.getString("subjectName");
//                    String year = resultSet.getString("year");
//                    String term = resultSet.getString("term");
                    String previousSubject = resultSet.getString("previousSubject");
//                    boolean status = resultSet.getBoolean("status");
//                    String color = resultSet.getString("color");
                    //subjects.add(new Subject(id, name, year, term, previousSubject, status, color));
                    preList.add(previousSubject);
                }
                connection.close();


            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return preList;
    }

    public static boolean getStatus(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select status from subject where subject.subjectID=='" + id + "'";
                //               PreparedStatement p = connection.prepareStatement(query);
//                p.executeUpdate();
                Statement statement = connection.createStatement();
                Boolean status = statement.execute(query);
                connection.close();
                return status;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getPreviousId(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select previousSubject from subject where subject.subjectID=='" + id + "'";
                //PreparedStatement p = connection.prepareStatement(query);
                //p.executeUpdate();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String preid = resultSet.getString(1);
                    //System.out.println("POOM "+preid);

                    connection.close();
                    return preid;
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static boolean isPreviousPassed(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select status from subject where subject.subjectID=='" + id + "'";
                //PreparedStatement p = connection.prepareStatement(query);
                //p.executeUpdate();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    boolean status = resultSet.getBoolean(1);
                    //boolean status = resultSet.getString(1);
                    //System.out.println("POOM "+preid);

                    connection.close();
                    return status;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
