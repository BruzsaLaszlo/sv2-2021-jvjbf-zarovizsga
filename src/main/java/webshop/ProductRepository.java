package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {

    JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> getPreparedStatement(productName, price, stock, con), keyHolder);
        return keyHolder.getKey().longValue();
    }

    private PreparedStatement getPreparedStatement(String productName, int price, int stock, Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("""   
                INSERT INTO products (product_name,price,stock)
                VALUES (?,?,?)""", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, productName);
        stmt.setInt(2, price);
        stmt.setLong(3, stock);
        return stmt;
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM products WHERE id = ?",
                this::getProduct,
                id);
    }

    private Product getProduct(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4));
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("""
                UPDATE products
                SET stock = stock - ?
                WHERE id = ?""", amount, id);
    }
}
