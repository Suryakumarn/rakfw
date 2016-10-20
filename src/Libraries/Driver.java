package Libraries;   
// imports------------------------------------------------------------------------------------------------
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import Libraries.Initiator;
//---------------------------------------------------------------------------------------------------------
/*---------------------------------------------------------------------------------------------------------
 * Class Name			: Driver
 * Use 					: Has all excel connection and framework related functions 
 * Designed By			: AG
 * Last Modified Date 	: 25-Apr-2016
--------------------------------------------------------------------------------------------------------*/

public class Driver extends RemoteWebDriver 
{ 
	// Thread local static Variable declaration. The variables are used across the classes in the framework. 
	static Calendar cal = Calendar.getInstance();
	static int currbatch = 0;
	static int itecount = 1;
	public static ThreadLocal<String> BatchName = new ThreadLocal<String>();
	public static int passtc = 0;
	public static int failtc = 0;
	public static int totaltc = 0;
	public static ThreadLocal<String> fdb = new ThreadLocal<String>();
	public static ThreadLocal<Session> nsession = new ThreadLocal<Session>();
	public static ThreadLocal<Channel> channel = new ThreadLocal<Channel>();
	public static ThreadLocal<String> testkeywords = new ThreadLocal<String>();
	public static ThreadLocal<String> WebServicesTempltes = new ThreadLocal<String>();
	public static ThreadLocal<String> Zipfilepath = new ThreadLocal<String>();
	public static ThreadLocal<String> Zipfilename = new ThreadLocal<String>();
	public static ThreadLocal<String> screenname = new ThreadLocal<String>();
	public static ThreadLocal<String> workingDir = new ThreadLocal<String>();
	public static ThreadLocal<String> basepth = new ThreadLocal<String>();
	public static ThreadLocal<String> Resultpath = new ThreadLocal<String>();
	public static ThreadLocal<String> DriverDBpth = new ThreadLocal<String>();
	public static ThreadLocal<String> TestDatafold = new ThreadLocal<String>();
	public static ThreadLocal<String> templatfold = new ThreadLocal<String>();
	public static ThreadLocal<String> libfold = new ThreadLocal<String>();
	public static ThreadLocal<String> tempfold = new ThreadLocal<String>();
	public static ThreadLocal<String> ORfold = new ThreadLocal<String>();
	public static ThreadLocal<Boolean> Continue = new ThreadLocal<Boolean>();
	public static ThreadLocal<String> currtcstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currscnstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currbatchstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currbatchname = new ThreadLocal<String>();
	public static ThreadLocal<String> currscnname = new ThreadLocal<String>();
	public static ThreadLocal<String> currtc = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseStartTime = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseEndTime = new ThreadLocal<String>();
	public static ThreadLocal<String> ExeEndTime = new ThreadLocal<String>();
	public static ThreadLocal<String> TestScnStartTime = new ThreadLocal<String>();
	public static ThreadLocal<String> exestarttime = new ThreadLocal<String>();
	public static ThreadLocal<String> TestScnEndTime = new ThreadLocal<String>();
	public static ThreadLocal<String> resultdescription = new ThreadLocal<String>();
	public static ThreadLocal<String> currkeywordstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> ExecutionStarttimestr = new ThreadLocal<String>();
	public static ThreadLocal<String> keywordstarttime = new ThreadLocal<String>();
	public static ThreadLocal<String> keywordstartdate = new ThreadLocal<String>();
	public static ThreadLocal<String> currmtc = new ThreadLocal<String>();
	public static ThreadLocal<Integer> scrnno = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> currtcite = new ThreadLocal<Integer>();
	public static ThreadLocal<Float> totbatchlength = new ThreadLocal<Float>();
	public static ThreadLocal<String> attachtoold = new ThreadLocal<String>();
	public static ThreadLocal<Integer> currbathno = new ThreadLocal<Integer>();  
	public DateFormat currkeywordstarttime = new SimpleDateFormat("HH:mm:ss");
	static int sbrowsery = 0;
	static int rowscrnno = 0;
	public static ThreadLocal<RemoteWebDriver> cDriver = new ThreadLocal<RemoteWebDriver>();
	@SuppressWarnings("rawtypes")
	public static ThreadLocal<Dictionary> TestData = new ThreadLocal<Dictionary>();
	public static ThreadLocal<Point> p = new ThreadLocal<Point>();
	public static ThreadLocal<Dimension> d = new ThreadLocal<Dimension>();
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Main Function
	 * Use 					: The Batch Execution starts from the main function 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void Drivermain(String Batch,String BatchCount) throws IOException, ClassNotFoundException, FilloException, EncryptedDocumentException, InvalidFormatException
	{
		try
		{
			Thread.sleep(Integer.parseInt(BatchCount)*2000);
			attachtoold.set("Yes");
			String[] totbatches= Initiator.floadbatches();
			totbatchlength.set((float) totbatches.length);
			scrnno.set(1);
			BatchName.set(Batch);	
			currbathno.set(Integer.parseInt(BatchCount));
			workingDir.set(System.getProperty("user.dir"));
			basepth.set(workingDir.get().replace("\\","/"));
			Resultpath.set(basepth.get()+"/Results");
			templatfold.set(basepth.get()+"/Templates");
			WebServicesTempltes.set(templatfold.get()+"/"+"WebServicesTemplates");
			libfold.set(basepth.get()+"/Libraries");
			tempfold.set(basepth.get()+"/Temp");
			ORfold.set(tempfold.get()+"/"+Batch+"//ObjectRepository.xlsx");
			Continue.set(true);
			currbatchname.set(Batch);
			DriverDBpth.set(tempfold.get()+"/"+Batch+"/DriverDB/DriverDB.xlsx");
			TestDatafold.set(tempfold.get()+"/"+Batch+"/TestDataDB");
			DateFormat ExecutionStarttime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			ExecutionStarttimestr.set(ExecutionStarttime.format(cal.getTime()).toString());
			Result.fCreateReportFiles(Batch);
			System.out.println("Execution initiated at ---> "+ ExecutionStarttime.format(cal.getTime()));
			Result.fUpdateLog("Execution initiated at ---> "+ ExecutionStarttime.format(cal.getTime()));
			fPrepareTDReference();
			Result.fCreateReportFiles(Batch);
			exestarttime.set(ExecutionStarttime.format(cal.getTime()));
			currbatchstatus.set("Pass");
			Result.fUpdateMasterReport("Batch");
			String[] totscenarios = floadscenarios(Batch);
			int currscn = 0;
			System.out.println("No of Scenario(s) to be executed in "+Batch+":"+totscenarios.length);
			Result.fUpdateLog("No of Scenario(s) to be executed in "+Batch+":"+totscenarios.length);
			System.out.println("->"+Batch);
			Result.fUpdateLog("->"+Batch);
			for(currscn = 0 ; currscn < totscenarios.length ; currscn++)
			{	
				currscnstatus.set("Pass");
				DateFormat TSStarttime = new SimpleDateFormat("HH:mm:ss");
				Calendar cal5 = Calendar.getInstance();
				TestScnStartTime.set(TSStarttime.format(cal5.getTime()));
				currscnname.set(totscenarios[currscn]);
				Result.fUpdateMasterReport("Scenario");
				String[] tottestcases = floadtestcases(currscnname.get());
				int currtestcase = 0;
				int tcitecount;
				System.out.println("No of TestCase(s) to be executed in "+totscenarios[currscn]+":"+tottestcases.length);
				Result.fUpdateLog("No of TestCase(s) to be executed in "+totscenarios[currscn]+":"+tottestcases.length);
				System.out.println("--->"+totscenarios[currscn]);
				Result.fUpdateLog("--->"+totscenarios[currscn]);
				totaltc = totaltc+tottestcases.length;
				for(currtestcase = 0 ; currtestcase < tottestcases.length ; currtestcase++)
				{
					System.out.println("----->"+tottestcases[currtestcase]);
					Result.fUpdateLog("----->"+tottestcases[currtestcase]);
					currtcstatus.set("Pass");
					currtc.set(tottestcases[currtestcase]);
					tcitecount = getIterationcount();
					for(int currite = 1; currite <= tcitecount; currite++)
					{
						currtcite.set(currite);
						DateFormat TcStarttime = new SimpleDateFormat("HH:mm:ss");
						Calendar cal1 = Calendar.getInstance();
						TestCaseStartTime.set(TcStarttime.format(cal1.getTime()));
						Result.fUpdateMasterReport("InsertTestCase");
						Result.createTCScreenshotFold();
						fexecuttestcase(currtc.get());
						System.out.println("----->"+tottestcases[currtestcase]+ "  :"+currtcstatus.get());
						Result.fUpdateLog("----->"+tottestcases[currtestcase]+ "  :"+currtcstatus.get());
						if (currtcstatus.get().equals("Fail"))
						{
							failtc= failtc+1;
							currscnstatus.set("Fail");
						}	
						else
						{
							passtc = passtc+1;
						}
						Continue.set(true);
						DateFormat TcEndtime = new SimpleDateFormat("HH:mm:ss");
						Calendar cal2 = Calendar.getInstance();
						TestCaseEndTime.set(TcEndtime.format(cal2.getTime()));
						Result.fUpdateMasterReport("TestCase");
						Result.fcreateMasterHTML();
					}
				}
				System.out.println("--->"+totscenarios[currscn]+"   :"+currscnstatus.get());
				Result.fUpdateLog("--->"+totscenarios[currscn]+"   :"+currscnstatus.get());
				if(currscnstatus.get().equals("Fail"))
				{
					currbatchstatus.set("Fail");
				}	
				Calendar cal3 = Calendar.getInstance();
				DateFormat TSEndtime = new SimpleDateFormat("HH:mm:ss");
				TestScnEndTime.set(TSEndtime.format(cal3.getTime()));
				Result.fUpdateMasterReport("UpdateScenario");
			}	
			Calendar cal4 = Calendar.getInstance();
			ExeEndTime.set(ExecutionStarttime.format(cal4.getTime()));
			System.out.println("->"+Batch+"   :"+currbatchstatus.get());
			Result.fUpdateLog("->"+Batch+"   :"+currbatchstatus.get());
			Result.fUpdateMasterReport("UpdateBatch");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: floadscenarios
	 * Arguments			: The current batch that is being executed.
	 * Use 					: Returns the list of scenarios mapped under the batch 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static String[] floadscenarios(String currentbatch)
	{
		try
		{
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(DriverDBpth.get());
			String strQuery="Select * from Scenarios Where Control = 1 AND BatchID = \'"+currentbatch+"\' ORDER BY SeqNo ASC";
			Recordset rs=connection.executeQuery(strQuery);
			//int size = 0;
			String[] scnros = new String[rs.getCount()];
			rs.moveFirst();
			for(int rscurrcount = 1 ; rscurrcount <= rs.getCount() ;  rscurrcount++)
			{
				scnros[rscurrcount-1] = rs.getField(3).value(); 
				if (rs.hasNext())
				{	
					rs.moveNext();
				}
			}
			rs.close();
			connection.close();
			return scnros;	
		} 
		catch(Exception ex) 
		{   
			System.err.print("Exception: ");   
			System.err.println(ex.getMessage());   
		}		 
		return null;
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: floadtestcases
	 * Arguments			: The current Scenario that is being executed.
	 * Use 					: Returns the list of test cases mapped under the batch 
	 * Designed By			:	AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static String[] floadtestcases(String currentscenario)
	{
		try
		{
			
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(DriverDBpth.get());
			String strQuery="Select * from TestScripts Where Control = 1 AND ScenarioID = \'"+currentscenario+"\' ORDER BY SeqNo ASC";
			Recordset rs=connection.executeQuery(strQuery);
			String[] testcases = new String[rs.getCount()];
			rs.moveFirst();
			for(int currtc = 1 ; currtc <= rs.getCount(); currtc++)
			{
				testcases[currtc-1] = rs.getField(3).value();    
				if (rs.hasNext())
				{
					rs.moveNext();
				}
			}
			rs.close();
			connection.close();
			return testcases;	
		}
		catch(Exception ex) 
		{
			System.err.print("Exception: ");   
			System.err.println(ex.getMessage());   
		}
		return null;
	}	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fexecuttestcase
	 * Arguments			: test case to be executed
	 * Use 					:  Reads the keywords mapped to the test case and starts executing the keywords in sequence 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	@SuppressWarnings("deprecation")
	public static void fexecuttestcase(String currtestcas)
	{
		try
		{   
			//Driver ndb = new Driver();
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(DriverDBpth.get());
			String strQuery="Select * from TestKeywords Where Control = 1 AND TestCaseID = \'"+currtestcas+"\' ORDER BY SeqNo ASC";
			Recordset rs=connection.executeQuery(strQuery);
			rs.moveFirst();
			for(int currkw =1; currkw <= rs.getCount(); currkw++)
			{
				if(Continue.get()==true)
				{
					testkeywords.set(rs.getField(3).value());
					screenname.set(rs.getField(5).value());
					String ftcdb = rs.getField(4).value();
					//String[] fsdb = ftcdb.split("-");
					fdb.set(ftcdb);
					//fdb = fsdb[1];
					currmtc.set(ftcdb);
					DateFormat currkeywordstartdate = new SimpleDateFormat("dd-MMM-yyyy");
					DateFormat currkeywordstarttime1 = new SimpleDateFormat("HH:mm:ss");
					Calendar cal3 = Calendar.getInstance();
					keywordstarttime.set(currkeywordstarttime1.format(cal3.getTime()));
					Calendar cal4 = Calendar.getInstance();
					keywordstartdate.set(currkeywordstartdate.format(cal4.getTime()));
					Class<?> noparams[] = {};
					try
					{
						Class<?> cls = Class.forName("Libraries.Common");
						Object obj = cls.newInstance();
						java.lang.reflect.Method method = cls.getDeclaredMethod(testkeywords.get(), noparams);
						method.invoke(obj);
					}
					catch(Exception e)
					{
						Class<?> cls = Class.forName("Libraries.KeyWord");
						Object obj = cls.newInstance();
						java.lang.reflect.Method method = cls.getDeclaredMethod(testkeywords.get(), noparams); 
						method.invoke(obj);
					}
					if(attachtoold.get().equals("Yess"))
					{ 
						cDriver.set(new ChromeDriver(DesiredCapabilities.chrome()));
					}
					TestData.set((Dictionary<?, ?>) Driver.freadfromtestdata());
					if(Continue.get() == true)
					{
						currkeywordstatus.set("Pass");
						Continue.set(true);
					}
					else
					{
						//JOptionPane.showMessageDialog(null, "KeyWord Failed");
						currkeywordstatus.set("Fail");
						currtcstatus.set("Fail");
					}
					Result.fUpdateReport();
					System.out.println("--------->"+testkeywords.get()+"   :"+currkeywordstatus.get());
					Result.fUpdateLog("--------->"+testkeywords.get()+"   :"+currkeywordstatus.get());
					if(rs.hasNext())
					{
						rs.moveNext();
					}
				}
			}
			rs.close();
			connection.close();
		}
		catch(Exception ex) 
		{
			System.err.print("Exception: ");   
			System.err.println(ex.getMessage());   
		}		 
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: freadfromtestdata
	 * Arguments			:  -
	 * Use 					:Reads data from the test data sheet based on the sheet name and data binding given in driver DB and 
	 * 				 		 returns the value in a hash map  
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static Object freadfromtestdata() throws ClassNotFoundException, FilloException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		String dbpath = ffindpath(screenname.get());
		Workbook wb = WorkbookFactory.create(new File(dbpath));
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheet(screenname.get());
		int columnsused = ws.getRow(0).getLastCellNum();
		Dictionary<String, String> dict = new Hashtable<String, String>();
		for (int colloop = 1 ; colloop < columnsused ; colloop++)
		{
			String cell = ws.getRow(0).getCell(colloop).getRichStringCellValue().toString();
			dict.put(cell,"");
		}
		wb.close();
		Fillo fillo = new Fillo();
		Connection connection=fillo.getConnection(dbpath);
		String strQuery="Select * from "+screenname.get()+" Where Databinding = \'"+fdb.get()+"\'";
		Recordset rs=connection.executeQuery(strQuery);
		int noOfColumns = rs.getFieldNames().size();
		ArrayList<String> fieldnames = rs.getFieldNames();
		rs.moveNext();
		for (int readloop = 0 ; readloop < noOfColumns ; readloop++)
		{
			String colname = fieldnames.get(readloop);
			if(!colname.equals("DataBinding"))
			{
				if (((Hashtable<String, String>) dict).containsKey(colname) == true)
				{
					String dat = rs.getField(readloop).value();
					if(dat == null)
					{
						dict.put(colname,"");
					}	
					else
					{
						if(dat.toLowerCase().contains("getdata"))
						{
							String[] datsplit=dat.split("--");
							dat = readpropval(datsplit[1]);
						}
						dict.put(colname,dat);
					}
				}
			}
		}
		rs.close();
		connection.close();
		return dict;
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fPrepareTDReference
	 * Arguments			:  -
	 * Use 					:Reads all the excel files in the test data folder and saves the sheet name and path 
	 * 						 in the TDReference excel
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static void fPrepareTDReference() throws IOException, ClassNotFoundException, FilloException, EncryptedDocumentException, InvalidFormatException
	{
		try
		{
		System.out.println("Preparing Test Data Reference");	
		String tdrefsrc = templatfold.get().replace("/","\\\\");
		String tdredest = tempfold.get().replace("/","\\\\");
		File source = new File(tdrefsrc+"\\\\TDReferense.xlsx");
		File dest = new File(tdredest+"\\\\"+BatchName.get()+"TDReferense.xlsx");
		InputStream in = new FileInputStream(source);
		OutputStream out = new FileOutputStream(dest); 
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) > 0)
		{
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(tempfold.get()+"/"+BatchName.get()+"TDReferense.xlsx");
		File folder = new File(TestDatafold.get());
		File[] listOfFiles = folder.listFiles();
		for (int filelist = 0; filelist < listOfFiles.length; filelist++) 
		{
			if (listOfFiles[filelist].isFile()) 
			{
				if (listOfFiles[filelist].getName().toLowerCase().endsWith((".xlsx")))
				{
					String exfilepth = listOfFiles[filelist].getPath();
					Workbook wb = WorkbookFactory.create(new File(exfilepth));
					int totsht = wb.getNumberOfSheets();
					for(int currscreen = 1 ; currscreen <= totsht ; currscreen++)
					{
						String sheetname = wb.getSheetName(currscreen-1);
						String colnames = "ScreenName,FIlePath";
						String columnVal =  "\'"+sheetname+"\',\'"+exfilepth+"\'";
						String InsertQuery="INSERT INTO TestDataReference("+colnames+") VALUES("+columnVal+")";
						connection.executeUpdate(InsertQuery);   
					}
					wb.close();
					wb =null;
				}
			}
		}
		connection.close();
		fillo = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: ffindpath
	 * Arguments			: Sheet name
	 * Use 					: Reads the sheet path for the sheet that is passed from the TDReference excel 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	@SuppressWarnings("deprecation")
	public static String ffindpath(String fscreenname) throws ClassNotFoundException, FilloException
	{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(tempfold.get()+"/"+BatchName.get()+"TDReferense.xlsx");
		String strQuery="Select * from TestDataReference Where ScreenName = \'"+fscreenname+"\'";
		Recordset rs=connection.executeQuery(strQuery);
		String pdbpath = null;
		rs.moveFirst();
		for(int scrcount =1 ; scrcount <= rs.getCount(); scrcount++)
		{
			String dbpth = rs.getField(1).value();
			pdbpath = dbpth.replace("\\","/");
			if(rs.hasNext())
			{
				rs.moveNext();
			}
		}
		rs.close();
		connection.close();
		return pdbpath;
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FindObject
	 * Arguments			: Object Name and Object Type
	 * Use 					: Read the object property from the Object DataBase  excel and return the value
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static String[] FindObject(String name,String objtype)
	{
		try
		{
			Fillo fillo=new Fillo();
			Connection ORconn=fillo.getConnection(ORfold.get());
			Recordset rs = ORconn.executeQuery("Select * from "+objtype+" Where ObjectName = \'"+name+"\'");
			String cellval1 = "blank";
			String cellval2 = "blank";
			String cellval3 = "blank";
			String cellval4 = "blank";
			String cellval5 = "blank";
			while(rs.next())
			{
						cellval1 = rs.getField(1).value();
						cellval2= rs.getField(2).value();
						cellval3 = rs.getField(3).value();
						cellval4 = rs.getField(4).value();
						cellval5 = rs.getField(5).value();
						String[] retval = {cellval1,cellval2,cellval3,cellval4,cellval5};
						return retval;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getdata
	 * Arguments			: ColumnName
	 * Use 					: To read value from TestData hashmap and type cast it to string
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static String getdata(String colname)
	{
		String c = KeyWord.TestData.get().get(colname).toString();
		return c;
	}
	
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : storeinproertiesfile
	* Arguments : Value to be stored along with reference for the value
	* Use : To store the values read from th e application in the java properties file
	* Designed By : AG
	* Last Modified Date : 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void storeinproertiesfile(String storeinval) 
	{
		try
		{
			String[] splitupdateval =  storeinval.split("=");
			String updateval =  splitupdateval[1];
			String updatepropname = splitupdateval[0];
			String storepth = basepth.get()+"/DataBase/Store.properties";
			String fstorepth =storepth.replace("/","\\\\");
			FileInputStream in = new FileInputStream(fstorepth);
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			OutputStream output = null;
			output = new FileOutputStream(fstorepth);
			if(currtcite.get() > 1)
			{
				prop.setProperty(currbatchname.get()+"_"+updatepropname+"_"+currtcite.get(),updateval);
				updateoutput(currbatchname.get()+"_"+updatepropname+"_"+currtcite.get(),updateval);
			}
			else
			{
				prop.setProperty(currbatchname.get()+"_"+updatepropname,updateval);
				updateoutput(currbatchname.get()+"_"+updatepropname,updateval);
			}
			
			prop.store(output, " ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readpropval
	 * Arguments			: Reference of value to be read
	 * Use 					: To read the values from the java properties file
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static String readpropval(String propname)
	{
		try
		{
			String fpropname = "";
			if(currtcite.get() > 1)
			{
				fpropname = currbatchname.get()+"_"+propname+"_"+currtcite.get();
			}
			else
			{
				fpropname = currbatchname.get()+"_"+propname;
			}
			String storepth = basepth.get()+"/DataBase/Store.properties";
			String fstorepth =storepth.replace("/","\\\\");
			FileReader reader = new FileReader(fstorepth);
			Properties properties = new Properties();
			properties.load(reader);
			String propval = properties.getProperty(fpropname);
			return propval;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: randname
	 * Arguments			: -
	 * Use 					: Picks random names from thr name DB
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public String randname()
	{
		try
		{
			Random rn = new Random();
			int Min = 2;
			int Max = 859;		
			int randnumber=  rn.nextInt((Max - Min) + 1) + Min;
			String refid = "Name"+randnumber; 
			String NameDBpth = tempfold.get()+"/"+BatchName.get()+"/TestDataDB/NameDB.xlsx";
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(NameDBpth);
			String strQuery="Select * from NameDB Where RefID ='"+refid+"'";
			Recordset rs=connection.executeQuery(strQuery);
			rs.moveFirst();
			String refname=rs.getField(1).value();
			return refname;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getIterationcount
	 * Arguments			: -
	 * Use 					: Read iteration count of a test case from driver DB
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static int getIterationcount()
	{
		int itecount1 = 1;
		try
		{
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(DriverDBpth.get());
			String strQuery="Select * from TestScripts Where TestCaseID = \'"+currtc.get()+"\'";
			Recordset rs=connection.executeQuery(strQuery);
			rs.moveFirst();
			if(rs.getField(4)!=null)
			{
				itecount1 = Integer.parseInt(rs.getField(4).value());
			}
				
			rs.close();
			connection.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return itecount1;
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: updateoutput
	 * Arguments			: -
	 * Use 					: Update output excel in the result folder with the store in values.
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void updateoutput(String NID, String IDvalue)
	{
		try
		{
			Fillo fillo = new Fillo();
			Connection ResEngine = fillo.getConnection(Result.outputfinal.get());
			String colnames = "OutputID,OutputValue";
			String columnVal = "\'"+NID+"\',\'"+IDvalue+"\'";
			String strQuery="insert into Outputsheet("+colnames+") values("+columnVal+")";
			ResEngine.executeUpdate(strQuery);
			ResEngine.close();
			
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

//------------------------------------------------------------------------------------------------------------