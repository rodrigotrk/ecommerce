package br.com.i4business.store;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN in);
}
