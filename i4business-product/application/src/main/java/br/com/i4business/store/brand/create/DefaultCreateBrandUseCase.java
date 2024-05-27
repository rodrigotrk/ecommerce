package br.com.i4business.store.brand.create;

import br.com.i4business.store.brand.Brand;
import br.com.i4business.store.brand.BrandGateway;
import br.com.i4business.store.exception.NotificationException;
import br.com.i4business.store.validation.handler.Notification;

public class DefaultCreateBrandUseCase extends CreateBrandUseCase {

    private final BrandGateway brandGateway;

    public DefaultCreateBrandUseCase(BrandGateway brandGateway) {
        this.brandGateway = brandGateway;
    }

    @Override
    public CreateBrandOutput execute(CreateBrandCommand createBrandCommand) {
        final var notification = Notification.create();
        final var brand = notification.validate(() -> Brand.newBrand(
                createBrandCommand.name(),
                createBrandCommand.description(),
                createBrandCommand.isActive()
        ));

        if(notification.hasError()){
            notify(notification);
        }
        return CreateBrandOutput.from(this.brandGateway.create(brand));
    }

    private void notify(Notification notification){
        throw new NotificationException("Could not create Aggregate Brand", notification);
    }


}
