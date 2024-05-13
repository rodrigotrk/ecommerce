package br.com.i4business.store.product;

import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product create(Product aProduct);

    void deleteById(ProductID anId);

    Optional<Product> findById(ProductID anId);

    Product update(Product aProduct);

    Pagination<Product> findAll(SearchQuery aQuery);

    List<ProductID> existsByIds(Iterable<ProductID> ids);
}
