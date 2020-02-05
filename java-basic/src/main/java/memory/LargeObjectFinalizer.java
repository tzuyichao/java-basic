package memory;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * https://www.baeldung.com/java-phantom-reference
 */
@Slf4j
public class LargeObjectFinalizer extends PhantomReference<Object> {
    public LargeObjectFinalizer(Object parent, ReferenceQueue<? super Object> referenceQueue) {
        super(parent, referenceQueue);
    }

    public void finalizeResources() {
        // free resources
        log.info("clearing ...");
    }
}
