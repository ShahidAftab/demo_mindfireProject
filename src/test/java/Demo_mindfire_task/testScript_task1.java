package Demo_mindfire_task;

import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class testScript_task1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C://Users//Sahid Aftab//eclipse-workspace//Demo_mindfire//driver//chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2KpZ6l _2doB4z']"))).click();
		Actions action = new Actions(driver);
		WebElement element=driver.findElement(By.xpath("//img[@alt='Electronics']"));
		action.moveToElement(element).perform();
		driver.findElement(By.linkText("Bluetooth Headphones")).click();
		List<WebElement> elementName = driver.findElements(By.className("_30jeq3"));
		int count =0;
		int min = Integer.MAX_VALUE;
		WebElement min_product = null;
		for(WebElement ele: elementName)
		{
			if(count>10)
			{
				break;
			}
			String price_of_product = ele.getText().replaceAll(",", "").replaceAll("\\u20B9", "");
			try
			{
				int price = Integer.parseInt(price_of_product);
				if(min>price) 
				{
					min= price;
					min_product = ele;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Price of the product is not picked correctly. Kindly check locator.");
				Assert.fail();
				
			}

		}
		action.moveToElement(min_product).click().perform();
		String parent_window=driver.getWindowHandle();

		Set<String>child_windows=driver.getWindowHandles();
		Iterator<String> I1= child_windows.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent_window.equals(child_window))
		{
		driver.switchTo().window(child_window);
		}
		}
		WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='aMaAEs']//div[@class='_3LWZlK']")));
		
		if(rating.isDisplayed())
		{
			System.out.println("The rating of the product with minimum value is "+rating.getText());
			System.out.println("Test Successfully executed");
		}
		else
		{
			System.out.println("Rating is not Displayed or we are not at the product detail page");
		}
		driver.quit();
	}

}
