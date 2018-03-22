package com.excle.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelTest {

	    private static final String EXTENSION_XLS = "xls";
	    private static final String EXTENSION_XLSX = "xlsx";

	    /***
	     * <pre>
	     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
	     *   xls:HSSFWorkbook
	     *   xlsx：XSSFWorkbook
	     * @param filePath
	     * @return
	     * @throws IOException
	     * </pre>
	     */
	    private static Workbook getWorkbook(String filePath) throws IOException {
	        Workbook workbook = null;
	        InputStream is = new FileInputStream(filePath);
	        if (filePath.endsWith(EXTENSION_XLS)) {
	            workbook = new HSSFWorkbook(is);
	        } else if (filePath.endsWith(EXTENSION_XLSX)) {
	        	try {
	        		workbook = new XSSFWorkbook(is);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println(e);
				}  
	        }
	        return workbook;
	    }

	    /**
	     * 文件检查
	     * @param filePath
	     * @throws FileNotFoundException
	     * @throws FileFormatException
	     */
	    private static void preReadCheck(String filePath) throws FileNotFoundException {
	        // 常规检查
	        File file = new File(filePath);
	        if (!file.exists()) {
	            throw new FileNotFoundException("传入的文件不存在：" + filePath);
	        }
	    }

	    /**
	     * 读取excel文件内容
	     * @param filePath
	     * @throws FileNotFoundException
	     * @throws FileFormatException
	     */
	    public static List<String[]> readExcel(String filePath) throws FileNotFoundException {
	        // 检查
	        preReadCheck(filePath);
	        // 获取workbook对象
	        Workbook workbook = null;
	        List<String[]> list = new ArrayList<String[]>();
	        try {
	            workbook = getWorkbook(filePath);
	            // 读文件 一个sheet一个sheet地读取
	              
	              
	            if(workbook != null){  
	                for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){  
	                    //获得当前sheet工作表  
	                    Sheet sheet = workbook.getSheetAt(sheetNum);  
	                    if(sheet == null){  
	                        continue;  
	                    }  
	                    //获得当前sheet的开始行  
	                    int firstRowNum  = sheet.getFirstRowNum();  
	                    //获得当前sheet的结束行  
	                    int lastRowNum = sheet.getLastRowNum();  
	                    //循环除了第一行的所有行  
	                    for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){  
	                        //获得当前行  
	                        Row row = sheet.getRow(rowNum);  
	                        if(row == null){  
	                            continue;  
	                        }  
	                        //获得当前行的开始列  
	                        int firstCellNum = row.getFirstCellNum();  
	                        //获得当前行的列数  
	                        int lastCellNum = row.getPhysicalNumberOfCells();  
	                        String[] cells = new String[row.getPhysicalNumberOfCells()];  
	                        //循环当前行  
	                        for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
	                            Cell cell = row.getCell(cellNum);  
	                            cells[cellNum] = getCellValue(cell);  
	                        }  
	                        list.add(cells);  
	                    }  
	                }
	                } 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        
	            
	        }
			return list;
	    }

	    /**
	     * 取单元格的值
	     * @param cell 单元格对象
	     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	     * @return
	     */
	    public static String getCellValue(Cell cell){  
	        String cellValue = "";  
	        if(cell == null){  
	            return cellValue;  
	        }  
	        //把数字当成String来读，避免出现1读成1.0的情况  
	        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
	            cell.setCellType(Cell.CELL_TYPE_STRING);  
	        }  
	        //判断数据的类型  
	        switch (cell.getCellType()){  
	            case Cell.CELL_TYPE_NUMERIC: //数字  
	                cellValue = String.valueOf(cell.getNumericCellValue());  
	                break;  
	            case Cell.CELL_TYPE_STRING: //字符串  
	                cellValue = String.valueOf(cell.getStringCellValue());  
	                break;  
	            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
	                cellValue = String.valueOf(cell.getBooleanCellValue());  
	                break;  
	            case Cell.CELL_TYPE_FORMULA: //公式  
	                cellValue = String.valueOf(cell.getCellFormula());  
	                break;  
	            case Cell.CELL_TYPE_BLANK: //空值   
	                cellValue = "";  
	                break;  
	            case Cell.CELL_TYPE_ERROR: //故障  
	                cellValue = "非法字符";  
	                break;  
	            default:  
	                cellValue = "未知类型";  
	                break;  
	        }  
	        return cellValue;  
	    }  

	
}
