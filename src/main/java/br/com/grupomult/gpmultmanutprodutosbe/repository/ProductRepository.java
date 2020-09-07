package br.com.grupomult.gpmultmanutprodutosbe.repository;

import br.com.grupomult.gpmultmanutprodutosbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product p WHERE UPPER(p.category) LIKE UPPER(?1) || '%' ORDER BY id", nativeQuery = true)
    List<Product> findByCategory(String category);

    @Query(value = "SELECT * FROM product p WHERE UPPER(p.name) LIKE UPPER(?1) || '%' ORDER BY id", nativeQuery = true)
    List<Product> findByName(String name);

}
