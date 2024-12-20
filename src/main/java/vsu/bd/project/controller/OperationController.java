package vsu.bd.project.controller;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vsu.bd.project.dto.OperationDto;
import vsu.bd.project.service.OperationService;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping({"", "/"})
    public String showAllOperations(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "20") int size,
                                    @RequestParam(required = false) String searchValue,
                                    @RequestParam(required = false) String searchColumn) {
        Page<OperationDto> operationPage = operationService.findAll(page, size, searchValue, searchColumn);
        model.addAttribute("operations", operationPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", operationPage.getTotalPages());
        model.addAttribute("totalItems", operationPage.getTotalElements());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchColumn", searchColumn);
        return "operations/index";
    }

}
