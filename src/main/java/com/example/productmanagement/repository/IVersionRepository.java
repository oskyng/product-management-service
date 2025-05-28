package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVersionRepository extends JpaRepository<Version,Long> {
    Page<Version> findAllByProduct(Pageable pageable, Product product);
}
