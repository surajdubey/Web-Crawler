
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    
    public static DB db = new DB();
    static String urlToParse = "http://www.citypincode.in/ANDHRA_PRADESH/Hyderabad";
    public static void main(String args[]) throws SQLException, IOException
    {
        process(urlToParse);
    }
    
    public static void process(String URL) throws SQLException, IOException
    {
        String sql = "select * from Record where URL = '"+URL+"'";
        ResultSet rs = db.runExecuteQuery(sql);
        
        if(!rs.next())//new Url found
        {
            //store the URL to database to avoid parsing again
            sql = "INSERT INTO  `crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
            PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, URL);
            stmt.execute();
            
            //get info required
            
            Document doc = Jsoup.connect(urlToParse).get();
            //Elements body = doc.select("a[href]");
            //System.out.println(doc.text());
            //System.out.println(body.text());
            System.out.println(URL);
            //get all links and recursively call the processPage method
            
            Elements ques = doc.select("a[href]");
            
            // for each element of ques
            for(Element link:ques)
            {
                process(link.attr("abs:href"));
                /*if(link.attr("href").contains("mit.edu"))
                {
                    process(link.attr("abs:href"));
                }*/
            }
            
            
        }
    }
    
}
