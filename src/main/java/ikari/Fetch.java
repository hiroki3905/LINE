package ikari;

import java.io.IOException;
import java.util.List;
/**
 * スクレイピングを行う関数を定義するインタフェース
 * @author omaoma-code
 *
 */
public interface Fetch {
	/**
	 * 検索文字列から該当するタイトルを探し出す
	 * @param serch_name 検索文字列。本の名前、著作者など
	 * @return 検索結果が入った、文字列のリスト
	 */
	public List<String> getTitles(String serch_name);
	
	/**
	 * それぞれの動きのサンプルをコンソールに出力
	 */
	public void view_sample() throws IOException;
}
