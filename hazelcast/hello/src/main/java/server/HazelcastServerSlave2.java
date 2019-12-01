package server;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastServerSlave2 {
    public static void main(String[] args) {
        Config config = new Config();
        GroupConfig groupConfig = new GroupConfig("demo", "demo-pass");
        config.setGroupConfig(groupConfig);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        hazelcastInstance.addDistributedObjectListener(new SimpleListener());
    }
}

class SimpleListener implements DistributedObjectListener {

    @Override
    public void distributedObjectCreated(DistributedObjectEvent distributedObjectEvent) {
        System.out.println(distributedObjectEvent.toString());
    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent distributedObjectEvent) {
        System.out.println(distributedObjectEvent.toString());
    }
}
