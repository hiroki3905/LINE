package ikari;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonSample implements Fetch {

	final String className = ".a-link-normal.a-text-normal";
	final String URL = "https://www.amazon.co.jp/s?i=stripbooks&k=";
	
	/**
	 * amazonブックで検索し、(検索ボックスに文字列を入れた状態)ヒットした本の名前
	 * (今は、ページに表示される本全てが入っている) の一覧を取得
	 * @param serch_str 検索ボックスに入れる文字列。作者とか著者とかは気にしてない
	 */
	@Override
	public List<String> getTitles(String serch_str){
		var e = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(URL + serch_str).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        Elements newsHeadlines = doc.select(className);
        for (Element headline : newsHeadlines) {
            e.add(headline.child(0).ownText());
        }
		return e;
	}

	
	/**
	 * 
	 */
	@Override
	public void view_sample() {
		Document doc = null;
		try {
			doc = Jsoup.connect(URL + "ミランクンデラ").get();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
        Elements newsHeadlines = doc.select(className);
        System.out.println("\n-----------Amazonの本一覧-----------\n\n");
        
        for (Element headline : newsHeadlines) {
        	String title = headline.child(0).ownText();
        	if(title == "" || title == null) {
        		continue;
        	}
        	String decodedUrl = "";
        	
			try {
				decodedUrl = URLDecoder.decode(headline.absUrl("href"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			decodedUrl = decodedUrl.substring(0,decodedUrl.indexOf("?"));
            System.out.println("title: " + title + "\tlink: " + decodedUrl);
        }
	}

}
