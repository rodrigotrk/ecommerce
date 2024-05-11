package br.com.i4business.store.configuration.usecase;

import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.category.CreateCategoryUseCase;
import br.com.i4business.store.category.DefaultCreateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfiguration {

    private final CategoryGateway categoryGateway;

    public CategoryConfiguration(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(){
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }
}
