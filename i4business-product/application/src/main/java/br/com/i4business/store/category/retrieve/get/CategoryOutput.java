package br.com.i4business.store.category.retrieve.get;

import br.com.i4business.store.category.Category;

import java.time.Instant;

public record CategoryOutput(
        String id,
        String name,
        String description,
        Boolean active,
        Category parent,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CategoryOutput from(final Category aCategory){
        return new CategoryOutput(
                aCategory.getId().getValue(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getParent(),
                aCategory.getCreatedAt(),
                aCategory.getUpdatedAt(),
                aCategory.getDeletedAt()
        );
    }
}
