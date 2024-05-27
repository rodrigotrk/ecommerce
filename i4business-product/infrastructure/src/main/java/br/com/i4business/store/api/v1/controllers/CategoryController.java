package br.com.i4business.store.api.v1.controllers;

import br.com.i4business.store.api.v1.CategoryAPI;
import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.category.create.CreateCategoryCommand;
import br.com.i4business.store.category.create.CreateCategoryUseCase;
import br.com.i4business.store.category.delete.DeleteCategoryUseCase;
import br.com.i4business.store.category.models.CategoryListResponse;
import br.com.i4business.store.category.models.CategoryResponse;
import br.com.i4business.store.category.models.CreateCategoryRequest;
import br.com.i4business.store.category.models.UpdateCategoryRequest;
import br.com.i4business.store.category.presenters.CategoryApiPresenter;
import br.com.i4business.store.category.retrieve.get.GetCategotyByIdUseCase;
import br.com.i4business.store.category.retrieve.list.ListCategoryUseCase;
import br.com.i4business.store.category.update.UpdateCategoryCommand;
import br.com.i4business.store.category.update.UpdateCategoryUseCase;
import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final GetCategotyByIdUseCase getCategotyByIdUseCase;
    private final ListCategoryUseCase listCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase,
                              final UpdateCategoryUseCase updateCategoryUseCase,
                              final DeleteCategoryUseCase deleteCategoryUseCase,
                              final GetCategotyByIdUseCase getCategotyByIdUseCase,
                              final ListCategoryUseCase listCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.getCategotyByIdUseCase = Objects.requireNonNull(getCategotyByIdUseCase);
        this.listCategoryUseCase = Objects.requireNonNull(listCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() :  true,
                input.parent().isBlank() ? null : Category.with(CategoryID.from(input.parent()))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createCategoryUseCase.execute(aCommand));
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(id, input.name(), input.description(), input.active(),
                input.parent().isEmpty() ? null : Category.with(CategoryID.from(input.parent())));

        final var output = this.updateCategoryUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(String id) {
        this.deleteCategoryUseCase.execute(id);
    }

    @Override
    public CategoryResponse getById(String id) {
        return CategoryApiPresenter.present(this.getCategotyByIdUseCase.execute(id));
    }

    @Override
    public Pagination<CategoryListResponse> listCategories(String search, int page, int perPage, String sort, String direction) {
        return this.listCategoryUseCase.execute(new SearchQuery(page, perPage, search,sort,direction))
                .map(CategoryApiPresenter::present);
    }


}
