package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByNameAndDeletedAtIsNotNull(String name);
    Page<Product> findAllByNameAndDeletedAtIsNotNull(Pageable pageable, String name);
    Page<Product> findAllByDeletedAtIsNotNull(Pageable pageable);
}
