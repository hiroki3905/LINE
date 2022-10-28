package packag;

import java.io.IOException;
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
		List<String> sample_list = new ArrayList<String>();
		String url = "https://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=title%3d%22%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%22&recordPacking=xml&recordSchema=dcndl";
		String tag = "dcterms:title";
		System.out.println(getXMLContents(10,sample_list,tag,url));
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
