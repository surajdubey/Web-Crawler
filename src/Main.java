import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suraj
 */
public class Main {
    
    public static DB db = new DB();
    static String urlToParse = "http://www.asklaila.com/Hyderabad-Locality-Guides?pn=3";
    static String tempUrl = urlToParse;
    public static void main(String args[]) throws SQLException, IOException
    {
        process(urlToParse);
    }
    
    public static void process(String URL) throws SQLException, IOException
    {
        String sql = "select * from Record where URL = '"+URL+"'";
        ResultSet rs = db.runExecuteQuery(sql);
        ArrayList<String> list = new ArrayList<String>();
        //if(!rs.next())//new Url found
        if(true)
        {
            //store the URL to database to avoid parsing again
            sql = "INSERT INTO  `crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
            PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, URL);
            stmt.execute();
            
            //get info required
            
            Document doc = Jsoup.connect(urlToParse).get();
            Elements body = doc.select("div#all-content ul li");
            
            //Elements ah = doc.select("div#all-content ul li a");
            
            //System.out.println(doc.text());
            for(Element temp: body)
            {
                System.out.println(temp.text());
                String newUrl = temp.select("a").attr("href");
                //System.out.println(newUrl);
                list.add(newUrl);
                /*doc = Jsoup.connect(newUrl).get();
                Element pinCode = doc.select("div#all-content h2").get(1);
                System.out.println(pinCode.text());
                */
            }
            System.out.println("");
            for(String s: list)
            {
                doc = Jsoup.connect(s).timeout(0).get();
                System.out.println(doc.select("div#all-content h2").get(1).text());
                
                
            }
            /*System.out.println(URL);
            //get all links and recursively call the processPage method
            
            Elements ques = doc.select("a[href]");
            
            // for each element of ques
            for(Element link:ques)
            {
                if(link.attr("href").contains(tempUrl))
                {
                    process(link.attr("abs:href"));
                }
            }
            
            */
        }
    }
    
}