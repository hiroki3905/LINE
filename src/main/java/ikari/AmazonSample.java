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

/**
 * 
 * @author omaoma-code
 *
 */
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

	
	public static void main(String... args) {
		try {
			new AmazonSample().view_sample();
			new AmazonSample().other_view();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws IOException 
	 */
	@Override
	public void view_sample() throws IOException {
		Document doc = null;
		doc = Jsoup.connect(URL + "ミランクンデラ").get();

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

	/**
	 * 本の情報を1つずつ取る形で実行。
	 * 1つのファイルは重くなるので、どちらが速いかな?
	 * @throws IOException
	 * @see {@link AmazonSample#view_sample()}
	 */
	public void other_view() throws IOException {
		Document doc = null;
		doc = Jsoup.connect(URL + "ミランクンデラ").get();
		Elements newsHeadlines = doc.select(".sg-row");
		System.out.println("\n-----------Amazonの本一覧-----------\n\n");
		
		/* headline : 本の情報全て(HTMLで本1つの領域すべて取得) */
		for (Element headline : newsHeadlines) {
			/* 名前とリンクが入っている部分を、headline中から取得 */
			Element name = headline.select(className).first();
			if(name == null)
				continue;
			
			String title = name.child(0).ownText();
			if(title == "" || title == null) {
				continue;
			}			
			
			/* 値段情報を取得 */
			String priceStr = headline.select(".a-price-whole").first().ownText();
			int price = Integer.parseInt(priceStr.replace("￥", "").replace(",", ""));
			String decodedUrl = "";
			try {
				decodedUrl = URLDecoder.decode(name.absUrl("href"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			decodedUrl = decodedUrl.substring(0,decodedUrl.indexOf("?"));
			System.out.println("title: " + title + "\tlink: " + decodedUrl + "\tprice: " + price);
		}
	}
	
}
