package table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\jawah\\eclipse-workspace\\table\\src\\test\\resources\\Book1.xlsx");
		FileInputStream stream = new FileInputStream(f);
		Workbook book = new XSSFWorkbook(stream);
		Sheet sheet = book.getSheet("Sheet1");
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			Row row = sheet.getRow(i);
			int cells = row.getPhysicalNumberOfCells();
			for (int j = 0; j < cells; j++) {
				Cell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					String stringCellValue = cell.getStringCellValue();
					System.out.println("Name is: "+stringCellValue);
					break;

				default:
					if(DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						System.out.println(dateCellValue);
						SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
						String format = s.format(dateCellValue);
						System.out.println(format);

					}
					else {
						double numericCellValue = cell.getNumericCellValue();
						System.out.println(numericCellValue);
						long l =(long)numericCellValue;
						System.out.print("DOB is:"+l);

					}
					break;
				}
			}
		}



	}



}
