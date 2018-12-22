package com.group2.dataStore.hbase;

import com.group2.util.AuraConfig;
import org.apache.hadoop.conf.Configuration;

/**
 * Created by bigdata on 10/20/18.
 */
public class HBaseConfigurationDTO {
    public static Configuration getHBaseConfiguration() {
        Configuration conf = org.apache.hadoop.hbase.HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",
                AuraConfig.getRoot().getString("hbase.hbase.zookeeper.quorum"));
        conf.set("zookeeper.znode.parent", AuraConfig.getRoot().getString("hbase.zookeeper.znode.parent"));

        return conf;
    }
}
