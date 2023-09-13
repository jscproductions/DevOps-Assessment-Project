package com.pluralsight.springbootcrudwebapp.controller;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.models.Promotion;
import com.pluralsight.springbootcrudwebapp.repositories.EmployeeRepository;
import com.pluralsight.springbootcrudwebapp.repositories.PromotionRepository;
import com.pluralsight.springbootcrudwebapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PromotionRepository promotionRepository;


    /*@GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }*/

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model){
        Employee employee=employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, Model model){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    /*@GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }*/
    /*@PostMapping("/copydata")
    public String copyData(@RequestParam("id") Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            Promotion promotion = new Promotion();
            promotion.setFirstName(employee.getFirstName());
            promotion.setLastName(employee.getLastName());
            promotion.setEmail(employee.getEmail());

            promotionRepository.save(promotion);
        }

        return "redirect:/";
    }*/
    @GetMapping("/apply/{id}")
    public String showApplyForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job application ID"));
        model.addAttribute("employee", employee);
        return "apply_form";
    }

    @PostMapping("/apply/{id}")
    public String submitApplyForm(@PathVariable Long id, @RequestParam String position) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job application ID"));

        Promotion promotion = new Promotion();
        promotion.setFirstName(employee.getFirstName());
        promotion.setLastName(employee.getLastName());
        promotion.setEmail(employee.getEmail());
        promotion.setPosition(position);

        promotionRepository.save(promotion);
        return "redirect:/";
    }
}
