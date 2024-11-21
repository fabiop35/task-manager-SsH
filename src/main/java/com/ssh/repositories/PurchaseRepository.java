package com.ssh.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.ssh.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    /*public PurchaseRepository(DataSource dataSource) {
        jdbc = new JdbcTemplate(dataSource);
    }*/
    @Modifying
    @Query("INSERT INTO purchase(product, price) VALUES (:product , :price)")
    public void storePurchase(String product, BigDecimal price);

    @Query("SELECT * FROM purchase")
    public List<Purchase> findAllPurchases();
    /*{

        String sql = "SELECT * FROM purchase";
        RowMapper<Purchase> purchaseRowMapper = (r, i) -> {
            Purchase rowObject = new Purchase();
            rowObject.setId(r.getInt("id"));
            rowObject.setProduct(r.getString("product"));
            rowObject.setPrice(r.getBigDecimal("price"));
            return rowObject;
        };

        return jdbc.query(sql, purchaseRowMapper);
    }*/

}
