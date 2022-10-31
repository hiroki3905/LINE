package packag;

import java.util.ArrayList;
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
		String year = "2018";	
		
		Fujii a = new Fujii();
	
		
		ArrayList<ArrayList<String>> sample_list = new ArrayList<ArrayList<String>>();
		//System.out.println(getXMLContents(num,sample_list,tag,finalEncodedUrl));
		
		
		GetXMLContents getXML = new GetXMLContents();
		getXML.getXMLContents(num,sample_list,a.URLencode(num, serch, year));
		
		
		for(int i=0; i<sample_list.size(); i++){
            System.out.printf("%02d\t", i);
            for(int j=0; j<sample_list.get(i).size(); j++){//←それぞれの行の要素数

                  String str = sample_list.get(i).get(j);
                  //インデントを気にせず表示
                  System.out.println(str + "\t");

            }
            System.out.println();//改行
      }
      System.out.println("end");
  }

}