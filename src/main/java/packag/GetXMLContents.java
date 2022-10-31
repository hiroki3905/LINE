package packag;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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


public class GetXMLContents{
	public ArrayList<ArrayList<String>> getXMLContents(int i,ArrayList<ArrayList<String>> p,String url) throws SAXException, IOException, ParserConfigurationException{

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
		Element rootElement1 = document.getDocumentElement();
		Element rootElement2 = document.getDocumentElement();
		Element rootElement3 = document.getDocumentElement();

		NodeList nodeList1 = rootElement1.getElementsByTagName("dcterms:title");
		NodeList nodeList2 = rootElement1.getElementsByTagName("dc:creator");
		NodeList nodeList3 = rootElement1.getElementsByTagName("dcndl:publicationName");

		for(int x = 0;x < i;x++) {
			Element elem1 = (Element)nodeList1.item(x);
			Element elem2 = (Element)nodeList2.item(x);
			Element elem3 = (Element)nodeList3.item(x);
			ArrayList<String> list;
			list = new ArrayList<String>();
			list.add(elem1.getFirstChild().getNodeValue());
			list.add(elem2.getFirstChild().getNodeValue());
			list.add(elem3.getFirstChild().getNodeValue());

			p.add(list);

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