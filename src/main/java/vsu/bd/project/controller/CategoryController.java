package vsu.bd.project.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vsu.bd.project.dto.CategoryCreationDto;
import vsu.bd.project.dto.CategoryDto;
import vsu.bd.project.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public String showAllCategories(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "20") int size) {
        Page<CategoryDto> categoryPage = categoryService.findAll(page, size);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("totalItems", categoryPage.getTotalElements());
        return "categories/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("category", new CategoryCreationDto());
        return "categories/creationPage";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryCreationDto category,
                                 BindingResult result) {

        if (result.hasErrors()) {
            return "categories/creationPage";
        }

        categoryService.save(category);

        return "redirect:/categories";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
            CategoryDto categoryDto = categoryService.findById(id);
            model.addAttribute("original", categoryDto);
            CategoryCreationDto categoryCreationDto = categoryService.toCreationDto(categoryDto);
            model.addAttribute("edited", categoryCreationDto);
            return "categories/editPage";
        } catch (Exception e) {
            return "redirect:/categories";
        }
    }

    @PostMapping("/edit")
    public String editCategory(Model model,
                               @RequestParam Long id,
                               @Valid @ModelAttribute("edited") CategoryCreationDto categoryCreationDto,
                               BindingResult result) {
        try {
            CategoryDto categoryDto = categoryService.findById(id);
            model.addAttribute("original", categoryDto);

            if (result.hasErrors()) {
                return "categories/editPage";
            }
        } catch (Exception e) {
            return "redirect:/categories";
        }

        categoryService.update(id, categoryCreationDto);

        return "redirect:/categories";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

}
