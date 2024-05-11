package br.com.i4business.store.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("parent_id") String parent
) {
}
