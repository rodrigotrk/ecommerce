package br.com.i4business.store.category.create;

import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.exception.NotificationException;
import br.com.i4business.store.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public CreateCategoryOutput execute(CreateCategoryCommand createCategoryCommand) {
        final var notification = Notification.create();
        final var category = notification.validate(() -> Category.newCategory(
                createCategoryCommand.name(),
                createCategoryCommand.description(),
                createCategoryCommand.isActive(),
                isEmptyParent(createCategoryCommand.parent()) ? null
                        : createCategoryCommand.parent()
        ));

        if(notification.hasError()){
            notify(notification);
        }
        return CreateCategoryOutput.from(this.categoryGateway.create(category));
    }

    private void notify(Notification notification){
        throw new NotificationException("Could not create Aggregate Category", notification);
    }

    private boolean isEmptyParent(Category aCategory){
        if(aCategory == null){
            return true;
        }

        return aCategory.getId().getValue().trim().isEmpty();
    }


}
