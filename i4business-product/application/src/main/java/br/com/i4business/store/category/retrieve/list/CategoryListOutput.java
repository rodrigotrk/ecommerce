package br.com.i4business.store.category.retrieve.list;

import br.com.i4business.store.category.Category;

import java.time.Instant;

public record CategoryListOutput(
        String id,
        String name,
        String description,
        boolean active,
        Category parent,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt

) {

    public static CategoryListOutput from(final Category aCategory){
        return new CategoryListOutput(
                aCategory.getId().getValue(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getParent() == null ? null : aCategory.getParent(),
                aCategory.getCreatedAt(),
                aCategory.getUpdatedAt(),
                aCategory.getDeletedAt()
        );
    }
}
