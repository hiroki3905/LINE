package packag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * asdfas
 * @author kouda
 *
 */
public class Main {
	/**
	 * Hello
	 * @param args
	 */
	public static void main(String args[]) throws Exception{
		System.out.print("検索する図書名を入力してください:");
		Scanner scan = new Scanner(System.in);
		String serch = scan.nextLine();
		int num = 10;                    //結果を何個表示するか
		String tag = "dcterms:title";    //表示するタグ
		String year = "2018";	
		
		Fujii a = new Fujii();
		a.URLencode(num, serch, tag, year);
		
		List<String> sample_list = new ArrayList<String>();
		//System.out.println(getXMLContents(num,sample_list,tag,finalEncodedUrl));
		
		
		GetXMLContents getXML = new GetXMLContents();
		getXML.getXMLContents(num,sample_list,tag,finalEncodedUrl);
		System.out.println(sample_list);

}
}