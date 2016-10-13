Set objFS = CreateObject("Scripting.FileSystemObject")
strFile = "C:\Users\admin\Desktop\graph\amcharts\amcharts.js"
strTemp = "C:\Users\admin\Desktop\graph\amcharts\output.txt"
Set objFile = objFS.GetFile(strFile)
Set objOutFile = objFS.CreateTextFile(strTemp,True)
Set ts = objFile.OpenAsTextStream(1,-2)
Do Until ts.AtEndOfStream
    strLine = ts.ReadLine
    ' do something with strLine 
if instr(strLine,"if") then
   valif= split(strLine,"if")
    arr=split(strLine,";")
    for i=0 to ubound(arr)
     if arr(i) <> "" then

    val=arr(i)+";" 
    objOutFile.WriteLine(val)
  end if 
next
Loop
objOutFile.Close
ts.Close
