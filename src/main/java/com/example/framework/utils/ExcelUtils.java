package com.example.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ExcelUtils {
    public static List<Map<String, String>> readSheet(String resourcePath, String sheetName) {
        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(resourcePath);
             Workbook wb = new XSSFWorkbook(is)) {
            Sheet sheet = wb.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();
            List<Map<String, String>> data = new ArrayList<>();
            List<String> headers = new ArrayList<>();
            if (rows.hasNext()) {
                Row header = rows.next();
                header.forEach(c -> headers.add(c.getStringCellValue()));
            }
            rows.forEachRemaining(r -> {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = r.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    row.put(headers.get(i), cell.getStringCellValue());
                }
                data.add(row);
            });
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel: " + resourcePath + "#" + sheetName, e);
        }
    }
}
