package br.com.i4business.store.brand;

import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface BrandGateway {

    Brand create(Brand aBrand);

    void deleteById(BrandID anId);

    Optional<Brand> findById(BrandID anId);

    Brand update(Brand aBrand);

    Pagination<Brand> findAll(SearchQuery aQuery);

    List<BrandID> existsByIds(Iterable<BrandID> ids);
}
