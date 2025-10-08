package demo;

import org.apache.kafka.common.Configurable;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;

public class LowercaseTopic implements Transformation<SourceRecord>, Configurable {
    private static final Logger log = LoggerFactory.getLogger(LowercaseTopic.class);

    @Override
    public SourceRecord apply(SourceRecord record) {
        if (record == null || record.topic() == null) return record;
        String lower = record.topic().toLowerCase(Locale.ROOT);
        if (lower.equals(record.topic())) return record;
        log.info("lowercased from {} to {}", record.topic(), lower);
        return record.newRecord(
                lower,
                record.kafkaPartition(),
                record.keySchema(), record.key(),
                record.valueSchema(), record.value(),
                record.timestamp()
        );
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
