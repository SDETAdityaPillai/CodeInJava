package testUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	private static String path = "C:\\softWare\\eclipse_workspace\\MavenFacebookAutom\\src\\main\\java\\testdata\\APITestData.xlsx";
	
	static XSSFWorkbook book;
    static XSSFSheet sheet;     
	
	public static Object[][] getDataFromSheet(){
		Map<String, List<String>> myMap = Xls_reader.getTestData();
		Object[] header = myMap.keySet().toArray();
		
		int getColumns = myMap.size();
		int getRows = myMap.get(header[1]).size()+1;
		
		System.out.println(getColumns + " "+ getRows);
		Object[][] data = new Object[getRows-1][getColumns];
		
		/*for (int k = 0; k < getColumns; k++) {
				data[0][k] = header[k]; 
		}*/
		for (int i = 0; i < getRows-1; i++) {
			for (int k = 0; k < getColumns; k++) {
			data[i][k] = myMap.get(header[k]).get(i); 
			
		}
		
		}
		return data;		
	}
	
	public static String getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
	            return cell.getStringCellValue() + "\t\t\t";
	        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
	            return cell.getNumericCellValue() + "\t\t\t";
	        default:
	            return "";
	    }
	}
	public static void main (String arg[]){
		Object[][] Testdata = getDataFromSheet();
		for (int i = 0; i < Testdata.length; i++)
		{
			System.out.println("");
			for (int k = 0; k < Testdata[0].length; k++)
			System.out.print(Testdata[i][k]+ " ");
		}	
	}	
}