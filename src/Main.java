/**
 * This program generates list of all available areas and pin codes.
 * It will first display list of areas.
 * Then it'll display list of pin codes
 * The output order remains the same
 */
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    
    static String urlToParse = "http://www.asklaila.com/Hyderabad-Locality-Guides?pn=3";

    public static void main(String args[]) throws IOException
    {
        process(urlToParse);
    }
    
    public static void process(String URL) throws IOException
    {
        ArrayList<String> list = new ArrayList<>();
            
        //get list of all area
        Document doc = Jsoup.connect(urlToParse).get();
        Elements body = doc.select("div#all-content ul li");
        
        for(Element temp: body)
        {
            System.out.println(temp.text());
            String newUrl = temp.select("a").attr("href"); //get pin code url address
            list.add(newUrl); //add address
         }
        System.out.println("");
        for(String s: list)
        {
            doc = Jsoup.connect(s).timeout(0).get(); //infinite timeout
            System.out.println(doc.select("div#all-content h2").get(1).text()); //get text
        }
         
    }
    
}