package br.com.i4business.store.inventory.create;

import br.com.i4business.store.exception.NotificationException;
import br.com.i4business.store.inventory.Inventory;
import br.com.i4business.store.inventory.InventoryGateway;
import br.com.i4business.store.validation.handler.Notification;

public class DefaultCreateInventoryUseCase extends CreateInventoryUseCase {

    private final InventoryGateway inventoryGateway;

    public DefaultCreateInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    @Override
    public CreateInventoryOutput execute(CreateInventoryCommand createInventoryCommand) {
        final var notification = Notification.create();
        final var inventory = notification.validate(() -> Inventory.newInventory(
                createInventoryCommand.quantity(),
                createInventoryCommand.quantityAlert(),
                createInventoryCommand.isActive()
        ));

        if(notification.hasError()){
            notify(notification);
        }
        return CreateInventoryOutput.from(this.inventoryGateway.create(inventory));
    }

    private void notify(Notification notification){
        throw new NotificationException("Could not create Aggregate Inventory", notification);
    }


}
