package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_reader {

	private static String path = "C:\\softWare\\eclipse_workspace\\MavenFacebookAutom\\src\\main\\java\\testdata\\APITestData.xlsx";
	public static Map<String, List<String>> getTestData() {
	    try {
	        File file = new File(path);   //creating a new file instance
	        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
	       
	        //creating Workbook instance that refers to .xlsx file
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
	        Iterator<Row> itr = sheet.iterator();    //iterating over excel file

	        Map<String, List<String>> myMap = new LinkedHashMap<String, List<String>>();

	        // populate map with headers and empty list
	        if (itr.hasNext()) {
	            Row row = itr.next();
	            Iterator<Cell> headerIterator = row.cellIterator();
	            while (headerIterator.hasNext()) {
	                Cell cell = headerIterator.next();
	                myMap.put(getCellValue(cell).trim(), new ArrayList<String>());
	            }
	        }

	        Iterator<List<String>> columnsIterator;
	        // populate lists
	        while (itr.hasNext()) {

	            // get the list iterator every row to start from first list
	            columnsIterator = myMap.values().iterator();
	            Row row = itr.next();
	            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();

	                // here don't check hasNext() because if the file not contains problems
	                // the # of columns is same as # of headers
	                columnsIterator.next().add(getCellValue(cell).trim());
	            }
	        }

	        // here your map should be filled with data as expected
	        return myMap;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	    
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
		Map<String, List<String>> data = getTestData();
		System.out.println(data);
	}
}

