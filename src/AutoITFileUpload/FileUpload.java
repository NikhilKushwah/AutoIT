package AutoITFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		//setting the directory to download files in this project dyanmically
	    String downloadPath=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rahul\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		//Set download directory this code you find in chromeoptions documentation link
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "/directory/path");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=new ChromeDriver(options);//passing options

		driver.get("https://pdf2jpg.net/");
		driver.findElement(By.xpath("//button[@id='advanced_pdf_file']")).click();
		Thread.sleep(3000);
		// now will call that exe file here
		Runtime.getRuntime().exec("C:\\Users\\Rahul\\Documents\\FileUpload.exe");
		//introducing implicitWait
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("download your pictures as a ZIP file")));
		driver.findElement(By.linkText("download your pictures as a ZIP file")).click();
		//passing the file path which has been dowloaded using File
		// Creating an object of File
		
		//File path=new File("C:\\Users\\Rahul\\Downloads\\w-9-2019.zip"); //but this is not a dynamic path
		
		File path=new File(downloadPath+"/w-9-2019.zip");//Now this is dynamic this file can be download in any system just using this code
		if(path.exists()) // if this above path exists
		{
			System.out.println("File Found");
		}
		
	}

}
