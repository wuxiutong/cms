package wxt.dao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by wuxiutong on 2015/9/14.
 */
public class ExportOutExcelUtils {
    @Test
    public void outAExcel() {
        Workbook wb = null;
        FileOutputStream fileOut = null;
        try {
            //新建一个excel表格
            wb = new HSSFWorkbook();
            //新建一个sheet
            Sheet sheet1 = wb.createSheet("工作簿1");
            Sheet sheet2 = wb.createSheet("工作簿12");
            //创建一个cells
            CreationHelper createHelper = wb.getCreationHelper();
            Row row1 = sheet1.createRow(0);
            row1.createCell(0).setCellValue("标题居中1到4列");
            Row row = sheet1.createRow(1);
            Cell cell = row.createCell(0);
            row.createCell(1).setCellValue("1-0");
            row.createCell(2).setCellValue("2-0");
            row.createCell(3).setCellValue(true);
            //设置单元格格式属性
            CellStyle style = wb.createCellStyle();
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_THIN);
            style.setAlignment(CellStyle.ALIGN_CENTER);
             row1.getCell(0).setCellStyle(style);
             row.getCell(0).setCellStyle(style);
             row.getCell(2).setCellStyle(style);
             row.getCell(3).setCellStyle(style);
             row.getCell(1).setCellStyle(style);

            //跨行和跨列居中
            sheet1.setRepeatingColumns(CellRangeAddress.valueOf("A:D"));
            //合并单元格 第一，二个参数的开始行、结束行，第三、四个参数是开始列和结束列。
            sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            //新建一个文件流
            fileOut = new FileOutputStream("workbook.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
