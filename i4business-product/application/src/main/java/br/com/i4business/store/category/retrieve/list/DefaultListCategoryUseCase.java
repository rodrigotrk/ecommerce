package br.com.i4business.store.category.retrieve.list;

import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCategoryUseCase extends ListCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultListCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(SearchQuery searchQuery) {
        return this.categoryGateway.findAll(searchQuery)
                .map(CategoryListOutput::from);
    }
}
