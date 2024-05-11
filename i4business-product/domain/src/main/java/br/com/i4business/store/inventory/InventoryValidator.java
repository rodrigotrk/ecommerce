package br.com.i4business.store.inventory;

import br.com.i4business.store.validation.Error;
import br.com.i4business.store.validation.ValidationHandler;
import br.com.i4business.store.validation.Validator;

public class InventoryValidator extends Validator {

    public static final int MIN_LENGTH = 0;
    private final Inventory inventory;

    public InventoryValidator(final Inventory inventory, final ValidationHandler aHandler) {
        super(aHandler);
        this.inventory = inventory;
    }

    @Override
    public void validate() {
        checkQuantityConstraints();
        checkQuantityAlertConstraints();
    }

    private void checkQuantityConstraints() {
        final var quantity = this.inventory.getQuantity();
        if (quantity == null) {
            this.validationHandler().append(new Error("'quantity' should not be null"));
            return;
        }

        if (quantity < MIN_LENGTH) {
            this.validationHandler().append(new Error("'quantity' should be a positive number"));
        }
    }

    private void checkQuantityAlertConstraints() {
        final var quantityAlert = this.inventory.getQuantityAlert();
        if (quantityAlert == null) {
            this.validationHandler().append(new Error("'quantity alert' should not be null"));
            return;
        }

        if (quantityAlert < MIN_LENGTH) {
            this.validationHandler().append(new Error("'quantity alert' should be a positive number"));
        }
    }
}
