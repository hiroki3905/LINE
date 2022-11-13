package ikari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * メルカリから情報を取得。Javaで作るのが難しかったのでPythonを実行
 * @author omaoma-code
 * @see Fetch
 *
 */
public class Mercari implements Fetch {

	
	@Override
	public List<String> getTitles(String serch_name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static void main(String... args) throws IOException {
		new Mercari().view_sample();
	}

	/**
	 * pythonプログラムを実行して本の名前、値段を出力させ、データを取得
	 */
	@Override
	public void view_sample() throws IOException {
		
		/* コマンドで、「python (プログラム名)」 を実行。 */
		ProcessBuilder processBuilder = new ProcessBuilder("python", "src\\main\\py_code\\mercari\\hoge.py");
		Process process = processBuilder.start();
		
		/* 標準出力に出力された文字列を取得 */
        try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF8"))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        }
	}



}
