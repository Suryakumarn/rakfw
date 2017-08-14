package Libraries;
import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import java.io.File;

public class autotest {
	
	public static void main(String argv[]) throws InterruptedException
	{
		String jacobDllVersionToUse = "D:\\eclipse\\WorkSpace\\FrameWork\\lib\\jacob-1.18-x86.dll";
		//File file = new File("lib", jacobDllVersionToUse);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobDllVersionToUse);
		AutoItX x = new AutoItX();
		x.run("calc.exe");
		x.winActivate("Calculator");
		x.winWaitActive("Calculator");
		//Enter 3
		x.controlClick("Calculator", "", "133") ;
		Thread.sleep(1000);
		//Enter +
		x.controlClick("Calculator", "", "93") ;
		Thread.sleep(1000);
		//Enter 3
		x.controlClick("Calculator", "", "133") ;
		Thread.sleep(1000);
		//Enter =
		x.controlClick("Calculator", "", "121") ;
		
		
	}

}
