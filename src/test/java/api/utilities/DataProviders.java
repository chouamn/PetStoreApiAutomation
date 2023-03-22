package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {


	@DataProvider(name="Data")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";//taking xl file from testData

		XLUtility xl=new XLUtility(path);//creating an object for XLUtility

		int totalrows=xl.getRowCount("Sheet1");	
		int totalcols=xl.getCellCount("Sheet1",1);

		String apidata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password

		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				apidata[i-1][j]= xl.getCellData("Sheet1", i, j);  //1,0
			}
		}
		return apidata;//returning two dimension array

	}


	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";//taking xl file from testData

		XLUtility xl=new XLUtility(path);//creating an object for XLUtility

		int totalrows=xl.getRowCount("Sheet1");	
		

		String apidata[]=new String[totalrows];//created for two dimension array which can store the data user and password

		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			
				apidata[i-1]= xl.getCellData("Sheet1", i, 1);  //1,0
			
		}
	
		return apidata;//returning single  dimension array

	}














}
