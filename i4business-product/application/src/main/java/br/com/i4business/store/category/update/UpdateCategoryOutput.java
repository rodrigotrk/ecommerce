package br.com.i4business.store.category.update;

import br.com.i4business.store.category.Category;

public record UpdateCategoryOutput(String id) {

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }
}
