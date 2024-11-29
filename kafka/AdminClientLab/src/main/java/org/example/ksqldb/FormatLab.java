package org.example.ksqldb;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;

/**
 * Exception in thread "main" org.apache.calcite.sql.parser.SqlParseException: Incorrect syntax near the keyword 'CREATE'
 */
public class FormatLab {
    public static void main(String[] args) throws SqlParseException {
        SqlParser parser = SqlParser.create("CREATE STREAM QUALITY_QUALITYALERT_PRODUCTLINEBLOCK_V0_CNS WITH (value_format='JSON',kafka_topic='quality.quality-alert.product-line-block.v0.cns', PARTITIONS = 3, REPLICAS = 3) AS SELECT `eventId`, `sourceSystem`, `eventName`, `eventTime`, `actionIds`, `issueSystem`, `issueId`, `issueLevel`, `alertSource`, `alertId`, `workOrder`, `factory`, `bu`, `bg`, `line`, `blockRule`, `station`, `modelNumber`, `region`, `prodPlant`, `group` FROM QUALITY_QUALITYALERT_PRODUCTLINEBLOCK_V0 WHERE `region` = 'CNS' EMIT CHANGES;");
        SqlNode sqlNode = parser.parseStmt();
        SqlPrettyWriter writer = new SqlPrettyWriter();
        sqlNode.unparse(writer, 0, 0);
        System.out.println(writer.toString());
    }
}
