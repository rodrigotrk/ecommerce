package br.com.i4business.store.configuration.usecase;

import br.com.i4business.store.category.CategoryGateway;
import br.com.i4business.store.category.create.CreateCategoryUseCase;
import br.com.i4business.store.category.create.DefaultCreateCategoryUseCase;
import br.com.i4business.store.category.delete.DefaultDeleteCategoryUseCase;
import br.com.i4business.store.category.delete.DeleteCategoryUseCase;
import br.com.i4business.store.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import br.com.i4business.store.category.retrieve.get.GetCategotyByIdUseCase;
import br.com.i4business.store.category.retrieve.list.DefaultListCategoryUseCase;
import br.com.i4business.store.category.retrieve.list.ListCategoryUseCase;
import br.com.i4business.store.category.update.DefaultUpdateCategoryUseCase;
import br.com.i4business.store.category.update.UpdateCategoryUseCase;
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

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(){
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(){
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategotyByIdUseCase getCategotyByIdUseCase(){
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase(){
        return new DefaultListCategoryUseCase(categoryGateway);
    }
}
