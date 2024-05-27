package br.com.i4business.store.category.retrieve.get;

import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.exception.NotFoundException;

import java.util.Objects;

public class DefaultGetCategoryByIdUseCase extends GetCategotyByIdUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anIn) {
        final var aCategoryId = CategoryID.from(anIn);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(() -> NotFoundException.with(Category.class, aCategoryId));
    }
}
