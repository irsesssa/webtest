package com.webtest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WorkBookCreater {

    public void doSomething(Map<String, String> urlAndTitle) throws Exception {

        createWorkBook();

        int page=0;

        for (Map.Entry<String, String> entry : urlAndTitle.entrySet()) {
            page++;
            openWorkBook(entry.getKey(),entry.getValue(),page);
        }
    }


    public void createWorkBook() throws IOException {

        //Create Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create file system using specific name
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\iraci\\IdeaProjects\\webtest\\workbook.xlsx"));

        //write operation workbook using file out object
        workbook.write(out);
        out.close();
    }


    public void openWorkBook(String title,String url,int numPage) throws Exception {

        File file = new File("C:\\Users\\iraci\\IdeaProjects\\webtest\\workbook.xlsx");
        FileInputStream fIP = new FileInputStream(file);


        XSSFWorkbook workbook = new XSSFWorkbook(fIP);
        XSSFSheet spreadsheet = workbook.createSheet("Page number "+numPage);


        XSSFRow row = spreadsheet.createRow((short) 1);


        Map<String, Object[]> valutes =
                new TreeMap<String, Object[]>();

        valutes.put("Name", new Object[]{"Title","Url"});

        valutes.put("Value", new Object[]{title,url});





        //Iterate over data and write to sheet
        Set<String> keyid = valutes.keySet();
        int rowid = 0;


        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = valutes.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\iraci\\IdeaProjects\\webtest\\workbook.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Workbook.xlsx written successfully");
    }
}
