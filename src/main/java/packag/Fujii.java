package packag;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



public class Fujii {

	public String URLencode(int num,String serch,String year) throws SAXException, IOException, ParserConfigurationException, URISyntaxException{
		
		final String liblary_url = "https://iss.ndl.go.jp/api/sru?operation=searchRetrieve&recordPacking=xml&recordSchema=dcndl&query=";
		
		
		String q = "title=\""+ serch + "\""+" AND from=" + year + "\"";
		URL urlToEncode = new URL(liblary_url + q);

			URI uri = new URI(urlToEncode.getProtocol(),
					urlToEncode.getUserInfo(),
					IDN.toASCII(urlToEncode.getHost()),
					urlToEncode.getPort(),
					urlToEncode.getPath(),
					urlToEncode.getQuery(), urlToEncode.getRef());

			String finalEncodedUrl = uri.toASCIIString();
			return finalEncodedUrl;


	}


}
