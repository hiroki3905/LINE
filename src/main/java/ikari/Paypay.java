package ikari;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Paypay implements Fetch {

	private static String URL = "https://paypayfleamarket.yahoo.co.jp/search/ミランクンデラ";
	//private static String URL = "https://jp.mercari.com";
	static String  CLASS = ".jRXEcC";
	@Override
	public List<String> getTitles(String serch_name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static void main(String... args) {
		try {
			new Paypay().view_sample();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void view_sample() throws IOException {
		Document doc = null;

		/* 属性の中に、"price:***" の形で値段が入っているため、そのパターンを読みだす */
		Pattern p = Pattern.compile("price:[0-9]+");
		doc = Jsoup.connect(URL).get();

		Elements newsHeadlines = doc.select(".sc-6dae2d2e-0.jRXEcC");
		System.out.println("\n-----------Amazonの本一覧-----------\n\n");

		for (Element headline : newsHeadlines) {
			Matcher matcher = p.matcher(headline.attr("data-cl-params"));
			if (matcher.find()) {
				// マッチした部分文字列の表示を行う
				System.out.print( matcher.group() + "  " );
			} else {
				System.out.println("値段を取得できませんでした");
			}
			System.out.println(headline.child(0).attr("alt"));
		}
	}



}
