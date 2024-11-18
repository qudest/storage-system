package vsu.bd.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.bd.project.service.OperationService;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping({"", "/"})
    public String showAllOperations(Model model) {
        model.addAttribute("operations", operationService.findAll());
        return "operations/index";
    }
}
