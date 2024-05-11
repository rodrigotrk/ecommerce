package br.com.i4business.store.brand;

import br.com.i4business.store.Identifier;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.utils.IdUtils;

import java.util.Objects;

public class BrandID extends Identifier {

    private final String value;

    private BrandID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static BrandID unique() {
        return BrandID.from(IdUtils.uuid());
    }

    public static BrandID from(final String anId) {
        return new BrandID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
