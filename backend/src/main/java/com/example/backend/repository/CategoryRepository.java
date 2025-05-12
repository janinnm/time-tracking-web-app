package com.example.backend.repository;

import com.example.backend.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>  {

    Category findByName(String name);

    boolean existsByName(String name);
}
