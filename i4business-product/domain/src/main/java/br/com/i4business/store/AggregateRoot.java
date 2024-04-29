package br.com.i4business.store;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id){
        super(id);
    }

}
