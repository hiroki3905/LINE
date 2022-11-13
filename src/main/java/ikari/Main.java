package ikari;

import java.io.IOException;

/**
 * コンソールでの実行を試してみる
 * @author omaoma-code
 *
 */
public class Main {
	public static void main(String... args) {
		
		Fetch amazon = new AmazonSample();
		Fetch mercari = new Mercari();
		Fetch paypay = new Paypay();
		//amazon.getTitles("ミランクンデラ").forEach((e) -> System.out.println(e));
		
		Fetch yahoo = new YahooSample();
		try {
			yahoo.view_sample();
			System.out.println("\n-----------Amazonの本一覧-----------\n\n");
			amazon.view_sample();
			System.out.println("\n-----------メルカリの本一覧-----------\n\n");
			mercari.view_sample();
			System.out.println("\n-----------ペイペイフリマの本一覧-----------\n\n");
			paypay.view_sample();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		

		
	}
}
