
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    
    public static DB db = new DB();
    static String urlToParse = "https://www.jabong.com/onecheckout/widget/lookup/postcode/401107/?YII_CSRF_TOKEN=30fb4044ea1cb2352cd23e0b5e41e2b56055fd8c";
    public static void main(String args[]) throws SQLException, IOException
    {
        process(urlToParse);
    }
    
    public static void process(String URL) throws SQLException, IOException
    {
        System.setProperty("javax.net.ssl.trustStore", "/home/suraj/Desktop/work/truestore/www.jabang.com.jks");
        Document doc = Jsoup.connect(urlToParse).get();
        Elements body = doc.select("body");
        
        String result = body.text();
        System.out.println(result);
        /*String text[] = result.split(" ");
        
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
        }*/

        
    }
    /*
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
    */
}
