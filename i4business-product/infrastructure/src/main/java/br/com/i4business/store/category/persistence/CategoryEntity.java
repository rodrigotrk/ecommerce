package br.com.i4business.store.category.persistence;

import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryID;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Category")
@Table(name = "category",
        uniqueConstraints = { @UniqueConstraint(name = "unq_name", columnNames = { "name" }) })
public class CategoryEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "parent_id",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_category_parent"))
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> categories;

    public CategoryEntity() {}

    private CategoryEntity(String id){
        this.id = id;
    }

    private CategoryEntity(
            final String id,
            final String name,
            final String description,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final boolean active,
            final CategoryEntity parent
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.active = active;
        this.parent = parent;
        this.categories = new ArrayList<>();
    }

    public static CategoryEntity from(final Category category) {
       return new CategoryEntity(
               category.getId().getValue(),
               category.getName(),
               category.getDescription(),
               category.getCreatedAt(),
               category.getUpdatedAt(),
               category.getDeletedAt(),
               category.isActive(),
               category.getParent() == null ? null : from(category.getParent())
       );
    }

    public static CategoryEntity from(final CategoryID categoryID){
        return new CategoryEntity(categoryID.getValue());
    }

    public void addParent(CategoryEntity parent) {
        categories.add(parent);
        parent.setParent(this);
    }

    public void removeParent(CategoryEntity parent) {
        categories.remove(parent);
        parent.setParent(null);
    }

    public Category toAggregate() {
        return Category.with(
                CategoryID.from(getId()),
                getName(),
                getDescription(),
                isActive(),
                getParent() == null ? null : this.getParent().toAggregate(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public CategoryEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CategoryEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public CategoryEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public CategoryEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public CategoryEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public CategoryEntity setParent(CategoryEntity parent) {
        this.parent = parent;
        return this;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public CategoryEntity setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }
}
