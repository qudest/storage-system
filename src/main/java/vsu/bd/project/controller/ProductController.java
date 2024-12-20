package vsu.bd.project.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vsu.bd.project.dto.ProductCreationDto;
import vsu.bd.project.dto.ProductDto;
import vsu.bd.project.service.CategoryService;
import vsu.bd.project.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size,
                                  @RequestParam(required = false) String searchValue,
                                  @RequestParam(required = false) String searchColumn) {
        Page<ProductDto> productPage = productService.findAll(page, size, searchValue, searchColumn);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchColumn", searchColumn);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("product", new ProductCreationDto());
        model.addAttribute("categories", categoryService.findAll());
        return "products/creationPage";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductCreationDto product,
                                BindingResult result,
                                Model model) {

        if (productService.existsByArticle(product.getArticle())) {
            result.addError(new FieldError("product", "article", "Article already exists"));
        }

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "products/creationPage";
        }

        productService.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
            ProductDto productDto = productService.findById(id);
            model.addAttribute("original", productDto);
            ProductCreationDto productCreationDto = productService.toCreationDto(productDto);
            model.addAttribute("edited", productCreationDto);
            model.addAttribute("categories", categoryService.findAll());
        } catch (Exception e) {
            return "redirect:/products";
        }

        return "products/editPage";
    }

    @PostMapping("/edit")
    public String editProduct(Model model,
                              @RequestParam Long id,
                              @Valid @ModelAttribute("edited") ProductCreationDto productCreationDto,
                              BindingResult result) {

        try {
            ProductDto productDto = productService.findById(id);
            model.addAttribute("original", productDto);
            model.addAttribute("categories", categoryService.findAll());

            if (!productCreationDto.getArticle().equals(productDto.getArticle())
                    && productService.existsByArticle(productCreationDto.getArticle())) {
                result.addError(new FieldError("edited", "article", "Article already exists"));
            }

            if (result.hasErrors()) {
                return "products/editPage";
            }

        } catch (Exception e) {
            return "redirect:/products";
        }

        productService.update(id, productCreationDto);

        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
