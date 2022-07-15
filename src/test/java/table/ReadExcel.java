package table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.DocFlavor.STRING;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		
		File f = new File("C:\\Users\\jawah\\Desktop\\jawahar.xlsx");
		FileInputStream stream = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet sheet = w.getSheet("Sheet1");
		Sheet createSheet = w.createSheet("Sheet2");

		for (int i = 0; i <sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			Row createRow = sheet.createRow(i);
			
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell = row.getCell(j);
				Cell createCell = row.createCell(j);
				CellType cellType = cell.getCellType();
				
				if (cellType == CellType.STRING) {
					String stringCellValue = cell.getStringCellValue();
					createCell.setCellValue(stringCellValue);					
				}
				else if (DateUtil.isCellDateFormatted(cell)) {
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat s = new SimpleDateFormat("MM/DD/YYYY");
					String format = s.format(dateCellValue);
					createCell.setCellValue(dateCellValue);					
				}
				else if (cellType==cellType.NUMERIC){
					double numericCellValue = cell.getNumericCellValue();
					long l = (long) numericCellValue;
					createCell.setCellValue(l);					
				}
								
			}
			
		}
		FileOutputStream os = new FileOutputStream(f);
		w.write(os);
	}

}
