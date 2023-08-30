package com.clover.labs.excel;

/*import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

import com.clover.labs.phonetic.ForwardOnlyCombinations;

public class ReadExcelFileDemo {

    private static ForwardOnlyCombinations combinations = new ForwardOnlyCombinations();

    private static String nameInSwiftMessage = "";
    private static String nameInCoreBanking = "";

    /*public static void main(String[] args) {
        try {
            File file = new File("/mnt/hgfs/Shared/Name Matching_POC.xlsx"); //creating a new file instance
            FileInputStream fis = new FileInputStream(file); //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file  
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0); //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator(); //iterating over excel file  
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator(); //iterating over each column  
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if(cell.getColumnIndex() == 0) {
                        nameInSwiftMessage = cell.getStringCellValue();
                    } else if(cell.getColumnIndex() == 1) {
                        nameInCoreBanking = cell.getStringCellValue();
                    }
                    *//*if (cell.getCellType() == CellType.NUMERIC) {
                        //System.out.print(cell.getNumericCellValue() + "           ");
                    } else if (cell.getCellType() == CellType.STRING) {
                        //System.out.print(cell.getStringCellValue() + "           ");
                    }*//*
                }
                System.out.print(new ForwardOnlyCombinations().percentage(nameInSwiftMessage, nameInCoreBanking));
                //System.out.print(nameInSwiftMessage + "\t\t");
                //System.out.print(nameInCoreBanking + "\t\t");
                System.out.println("");
                nameInSwiftMessage = "";
                nameInCoreBanking = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}