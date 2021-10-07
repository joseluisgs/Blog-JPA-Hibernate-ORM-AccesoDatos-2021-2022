package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.dto.CategoryDTO;

public class CategoryMapper extends BaseMapper<Category, CategoryDTO> {
    @Override
    public Category fromDTO(CategoryDTO item) {
        Category category = new Category();
        if (item.getId() != null) {
            category.setId(item.getId());
        }
        category.setTexto(item.getTexto());
        return category;
    }

    @Override
    public CategoryDTO toDTO(Category item) {
        return CategoryDTO.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .build();
    }
}
