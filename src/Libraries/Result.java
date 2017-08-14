package Libraries;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Libraries.Driver;
import Libraries.KeyWord;
import Libraries.CustomXWPFDocument;
/*---------------------------------------------------------------------------------------------------------
 * Class Name			: Result
 * Use 					: Contains the functions related to generating reports 
 * Designed By			: AG
 * Last Modified Date 	: 25-Apr-2016
--------------------------------------------------------------------------------------------------------*/
public class Result 
{
	
	public static ThreadLocal<String> masterreppth = new ThreadLocal<String>();
	public static ThreadLocal<String> compretempstatic = new ThreadLocal<String>();
	public static ThreadLocal<String> outputfinal = new ThreadLocal<String>();
	public static ThreadLocal<String> WebServiceRequestg = new ThreadLocal<String>();
	public static ThreadLocal<String> WebServiceResponseg = new ThreadLocal<String>();
	public static ThreadLocal<String> detreppath = new ThreadLocal<String>();
	public static ThreadLocal<String> logfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> screenfoldpth = new ThreadLocal<String>();
	public static ThreadLocal<String> Billlogfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> EOMXML = new ThreadLocal<String>();
	public static ThreadLocal<String> xmlpth = new ThreadLocal<String>();
	public static ThreadLocal<String> EOOMXMLTC = new ThreadLocal<String>();
	public static ThreadLocal<String> pdfpth = new ThreadLocal<String>();
	public static ThreadLocal<String> tcxmlpth = new ThreadLocal<String>();
	public static ThreadLocal<String> Billlogs = new ThreadLocal<String>();
	public static ThreadLocal<String> tcbillogpth = new ThreadLocal<String>();
	public static ThreadLocal<String> tcpdfpth = new ThreadLocal<String>();
	public static ThreadLocal<String> tcscreenfoldpth = new ThreadLocal<String>();
	public static ThreadLocal<String> tcscreenfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> masterrephtml = new ThreadLocal<String>();
	public static String updatelogmsg = "";
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fCreateReportFiles
	 * Arguments			: Batch Name
	 * Use 					: Creates report files and folders
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void fCreateReportFiles(String Batch) throws IOException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		File resfold = new File(Driver.Resultpath.get()+"/"+dateFormat.format(cal.getTime()));
		if((!resfold.exists()))
		{	
			try
			{
				resfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		String timefold = Driver.ExecutionStarttimestr.get().replace(":","-");
		File tresfold = new File(resfold+"/"+timefold);
		if((!tresfold.exists()))
		{	
			try
			{
				tresfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		File bthresfold = new File(tresfold+"/"+Batch);
		if((!bthresfold.exists()))
		{	
			try
			{
				bthresfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		File scriptfold = new File(tresfold+"/Scripts");
		if((!scriptfold.exists()))
		{	
			try
			{
				scriptfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		String bathresFold = bthresfold.toString();
		screenfoldpth.set(bthresfold+"/ScreenShots");
		File screenfold = new File(screenfoldpth.get());
		if((!screenfold.exists()))
		{	
			try
			{
				screenfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		xmlpth.set(bthresfold+"\\Billxml");
		File xmlfold = new File(xmlpth.get());
		if((!xmlfold.exists()))
		{	
			try
			{
				xmlfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		pdfpth.set(bthresfold+"\\BillPDF");
		File pdffold = new File(pdfpth.get());
		if((!pdffold.exists()))
		{
			try
			{
				pdffold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		Billlogs.set(bthresfold+"\\BillLogs");
		File billlogfold = new File(Billlogs.get());
		if((!billlogfold.exists()))
		{	
			try
			{
				billlogfold.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		EOMXML.set(bthresfold+"\\EOMXML");
		File EOMXMLpth = new File(EOMXML.get());
		if((!EOMXMLpth.exists()))
		{	
			try
			{
				EOMXMLpth.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		WebServiceRequestg.set(bthresfold+"\\WebServiceRequest");
		File WebServiceRequest = new File(WebServiceRequestg.get());
		if((!WebServiceRequest.exists()))
		{	
			try
			{
				WebServiceRequest.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		WebServiceResponseg.set(bthresfold+"\\WebServiceResponse");
		File WebServiceResponse = new File(WebServiceResponseg.get());
		if((!WebServiceResponse.exists()))
		{	
			try
			{
				WebServiceResponse.mkdir();	
			}
			catch(SecurityException se)
			{
				//handle it
			}
		}
		String tempref = Driver.templatfold.get().replace("/","\\\\");
		String resref = bathresFold.replace("/","\\\\");
		detreppath.set(resref+"\\\\DetailedReports.xlsx");
		File detailedrepsource = new File(tempref+"\\\\DetailedReports.xlsx");
		File scriptrepsource = new File(tempref+"\\\\Scripts");
		FileUtils.copyDirectory(scriptrepsource,scriptfold);
		File detailedrepdest = new File(resref);
		FileUtils.copyFileToDirectory(detailedrepsource, detailedrepdest);
		File masterrepsource = new File(tempref+"\\\\MasterReport.xlsx");
		masterreppth.set(resref+"\\\\MasterReport.xlsx");
		File masterrepdest = new File(resref);
		FileUtils.copyFileToDirectory(masterrepsource, masterrepdest);
		File compretemp = new File(tempref+"\\\\compare.xlsx");
		compretempstatic.set(resref+"\\\\compare.xlsx");
		FileUtils.copyFileToDirectory(compretemp, masterrepdest);
		File outputtemp = new File(tempref+"\\\\Output.xlsx");
		outputfinal.set(resref+"\\\\Output.xlsx");
		FileUtils.copyFileToDirectory(outputtemp, masterrepdest);
		logfilepth.set(resref+"/Logs.txt");
		try 
		{
			File logfile = new File(logfilepth.get());
			if (!logfile.exists())
			{
				logfile.createNewFile();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		File masterhtml = new File(tempref+"\\\\MasterReport.HTML");
		FileUtils.copyFileToDirectory(masterhtml, tresfold);
		masterrephtml.set(tresfold.toString()+"\\\\MasterReport.HTML");
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fUpdateLog
	 * Arguments			: logmessage
	 * Use 					: Update the log file with the logmessage passed
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void fUpdateLog(String logmessage) throws IOException
	{
		File logfile = new File(logfilepth.get());
		FileWriter fw = new FileWriter(logfile.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(logmessage+"\r\n");
		bw.close();  
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fUpdateReport
	 * Arguments			:
	 * Use 					: Update the detailed report
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void fUpdateReport()
	{
		try
		{
			Driver fd = new Driver();
			Fillo fillo = new Fillo();
			Connection connection=fillo.getConnection(detreppath.get());
			@SuppressWarnings("static-access")
			String drtime = fd.keywordstarttime.get();
			//@SuppressWarnings("static-access")
			//String dr = drtime.HOUR0_FIELD+":"+drtime.MINUTE_FIELD+":"+drtime.SECOND_FIELD;
			String colnames = "BatchName,FunctionalScenario,TestScriptID,ManualTestCaseID,Keywords,Description,Result,Date,Time";
			String columnVal = "\'"+Driver.currbatchname.get()+"\',\'"+Driver.currscnname.get()+"\',\'"+Driver.currtc.get()+"\',\'"+Driver.currmtc.get()+"\',\'"+Driver.testkeywords.get()+"\',\'"+Driver.resultdescription.get()+"\',\'"+Driver.currkeywordstatus.get()+"\',\'"+Driver.keywordstartdate.get()+"\',\'"+drtime+"\'";
			String insertquerry = "insert into detailedreport("+colnames+") values("+columnVal+")";
			connection.executeUpdate(insertquerry);
		}
		catch(Exception e)
		{
			System.err.print(e.getMessage());
		}
		
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fUpdateMasterReport
	 * Arguments			: 
	 * Use 					: Update theMaster Report
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void fUpdateMasterReport(String Val) {
		try {
			Fillo fillo = new Fillo();
			Connection ResEngine = fillo.getConnection(masterreppth.get());
			
			if (Val.equals("TDCase"))
			{
				
			}	
			else if (Val.equals("InsertTestCase"))
			{
				String strQuery="INSERT INTO MasterReport(TestCase,StartTime) VALUES('"+Driver.currtc.get()+"','"+Driver.TestCaseStartTime.get()+"')";
				ResEngine.executeUpdate(strQuery);
				
			}
			else if (Val.equals("TestCase"))
			{
				String updateqry = "Update MasterReport Set TestCaseResult='"+Driver.currtcstatus.get()+"' where TestCase='"+Driver.currtc.get()+"'";
				ResEngine.executeUpdate(updateqry);
				String nupdateqry = "Update MasterReport Set EndTime='"+Driver.TestCaseEndTime.get()+"' where TestCase='"+Driver.currtc.get()+"'";
				ResEngine.executeUpdate(nupdateqry);
				nupdateqry = "Update MasterReport Set TestCaseDis='"+Driver.tcdiscription.get()+"' where TestCase='"+Driver.currtc.get()+"'";
				ResEngine.executeUpdate(nupdateqry);
				
			}
			else if (Val.equals("Scenario"))
			{
				String TSInsertQuery="INSERT INTO MasterReport(FunctionalScenario,StartTime) VALUES('"+Driver.currscnname.get()+"','"+Driver.TestScnStartTime.get()+"')";
				ResEngine.executeUpdate(TSInsertQuery);
			}
			else if (Val.equals("UpdateScenario"))
			{
				String updateqry = "Update MasterReport Set ScenarioResult='"+Driver.currscnstatus.get()+"' where FunctionalScenario='"+Driver.currscnname.get()+"'";
				ResEngine.executeUpdate(updateqry);
				String nupdateqry = "Update MasterReport Set EndTime='"+Driver.TestScnEndTime.get()+"' where FunctionalScenario='"+Driver.currscnname.get()+"'";
				ResEngine.executeUpdate(nupdateqry);
			}
			else if (Val.equals("Batch"))
			{
				String TBInsertQuery="INSERT INTO MasterReport(BatchName,StartTime) VALUES('"+Driver.currbatchname.get()+"','"+Driver.exestarttime.get()+"')";
				ResEngine.executeUpdate(TBInsertQuery);
			}
			else if (Val.equals("UpdateBatch"))
			{
				String updateqry = "Update MasterReport Set BatchResult='"+Driver.currbatchstatus.get()+"' where BatchName='"+Driver.currbatchname.get()+"'";
				ResEngine.executeUpdate(updateqry);
				String nupdateqry = "Update MasterReport Set EndTime='"+Driver.ExeEndTime.get()+"' where BatchName='"+Driver.currbatchname.get()+"'";
				ResEngine.executeUpdate(nupdateqry);
			}
		ResEngine.close();
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: takescreenshot
	 * Arguments			: 
	 * Use 					: take screenshot and save it in screen shots folder
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void takescreenshot(String LogMessage)
	{
		try
		{
			Driver.scrnno.set(Driver.scrnno.get()+1);		
			File scrFile = ((TakesScreenshot)KeyWord.cDriver.get()).getScreenshotAs(OutputType.FILE);
			CustomXWPFDocument document = new CustomXWPFDocument(new FileInputStream(new File(tcscreenfilepth.get())));
			FileOutputStream fos = new FileOutputStream(new File(tcscreenfilepth.get()));
			String id = document.addPictureData(new FileInputStream(new File(scrFile.toString())), Document.PICTURE_TYPE_PNG);
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run=paragraph.createRun();
			run.setText(LogMessage);
			document.createPicture(id, document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 640, 480);
			document.write(fos);
			fos.flush();
			fos.close();
			document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: fcreateMasterHTML
	 * Arguments			: 
	 * Use 					: Create HTML Master report
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void fcreateMasterHTML() throws IOException
	{
		File logfile = new File(masterrephtml.get());
		FileWriter fw = new FileWriter(logfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String logmessage = "<!-- saved from url=(0016)http://localhost -->"+ 
				"<html>"+
				"<head>"+
				"<title>Execution Results</title>"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"+
				"<link rel=\"stylesheet\" href=\""+"Scripts\\style.css\" type=\"text/css\">"+
				"<script src=\""+"Scripts/amcharts.js\" type=\"text/javascript\"></script>"+
				"<style>"+
				"table {font-size: 12px;"+
				"background:#E6E6E6;"+
				"}"+
				"</style>"+
				"<script>"+
				"var chart;"+
				"var chartData = [{"+
				"Status: \"Pass\","+
				"Count:"+Driver.passtc+
				"}, {"+
				"Status: \"Fail\","+
				"Count:"+Driver.failtc+
				"}];"+
				"AmCharts.ready(function () {"+
				"chart = new AmCharts.AmPieChart();"+
				"chart.addTitle(\"Execution Status\", 16);"+
				"chart.dataProvider = chartData;"+
				"chart.titleField = \"Status\";"+
				"chart.valueField = \"Count\";"+
				"chart.sequencedAnimation = true;"+
				"chart.startEffect = \"elastic\";"+
				"chart.innerRadius = \"30%\";"+
				"chart.startDuration = 2;"+
				"chart.labelRadius = 15;"+
				"chart.depth3D = 10;"+
				"chart.angle = 15;"+
				"chart.write(\"chartdiv\");"+
				"});"+
				"</script>"+
				"</script>"+
				"</head>"+
				"<body bgcolor = \"white\">"+
				"<div id = \"lastres\">"+
				"<table width='100%' border=2>"+
				"<tr>"+
				"<td border='0'>"+
				"<img src ='"+"Scripts\\"+"AG-logo.jpg' height = 30% width = 100%>"+
				"</td>"+	
				"<td width = 73%>"+
				"<center><h1> Master Report </h1>  </center>"+
				"</td>"+
				"<td border='0'>"+
				"<img src ='"+"Scripts\\"+"Client-logo.jpg' height = 30% width = 100%>"+
				"</td>"+	
				"</tr>"+
				"<table width='100%' border=2>"+
				"<tr>"+
				"<td align=\"center\" colspan=3><h3>Execution overview </h3></td>"+
				"</tr>"+
				"<tr>"+
				"<td align=\"center\">Total</td>"+
				"<td align=\"center\">Pass</td>"+
				"<td align=\"center\">Fail</td>"+
				"</tr>"+
				"<tr>"+
				"<td align=\"center\" id=\"tot\">"+Driver.totaltc+"</td>"+
				"<td align=\"center\" id=\"totpass\">"+Driver.passtc+"</td>"+
				"<td align=\"center\" id=\"totfail\">"+Driver.failtc+"</td>"+
				"</tr>"+
				"</table>"+
				"<table border=2 width='100%'>"+
				"<tr>"+
				"<td width='50%' align=\"center\" colspan = 2><div id=\"chartdiv\" style=\"width:450px; height:350px;\"></div></td>"+
				"<td valign ='top'>"+
				"<table border =1 width = 100%>"+
				"<tr>"+
				"<td>"+
				"<center><b>Test Cases</b></center>"+
				"</td>"+
				"</tr>"+	
				"<table border = 1 width = 100%>"+
				"<tr>"+
				"<td width = 50%><b><center>Test Case ID</center></b></td>"+
				"<td width = 50%><b><center>Status</center></b></td>"+
				"</tr>";
		bw.write(logmessage+"\r\n");
		logmessage = "";
		updatelogmsg = updatelogmsg+"<tr>"+
				"<td width = 50%><center><a href =\".\\"+Driver.BatchName.get()+"\\ScreenShots\\"+Driver.currtc.get()+".docx"+"\">"+Driver.currtc.get()+"</a></center></td>";
		if(Driver.currtcstatus.get().equals("Pass"))
		{
			updatelogmsg =updatelogmsg+"<td width = 50% Style=\"color:green\"><center>Pass</center></td>";
		}
		else if(Driver.currtcstatus.get().equals("Fail"))
		{
			updatelogmsg =updatelogmsg+"<td width = 50% Style=\"color:Red\"><center>Fail</center></td>";
		}
		updatelogmsg = updatelogmsg+"</tr>";
		bw.write(updatelogmsg);
		bw.close();
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: createTCScreenshotFold
	 * Arguments			: 
	 * Use 					: Create screen shots folder according to test case id
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void createTCScreenshotFold()
	{
		File tcscreenfold = new File(screenfoldpth.get()+"/"+Driver.currtc.get()+Driver.currtcite.get()+".docx");
		tcscreenfoldpth.set(tcscreenfold.toString());
		try
		{
			@SuppressWarnings("resource")
			XWPFDocument document= new XWPFDocument(); 
			//Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File(tcscreenfoldpth.get()));
			document.write(out);
			out.close();
			tcscreenfilepth.set(tcscreenfoldpth.get()); 
			tcxmlpth.set(xmlpth.get()+"\\"+Driver.currtc.get());
			File tcxmlfold = new File(tcxmlpth.get());
			if((!tcxmlfold.exists()))
			{	
				try
				{
					tcxmlfold.mkdir();	
				}
				catch(SecurityException se)
				{
					//handle it
				}
			}
			tcpdfpth.set(pdfpth.get()+"\\"+Driver.currtc.get());
			File tcpdffold = new File(tcpdfpth.get());
			if((!tcpdffold.exists()))
			{	
				try
				{
					tcpdffold.mkdir();	
				}
				catch(SecurityException se)
				{
				//handle it
				}
			}
			tcbillogpth.set(Billlogs.get()+"\\"+Driver.currtc.get());
			File tcbilllogfold = new File(tcbillogpth.get());
			if((!tcbilllogfold.exists()))
			{
				try
				{
					tcbilllogfold.mkdir();	
				} 
				catch(SecurityException se)
				{
					//handle it
				}
			}
			EOOMXMLTC.set(EOMXML.get()+"\\"+Driver.currtc.get());
			File EOMXMLTCPth = new File(EOOMXMLTC.get());
			if((!EOMXMLTCPth.exists()))
			{	
				try
				{
					EOMXMLTCPth.mkdir();	
				}
				catch(SecurityException se)
				{
				//handle it
				}
			}
			Billlogfilepth.set(tcbillogpth.get()+"/BillLogs.txt");
			try 
			{
				File logfile = new File(Billlogfilepth.get());
				if (!logfile.exists())
				{
					logfile.createNewFile();
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void UpdateBilllog(String logmessage)
	{
		try
		{
			File logfile = new File(Billlogfilepth.get());
			FileWriter fw = new FileWriter(logfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\r\n");
			bw.write(logmessage+"\r\n");
			System.out.println(logmessage);
			bw.close();  
			@SuppressWarnings("resource")
			CustomXWPFDocument document = new CustomXWPFDocument(new FileInputStream(new File(tcscreenfilepth.get())));
			FileOutputStream fos = new FileOutputStream(new File(tcscreenfilepth.get()));
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run=paragraph.createRun();
			run.setText(logmessage);
			run.addBreak();
			document.write(fos);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}