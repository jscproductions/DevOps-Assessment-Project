package com.pluralsight.springbootcrudwebapp.services;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.models.Promotion;
import com.pluralsight.springbootcrudwebapp.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }
}
