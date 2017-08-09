/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.excel.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;


/**
 * excel工具类
 * 1. 导出excel.
 * @author zh
 * @version $Id: ExcelUtils.java, v 0.1 2017年3月8日 下午6:03:06 zh Exp $
 */
public class ExcelUtils {
    
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    
    private static final String DATE_FORMAT = "yyyy年MM月dd日";
    
    
    public static <T> void exportExcel(Class<T> clz, Collection<T> dataset, OutputStream out,
                                       String datePattren) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        write2Sheet(clz, dataset, workbook, datePattren);
        try {
            workbook.write(out);
        } catch (IOException e) {
            logger.error(e.toString(), e);
        } finally {
           IOUtils.closeQuietly(workbook);
        }
    }

    /**
     * clz对象.
     * @param clz
     * @param dataset
     * @param datePattren
     */
    private static <T> void write2Sheet(Class<T> clz, Collection<T> dataset, Workbook workbook, String datePattren) {
            try {
                ExcelSheet<T> excelSheet = createExcelSheet(clz, dataset, datePattren);
                wirteData2Sheet(clz, excelSheet, workbook);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.error("对象属性读取失败", e);
            }
    }

    /**
     * 将data数据写入excel
     * @param excelSheet
     * @param sheet
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private static <T> void wirteData2Sheet(Class<T> clz, ExcelSheet<T> excelSheet, Workbook workbook) throws IllegalArgumentException, IllegalAccessException {
        // 设置表名
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet(excelSheet.getSheetName());
        
        // 设置表头
        List<ExcelField> fieldList = excelSheet.getExcelFieldList();
        
        int rowIndex = 0;
        XSSFRow headerRow = sheet.createRow(rowIndex);
        rowIndex ++;
        for (int i = 0; i < fieldList.size(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(fieldList.get(i).getName());
            cell.setCellValue(text);
        }
        
        // 填充字段
        Collection<T> dataset = excelSheet.getDataset();
        if(dataset == null || dataset.isEmpty()) {
            return;
        }
        Iterator<T> iterator = dataset.iterator();
        
        // 单元格统一样式
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setAlignment(HorizontalAlignment.CENTER);
        
        while (iterator.hasNext()) {
            T t = iterator.next();
            XSSFRow row = sheet.createRow(rowIndex);
            int columnIndex = 0;
            for (ExcelField filed : fieldList) {
                XSSFCell cell = row.createCell(columnIndex);
                Field field = filed.getField();
                field.setAccessible(true);
                cell.setCellValue(filed.getConverter().convert(field.get(t)));
                cell.setCellStyle(cs);
                columnIndex ++;
            }
            rowIndex++;
        }
        // 设定自动宽度
        for (int i = 0; i < fieldList.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 根据类信息和配置信息
     * @param clz
     * @param dataset
     * @param sheet
     * @param datePattren
     * @return
     */
    private static <T> ExcelSheet<T> createExcelSheet(Class<T> clz, Collection<T> dataset, String datePattren) {
        ExcelSheet<T> excelSheet = getExportInfoInAnnotations(clz, datePattren);
        excelSheet.setDataset(dataset);
        excelSheet.setDateFormat(StringUtils.isNotBlank(datePattren) ? datePattren : DATE_FORMAT);
        return excelSheet;
    }

    /**
     * 获取导出信息在类中
     * @param clz
     * @return
     */
    private static <T> ExcelSheet<T> getExportInfoInAnnotations(Class<T> clz, String pattern) {
        ExcelSheet<T> sheet = new ExcelSheet<>();
        // 获取表名
        ExcelExportField sheetNameAnnotation = AnnotationUtils.findAnnotation(clz, ExcelExportField.class);
        if(sheetNameAnnotation == null) {
            throw new RuntimeException("导出配置有误,表名不能为空.");
        }
        sheet.setSheetName(sheetNameAnnotation.value());
        // 获取列名
        List<ExcelField> fields = getExcelFiledList(clz, pattern);
        if(fields.isEmpty()) {
            throw new RuntimeException("不能获取导出的表头信息");
        }
        sheet.setExcelFieldList(fields);
        return sheet;
    }

    /**
     * 解析字段和导出列的对应
     * @param clz
     * @return
     */
    private static <T> List<ExcelField> getExcelFiledList(Class<T> clz, String pattern) {
        List<Field> fieldsList = FieldUtils.getFieldsListWithAnnotation(clz, ExcelExportField.class);
        List<ExcelField> fieldList = new ArrayList<>(fieldsList.size());
        
        StringConverter defConverter = StringConverterFactory.getDefaultStringConverter();
        StringConverter dateConverter = StringConverterFactory.getDateStringConverter(pattern);
        // 获取所有导出注解的字段
        for (Field field : fieldsList) {
            ExcelField ef = new ExcelField();
            ExcelExportField fieldAnnotation = field.getAnnotation(ExcelExportField.class);
            ef.setName(fieldAnnotation.value());
            ef.setProperty(field.getName());
            Class<?> cls = field.getType();
            if(cls.equals(Date.class)) {
                ef.setConverter(dateConverter);
            }else {
                ef.setConverter(defConverter);
            }
            ef.setField(field);
            fieldList.add(ef);
        }
        return fieldList;
    }
}
