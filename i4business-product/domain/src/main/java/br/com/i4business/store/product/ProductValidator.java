package br.com.i4business.store.product;

import br.com.i4business.store.validation.Error;
import br.com.i4business.store.validation.ValidationHandler;
import br.com.i4business.store.validation.Validator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ProductValidator extends Validator {

    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;
    private final Product product;

    public ProductValidator(ValidationHandler aHandler, Product product) {
        super(aHandler);
        this.product = product;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
        checkPriceConstraints();
        checkWeightConstraints();
        checkHeightConstraints();
        checkWidthConstraints();
        checkLenghtConstraints();
    }

    private void checkNameConstraints(){

        final var name = this.product.getName();

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

    private void checkDescriptionConstraints(){

        final var description = this.product.getDescription();

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

    private void checkPriceConstraints(){

        final var price = this.product.getPrice();

        if (price == null) {
            this.validationHandler().append(new Error("'price' should not be null"));
            return;
        }

        if(isInvalidValidBigDecimal(price)){
            this.validationHandler().append(new Error("'price' its invalid or does not have exactly two decimal places."));
        }

        if(isNegativeBigDecimal(price)){
            this.validationHandler().append(new Error("'price' must be a positive number"));
        }

    }

    private void checkHeightConstraints(){

        final var height = this.product.getHeight();

        if (height == null) {
            this.validationHandler().append(new Error("'height' should not be null"));
            return;
        }

        if(isInvalidValidBigDecimal(height)){
            this.validationHandler().append(new Error("'height' its invalid or does not have exactly two decimal places."));
        }

        if(isNegativeBigDecimal(height)){
            this.validationHandler().append(new Error("'height' must be a positive number"));
        }
    }

    private void checkWidthConstraints(){

        final var width = this.product.getWidth();

        if (width == null) {
            this.validationHandler().append(new Error("'width' should not be null"));
            return;
        }

        if(isInvalidValidBigDecimal(width)){
            this.validationHandler().append(new Error("'width' its invalid or does not have exactly two decimal places."));
        }

        if(isNegativeBigDecimal(width)){
            this.validationHandler().append(new Error("'width' must be a positive number"));
        }
    }

    private void checkLenghtConstraints(){

        final var lenght = this.product.getLenght();

        if (lenght == null) {
            this.validationHandler().append(new Error("'lenght' should not be null"));
            return;
        }

        if(isInvalidValidBigDecimal(lenght)){
            this.validationHandler().append(new Error("'lenght' its invalid or does not have exactly two decimal places."));
        }

        if(isNegativeBigDecimal(lenght)){
            this.validationHandler().append(new Error("'lenght' must be a positive number"));
        }
    }

    private void checkWeightConstraints(){

        final var weight = this.product.getWeight();

        if (weight == null) {
            this.validationHandler().append(new Error("'weight' should not be null"));
            return;
        }

        if(isInvalidValidWeightBigDecimal(weight)){
            this.validationHandler().append(new Error("'weight' its invalid or does not have exactly two decimal places."));
        }

        if(isNegativeBigDecimal(weight)){
            this.validationHandler().append(new Error("'weight' must be a positive number"));
        }
    }

    private static boolean isInvalidValidBigDecimal(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setParseBigDecimal(true);

        String formattedNumber = decimalFormat.format(number);
        BigDecimal parsedNumber = (BigDecimal) decimalFormat.parse(formattedNumber, new java.text.ParsePosition(0));

        return number.compareTo(parsedNumber) != 0;
    }

    private static boolean isInvalidValidWeightBigDecimal(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.000");
        decimalFormat.setParseBigDecimal(true);

        String formattedNumber = decimalFormat.format(number);
        BigDecimal parsedNumber = (BigDecimal) decimalFormat.parse(formattedNumber, new java.text.ParsePosition(0));

        return number.compareTo(parsedNumber) != 0;
    }

    private static boolean isNegativeBigDecimal(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) < 0;
    }
}
