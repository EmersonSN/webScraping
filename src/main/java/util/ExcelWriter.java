package util;

import model.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    public void createSheet(List<Product> products) {//Create an Excel file
        Workbook workFolder = new XSSFWorkbook();

        //Create a sheet
        Sheet sheet = workFolder.createSheet("Products");

        //Create columns for description and value
        Row line = sheet.createRow(0);
        Cell cell = line.createCell(0);
        cell.setCellValue("Description");

        Cell cell1 = line.createCell(1);
        cell1.setCellValue("Value");

        //Sheet size
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        //Filling the data
        if (!products.isEmpty()) {
            int i = 0;
            for (Product p : products) {
                Row productLine = sheet.createRow(i);
                Cell descriptionCell = productLine.createCell(0);
                descriptionCell.setCellValue(p.getDescription());
                Cell valueCell = productLine.createCell(1);
                valueCell.setCellValue(p.getValue(p.getPrice()).toString());
                i++;
            }
        }

        try (FileOutputStream file = new FileOutputStream("products.xlsx")) {
            workFolder.write(file);
            System.out.println("SHEET CREATED WITH SUCCESS!!");
        } catch (IOException e) {
            System.out.println("Create sheet file error: " + e.getMessage());
        } finally {
            try {
                workFolder.close();
            } catch (IOException e) {
                System.out.println("Close folder error: " + e.getMessage());
            }

        }
    }
}
