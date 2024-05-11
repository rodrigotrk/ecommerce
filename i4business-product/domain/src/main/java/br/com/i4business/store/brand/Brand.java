package br.com.i4business.store.brand;

import br.com.i4business.store.Entity;
import br.com.i4business.store.utils.InstantUtils;
import br.com.i4business.store.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Brand extends Entity<BrandID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Brand(
            final BrandID anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ){
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    private Brand(BrandID id){
        super(id);
    }

    public static Brand newBrand(final String aName, final String aDescription, final boolean isActive) {
        final var id = BrandID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Brand(id, aName, aDescription, isActive, now, now, deletedAt);
    }

    public static Brand with(
            final BrandID anId,
            final String name,
            final String description,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Brand(
                anId,
                name,
                description,
                active,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static Brand with(final Brand brand) {
        return with(
                brand.getId(),
                brand.name,
                brand.description,
                brand.isActive(),
                brand.createdAt,
                brand.updatedAt,
                brand.deletedAt
        );
    }

    public static Brand with(final BrandID anId){
        return new Brand(anId);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new BrandValidator(this, handler).validate();
    }

    public Brand activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Brand deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Brand update(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
