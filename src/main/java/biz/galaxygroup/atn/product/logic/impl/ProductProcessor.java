package biz.galaxygroup.atn.product.logic.impl;

import biz.galaxygroup.atn.product.entities.AtnProduct;
import biz.galaxygroup.atn.product.exceptions.HandlerInternalServerErrorException;
import biz.galaxygroup.atn.product.exceptions.HandlerNotFoundException;
import biz.galaxygroup.atn.product.facades.AtnProductRepository;
import biz.galaxygroup.atn.product.facades.FilterProcessor;
import biz.galaxygroup.atn.product.logic.IProductProcessor;
import biz.galaxygroup.atn.product.models.FilterModel;
import biz.galaxygroup.atn.product.models.GetResponseModel;
import biz.galaxygroup.atn.product.models.SuccessResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author blaise irakoze
 */
@Service
public class ProductProcessor implements IProductProcessor {

    @Autowired
    private AtnProductRepository atnProductRepository;

    @Autowired
    private FilterProcessor filterProcessor;

    /**
     * Create product processor
     *
     * @param atnProduct
     * @return
     */
    @Override
    public SuccessResponseModel createProduct(List<AtnProduct> atnProduct) {
        try {
            for (AtnProduct atnPro : atnProduct) {
                atnProductRepository.save(atnPro);
            }
            return new SuccessResponseModel(HttpStatus.CREATED.toString(), "Atn product successfully created");
        } catch (Exception e) {
            throw new HandlerInternalServerErrorException("Error occurs");
        }
    }

    /**
     * Edit product processor
     *
     * @param id
     * @param atnProduct
     * @return
     */
    @Override
    public SuccessResponseModel editProduct(String id, AtnProduct atnProduct) {
        AtnProduct foundProduct = atnProductRepository.findById(id).orElse(new AtnProduct());
        if (foundProduct.getId() == null) {
            throw new HandlerNotFoundException("product not found");
        }
        try {
            atnProductRepository.save(new AtnProduct(foundProduct.getId(), atnProduct.getName(), atnProduct.getStatus(), foundProduct.getCreationTime(), foundProduct.getSearchBy()));
            return new SuccessResponseModel(HttpStatus.CREATED.toString(), "product successfully updated");
        } catch (Exception e) {
            throw new HandlerInternalServerErrorException("Error occurs");
        }
    }

    /**
     * Enable / Disable product processor
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public SuccessResponseModel enableOrDisableProduct(String id, String status) {
        AtnProduct foundMnoProduct = atnProductRepository.findById(id).orElse(new AtnProduct());
        if (foundMnoProduct.getId() == null) {
            throw new HandlerNotFoundException("product not found");
        }
        try {
            atnProductRepository.save(new AtnProduct(foundMnoProduct.getId(), foundMnoProduct.getName(), status, foundMnoProduct.getCreationTime(), foundMnoProduct.getSearchBy()));
            return new SuccessResponseModel(HttpStatus.CREATED.toString(), "product successfully status changed");
        } catch (Exception e) {
            throw new HandlerInternalServerErrorException("Error occurs");
        }
    }

    /**
     * Get productByFilterPrams processor
     *
     * @param pageNumber
     * @param pageSize
     * @param searchBy
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public GetResponseModel getProductByFilterPrams(String pageNumber, String pageSize, String searchBy, String startDate, String endDate) {
        try {
            FilterModel filterModel = new FilterModel();
            if (startDate.isEmpty() && endDate.isEmpty()) {
                filterModel = new FilterModel(pageNumber, pageSize, searchBy);
            } else if (!startDate.isEmpty() && endDate.isEmpty()) {
                Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                filterModel = new FilterModel(pageNumber, pageSize, searchBy, sDate);
            } else if (startDate.isEmpty() && !endDate.isEmpty()) {
                Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                filterModel = new FilterModel(pageNumber, pageSize, searchBy, eDate);
            } else {
                Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                filterModel = new FilterModel(pageNumber, pageSize, searchBy, eDate, sDate);
            }
            List<Object> list = filterProcessor.filterTransfer(filterModel, "AtnProduct");
            GetResponseModel getResponseModel = new GetResponseModel(list.size(), Integer.valueOf(pageNumber), list);
            return getResponseModel;
        } catch (Exception e) {
            throw new HandlerInternalServerErrorException("Error occurs");
        }
    }
}
