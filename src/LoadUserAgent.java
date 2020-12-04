import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LoadUserAgent {
	List<String> userAgents = new ArrayList<>();
	
	public LoadUserAgent() {
		
		BufferedReader br = null ;
			try {
				br = new BufferedReader(new FileReader(new File("user-agents.txt")));	
				String sCurrentline;
				while((sCurrentline = br.readLine()) != null) {
					userAgents.add(sCurrentline);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		
	}
	
	public String getRandomUserAgent() {
		Random rand = new Random();
		return userAgents.get(rand.nextInt(userAgents.size()));
		
	}
	
	
	
	
	
	
	
}
