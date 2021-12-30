package reflect.proxy.dummymock;

import java.util.Optional;

public interface Repository <T, ID> {
    T save(T model);
    Optional<T> findById(ID id);
}
