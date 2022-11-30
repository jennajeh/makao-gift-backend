package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
