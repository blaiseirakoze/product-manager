package biz.galaxygroup.atn.product.services;

import biz.galaxygroup.atn.product.entities.AtnProduct;
import biz.galaxygroup.atn.product.logic.IProductProcessor;
import biz.galaxygroup.atn.product.models.GetResponseModel;
import biz.galaxygroup.atn.product.models.SuccessResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/product")
public class ProductService {

    @Autowired
    private IProductProcessor productProcessor;

    /**
     * Create product service
     *
     * @param atnProducts
     * @return
     */
    @RolesAllowed({"admin"})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createMnoAccount(@RequestBody List<AtnProduct> atnProducts) {
        return new ResponseEntity<SuccessResponseModel>(productProcessor.createProduct(atnProducts), HttpStatus.CREATED);
    }

    /**
     * Edit product service
     *
     * @param id
     * @param atnProduct
     * @return
     */
    @RolesAllowed({"admin"})
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> editProduct(@PathVariable("id") String id, @RequestBody AtnProduct atnProduct) {
        return new ResponseEntity<SuccessResponseModel>(productProcessor.editProduct(id, atnProduct), HttpStatus.CREATED);
    }

    /**
     * Enable product service
     *
     * @param id
     * @return
     */
    @RolesAllowed({"admin"})
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> enableProduct(@PathVariable("id") String id) {
        return new ResponseEntity<SuccessResponseModel>(productProcessor.enableOrDisableProduct(id, "enable"), HttpStatus.CREATED);
    }

    /**
     * Disable product service
     *
     * @param id
     * @return
     */
    @RolesAllowed({"admin"})
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> disableProduct(@PathVariable("id") String id) {
        return new ResponseEntity<SuccessResponseModel>(productProcessor.enableOrDisableProduct(id, "disable"), HttpStatus.CREATED);
    }

    /**
     * Get productByFilterPrams service
     *
     * @param pageNumber
     * @param pageSize
     * @param searchBy
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    @RolesAllowed({"admin"})
    @RequestMapping(value = "/filter/", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByFilterPrams(@RequestParam String pageNumber, @RequestParam String pageSize, @RequestParam String searchBy, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        return new ResponseEntity<GetResponseModel>((GetResponseModel) productProcessor.getProductByFilterPrams(pageNumber, pageSize, searchBy, startDate, endDate), HttpStatus.OK);
    }
}
