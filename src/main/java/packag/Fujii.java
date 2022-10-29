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
		//////////////////////////////////////
		String serch = "プログラミング"; //検索ワード
		int num = 10;                    //結果を何個表示するか
		String tag = "dcterms:title";    //表示するタグ

		String year = "2018";	
		/////////////////////////////////////////
		
		String q = "title=\""+ serch + "\""+" AND from=" + year + "\"";
		URL urlToEncode = new URL("https://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordPacking=xml&recordSchema=dcndl&query=" + q);

		try {
			URI uri = new URI(urlToEncode.getProtocol(),
					urlToEncode.getUserInfo(),
					IDN.toASCII(urlToEncode.getHost()),
					urlToEncode.getPort(),
					urlToEncode.getPath(),
					urlToEncode.getQuery(), urlToEncode.getRef());

			String finalEncodedUrl = uri.toASCIIString();
			List<String> sample_list = new ArrayList<String>();
			//System.out.println(getXMLContents(num,sample_list,tag,finalEncodedUrl));
			getXMLContents(num,sample_list,tag,finalEncodedUrl);
			System.out.println(sample_list);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	/**
	 * XMLからコンテンツを取得するメソッド
	 * 
	 * @param i 取り出す要素数
	 * @param p データを入れるリスト
	 * @param tag 取り出したいデータのタグ名
	 * @param url データ元のURL(クエリ含む)
	 * @return	List 持ってきたデータのリスト(pの要素が返される)
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * 
	 * 
	 */
	public static List<String> getXMLContents(int i,List<String> p,String tag,String url) throws SAXException, IOException, ParserConfigurationException{

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
		Element rootElement = document.getDocumentElement();
		NodeList nodeList = rootElement.getElementsByTagName(tag);

		for(int x = 0;x < i;x++) {
			Element elem = (Element)nodeList.item(x);
			p.add(elem.getFirstChild().getNodeValue());
		}
		return p;
		
		/*
		 * TODO 結果を返す方法が2重になっているので、
		 * ➀関数内でインスタンスを作って返り値で返す 
		 * ➁引数で受け取ったインスタンスを書き換える
		 * のどちらかにした方がよい?
		 */
	}
}
