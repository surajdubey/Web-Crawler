
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    
    public static DB db = new DB();
    static String urlToParse = "http://www.mapsofindia.com/pincode/india/andhra-pradesh/hyderabad/";
    public static void main(String args[]) throws SQLException, IOException
    {
        process(urlToParse);
    }
    
    public static void process(String URL) throws SQLException, IOException
    {
        Document doc = Jsoup.connect(urlToParse).get();
        Elements body = doc.select("table>tbody>tr>td");

        String result = body.text();
        String text[] = result.split(" ");
        
        for(String r:text)
        {
            if(r.length() == 6 && isPinCode(r))
            {
                try{
                String sql = "INSERT INTO PinCodeList(pincode) VALUES('"+r+"')"; 
                db.runExecuteUpdate(sql);
                System.out.println("New Pin Code "+r);
                
                }
                catch(SQLException e)
                {
                    //duplicate entry
                }
            }
        }

        
    }
    
    static boolean isPinCode(String str)
    {
        try{
           
            Integer.parseInt(str);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
}
