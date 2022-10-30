package ikari;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YahooSample implements Fetch {

	final static String URL = "https://news.yahoo.co.jp";
	final static String className = ".sc-btzYZH.kitJFB";
	
	
	/**
	 * ヤフーニュースのトップニュース(左上にあるやつ)のニュースタイトルを取得
	 * @param serch_str 今のところ意味なし
	 */
	@Override
	public List<String> getTitles(String serch_str){
		var e = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        Elements newsHeadlines = doc.select(className);
        System.out.println("start");
        for (Element headline : newsHeadlines) {
            e.add(headline.ownText());
        }
		return e;
	}

	
	/**
	 * トップニュースのタイトルと、飛ぶページのURLを表示
	 */
	@Override
	public void view_sample() {
		Document doc = null;
		
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
        Elements newsHeadlines = doc.select(className);
        System.out.println("\n-----------ヤフーニュースのメイン記事一覧-----------\n\n");
        
        for (Element headline : newsHeadlines) {
            System.out.println("title: " + headline.ownText() +
            		",  href: " + headline.absUrl("href"));
        }
	}
}