import java.util.Arrays;
import java.util.LinkedList;

public class LoadProxy {

	public LoadProxy() {}
	
	public int count() {
		return getProxy().size();
	}
	
	public LinkedList<String> getProxy(){
		String[] sa = new String[] {
				"206.123.150.152:139",
				"206.123.150.152:139"
				
		}; 
		
		LinkedList<String> ll = new LinkedList<String>(Arrays.asList(sa));
	    return ll;
	}
	
}
