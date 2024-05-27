package br.com.i4business.store.brand.create;


import br.com.i4business.store.brand.Brand;

public record CreateBrandOutput(
        String id
) {

    public static CreateBrandOutput from(final String anId) {
        return new CreateBrandOutput(anId);
    }

    public static CreateBrandOutput from(final Brand aBrand) {
        return new CreateBrandOutput(aBrand.getId().getValue());
    }
}
