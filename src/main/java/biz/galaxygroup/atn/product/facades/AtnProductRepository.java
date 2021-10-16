package biz.galaxygroup.atn.product.facades;

import biz.galaxygroup.atn.product.entities.AtnProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author blaise irakoze
 */
public interface AtnProductRepository extends JpaRepository<AtnProduct, String> {

}
