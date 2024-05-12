package br.com.i4business.store.inventory;

import br.com.i4business.store.Identifier;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.utils.IdUtils;

import java.util.Objects;

public class InventoryID extends Identifier {

    private final String value;

    private InventoryID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static InventoryID unique() {
        return InventoryID.from(IdUtils.uuid());
    }

    public static InventoryID from(final String anId) {
        return new InventoryID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InventoryID that = (InventoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
