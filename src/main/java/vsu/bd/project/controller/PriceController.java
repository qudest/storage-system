package vsu.bd.project.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vsu.bd.project.dto.PriceDto;
import vsu.bd.project.service.PriceService;

@Controller
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping({"", "/"})
    public String showAllPrices(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size,
                                @RequestParam(required = false) String searchValue,
                                @RequestParam(required = false) String searchColumn) {
        Page<PriceDto> pricePage = priceService.findAll(page, size, searchValue, searchColumn);
        model.addAttribute("prices", pricePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pricePage.getTotalPages());
        model.addAttribute("totalItems", pricePage.getTotalElements());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchColumn", searchColumn);
        return "prices/index";
    }

}
