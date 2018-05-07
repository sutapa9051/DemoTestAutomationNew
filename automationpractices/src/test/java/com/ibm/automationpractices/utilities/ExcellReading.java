package com.ibm.automationpractices.utilities;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.ibm.automationpractices.utilities.ExcellReading;

public class ExcellReading {
	static FileInputStream fis=null;
	static Workbook wb = null;
	static Sheet s = null;

	static {

		try {
			fis = new FileInputStream(".\\Testdata\\testdata.xlsx");
			wb = WorkbookFactory.create(fis);
			s = wb.getSheet("Sheet1");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static int getTestCaseRowCount(String tcn) {
		int c = 0;
		int rc = ExcellReading.s.getPhysicalNumberOfRows();
		for (int i = 1; i < rc; i++) {
			Row r = ExcellReading.s.getRow(i);
			String tname = r.getCell(1).getStringCellValue().trim();
			String ts = r.getCell(2).getStringCellValue().trim();
			if (tname.equalsIgnoreCase(tcn) && ts.equalsIgnoreCase("Y")) {
				c++;
			}
		}
		return c;
	}

	static int getTestCaseCellCount(String tcn) {
		int c = 0;
		int rc = ExcellReading.s.getPhysicalNumberOfRows();
		// System.out.println(rc);
		for (int i = 1; i < rc; i++) {
			Row r = ExcellReading.s.getRow(i);
			String tname = r.getCell(1).getStringCellValue().trim();
			String ts = r.getCell(2).getStringCellValue().trim();
			if (tname.equalsIgnoreCase(tcn) && ts.equalsIgnoreCase("Y")) {
				return r.getPhysicalNumberOfCells() - 3;

			}

		}
		return 0;
	}

	@DataProvider(name = "xyz")
	public static String[][] StoreTestCellData(Method m1) {
		String tname = m1.getName();
		int ri = ExcellReading.getTestCaseRowCount(tname);
		int ci = ExcellReading.getTestCaseCellCount(tname);
		String[][] td = new String[ri][ci + 1];
		int nri = 0;
		int rc = ExcellReading.s.getPhysicalNumberOfRows();
		for (int i = 1; i < rc; i++) {
			Row r = ExcellReading.s.getRow(i);
			String tcname = r.getCell(1).getStringCellValue().trim();
			String ts = r.getCell(2).getStringCellValue().trim();
			if (tcname.equalsIgnoreCase(tname) && ts.equalsIgnoreCase("Y")) {
				int nci = 0;
				for (int c = 3; c < r.getPhysicalNumberOfCells(); c++) {
					String tcdata = r.getCell(c).getStringCellValue();
					td[nri][nci++] = tcdata;
				}
				td[nri++][nci] = i + "";
			}
		}
		return td;
	}
}
