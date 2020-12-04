import java.util.Arrays;
import java.util.LinkedList;

public class City {

	public int count() {
		return getCity().size();
	}
	
	public LinkedList<String> getCity(){
		String[] sa = new String[] {
				"United States",
				"Canada",
				"Austria",
				"Belgium",
				"Czechia",
				"Denmark",
				"Estonia",
				"Finland",
				"France",
				"Germany",
				"Greece",
				"Hungary",
				"Iceland",
				"Italy",
				"Latvia",
				"Liechtenstein",
				"Lithuania",
				"Luxembourg",
				"Malta",
				"Netherlands",
				"Norway",
				"Poland",
				"Portugal",
				"Slovakia",
				"Slovenia",
				"Spain",
				"Sweden",
				"Switzerland",
				"Moscow, Russia",
				"India",
				"Kazakhstan",
				"Saudi Arabia",
				"Indonesia",
				"Iran",
				"Mongolia",
				"Pakistan",
				"Turkey",
				"Myanmar",
				"Afghanistan",
				"Yemen",
				"Thailand",
				"Turkmenistan",
				"Uzbekistan",
				"Iraq",
				"Japan",
				"Vietnam",
				"Malaysia",
				"Oman",
				"Philippines",
				"Laos",
				"Kyrgyzstan",
				"Syria",
				"Cambodia",
				"Bangladesh",
				"Nepal",
				"Tajikistan",
				"North Korea",
				"South Korea",
				"Jordan",
				"Azerbaijan",
				"United Arab Emirates",
				"Georgia",
				"Sri Lanka",
				"Bhutan",
				"Taiwan",
				"Armenia",
				"Israel",
				"Kuwait",
				"Timor-Leste",
				"Qatar",
				"Lebanon",
				"Cyprus",
				"Palestine",
				"Brunei",
				"Bahrain",
				"Singapore",
				"Maldives",
				"Ecudor",
				"Colombia",
				"Cuba",
				"panama",
				"Aruba",
				"Chile",
				"Uruguay",
				"Paraguay",
				"Peru",
				"United Kingdom",
				"China",
				"Hong Kong"

		};
		 
	    // convert array to LinkedList
	    LinkedList<String> ll = new LinkedList<String>(Arrays.asList(sa));
	    return ll;
	}
	
	
	
}
