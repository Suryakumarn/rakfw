package Libraries;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.JSch;

public class SSH extends Driver{
	/*------------------------------------------------------------------------------------------------------
	 * Function Name: LoginSSH
	 * Use : Login to the server   
	 * Designed By: AG
	 * Last Modified Date : 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static void LoginSSH(String host,String username,String password)
	{
		try
		{
			//Properties config = new Properties(); 
			//config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			nsession.set(jsch.getSession(username, host, 22));
			nsession.get().setPassword(password);
			nsession.get().setConfig("StrictHostKeyChecking","no");
			nsession.get().connect();
			channel.set(nsession.get().openChannel("shell"));
			System.out.println("Connected to: "+host);
			channel.get().connect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------------------------------------------------
	 * Function Name: Executecmd
	 * Use : Execute the command passed in the server and get the output.    
	 * Designed By: AG
	 * Last Modified Date : 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/    
	@SuppressWarnings("unused")
	public static String Executecmd(String command,String Exitval)
	{
		try
		{
			String str = "";
			String str1;
			OutputStream ops = channel.get().getOutputStream();
			PrintStream ps = new PrintStream(ops, true);
			ps.println(command);
			InputStream in=channel.get().getInputStream();    		
			byte[] bt=new byte[1024];
			while(true)
			{
				int i=in.read(bt, 0, 1024);
				if(i<0) break;
				str1 = new String(bt, 0, i);
				if(str1==null)
				{
					break;
				}
				str=str+str1;
				//System.out.println(str);
				//System.out.print(str);
				if(str.contains(Exitval))
				{
					break;
				}
				if(channel.get().isClosed())
				{
					System.out.println("exit-status: "+channel.get().getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
			//channel.disconnect();
			System.out.println("DONE");
			return str;
		}
		catch(Exception e)
		{
			Continue.set(false);
			e.printStackTrace();
			return "";
		}
	}


}
