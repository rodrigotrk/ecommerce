package br.com.i4business.store.inventory;

import br.com.i4business.store.pagination.Pagination;
import br.com.i4business.store.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface InventoryGateway {

    Inventory create(Inventory aInventory);

    void deleteById(InventoryID anId);

    Optional<Inventory> findById(InventoryID anId);

    Inventory update(Inventory aInventory);

    Pagination<Inventory> findAll(SearchQuery aQuery);

    List<InventoryID> existsByIds(Iterable<InventoryID> ids);
}
