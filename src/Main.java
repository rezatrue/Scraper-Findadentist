import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	


	//final static String URL = "file:///F:/Scraping/SS/Dr.%20Olena%20Norris_%20American%20Dental%20Association.html";
	final static String URL = "http://technogearup.com/";
	public static void main(String[] args) {
		
		FireFoxHandaler fireFoxHandaler = new FireFoxHandaler();
		
		// R&D
		//String prox = "186.118.169.85:8080";
		//fireFoxHandaler.openBrowser(prox, URL);
		//fireFoxHandaler.getDentistData(URL);
		
		
		ListUrl listUrl = new ListUrl();
		
		LoadUserAgent userAgent = new LoadUserAgent();
		
		LoadProxy listProxy = new LoadProxy();
		LinkedList<String> proxies = listProxy.getProxy();
		Iterator<String> itProx = proxies.iterator();
		
		
		
		int count = 0;
		LinkedList<String> lUrl = listUrl.getListUrl();
		for (String url : lUrl) {
			if(count == 0) {
				if(!itProx.hasNext()) break;
				fireFoxHandaler.openBrowser(itProx.next(), userAgent.getRandomUserAgent());
			}
			fireFoxHandaler.getDentistData(url);
			
			count++;
			if(count >= 10) {
				count = 0;
				fireFoxHandaler.closeBrowser();
			}
			
		}
		
		if(fireFoxHandaler.isBrowserOpen()) fireFoxHandaler.closeBrowser();
	
		LinkedList<Dentist> infos = fireFoxHandaler.getStoresInfo();
		
		CsvGenerator csvGenerator = new CsvGenerator();
		csvGenerator.listtoCsv(infos);
		
		//writeFile("Errors", fireFoxHandaler.errorCountries);
		
	}
	
	public static void writeFile(String name, LinkedList<String> url) {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(name+".txt", "UTF-8");
			writer.println("[");
			for(int i=0; i< url.size(); i++) {
				writer.println("\"" + url.get(i) + "\", ");
			}
			writer.println("]");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
