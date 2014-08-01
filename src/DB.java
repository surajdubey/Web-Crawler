
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DB {
    
    String URL = "jdbc:mysql://localhost:3306/crawler";
    String username = "root";
    String password = "123456";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    static String tableName = "Record";
    DB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(URL , username , password);
            
            stmt = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ResultSet runExecuteQuery(String sql) throws SQLException
    {
        return stmt.executeQuery(sql);
    }
    
    public boolean runExecute(String sql) throws SQLException
    {
        return stmt.execute(sql);
    }
    
    
    
}
