package br.com.i4business.store.brand;


import br.com.i4business.store.exception.DomainException;
import br.com.i4business.store.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BrandTest {

    @Test
    public void givenAValidParams_whenCallNewBrand_thenInstantiateABrand() {
        final var expectedName = "PC";
        final var expectedDescription = "Games for computer";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualBrand);
        Assertions.assertNotNull(actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertEquals(expectedIsActive, actualBrand.isActive());
        Assertions.assertNotNull(actualBrand.getCreatedAt());
        Assertions.assertNotNull(actualBrand.getUpdatedAt());
        Assertions.assertNull(actualBrand.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = """
                Computer games have evolved tremendously over the decades, transforming from simple pixel-based puzzles
                to vast, immersive worlds that offer complex narratives and detailed environments. This evolution has
                been driven by technological advancements that enable developers to create more detailed graphics and
                sophisticated game mechanics, thus allowing players to experience stories and adventures in ways that
                were previously unimaginable. Today's games can simulate incredibly realistic environments, from
                bustling cityscapes to serene natural landscapes, all populated by characters with deep backstories and
                intricate behaviors.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidDescriptionLengthMoreThan255_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = "Xbox";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 3 and 255 characters";
        final var expectedDescription = """
                Computer games have evolved tremendously over the decades, transforming from simple pixel-based puzzles
                to vast, immersive worlds that offer complex narratives and detailed environments. This evolution has
                been driven by technological advancements that enable developers to create more detailed graphics and
                sophisticated game mechanics, thus allowing players to experience stories and adventures in ways that
                were previously unimaginable. Today's games can simulate incredibly realistic environments, from
                bustling cityscapes to serene natural landscapes, all populated by characters with deep backstories and
                intricate behaviors.
                """;
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = "Xbox";
        final var expectedDescription = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullDescription_whenCallNewBrandAndValidate_thenShouldReceiveError() {
        final var expectedName = "Xbox";
        final String expectedDescription = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be null";
        final var expectedIsActive = true;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewBrandAndValidate_thenShouldReceiveOK() {
        final var expectedName = "Xbox";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = false;

        final var actualBrand =
                Brand.newBrand(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualBrand);
        Assertions.assertNotNull(actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertEquals(expectedIsActive, actualBrand.isActive());
        Assertions.assertNotNull(actualBrand.getCreatedAt());
        Assertions.assertNotNull(actualBrand.getUpdatedAt());
        Assertions.assertNotNull(actualBrand.getDeletedAt());
    }

    @Test
    public void givenAValidActiveBrand_whenCallDeactivate_thenReturnBrandInactivated() {
        final var expectedName = "Xbox";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = false;

        final var aBrand =
                Brand.newBrand(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(() -> aBrand.validate(new ThrowsValidationHandler()));

        final var createdAt = aBrand.getCreatedAt();
        final var updatedAt = aBrand.getUpdatedAt();

        Assertions.assertTrue(aBrand.isActive());
        Assertions.assertNull(aBrand.getDeletedAt());

        final var actualBrand = aBrand.deactivate();

        Assertions.assertDoesNotThrow(() -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aBrand.getId(), actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertEquals(expectedIsActive, actualBrand.isActive());
        Assertions.assertEquals(createdAt, actualBrand.getCreatedAt());
        Assertions.assertTrue(actualBrand.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualBrand.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveBrand_whenCallActivate_thenReturnBrandActivated() {
        final var expectedName = "Xbox";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var aBrand =
                Brand.newBrand(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(() -> aBrand.validate(new ThrowsValidationHandler()));

        final var createdAt = aBrand.getCreatedAt();
        final var updatedAt = aBrand.getUpdatedAt();

        Assertions.assertFalse(aBrand.isActive());
        Assertions.assertNotNull(aBrand.getDeletedAt());

        final var actualBrand = aBrand.activate();

        Assertions.assertDoesNotThrow(() -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aBrand.getId(), actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertEquals(expectedIsActive, actualBrand.isActive());
        Assertions.assertEquals(createdAt, actualBrand.getCreatedAt());
        Assertions.assertTrue(actualBrand.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualBrand.getDeletedAt());
    }

    @Test
    public void givenAValidBrand_whenCallUpdate_thenReturnBrandUpdated() {
        final var expectedName = "Xbox";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var aBrand =
                Brand.newBrand("Film", "Brand", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aBrand.validate(new ThrowsValidationHandler()));

        final var createdAt = aBrand.getCreatedAt();
        final var updatedAt = aBrand.getUpdatedAt();

        final var actualBrand = aBrand.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aBrand.getId(), actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertEquals(expectedIsActive, actualBrand.isActive());
        Assertions.assertEquals(createdAt, actualBrand.getCreatedAt());
        Assertions.assertTrue(actualBrand.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualBrand.getDeletedAt());
    }

    @Test
    public void givenAValidBrand_whenCallUpdateToInactive_thenReturnBrandUpdated() {
        final var expectedName = "Xbox";
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = false;

        final var aBrand =
                Brand.newBrand("Film", "Brand", true);

        Assertions.assertDoesNotThrow(() -> aBrand.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aBrand.isActive());
        Assertions.assertNull(aBrand.getDeletedAt());

        final var createdAt = aBrand.getCreatedAt();
        final var updatedAt = aBrand.getUpdatedAt();

        final var actualBrand = aBrand.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualBrand.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aBrand.getId(), actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertFalse(aBrand.isActive());
        Assertions.assertEquals(createdAt, actualBrand.getCreatedAt());
        Assertions.assertTrue(actualBrand.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(aBrand.getDeletedAt());
    }

    @Test
    public void givenAValidBrand_whenCallUpdateWithInvalidParams_thenReturnBrandUpdated() {
        final String expectedName = null;
        final var expectedDescription = "The Brand most visited";
        final var expectedIsActive = true;

        final var aBrand =
                Brand.newBrand("Categories", "The Brand", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aBrand.validate(new ThrowsValidationHandler()));

        final var createdAt = aBrand.getCreatedAt();
        final var updatedAt = aBrand.getUpdatedAt();

        final var actualBrand = aBrand.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aBrand.getId(), actualBrand.getId());
        Assertions.assertEquals(expectedName, actualBrand.getName());
        Assertions.assertEquals(expectedDescription, actualBrand.getDescription());
        Assertions.assertTrue(aBrand.isActive());
        Assertions.assertEquals(createdAt, actualBrand.getCreatedAt());
        Assertions.assertTrue(actualBrand.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(aBrand.getDeletedAt());
    }
}
