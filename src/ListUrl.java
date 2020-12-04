import java.util.Arrays;
import java.util.LinkedList;

public class ListUrl {

	
	public LinkedList<String> getListUrl(){
		String[] sa = new String[] {
				"https://www.httpbin.org/ip"
				
		};
		 // "https://www.breitling.com/us-en/stores/us/ny/new-york/",
	    // convert array to LinkedList
	    LinkedList<String> ll = new LinkedList<String>(Arrays.asList(sa));
	    return ll;
	}
	
}
