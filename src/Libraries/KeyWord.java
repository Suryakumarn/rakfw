package Libraries;

import org.openqa.selenium.By;

public class KeyWord extends Driver 
{
	public static void search()
	{
		cDriver.get().findElement(By.id("lst-ib")).sendKeys("Arun Ganesh");
	}
}