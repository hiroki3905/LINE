package packag;

import java.io.IOException;
import java.util.List;

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
	public List<String> getXMLContents(int i,List<String> p,String tag,String url) throws SAXException, IOException, ParserConfigurationException{

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