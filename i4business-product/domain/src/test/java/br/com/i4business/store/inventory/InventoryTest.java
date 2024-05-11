package br.com.i4business.store.inventory;


import br.com.i4business.store.exception.DomainException;
import br.com.i4business.store.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryTest {

    @Test
    public void givenAValidParams_whenCallNewInventory_thenInstantiateAInventory() {
        final var expectedQuantity = 10L;
        final var expectedQuantityAlert = 3L;
        final var expectedIsActive = true;

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        Assertions.assertNotNull(actualInventory);
        Assertions.assertNotNull(actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertEquals(expectedIsActive, actualInventory.isActive());
        Assertions.assertNotNull(actualInventory.getCreatedAt());
        Assertions.assertNotNull(actualInventory.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullQuantity_whenCallNewInventoryAndValidate_thenShouldReceiveError() {
        final Long expectedQuantity = null;
        final var expectedQuantityAlert = 3L;
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity' should not be null";

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNegativeQuantity_whenCallNewInventoryAndValidate_thenShouldReceiveError() {
        final var expectedQuantity = -10L ;
        final var expectedQuantityAlert = 3L;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity' should be a positive number";
        final var expectedIsActive = true;

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullQuantityAlert_whenCallNewInventoryAndValidate_thenShouldReceiveError() {
        final var expectedQuantity = 10L;
        final Long expectedQuantityAlert = null;
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity alert' should not be null";

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNegativeQuantityAlert_whenCallNewInventoryAndValidate_thenShouldReceiveError() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = -10L;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity alert' should be a positive number";
        final var expectedIsActive = true;

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }



    @Test
    public void givenAValidFalseIsActive_whenCallNewInventoryAndValidate_thenShouldReceiveOK() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = false;

        final var actualInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualInventory);
        Assertions.assertNotNull(actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertEquals(expectedIsActive, actualInventory.isActive());
        Assertions.assertNotNull(actualInventory.getCreatedAt());
        Assertions.assertNotNull(actualInventory.getUpdatedAt());
    }

    @Test
    public void givenAValidActiveInventory_whenCallDeactivate_thenReturnInventoryInactivated() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = false;

        final var aInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, true);

        Assertions.assertDoesNotThrow(() -> aInventory.validate(new ThrowsValidationHandler()));

        final var createdAt = aInventory.getCreatedAt();
        final var updatedAt = aInventory.getUpdatedAt();

        Assertions.assertTrue(aInventory.isActive());

        final var actualInventory = aInventory.deactivate();

        Assertions.assertDoesNotThrow(() -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aInventory.getId(), actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertEquals(expectedIsActive, actualInventory.isActive());
        Assertions.assertEquals(createdAt, actualInventory.getCreatedAt());
        Assertions.assertTrue(actualInventory.getUpdatedAt().isAfter(updatedAt));
    }

    @Test
    public void givenAValidInactiveInventory_whenCallActivate_thenReturnInventoryActivated() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = true;

        final var aInventory =
                Inventory.newInventory(expectedQuantity, expectedQuantityAlert, false);

        Assertions.assertDoesNotThrow(() -> aInventory.validate(new ThrowsValidationHandler()));

        final var createdAt = aInventory.getCreatedAt();
        final var updatedAt = aInventory.getUpdatedAt();

        Assertions.assertFalse(aInventory.isActive());

        final var actualInventory = aInventory.activate();

        Assertions.assertDoesNotThrow(() -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aInventory.getId(), actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertEquals(expectedIsActive, actualInventory.isActive());
        Assertions.assertEquals(createdAt, actualInventory.getCreatedAt());
        Assertions.assertTrue(actualInventory.getUpdatedAt().isAfter(updatedAt));
    }

    @Test
    public void givenAValidInventory_whenCallUpdate_thenReturnInventoryUpdated() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = true;

        final var aInventory =
                Inventory.newInventory(40L, 5L, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aInventory.validate(new ThrowsValidationHandler()));

        final var createdAt = aInventory.getCreatedAt();
        final var updatedAt = aInventory.getUpdatedAt();

        final var actualInventory = aInventory.update(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aInventory.getId(), actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertEquals(expectedIsActive, actualInventory.isActive());
        Assertions.assertEquals(createdAt, actualInventory.getCreatedAt());
        Assertions.assertTrue(actualInventory.getUpdatedAt().isAfter(updatedAt));
    }

    @Test
    public void givenAValidInventory_whenCallUpdateToInactive_thenReturnInventoryUpdated() {
        final var expectedQuantity = 30L;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = false;

        final var aInventory =
                Inventory.newInventory(40L, 5L, true);

        Assertions.assertDoesNotThrow(() -> aInventory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aInventory.isActive());

        final var createdAt = aInventory.getCreatedAt();
        final var updatedAt = aInventory.getUpdatedAt();

        final var actualInventory = aInventory.update(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualInventory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aInventory.getId(), actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertFalse(aInventory.isActive());
        Assertions.assertEquals(createdAt, actualInventory.getCreatedAt());
        Assertions.assertTrue(actualInventory.getUpdatedAt().isAfter(updatedAt));
    }

    @Test
    public void givenAValidInventory_whenCallUpdateWithInvalidParams_thenReturnInventoryUpdated() {
        final Long expectedQuantity = null;
        final var expectedQuantityAlert = 10L;
        final var expectedIsActive = true;

        final var aInventory =
                Inventory.newInventory(40L, 5L, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aInventory.validate(new ThrowsValidationHandler()));

        final var createdAt = aInventory.getCreatedAt();
        final var updatedAt = aInventory.getUpdatedAt();

        final var actualInventory = aInventory.update(expectedQuantity, expectedQuantityAlert, expectedIsActive);

        Assertions.assertEquals(aInventory.getId(), actualInventory.getId());
        Assertions.assertEquals(expectedQuantity, actualInventory.getQuantity());
        Assertions.assertEquals(expectedQuantityAlert, actualInventory.getQuantityAlert());
        Assertions.assertTrue(aInventory.isActive());
        Assertions.assertEquals(createdAt, actualInventory.getCreatedAt());
        Assertions.assertTrue(actualInventory.getUpdatedAt().isAfter(updatedAt));
    }
}
