package br.com.i4business.store.category.update;

import br.com.i4business.store.category.Category;

public record UpdateCategoryCommand(
        String id,
        String name,
        String description,
        boolean isActive,
        Category parent
) {

    public static UpdateCategoryCommand with(
            final String aId,
            final String aName,
            final String aDescription,
            final Boolean isActive,
            final Category parent
    ) {
        return new UpdateCategoryCommand(aId, aName, aDescription, isActive != null ? isActive : true, parent);
    }
}
