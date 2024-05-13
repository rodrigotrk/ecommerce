package br.com.i4business.store.product;

import br.com.i4business.store.AggregateRoot;
import br.com.i4business.store.brand.Brand;
import br.com.i4business.store.category.Category;
import br.com.i4business.store.inventory.Inventory;
import br.com.i4business.store.utils.InstantUtils;
import br.com.i4business.store.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Product extends AggregateRoot<ProductID> {

    private String name;
    private String description;
    private BigDecimal price;
    private String video;
    private BigDecimal weight;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal lenght;
    private Category category;
    private Brand brand;
    private Inventory inventory;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private boolean active;

    private Product(
            final ProductID id,
            final String name,
            final String description,
            final BigDecimal price,
            final String video,
            final BigDecimal weight,
            final BigDecimal height,
            final BigDecimal width,
            final BigDecimal lenght,
            final Category category,
            final Brand brand,
            final Inventory inventory,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final boolean isActive
    ){
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.video = video;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.category = category;
        this.brand = brand;
        this.inventory = inventory;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
        this.deletedAt = deletedAt;
    }

    public static Product newProduct(
            final String name,
            final String description,
            final BigDecimal price,
            final String video,
            final BigDecimal weight,
            final BigDecimal height,
            final BigDecimal width,
            final BigDecimal lenght,
            final Category category,
            final Brand brand,
            final Inventory inventory,
            final Boolean isActive
    ){

        final var id = ProductID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;

        return new Product(
                id,
                name,
                description,
                price,
                video,
                weight,
                height,
                width,
                lenght,
                category,
                brand,
                inventory,
                now,
                now,
                deletedAt,
                true
        );
    }

    public static Product with(
            final ProductID id,
            final String name,
            final String description,
            final BigDecimal price,
            final String video,
            final BigDecimal weight,
            final BigDecimal height,
            final BigDecimal width,
            final BigDecimal lenght,
            final Category category,
            final Brand brand,
            final Inventory inventory,
            final Instant createAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final boolean isActive
    ){
        return new Product(
                id,
                name,
                description,
                price,
                video,
                weight,
                height,
                width,
                lenght,
                category,
                brand,
                inventory,
                createAt,
                updatedAt,
                deletedAt,
                isActive
        );
    }

    public static Product with(final Product product){
        return with(
                product.getId(),
                product.name,
                product.description,
                product.price,
                product.video,
                product.weight,
                product.height,
                product.width,
                product.lenght,
                product.category,
                product.brand,
                product.inventory,
                product.createdAt,
                product.updatedAt,
                product.deletedAt,
                product.isActive()
        );
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    public Product activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Product deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Product update(
            final String name,
            final String description,
            final BigDecimal price,
            final String video,
            final BigDecimal weight,
            final BigDecimal height,
            final BigDecimal width,
            final BigDecimal lenght,
            final Category category,
            final Brand brand,
            final Inventory inventory,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.name = name;
        this.description = description;
        this.price = price;
        this.video = video;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.category = category;
        this.brand = brand;
        this.inventory = inventory;
        this.updatedAt = Instant.now();

        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getVideo() {
        return video;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getLenght() {
        return lenght;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public Inventory getInventory() {
        return inventory;
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

    public boolean isActive() {
        return active;
    }
}
