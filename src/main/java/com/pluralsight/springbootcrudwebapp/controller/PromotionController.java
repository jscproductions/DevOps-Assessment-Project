package com.pluralsight.springbootcrudwebapp.controller;

import com.pluralsight.springbootcrudwebapp.models.Promotion;
import com.pluralsight.springbootcrudwebapp.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String listPromotion(Model model) {
        List<Promotion> promotion = promotionService.getAllPromotion();
        model.addAttribute("promotion", promotion);
        return "promotion_list";
    }
}