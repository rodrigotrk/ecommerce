package br.com.i4business.store.inventory.create;

import br.com.i4business.store.inventory.Inventory;

public record CreateInventoryOutput(
        String id
) {

    public static CreateInventoryOutput from(final String anId) {
        return new CreateInventoryOutput(anId);
    }

    public static CreateInventoryOutput from(final Inventory aInventory) {
        return new CreateInventoryOutput(aInventory.getId().getValue());
    }
}
