package biz.galaxygroup.atn.product.facades;

import biz.galaxygroup.atn.product.models.FilterModel;

import java.text.ParseException;
import java.util.List;

/**
 * @author blaise irakoze
 */
public interface FilterProcessor {

    /**
     * FIlter processor interface
     *
     * @param pageNumber
     * @param pageSize
     * @param searchBy
     * @param startDate
     * @param endDate
     * @param table
     * @return
     */
    public List<Object> filterTransfer(String pageNumber, String pageSize, String searchBy, String startDate, String endDate, String table) throws ParseException;
}
