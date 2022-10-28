package packag;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Fujii {

	
	public static void main(String args[]) throws Exception{
		String q = "title=\"プログラミング\"";
        URL urlToEncode = new URL("https://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordPacking=xml&recordSchema=dcndl&query=" + q);

        try {
            URI uri = new URI(urlToEncode.getProtocol(),
                    urlToEncode.getUserInfo(),
                    IDN.toASCII(urlToEncode.getHost()),
                    urlToEncode.getPort(),
                    urlToEncode.getPath(),
                    urlToEncode.getQuery(), urlToEncode.getRef());

            String finalEncodedUrl = uri.toASCIIString();
            System.out.println("Encoded Url: " + finalEncodedUrl);
            List<String> sample_list = new ArrayList<String>();
    		String tag = "dcterms:title";
    		System.out.println(getXMLContents(10,sample_list,tag,finalEncodedUrl));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
	}
    
 

    /*
     *XMLからコンテンツを取得するメソッド
     *(取り出す要素数,データを入れるリスト,url)
    */
	
	
    public static List<String> getXMLContents(int i,List<String> p,String tag,String url) throws SAXException, IOException, ParserConfigurationException{

    		
            Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
            Element rootElement=document.getDocumentElement();
            NodeList nodeList = rootElement.getElementsByTagName(tag);

            for(int x = 0;x <i;x++) {
            Element elem = (Element)nodeList.item(x);
            p.add(elem.getFirstChild().getNodeValue());
            }
            return p;
  
    }
}
