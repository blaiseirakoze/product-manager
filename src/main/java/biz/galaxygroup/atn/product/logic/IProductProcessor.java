package biz.galaxygroup.atn.product.logic;

import biz.galaxygroup.atn.product.entities.AtnProduct;
import biz.galaxygroup.atn.product.models.GetResponseModel;
import biz.galaxygroup.atn.product.models.SuccessResponseModel;

import java.util.List;

/**
 * @author blaise irakoze
 */
public interface IProductProcessor {

    /**
     * Create product interface
     *
     * @param atnProduct
     * @return
     */
    public SuccessResponseModel createProduct(List<AtnProduct> atnProduct);

    /**
     * Edit product interface
     *
     * @param id
     * @param atnProduct
     * @return
     */
    public SuccessResponseModel editProduct(String id, AtnProduct atnProduct);

    /**
     * Enable / Disable product interface
     *
     * @param id
     * @param enable
     * @return
     */
    public SuccessResponseModel enableOrDisableProduct(String id, String enable);

    /**
     * Get productByFilterPrams interface
     *
     * @param pageNumber
     * @param pageSize
     * @param searchBy
     * @param startDate
     * @param endDate
     * @return
     */
    public GetResponseModel getProductByFilterPrams(String pageNumber, String pageSize, String searchBy, String startDate, String endDate);
}
