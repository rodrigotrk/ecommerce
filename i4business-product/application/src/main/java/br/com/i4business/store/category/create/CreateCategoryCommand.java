package br.com.i4business.store.category.create;

import br.com.i4business.store.category.Category;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean isActive,
        Category parent
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final Boolean isActive,
            final Category parent
    ) {
        return new CreateCategoryCommand(aName, aDescription, isActive != null ? isActive : true, parent);
    }
}
