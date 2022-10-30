package ikari;

/**
 * コンソールでの実行を試してみる
 * @author omaoma-code
 *
 */
public class Main {
	public static void main(String... args) {
		
		Fetch yahoo = new YahooSample();
		yahoo.view_sample();
		
		Fetch amazon = new AmazonSample();
		System.out.println("\n-----------Amazonの本一覧-----------\n\n");
		amazon.getTitles("ミランクンデラ").forEach((e) -> System.out.println(e));
		amazon.view_sample();
	}
}
