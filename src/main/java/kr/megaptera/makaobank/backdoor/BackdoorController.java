package kr.megaptera.makaobank.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("reset-products")
    public String resetProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("setup-seventySix-products")
    public String setUpSeventySixProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        jdbcTemplate.execute("INSERT INTO product" +
                "(id, name, maker, price, description, imageUrl, created_at, updated_at) " +
                "VALUES(1,'205 돌침대 S(홍맥반석)','현대의료기','418000','205 돌침대 S(홍맥반석)','https://img.danawa.com/prod_img/500000/002/950/img/13950002_1.jpg??shrink=360:360&_v=20221130213722')");

        return "OK";
    }
}
