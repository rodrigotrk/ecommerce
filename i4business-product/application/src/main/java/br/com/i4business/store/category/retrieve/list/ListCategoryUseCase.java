package br.com.i4business.store.category.retrieve.list;

import br.com.i4business.store.UseCase;
import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;

public abstract class ListCategoryUseCase extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
