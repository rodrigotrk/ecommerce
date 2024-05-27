package br.com.i4business.store.category.presenters;

import br.com.i4business.store.category.models.CategoryListResponse;
import br.com.i4business.store.category.models.CategoryResponse;
import br.com.i4business.store.category.retrieve.get.CategoryOutput;
import br.com.i4business.store.category.retrieve.list.CategoryListOutput;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id(),
                output.name(),
                output.description(),
                output.active(),
                output.parent() == null ? null : output.parent().getId().getValue(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id(),
                output.name(),
                output.description(),
                output.active(),
                output.parent() == null ? null : output.parent().getId().getValue(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

}
