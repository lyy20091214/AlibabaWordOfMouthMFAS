package com.group2.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class AuraConfig {

    private static Config root = loadConfig();

    private static Config loadConfig() {
        String confPath = System.getenv("AURA_CONF");
        if (confPath != null) {
           return ConfigFactory.parseFile(new File(confPath));
        } else {
           return ConfigFactory.parseResources("aura.conf");
        }
    }

    public static Config getRoot() {
        return root;
    }

    public static Config getKafka() {
        return root.getConfig("kafka");
    }

    public static Config getStreaming() {
        return root.getConfig("streaming");
    }

    public static Config getOpents() {
        return root.getConfig("opents");
    }
    public static Config getRedis(){ return root.getConfig("redis");}
}
