package br.com.i4business.store.inventory.create;

public record CreateInventoryCommand(
        Long quantity,
        Long quantityAlert,
        boolean isActive
) {

    public static CreateInventoryCommand with(
            final Long aQuantity,
            final Long aQuantityAlert,
            final boolean isActive
    ) {
        return new CreateInventoryCommand(aQuantity, aQuantityAlert, isActive);
    }
}
