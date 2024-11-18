package vsu.bd.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.bd.project.service.PriceService;

@Controller
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping({"", "/"})
    public String showAllPrices(Model model) {
        model.addAttribute("prices", priceService.findAll());
        return "prices/index";
    }

}
