package br.com.i4business.store.api.v1.controllers;

import br.com.i4business.store.api.v1.CategoryAPI;
import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.category.CreateCategoryCommand;
import br.com.i4business.store.category.CreateCategoryUseCase;
import br.com.i4business.store.category.models.CreateCategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() :  true,
                input.parent() != null ? Category.with(CategoryID.from(input.parent())) : null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createCategoryUseCase.execute(aCommand));
    }
}
