package br.com.i4business.store.category;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean isActive,
        Category parent
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Category parent
    ) {
        return new CreateCategoryCommand(aName, aDescription, isActive, parent);
    }
}
