package com.pluralsight.springbootcrudwebapp.repositories;

import com.pluralsight.springbootcrudwebapp.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
}
