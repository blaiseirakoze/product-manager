package biz.galaxygroup.atn.product.facades;

import biz.galaxygroup.atn.product.models.FilterModel;

import java.util.List;

public interface FilterProcessor {

    /**
     * Filter processor interface
     *
     * @param filterModel
     * @param table
     * @return
     */
    public List<Object> filterTransfer(FilterModel filterModel, String table);
}
