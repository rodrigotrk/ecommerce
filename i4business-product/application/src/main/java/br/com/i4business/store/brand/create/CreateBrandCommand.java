package br.com.i4business.store.brand.create;

public record CreateBrandCommand(
        String name,
        String description,
        boolean isActive
) {

    public static CreateBrandCommand with(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new CreateBrandCommand(aName, aDescription, isActive);
    }
}
