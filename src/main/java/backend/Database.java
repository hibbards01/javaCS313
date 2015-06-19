/*
 * Database.java
 */
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database
 *  This will grab the data from the database.
 * @author samuel
 */
public class Database {
    /*
     * Member variables
     */
    private static Database database = null;
    private static String MYSQL_DRIVER;
    private static String DB_URL;
    private static String USER;
    private static String PASS;
    
    /*
     * Member methods
     */
    
    /**
     * Constructor
     *  Making this one private. Makes this class act 
     *      like the singleton pattern.
     */
    private Database() {
        // Set the static variables
        MYSQL_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://127.0.0.1/java_projects";
        USER = "java";
        PASS = "password";
    }
    
    /**
     * Non-Default Constructor
     * @param isOpenShift 
     */
    private Database(Boolean isOpenShift) {
        // Grab from openshift!
        String dbHost = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String dbPort = System.getenv("OPENSHIFT_MYSQL_DB_PORT");

        MYSQL_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://127.11.42.130/java_projects";
        USER = "admintRMqJ9Z";
        PASS = "Pux9tbxcKcEE";
    }
    
    /**
     * newInstance
     *  Grab instance of Database.
     * @param isOpenShift
     * @return 
     */
    public static Database newInstance(Boolean isOpenShift) {
        // See if the database is null.                                        
        if (database == null) {
            if (isOpenShift) {
                database = new Database(isOpenShift);
            } else {
                database = new Database();                
            }
        }
        
        return database;
    }
    
    /**
     * getInstance
     *  Grab the database!
     * @return 
     */
    public static Database getInstance() {
        return database;
    }
    
    /**
     * selectPassword
     *  This will grab the password from the database.
     * @param username
     * @return 
     */
    public String [] selectPassword(String username) {
        // Create variables
        String [] values = new String[2];
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        String query = "SELECT password, name FROM users WHERE username = '" +
                username + "'";
        
        // Now query from the database
        results = executeQuery(query);
        
        // Grab the values!
        if (!results.isEmpty()) {            
            values[0] = results.get(0).get("name");
            values[1] = results.get(0).get("pass");
        }
        
        return values;
    }
    
    /**
     * selectPosts
     *  Grab all the posts!
     * @return 
     */
    public List<Map<String, String>> selectPosts() {
        // Grab all the posts!
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        String query = "SELECT u.name, p.post_text, p.created_at FROM posts AS p " + 
                "JOIN users AS u ON p.user_id = u.id";
        
        // Now execute the query!
        results = executeQuery(query);
        
        return results;
    }
    
    /**
     * executeQuery
     *  Execute the query and grab the results. here we go
     * @param query
     * @return 
     */
    private List<Map<String, String>> executeQuery(String query) {
        // Create some variables
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        Connection connection = null;
        PreparedStatement statement = null;
        
        // Now grab from the Database
        try {
            // Set up the connection!
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://127.11.42.130:3306/java_projects", USER, PASS);
            statement = connection.prepareStatement(query);
            
            // Now execute it!
            ResultSet values = statement.executeQuery();            
            
            if (values != null) {
                // Grab the values!
                if (query.contains("users") && !query.contains("posts")) {
                    values.first();
                    Map<String, String> saveValues = new HashMap<String, String>();
                    saveValues.put("name", values.getString(2));
                    saveValues.put("pass", values.getString(1));
                    
                    // Add it the list
                    results.add(0, saveValues);
                } else {
                    int index = 0;
                    while (values.next()) {
                        Map<String, String> saveValues = new HashMap<String, String>();
                        
                        // Grab the values!
                        saveValues.put("name", values.getString("name"));
                        saveValues.put("text", values.getString("post_text"));
                        saveValues.put("date", values.getString("created_at"));
                        
                        // Put it on the list!
                        results.add(index++, saveValues);
                    }                    
                }
            }
        } catch (SQLException m) {
            m.printStackTrace();
        } catch (ClassNotFoundException m) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, m);
        } finally {
            // Make sure to close everything!                                                     
            try{
               if (statement != null) {
                  statement.close();
               }
            } catch(SQLException se) {
                se.printStackTrace();
            }
            try {
               if (connection != null) {
                  connection.close();
               }
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }

        return results;
    }
}
