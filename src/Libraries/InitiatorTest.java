package Libraries;
//imports-------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//---------------------------------------------------------------------------------------------------------
/*---------------------------------------------------------------------------------------------------------
* Class Name			: Initiator
* Use 					: Has functions related to TestNG 
* Designed By			: AG
* Last Modified Date 	: 25-Apr-2016
--------------------------------------------------------------------------------------------------------*/
public class InitiatorTest {
	private static String workingDir = System.getProperty("user.dir");
	private static String basepth = workingDir.replace("/","\\");
	public static String xmlpth = basepth+"\\src\\Libraries\\Driver.xml";
	private static String DriverDBpthI = basepth+"/DataBase/DriverDB/DriverDB.xlsx";
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Main Function
	 * Use 					: The Test Pack Execution starts from the main function 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void main(String argv[])
	{
		try 
		{ 
			//set parallel execution yes to run the batches in parallel, set no to run batches in sequence.
			String ParallelExecution = "yes";
			Driver.workingDir.set(System.getProperty("user.dir"));
			Driver.basepth.set(Driver.workingDir.get().replace("\\","/"));
			Driver.Resultpath.set(Driver.basepth.get()+"/Results");
			Driver.templatfold.set(Driver.basepth.get()+"/Templates");
			DateFormat ExecutionStarttime;
			ExecutionStarttime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Driver.ExecutionStarttimestr.set(ExecutionStarttime.format(Driver.cal.getTime()));
			String[] totbatches = floadbatches();
			createdynamicfiles(totbatches);
			if(ParallelExecution.equals("yes"))
			{
				if (totbatches != null) 
				{	
					ArrayList<RunnableDemo> bthArr = new ArrayList<RunnableDemo>();
					for(int currbatch = 0 ; currbatch < totbatches.length ;  currbatch++)
					{
						Result.fCreateReportFiles(totbatches[currbatch]);
						bthArr.add(new RunnableDemo(totbatches[currbatch],currbatch));
					}
					for(RunnableDemo cbthArr: bthArr )
					{
						cbthArr.start();	
					}
				}
			}
			else
			{
				if (totbatches != null) 
				{	
					for(int currbatch = 0 ; currbatch < totbatches.length ;  currbatch++)
					{
						Driver.Drivermain(totbatches[currbatch], Integer.toString(currbatch));
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: floadbatches
	 * Use 					: Reads all the batches with control 1 in the driver db and returns it in an array 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/	
	@SuppressWarnings("deprecation")
	static String[] floadbatches()
	{
		try
		{
			Fillo nfillo = new Fillo();
			Connection connection=nfillo.getConnection(DriverDBpthI);
			String strQuery="Select * from Batches where Control = 1";
			Recordset rs=connection.executeQuery(strQuery);
			rs.moveFirst();
			String[] batches = new String[rs.getCount()];
			for (int currs = 1 ; currs <= rs.getCount() ; currs++) 
			{
				String BatchID = rs.getField(2).value();
				batches[currs-1] = BatchID;
				if (rs.hasNext())
				{
					rs.moveNext();
				}
			}
			rs.close();
			connection.close();
			return batches;	
		}
		catch(Exception ex) 
		{
			System.err.print("Exception: ");   
			System.err.println(ex.getMessage());   
		} 
		return null;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: createdynamicfiles
	 * Use 					: Creates dynamic testdata, Driverdb and object repository for each batch 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/	
	private static void createdynamicfiles(String[] batches)
	{
		try
		{
			String ORsrc = 	basepth+"/ObjectRepository/ObjectRepository.xlsx";
			File cORsrc = new File(ORsrc);
			String DataDBsrc = basepth+"/DataBase";
			File cDataDBsrc = new File(DataDBsrc);
			String tempfold = basepth+"/Temp";
			for (String batchname : batches) {
				File batchdatafold = new File(tempfold + "/" + batchname);
				if ((!batchdatafold.exists())) 
					try {
						batchdatafold.mkdir();
						}
					catch (SecurityException se) 
					{
						/* handle it */
					}
				FileUtils.copyDirectory(cDataDBsrc, batchdatafold);
				FileUtils.copyFileToDirectory(cORsrc, batchdatafold);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

class RunnableDemo implements Runnable {
	   private Thread t;
	   private String threadName;
	   private String  batchcount;
	   RunnableDemo(String name,int rbatchcount) {
	      threadName = name;
	      batchcount = Integer.toString(rbatchcount);
	      System.out.println("Creating " +  threadName );
	   }  
	   public void run() {
	      System.out.println("Running " +  threadName );
	      try {
	    	   Driver.Drivermain(threadName, batchcount);
	      	}
	      catch (Exception e) 
	      {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
	      System.out.println("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start () throws InterruptedException {
	      System.out.println("Starting " +  threadName );
	      if (t == null) 
	      {  
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}

//-----------------------------------------------------------------------------------------------------------------