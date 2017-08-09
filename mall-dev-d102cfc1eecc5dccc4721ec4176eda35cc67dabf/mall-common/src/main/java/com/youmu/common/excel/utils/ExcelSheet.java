package com.youmu.common.excel.utils;

import java.util.Collection;
import java.util.List;

/**
 * excel对象.
 * 
 * @author zh
 * @version $Id: ExcelSheet.java, v 0.1 2017年3月8日 下午5:58:08 zh Exp $
 */
public class ExcelSheet<T> {
    private String sheetName;
    
    private List<ExcelField> excelFieldList;
    
    private Collection<T> dataset;
    
    private String dateFormat;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Collection<T> getDataset() {
        return dataset;
    }

    public void setDataset(Collection<T> dataset) {
        this.dataset = dataset;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public List<ExcelField> getExcelFieldList() {
        return excelFieldList;
    }

    public void setExcelFieldList(List<ExcelField> excelFieldList) {
        this.excelFieldList = excelFieldList;
    }
}
