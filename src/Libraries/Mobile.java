package Libraries;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
public class Mobile 
	{
		public static ThreadLocal<AndroidDriver> aDriver = new ThreadLocal<AndroidDriver>();	
		public static class MobileElement
		{
			public static void click(String Objname)
			{
				String[] identify = Driver.FindObject(Objname, "Mobile");
				int i = 0;
				Exception Error = null;
				for (i = 0; i < identify.length;) {
					try {
						if (Driver.Continue.get() == true) {
							switch (i) {
								case 0 :
									if (identify[0] != "") {
										aDriver.get().findElement(By.xpath(identify[0])).click();
										break;
									} else {
										throw new Exception(Error);
									}
								case 1 :
									if (identify[1] != "") {
										aDriver.get().findElement(By.name(identify[1])).click();
										break;
									} else {
										throw new Exception(Error);
									}
								case 2 :
									if (identify[2] != "") {
										aDriver.get().findElement(By.id(identify[2])).click();
										break;
									} else {
										throw new Exception(Error);
									}
								case 3 :
									if (identify[3] != "") {
										aDriver.get().findElement(By.className(identify[3])).click();
										break;
									} else {
										throw new Exception(Error);
									}
								case 4 :
									if (identify[4] != "") {
										aDriver.get().findElement(By.linkText(identify[4])).click();
										break;
									} else {
										throw new Exception(Error);
									}
							}
						}
						break;
					} catch (Exception e) {
						i++;
						Error = e ;
						Driver.Continue.set(true);
					}
				}
				if (i == identify.length) {
					Driver.Continue.set(false);
					System.out.println("Object doesn't Exists:\n");
					Error.printStackTrace();
				}
			}
			public static void set(String Objname,String val)
			{
				String[] identify = Driver.FindObject(Objname, "Mobile");
				int i = 0;
				Exception Error = null;
				for (i = 0; i < identify.length;) {
					try {
						if (Driver.Continue.get() == true) {
							switch (i) {
								case 0 :
									if (identify[0] != "") {
										aDriver.get().findElement(By.xpath(identify[0])).sendKeys(val);
										break;
									} else {
										throw new Exception(Error);
									}
								case 1 :
									if (identify[1] != "") {
										aDriver.get().findElement(By.name(identify[1])).sendKeys(val);
										break;
									} else {
										throw new Exception(Error);
									}
								case 2 :
									if (identify[2] != "") {
										aDriver.get().findElement(By.id(identify[2])).sendKeys(val);
										break;
									} else {
										throw new Exception(Error);
									}
								case 3 :
									if (identify[3] != "") {
										aDriver.get().findElement(By.className(identify[3])).sendKeys(val);
										break;
									} else {
										throw new Exception(Error);
									}
								case 4 :
									if (identify[4] != "") {
										aDriver.get().findElement(By.linkText(identify[4])).sendKeys(val);
										break;
									} else {
										throw new Exception(Error);
									}
							}
						}
						break;
					} catch (Exception e) {
						i++;
						Error = e;
						Driver.Continue.set(true);
					}
				}
				if (i == identify.length) {
					Driver.Continue.set(false);
					System.out.println("Object doesn't Exists:\n");
					Error.printStackTrace();
				}
			}
		}
	}