package br.com.i4business.store.category;

import br.com.i4business.store.validation.Error;
import br.com.i4business.store.validation.ValidationHandler;
import br.com.i4business.store.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;
    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }

    private void checkDescriptionConstraints() {
        final var description = this.category.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (description.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final int length = description.trim().length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            this.validationHandler().append(new Error("'description' must be between 3 and 255 characters"));
        }
    }
}
