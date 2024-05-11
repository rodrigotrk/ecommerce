package br.com.i4business.store.category;

import br.com.i4business.store.category.persistence.CategoryEntity;
import br.com.i4business.store.category.persistence.CategoryRepository;
import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.i4business.store.util.SpecificationUtils.like;

@Component
public class CategoryPostgresGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryPostgresGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anId) {
        final String anIdValue = anId.getValue();
        if(this.repository.existsById(anIdValue)){
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return this.repository.findById(anId.getValue())
                .map(CategoryEntity::toAggregate);
    }

    @Override
    public Category update(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );


        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CategoryEntity::toAggregate).toList());
    }

    @Override
    public List<CategoryID> existsByIds(Iterable<CategoryID> idsCategory) {
        final var ids = StreamSupport.stream(idsCategory.spliterator(), false)
                .map(CategoryID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(CategoryID::from)
                .toList();
    }

    private Category save(final Category category){
        return this.repository.save(CategoryEntity.from(category)).toAggregate();
    }

    private Specification<CategoryEntity> assembleSpecification(final String str) {
        final Specification<CategoryEntity> nameLike = like("name", str);
        final Specification<CategoryEntity> descriptionLike = like("description", str);
        return nameLike.or(descriptionLike);
    }
}
