package retry;

public interface BusinessOperation<T> {
    T perform() throws BusinessException;
}
