
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DB {
    
    //String URL = "jdbc:mysql://localhost:3306/servesy";
    String URL = "jdbc:mysql://localhost:3306/crawler";
    
    String username = "root";
    String password = "123456";

    static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    static String tableName = "PinCodeList";
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
    
    public void runExecuteUpdate(String sql) throws SQLException
    {
        stmt.executeUpdate(sql);
    }

    
    
    
}
