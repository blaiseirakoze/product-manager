package biz.galaxygroup.atn.product.facades;

import biz.galaxygroup.atn.product.entities.AtnProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author blaise irakoze
 */
public interface AtnProductRepository extends JpaRepository<AtnProduct, String> {
    @Query(value = "SELECT COUNT(*) FROM atn_product", nativeQuery = true)
    int countAtnProduct();
}
