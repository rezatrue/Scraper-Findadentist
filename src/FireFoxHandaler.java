import org.openqa.selenium.chrome.*;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


public class FireFoxHandaler {
	WebDriver driver;
	private LinkedList<Dentist> dentists;
	private LinkedList<Country> countries;
	private LinkedList<City> cities;
	public LinkedList<String> errorCountries;
	
	FireFoxHandaler(){
		dentists = new LinkedList<>();
		countries = new LinkedList<>();
		cities = new LinkedList<>();
		errorCountries = new LinkedList<>();
	}
	
	public LinkedList<Dentist> getStoresInfo(){
		return dentists;
	}
	
	
	public boolean openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver();
		return true;
	}
	
	public boolean openBrowser(String proxy, String userAgent) {
		runfirefoxHeadlessProxy(proxy, userAgent);
		
		//driver.get("https://www.httpbin.org/headers");
		//String s = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
		
		return true;
	}
		
	public void loadUrl(String url) {
		driver.get(url);
		System.out.println("---");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public boolean isBrowserOpen() {
		try {
			driver.findElement(By.tagName("html"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	public void closeBrowser() {
		driver.close();	
	}
	

	public void getDentistData(String url){
		
		By nameBy = By.xpath("//div[@class=\"dentist-details__item\"]//div[@class=\"name\"]/h1");
		By phoneBy = By.xpath("//div[@class=\"dentist-details__item\"]//div[@class=\"contact-info\"]//p[@class=\"phone\"]");
		By websiteBy = By.xpath("//div[@class=\"dentist-details__item\"]//div[@class=\"contact-info\"]//dt[contains(text(),\"Website\")]/following-sibling::dd/a");
		//By addressBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Address\")]/following-sibling::dd");
		By addressBy = By.xpath("//section[contains(@class,\"dentist-details__address\")][2]//dt[contains(text(),\"Address\")]/following-sibling::dd");
		
		
		//By payment_optionsBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Payment options\")]/following-sibling::dd[1]");
		By payment_optionsBy = By.xpath("//dl/dt[contains(text(),\"Payment options\")]/following-sibling::dd[1]");
		By insuranceBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Insurance\")]/following-sibling::dd[1]");
		By genderBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Gender\")]/following-sibling::dd[1]");
		//By specialtiesBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Specialties\")]/following-sibling::dd[1]");
		By specialtiesBy = By.xpath("//dl[@class=\"definition-list--last\"]//dt[contains(text(),\"Specialties\")]/following-sibling::dd[1]");
		
		By languageBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Language\")]/following-sibling::dd[1]");
		By educationBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Education\")]/following-sibling::dd[1]");
		By practice_descriptionBy = By.xpath("//div[@class=\"dentist-details__item\"]//dt[contains(text(),\"Practice description\")]/following-sibling::dd[1]");
		
		loadUrl(url);
		
		
		By capturBy = By.xpath("//button[contains(.,\"Verify\")]");
		if(isElementPresent(capturBy)) {
			try {
				takeSnapShot(driver, "F://Scraping//SS//test.png") ; 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//waitUntillVisible(nameBy);
		
		Dentist dentist = new Dentist();
		dentist.setProfileUrl(url);
		
		try {
			String name = driver.findElement(nameBy).getText();
			dentist.setName(name);
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String phone = driver.findElement(phoneBy).getText();
			dentist.setPhone(phone);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String website = driver.findElement(websiteBy).getText();
			dentist.setWebsite(website);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		
		try {
			String address = driver.findElement(addressBy).getText();
			dentist.setAddress(address);
			System.out.println(address);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String payment_options = driver.findElement(payment_optionsBy).getText();
			dentist.setPayment_options(payment_options);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String insurance = driver.findElement(insuranceBy).getText();
			dentist.setInsurance(insurance);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String gender = driver.findElement(genderBy).getText().trim();
			dentist.setInsurance(gender);
			System.out.println(gender);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String specialties = driver.findElement(specialtiesBy).getText();
			dentist.setSpecialties(specialties);
			System.out.println(specialties);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String language = driver.findElement(languageBy).getText();
			dentist.setLanguage(language);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String education = driver.findElement(educationBy).getText();
			dentist.setEducation(education);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String practice_description = driver.findElement(practice_descriptionBy).getText();
			dentist.setPractice_description(practice_description);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		dentists.add(dentist);
		
	}	
	
	private void acceptCookies() {
		By buttonAcceptCookyBy = By.xpath("//div[contains(@class, \"widget-cookie\")][@role=\"alertdialog\"]//button[child::span[contains(.,\"decline\")]]");
		
		if(isElementPresent(buttonAcceptCookyBy)) {
			//waitUntillCkickable(buttonAcceptCookyBy);
			driver.findElement(buttonAcceptCookyBy).click();
		}
		
	}
	
	
	private void closeSubscription() {
//		By closeSubscriptionBy = By.xpath("//div[@class=\"newsletter-popin-header\"]/div/span[@class=\"icon-newsletter icon-cross\"]");
//		driver.findElement(closeSubscriptionBy).click();
		try {
			By closeSubscriptionBy = By.xpath("//div[@class=\"newsletter-popin-header\"]/div/span[@class=\"icon-newsletter icon-cross\"]");
			Thread.sleep(60000);
			driver.findElement(closeSubscriptionBy).click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setFiltter() {
		
		By listBy = By.xpath("//div[@class=\"view-mode\"]/button[@class=\"list\"]");
	
		try{driver.findElement(listBy).click();}catch (Exception e) {;}
		
	
	}
	
	//...........................................

	
    public void resizeBrowser() {
        Dimension d = new Dimension(800,700);
        //Resize current window to the set dimension
        driver.manage().window().setSize(d);
    }	
	

	//..................................................
	
	LinkedList<String> storeUrl;
	public LinkedList<String> getStoreLink(){
		
		return new LinkedList<String>(storeUrl); 
	}
	
	
	public boolean isSearchPage() {
				
		By searchPageBy = By.xpath("//div[@id=\"retailers-form\"]");
		WebElement webElement = driver.findElement(searchPageBy);
		String searchVisiblility = webElement.getCssValue("visibility");
		System.out.println(searchVisiblility);
		
		if(searchVisiblility.contains("visible"))
			return true;
		else
			return false;
	}
	
	public boolean isStorePage() {
		
		//By searchPageBy = By.xpath("//div[@id=\"retailers-form\"]");
		By storePageBy = By.xpath("//div[@id=\"retailers-results\"]");
		
		WebElement webElement = driver.findElement(storePageBy);
		String searchVisiblility = webElement.getCssValue("visibility");
		System.out.println(": -- "+searchVisiblility);
		
		if(searchVisiblility.contains("visible"))
			return true;
		else
			return false;
	}
	
	
	private void printCountries() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Countries.txt", "UTF-8");
			writer.println("[");
			for(int i=0; i< countries.size(); i++) {
				writer.println("\"" + countries.get(i).getName() + "\", \"" + countries.get(i).getLink() + "\"");
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
	
	private void printCities() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Cities.txt", "UTF-8");
			writer.println("[");
			for(int i=0; i< cities.size(); i++) {
				writer.println("\"" + cities.get(i).getCountry() + "\", \"" + cities.get(i).getCity() + "\", \"" +cities.get(i).getLink() + "\"");
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
	
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	private String mainTabHandaler = null; 
	private String subWindowHandler = null; 
	
	public void createSubTab() {
		mainTabHandaler = driver.getWindowHandle();
		
		//((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
		((JavascriptExecutor) driver).executeScript("window.open();");
	   	  Set<String> handles = driver.getWindowHandles();
		  Iterator<String> iterator = handles.iterator();
		  while (iterator.hasNext()) {
		   subWindowHandler = iterator.next();
		  }
	}
	
	public void switchSubTab() {
		driver.switchTo().window(subWindowHandler);
	}
	public void switchMainTab() {
		driver.switchTo().window(mainTabHandaler);
	}



	// ---------------------------------------------------------------------------------------------------//	

	private void scrollToViewElement(By by) {
		
		try {
			WebElement element = driver.findElement(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void waitUntillCkickable(By by) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private boolean waitUntillVisible(By by) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2*60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void fullPageScroll() {
		// https://stackoverflow.com/questions/42982950/how-to-scroll-down-the-page-till-bottomend-page-in-the-selenium-webdriver
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("scroll(0, 250);");
			Thread.sleep(1000);
			jse.executeScript("scroll(0, 550);");
			Thread.sleep(1000);
			jse.executeScript("scroll(0, 750);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1050);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 2250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 3250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 4250);");
			Thread.sleep(500);
//			
//			jse.executeScript("scroll(0, 5250);");
//			Thread.sleep(1000);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if I direct go to bottom of the page page full content don't load
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// running system installed firefox [Testing date : 10 Mar 2018]
	// requires : selenium-server-standalone-3.10.0.jar,
	// client-combined-3.10.0.jar & geckodriver.exe

	
	protected void runfirefoxHeadlessProxy(String proxyName, String userAgent) {

	   FirefoxBinary firefoxBinary = new FirefoxBinary();
	   //firefoxBinary.addCommandLineOptions("--headless");
	   System.setProperty("webdriver.gecko.driver", "Geckodriver\\v0.26.0-win64\\geckodriver.exe");
	   FirefoxOptions firefoxOptions = new FirefoxOptions();
	   firefoxOptions.setBinary(firefoxBinary);
	   
	   // 1st way
	   Proxy proxy = new Proxy();
	   proxy.setProxyType(Proxy.ProxyType.MANUAL);
	   proxy.setHttpProxy(proxyName).setFtpProxy(proxyName).setSslProxy(proxyName);
	   
	    // 2nd way 
	   /*
	   String proxyProxyHost = "proxy.packetstream.io"; 
	   String proxyPort = "31112";
	   String proxyUserName = "infoalireza20";
	   String proxyPassword = "QPuUmJ7NM9bZNQVR";
	   firefoxOptions.addPreference("general.useragent.override", userAgent);
	   
	   Proxy proxy = new Proxy();
	   proxy.setProxyType(Proxy.ProxyType.MANUAL);
	   //proxy.setProxyAutoconfigUrl(proxyProxyHost);
	   proxy.setHttpProxy(proxyProxyHost+":"+proxyPort).setFtpProxy(proxyProxyHost+":"+proxyPort).setSslProxy(proxyProxyHost+":"+proxyPort);
	   proxy.setSocksUsername(proxyUserName);
	   proxy.setSocksPassword(proxyPassword);
	   */
	   
	   firefoxOptions.setProxy(proxy);
	   
	   driver = new FirefoxDriver(firefoxOptions);
	   
		

	}
	
	
	
	
	protected void runfirefoxDefaultProfile() {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("default");

		myprofile.setPreference("network.proxy.type", 0);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(FirefoxDriver.PROFILE, myprofile);
		
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver(capabilities);
		//driver = new FirefoxDriver();
		//driver.get("https://www.yellowpages.com");

	}

	private static void separatingCityStateZip() {
		String mydata = "Los Angeles, CA 90026";
		Pattern pattern = Pattern.compile("([^,]+), ([A-Z]{2}) (\\d{5})");
		Matcher matcher = pattern.matcher(mydata);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}
		
		//String mydata1 = "144 Kings Highway, S.W. Dover, DE 19901";
		//String mydata1 = "2299 Lewes-Georgetown Hwy, Georgetown, DE 19947";
		//String mydata1 = "580 North Dupont Highway, Dover, DE 19901";
		String mydata1 = "PO Box 778, Dover, DE 19901";
		Pattern pattern1 = Pattern.compile("([^,]+), ([^,]+), ([A-Z]{2}) (\\d{5})");
		Matcher matcher1 = pattern1.matcher(mydata1);
		if (matcher1.find()) {
			System.out.println(matcher1.group(1));
			System.out.println(matcher1.group(2));
			System.out.println(matcher1.group(3));
			System.out.println(matcher1.group(4));
		}
		
	
	}
	
    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }
	
	
	
}
