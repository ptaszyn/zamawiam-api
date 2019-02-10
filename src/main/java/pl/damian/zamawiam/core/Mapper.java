package pl.damian.zamawiam.core;

public interface Mapper<T, K> {
    K toDto(T entity);
    T toEntity(K dto);
}
