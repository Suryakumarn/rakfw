package Libraries;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
//import java.net.URL;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import Libraries.Driver;
import Libraries.KeyWord;

/*---------------------------------------------------------------------------------------------------------
 * Class Name			: Browser
 * Use 					: Class to establish Object hierarchy and to make application level coding more understandable  
 * Designed By			: AG
 * Last Modified Date 	: 25-Apr-2016
--------------------------------------------------------------------------------------------------------*/
public class Browser extends Driver
{
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebEdit 
	 * Use 					: Subclass of browser class represents the WebEdit in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebEdit
	{
		public static void Set(String objname, String objvalue) throws IOException
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.setTD(objprop,objvalue);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		
		public static String gettext(String objname)
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.getval(objprop);
		}
		
		public static void click(String objname) throws IOException
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clearTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static void clear(String objname) throws IOException
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clearTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static boolean exist(String objname)
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
		public static void waittillvisible(String objname) throws InterruptedException
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
			
		}
		public static void waitTillEnabled(String objname)throws InterruptedException
		{
			String objtype = "WebEdit";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.Methodwaittillenabled(objprop);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebButton 
	 * Use 					: Subclass of browser class represents the WebButton in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebButton
	{
		public static void click(String objname) throws IOException
		{
			String objtype = "WebButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static void waittillvisible(String objname) throws InterruptedException
		{
			String objtype = "WebButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
		}
		public static boolean exist(String objname)
		{
			String objtype = "WebButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
		public static void waitTillEnabled(String objname)throws InterruptedException
		{
			String objtype = "WebButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.Methodwaittillenabled(objprop);
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebLink 
	 * Use 					: Subclass of browser class represents the WebLink in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static class WebLink
	{
		public static void click(String objname) throws IOException
		{
			String objtype = "WebLink";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static boolean exist(String objname)
		{
			String objtype = "WebLink";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
		public static void waittillvisible(String objname) throws InterruptedException, IOException
		{
			String objtype = "WebLink";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: ListBox 
	 * Use 					: Subclass of browser class represents the ListBox in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class ListBox
	{
		public static void select(String objname,String objvalue) throws IOException
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.selectTD(objprop, objvalue);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static void click(String objname) throws IOException
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static void clear(String objname) throws IOException
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clearTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static String gettext(String objname)
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.getval(objprop);
		}
		public static boolean exist(String objname)
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
		public static void waitTillEnabled(String objname)throws InterruptedException
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.Methodwaittillenabled(objprop);
		}
		public static void waittillvisible(String objname) throws InterruptedException
		{
			String objtype = "ListBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebElement 
	 * Use 					: Subclass of browser class represents the WebElement in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebElement
	{
		public static void click(String objname) throws IOException
		{
			String objtype = "WebElement";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		
		public static void waittillvisible(String objname) throws InterruptedException, IOException
		{
			String objtype = "WebElement";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static boolean exist(String objname)
		{
			String objtype = "WebElement";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
		public static void waitTillEnabled(String objname)throws InterruptedException
		{
			String objtype = "WebElement";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.Methodwaittillenabled(objprop);
		}
		
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebRadioButton 
	 * Use 					: Subclass of browser class represents the WebRadioButton in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebRadioButton
	{
		public static void click(String objname) throws IOException
		{
			String objtype = "RadioButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
		public static boolean exist(String objname)
		{
			String objtype = "RadioButton";
			String[] objprop = Driver.FindObject(objname,objtype);
			return Method.existobj(objprop);	
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebCheckBox 
	 * Use 					: Subclass of browser class represents the WebRadioButton in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static class WebCheckBox
	{
		public static void click(String objname) throws IOException
		{
			String objtype = "CheckBox";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.clickTD(objprop);
			if(Driver.Continue.get()==false)
			{
				Result.fUpdateLog("Failed at : "+objname);	
			}
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebTable 
	 * Use 					: Subclass of browser class represents the WebTabel in the application and 
	 * 						  contains functions for all the operations performed on Web Table 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static class WebTable
	{
		/*------------------------------------------------------------------------------------------------------
		* Function Name: getRowCount
		* Use :	returns the total number of rows in the webtable
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		@SuppressWarnings("null")
		public static int getRowCount(String objname)
		{
			try{
				
			String[] objprop = Driver.FindObject(objname,"WebTable");
			String cellXpath = objprop[0]+"/tr";
			List<org.openqa.selenium.WebElement> rows =KeyWord.cDriver.get().findElements(By.xpath(cellXpath));
			int rowcount = rows.size();
			return rowcount;
			}
			catch(Exception e)
			{
				return (Integer) null;
			}
		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: getCellData
		* Use :	returns the value in the given row and column of the web table
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static String getCellData(String objname,int rownum,int columnnum)
		{
			try
			{
			String[] objprop = Driver.FindObject(objname,"WebTable");
			String cellXpath = objprop[0]+"/tr["+rownum+"]/td["+columnnum+"]";
			String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
			return celldata;
			}
			catch(Exception e)
			{
				return "";
			}
		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: click
		* Use :	Clicks the given row and column of the webtable
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void click(String objname,int rownum,int columnnum)
		{
			try
			{
			String[] objprop = Driver.FindObject(objname,"WebTable");
			String cellXpath = objprop[0]+"/tr["+rownum+"]/td["+columnnum+"]";
			KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
			}
			catch(Exception e)
			{
				
			}
		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: getColCount
		* Use : get the column count of the given web table
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		@SuppressWarnings("null")
		public static int getColCount(String objname)
		{
			try{
				
			String[] objprop = Driver.FindObject(objname,"WebTable");
			String cellXpath = objprop[0]+"/tr[1]/td";
			List<org.openqa.selenium.WebElement> cols =KeyWord.cDriver.get().findElements(By.xpath(cellXpath));
			int colcount = cols.size();
			return colcount;
			}
			catch(Exception e)
			{
				return ((Integer) null);
			}
		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: getColumnname
		* Use : get the column Name for the row and column
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static String getColumnname(String objname,int rownum,int columnnum)
		{
			try
			{
			String[] objprop = Driver.FindObject(objname,"WebTable");
			String cellXpath = objprop[0]+"/tr["+rownum+"]/th["+columnnum+"]";
			String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
			return celldata;
			}
			catch(Exception e)
			{
				return null;
			}
		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: waittillvisible
		* Use : Waits till the web table is visible
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void waittillvisible(String objname) throws InterruptedException
		{
			String objtype = "WebTable";
			String[] objprop = Driver.FindObject(objname,objtype);
			Method.waittillobjvisible(objprop);
		}
		
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: alert 
	 * Use 					:  class represents the alert in the application and 
	 * 						  contains functions for all the operations performed on alert 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class alert
	{
		public static void accept() throws InterruptedException
		{
			String alertpresent = "false";
			int loopcount =  1; 
			while(alertpresent.equals("false"))
			{
				try
				{
					Alert simpleAlert = ((WebDriver) Driver.cDriver.get()).switchTo().alert();	
					simpleAlert.accept();
					alertpresent = "true";
					break;
				}
				catch(Exception e)
				{
					alertpresent = "false";
					Thread.sleep(2000);
					loopcount = loopcount+1;
				}
				
			}
		}
	}
	
	/*------------------------------------------------------------------------------------------------------
	* Function Name: OpenBrowser
	* Use : Opens a new browser and resizes it according the number of parallel instances
	* Designed By: AG
	* Last Modified Date : 15-June-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void OpenBrowser(String BrowserName,String URL) throws MalformedURLException
	{
		if(BrowserName.toLowerCase().equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",Driver.basepth.get()+"/chromedriver.exe");
			/*DesiredCapabilities dr=null;
			dr=DesiredCapabilities.chrome();
			dr.setBrowserName("CHROME");
			dr.setPlatform(Platform.WINDOWS);
			Driver.cDriver.set(new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), dr));*/
			Driver.cDriver.set(new ChromeDriver());
		}
		else if(BrowserName.toLowerCase().equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",Driver.basepth.get()+"/geckodriver.exe");
			Driver.cDriver.set(new FirefoxDriver());
		}
		else if(BrowserName.toLowerCase().equals("internetexplorer"))
		{
			System.setProperty("webdriver.chrome.driver",Driver.basepth.get()+"/ieDriver.exe");
			Driver.cDriver.set(new InternetExplorerDriver());
		}
		Driver.cDriver.get().get(URL);
		Browser.maximize();
		//code to resize and position the browsers while doing parallel execution.
		//-----------------------------------------------------------------------
		int screenheight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenwidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		if(totbatchlength.get() >2) 
		{
			int fscreenhight = screenheight/2;
			float batchdevide = totbatchlength.get()/2;
			batchdevide = (float) Math.ceil(batchdevide);
			int chunkSize  = (int) (screenwidth/batchdevide);
			Math.ceil(chunkSize);
			int fscreenwidth = chunkSize;
			Dimension d1 = new Dimension(fscreenwidth,fscreenhight);
			Driver.currbatch = Driver.currbatch+1;
			int browserx = 0;
			if(currbatch > batchdevide && sbrowsery ==0 )
			{
				sbrowsery = fscreenhight+1;
				rowscrnno = 0;
			}
			browserx = fscreenwidth*rowscrnno;
			rowscrnno = rowscrnno+1;
			Point p1 = new Point(browserx,sbrowsery);
			cDriver.get().manage().window().setSize(d1);
			cDriver.get().manage().window().setPosition(p1);
		}
		else if(totbatchlength.get() == 2)
		{
			int fscreenwidth = screenheight/2;
			Dimension d = new Dimension(screenwidth,fscreenwidth);
			cDriver.get().manage().window().setSize(d);
			int browserx = 0;
			int browsery = 0;
			browsery = fscreenwidth*rowscrnno;
			rowscrnno = rowscrnno+1;
			Point p1 = new Point(browserx,browsery);
			cDriver.get().manage().window().setPosition(p1);
		}
		else if(totbatchlength.get() == 1)
		{
			cDriver.get().manage().window().maximize();
		}
		p.set(cDriver.get().manage().window().getPosition());
		d.set(cDriver.get().manage().window().getSize());
		//-------------------------------------------------------
		//cDriver.get().manage().window().maximize();
		cDriver.get().manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	}

	public static void maximize()
	{
		if(Driver.totbatchlength.get()!=1)
		{
			Driver.cDriver.get().manage().window().maximize();
		}
		
	}
}