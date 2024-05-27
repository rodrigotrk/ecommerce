package br.com.i4business.store.category.update;

import br.com.i4business.store.Identifier;
import br.com.i4business.store.category.Category;
import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.category.CategoryID;
import br.com.i4business.store.exception.NotFoundException;
import br.com.i4business.store.exception.NotificationException;
import br.com.i4business.store.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public UpdateCategoryOutput execute(UpdateCategoryCommand command) {
        final var notification = Notification.create();
        final var anId = CategoryID.from(command.id());
        final var name = command.name();
        final var description = command.description();
        final var isActive = command.isActive();
        final var parent = Objects.isNull(command.parent()) ? null : command.parent();

        final var aCategory = this.categoryGateway.findById(anId).orElseThrow(notFound(anId));

        final var category = notification.validate(() -> aCategory.update(name, description, isActive, parent));

        if(notification.hasError()){
            notify(anId, notification);
        }
        return UpdateCategoryOutput.from(this.categoryGateway.update(category));
    }

    private void notify(final Identifier anId, final Notification notification){
        throw new NotificationException("Could not update Aggregate Category %s".formatted(anId.getValue()), notification);
    }

    private Supplier<NotFoundException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }


}
