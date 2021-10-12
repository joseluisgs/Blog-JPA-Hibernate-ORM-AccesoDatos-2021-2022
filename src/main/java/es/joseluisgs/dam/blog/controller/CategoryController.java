package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.service.CategoryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryController {
    private static CategoryController controller = null;

    // Mi Servicio unido al repositorio
    private final CategoryService categoryService;

    // Implementamos nuestro Singleton para el controlador
    private CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public static CategoryController getInstance() {
        if (controller == null) {
            controller = new CategoryController(new CategoryService(new CategoryRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    // Usamos DTO para implementar este patrón en represantación y trasporte de la información
//    public List<CategoryDTO> getAllCategories() {
//        return categoryService.getAllCategories();
//    }

    public List<CategoryDTO> getAllCategories() {
        try {
            return categoryService.getAllCategories();
        } catch (SQLException e) {
            System.err.println("Error CategoryController en getAllCategories: " + e.getMessage());
            return null;
        }
    }

    public CategoryDTO getCategoryById(Long id) {
        try{
            return categoryService.getCategoryById(id);
        } catch (SQLException e) {
            System.err.println("Error CategoryController en getCategoryById: " + e.getMessage());
            return null;
        }
    }

    public CategoryDTO postCategory(CategoryDTO categoryDTO) {
        try {
            return categoryService.postCategory(categoryDTO);
        } catch (SQLException e) {
            System.err.println("Error CategoryController en postCategory: " + e.getMessage());
            return null;
        }
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        try {
            return categoryService.updateCategory(categoryDTO);
        } catch (SQLException e) {
            System.err.println("Error CategoryController en updateCategory: " + e.getMessage());
            return null;
        }
    }

    public CategoryDTO deleteCategory(CategoryDTO categoryDTO) {
        try {
           return categoryService.deleteCategory(categoryDTO);
        } catch (SQLException e) {
            System.err.println("Error CategoryController en deleteCategory: " + e.getMessage());
            return null;
        }
    }

    // Por gusto lo hago con un optional para que lo veas como tratar la excepción con if
    public Optional<CategoryDTO> getCategoryByIdOptional(Long id) {
        try {
            return Optional.of(categoryService.getCategoryById(id));
        } catch (SQLException e) {
            System.err.println("Error CategoryController en getCategoryById: " + e.getMessage());
            return Optional.empty();
        }
    }

}
