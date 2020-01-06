package lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZkLock implements Lock {
    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
