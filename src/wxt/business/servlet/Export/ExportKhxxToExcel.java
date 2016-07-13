package wxt.business.servlet.Export;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import wxt.dao.QueryData;
import wxt.model.*;
import wxt.utils.DMMCUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 2015/9/14.
 */
public class ExportKhxxToExcel extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        //新建excel文档
        Workbook wb = null;
        Sheet sheet = null;
        FileOutputStream fileOut = null;
        try {
            //获取先择到的客户dmmc
            String khdmmcs = request.getParameter("exportkhdms");
            String khdmmcA[] = khdmmcs.split(";");

            wb = new HSSFWorkbook();
            sheet = wb.createSheet("sheet1");

            //设置表头的格式属性
            CellStyle styleCaption = wb.createCellStyle();
            styleCaption.setAlignment(CellStyle.ALIGN_CENTER);//单元格文字居中
            Font fontCaption = wb.createFont();
            fontCaption.setFontHeight((short) 480);//设置字体大小
            fontCaption.setFontName("宋体");
            fontCaption.setBold(true);
            styleCaption.setFont(fontCaption);

            //设置单元格标题格式属性
            CellStyle styletTitle = wb.createCellStyle();
            styletTitle.setBorderBottom(CellStyle.BORDER_THIN);//单元格下边表格线
            styletTitle.setBorderTop(CellStyle.BORDER_THIN);//单元格上方表格线
            styletTitle.setBorderLeft(CellStyle.BORDER_THIN);//单元格左边表格线
            styletTitle.setBorderRight(CellStyle.BORDER_THIN);//单元格右边表格线
            styletTitle.setAlignment(CellStyle.ALIGN_CENTER);//单元格文字居中
            styletTitle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            styletTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            Font fontTitle = wb.createFont();
            fontTitle.setFontHeight((short) 240);//设置字体大小
            fontTitle.setFontName("宋体");
            fontTitle.setBold(true);
            styletTitle.setFont(fontTitle);

            //设置单元格内容格式属性
            CellStyle styleCell = wb.createCellStyle();
            styleCell.setBorderBottom(CellStyle.BORDER_THIN);//单元格下边表格线
            styleCell.setBorderTop(CellStyle.BORDER_THIN);//单元格上方表格线
            styleCell.setBorderLeft(CellStyle.BORDER_THIN);//单元格左边表格线
            styleCell.setBorderRight(CellStyle.BORDER_THIN);//单元格右边表格线
            styleCell.setAlignment(CellStyle.ALIGN_LEFT);//单元格文字居中
            Font fontCell = wb.createFont();
            fontCell.setFontHeight((short) 200);//设置字体大小
            fontCell.setFontName("宋体");
            styleCell.setFont(fontCell);

            int row = 1;
            //增加表格标题，和行标题、
            Row row0 = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            row0.createCell(0).setCellValue("客户信息资料");
            row0.getCell(0).setCellStyle(styleCaption);
            //合并单元格 第一，二个参数的开始行、结束行，第三、四个参数是开始列和结束列。
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
            Cell cellTitle0 = row1.createCell(0);
            cellTitle0.setCellValue("客户名称");
            cellTitle0.setCellStyle(styletTitle);
            sheet.setColumnWidth(0, 8000);
            sheet.setColumnWidth(1, 4500);
            sheet.setColumnWidth(2, 4500);
            sheet.setColumnWidth(3, 4500);
            sheet.setColumnWidth(4, 4500);
            sheet.setColumnWidth(5, 4500);
            sheet.setColumnWidth(6, 5500);
            sheet.setColumnWidth(7, 20000);
            sheet.setColumnWidth(8, 20000);
            Cell cellTitle1 = row1.createCell(1);
            cellTitle1.setCellValue("地址");
            cellTitle1.setCellStyle(styletTitle);
            Cell cellTitle2 = row1.createCell(2);
            cellTitle2.setCellValue("办公电话");
            cellTitle2.setCellStyle(styletTitle);
            Cell cellTitle3 = row1.createCell(3);
            cellTitle3.setCellValue("所属地区");
            cellTitle3.setCellStyle(styletTitle);
            Cell cellTitle4 = row1.createCell(4);
            cellTitle4.setCellValue("单位类型");
            cellTitle4.setCellStyle(styletTitle);
            Cell cellTitle5 = row1.createCell(5);
            cellTitle5.setCellValue("客户经理");
            cellTitle5.setCellStyle(styletTitle);
            Cell cellTitle6 = row1.createCell(6);
            cellTitle6.setCellValue("销售公司");
            cellTitle6.setCellStyle(styletTitle);
            Cell cellTitle7 = row1.createCell(7);
            cellTitle7.setCellValue("单位联系人");
            cellTitle7.setCellStyle(styletTitle);
            Cell cellTitle8 = row1.createCell(8);
            cellTitle8.setCellValue("使用软件");
            cellTitle8.setCellStyle(styletTitle);
            //获取客户信息
            for (String khdm : khdmmcA) {
                List khxxList = QueryData.getSomeEntity("Khxx", DMMCUtils.getDM(khdm, "["), "khdm");
                if (null != khxxList) {
                    Iterator itKh = khxxList.iterator();
                    //给添加
//            sb.append("{\"id\":\"100\",\"name\":\"销售单管理\",\"pId\":\"0\"},");
                    if (itKh.hasNext()) {
                        Row rowtemp = sheet.createRow(++row);
                        Khxx khxx = (Khxx) itKh.next();
                        rowtemp.createCell(0).setCellValue(khxx.getKhmc());
                        rowtemp.createCell(1).setCellValue(khxx.getGzdz());
                        rowtemp.createCell(2).setCellValue(khxx.getBgdh());
                        List listDwlx = QueryData.getSomeEntity("Dwlx", khxx.getKhlx(), "lxdm");
                        if (null == listDwlx || null == listDwlx.iterator() || !listDwlx.iterator().hasNext()) {
                            rowtemp.createCell(4).setCellValue("获取客户类型失败！");
                        } else {
                            Dwlx dwlx = (Dwlx) listDwlx.iterator().next();
                            if (dwlx == null) {
                                rowtemp.createCell(4).setCellValue("获取客户类型失败！");
                            } else {
                                rowtemp.createCell(4).setCellValue(dwlx.getLxmc());
                            }
                        }
                        ;
                        List listDqxx = QueryData.getSomeEntity("Dqxx", khxx.getSsdq(), "dqdm");
                        if (null == listDqxx || null == listDqxx.iterator() || !listDqxx.iterator().hasNext()) {
                            rowtemp.createCell(3).setCellValue("获取所属地区失败！");
                        } else {
                            Dqxx dqxx = (Dqxx) listDqxx.iterator().next();
                            if (dqxx == null) {
                                rowtemp.createCell(3).setCellValue("获取客户类型失败！");
                            } else {
                                rowtemp.createCell(3).setCellValue(dqxx.getDqmc());
                            }
                            ;
                        }
                        List listkhjl = QueryData.getSomeEntity("Zyxx", khxx.getKhjl(), "zydm");
                        if (null == listkhjl || null == listkhjl.iterator() || !listkhjl.iterator().hasNext()) {
                            rowtemp.createCell(5).setCellValue("获取所属地区失败！");
                        } else {
                            Zyxx zyxx = (Zyxx) listkhjl.iterator().next();
                            if (zyxx == null) {
                                rowtemp.createCell(5).setCellValue("获取客户类型失败！");
                            } else {
                                rowtemp.createCell(5).setCellValue(zyxx.getZyxm());
                            }
                            ;
                        }
                        ;
                        //获取销售公司信息
                        List listXsgs = QueryData.getSomeEntity("Enterprise", khxx.getXsgs(), "gsdm");
                        if (null != listXsgs && listXsgs.iterator().hasNext()) {
                            Enterprise en = (Enterprise) listXsgs.iterator().next();
                            rowtemp.createCell(6).setCellValue(en.getGsmc());
                        } else {
                            rowtemp.createCell(6).setCellValue(khxx.getXsgs());
                        }
                        /*
                        * 获取该用户的联系人信息
                        * */
                        //获取联系人信息
                        List allLxr = QueryData.getSomeEntity("Khxx_lxr", khdm, "khdm");
                        if (null != allLxr) {
                            Iterator itLxr = allLxr.iterator();
                            String lxrxx  ="";
                            //给添加
                           while (itLxr.hasNext()) {
                                JSONObject json_data = new JSONObject();
                                Khxx_lxr khxx_lxr = (Khxx_lxr) itLxr.next();

                               lxrxx += "姓名："+khxx_lxr.getLxrxm()+"; ";
                               lxrxx += "职务："+khxx_lxr.getZw()+"; ";
                               lxrxx += "手机："+khxx_lxr.getCellphone() +"; ";
                               lxrxx += "座机："+khxx_lxr.getTel()+"; ";
                               lxrxx += "QQ："+khxx_lxr.getQq()+"; ";
                               lxrxx += "其他联系方式："+khxx_lxr.getQtlxfs() +"; ";
                               lxrxx += "性别："+khxx_lxr.getSex() +"; ";
                               lxrxx += "年龄："+khxx_lxr.getAge()+"; ";
                               lxrxx += "email："+khxx_lxr.getEmail()+"; ";
                               lxrxx += "备注："+khxx_lxr.getPs()+"。\n";

                           }
                            rowtemp.createCell(7).setCellValue(lxrxx);
                        }
                        //获取单位使用软件信息
                        List listSoft = QueryData.getSomeEntity("Khxx_soft", khdm, "khdm");
                        String softxx = "";
                        if (null != listSoft) {
                            Iterator softIt = listSoft.iterator();
                            while (softIt.hasNext()) {
                                JSONObject json_data = new JSONObject();
                                Khxx_soft khxx_soft = (Khxx_soft) softIt.next();
                                softxx +="供应商："+khxx_soft.getGysMc()+"; ";
                                softxx +="软件版本："+khxx_soft.getVerMc()+"; ";
                                softxx +="模块："+khxx_soft.getModelMc()+"; ";
                                softxx +="用户数："+khxx_soft.getYhs()+"; ";
                                softxx +="softID："+khxx_soft.getSoftID()+"; ";
                                softxx +="购买日期："+khxx_soft.getGmrq()+"; ";
                                softxx +="备注："+khxx_soft.getPs()+"。\n";
                            }
                            rowtemp.createCell(8).setCellValue(softxx);
                        }

                    }
//                循环设置单元格表格属性
                    for (int rowIndex = 2; rowIndex <= row; rowIndex++) {
                        for (int cellIndex = 0; cellIndex <= 8; cellIndex++) {
                            sheet.getRow(rowIndex).getCell(cellIndex).setCellStyle(styleCell);
                        }
                    }
                }
            }

            //输出表尾巴
            Row rowFooter = sheet.createRow(row + 1);
            rowFooter.createCell(1).setCellValue("导出人：" + request.getSession().getAttribute("username"));
            sheet.addMergedRegion(new CellRangeAddress(row + 1, row + 1, 1, 2));
            //获取当前录入日期
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = format.format(calendar.getTime());
            rowFooter.createCell(5).setCellValue("导出时间：" + time);
            sheet.addMergedRegion(new CellRangeAddress(row + 1, row + 1, 5, 6));

            //新建一个文件流
            OutputStream op = response.getOutputStream();
//            fileOut = new FileOutputStream("workbook.xls");
            String fileName = "客户信息（" + time + ")";
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859_1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls" + "\"");
            //设置response,下载附件头设置
            response.setContentType("application/vnd.ms-excel");
            wb.write(op);
            wb.close();
            op.flush();
            op.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

}
