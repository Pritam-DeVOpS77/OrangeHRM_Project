package excelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadDataFromExcelFile {
	
	 String fPath ="C:\\Users\\prita\\eclipse-workspace\\OrangeHRM\\ExcelFiles\\LoginData.xlsx";
	 File file;
	 FileInputStream fis;
	 XSSFWorkbook wb;
	 XSSFSheet sheet;
	 XSSFRow row;
	 XSSFCell cell;
	
	 @DataProvider
	public Object[][] getLoginData() throws IOException{
		
		 file = new File(fPath);
		 fis = new FileInputStream(file);
		 wb = new XSSFWorkbook(fis);
		 sheet = wb.getSheetAt(0);
		 
		 int totalRows = sheet.getPhysicalNumberOfRows();
		 int totalCells  = sheet.getRow(0).getPhysicalNumberOfCells();
		 String [][] data = new String [totalRows][totalCells];
		 
		 for (int i =0; i<totalRows;i++)
		 {
			 for (int j=0; j<totalCells;j++)
			 {
				data [i][j]= sheet.getRow(i).getCell(j).getStringCellValue();
			 }
		 }
			 	 
		 return data;
			
	}
}
