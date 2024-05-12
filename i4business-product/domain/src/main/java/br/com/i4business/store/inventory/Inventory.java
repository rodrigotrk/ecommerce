package br.com.i4business.store.inventory;

import br.com.i4business.store.Entity;
import br.com.i4business.store.utils.InstantUtils;
import br.com.i4business.store.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Inventory extends Entity<InventoryID> {

    private Long quantity;
    private Long quantityAlert;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    private Inventory(
            final InventoryID anId,
            final Long aQuantity,
            final Long aQuantityAlert,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate
    ){
        super(anId);
        this.quantity = aQuantity;
        this.quantityAlert = aQuantityAlert;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
    }

    private Inventory(InventoryID id){
        super(id);
    }

    public static Inventory newInventory(final Long aQuantity, final Long aQuantityAlert, final boolean isActive) {
        final var id = InventoryID.unique();
        final var now = InstantUtils.now();
        return new Inventory(id, aQuantity, aQuantityAlert, isActive, now, now);
    }

    public static Inventory with(
            final InventoryID anId,
            final Long aQuantity,
            final Long aQuantityAlert,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Inventory(
                anId,
                aQuantity,
                aQuantityAlert,
                active,
                createdAt,
                updatedAt
        );
    }

    public static Inventory with(final Inventory inventory) {
        return with(
                inventory.getId(),
                inventory.quantity,
                inventory.quantityAlert,
                inventory.isActive(),
                inventory.createdAt,
                inventory.updatedAt
        );
    }

    public static Inventory with(final InventoryID anId){
        return new Inventory(anId);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new InventoryValidator(this, handler).validate();
    }

    public Inventory activate() {
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Inventory deactivate() {
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Inventory update(
            final Long aQuantity,
            final Long aQuantityAlert,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.quantity = aQuantity;
        this.quantityAlert = aQuantityAlert;
        this.updatedAt = Instant.now();
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getQuantityAlert() {
        return quantityAlert;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
