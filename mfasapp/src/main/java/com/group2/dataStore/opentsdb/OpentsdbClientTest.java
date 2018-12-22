package com.group2.dataStore.opentsdb;

/**
 * Created by lvyou on 2018/12/19.
 */

import com.group2.util.AuraConfig;
import com.group2.util.DateTimeUtil;
import org.junit.Test;
import org.opentsdb.client.request.Filter;
import org.opentsdb.client.util.Aggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class OpentsdbClientTest {

    private static Logger log = LoggerFactory.getLogger(OpentsdbClientTest.class);

    @Test
    public void testPutData() {
        OpentsdbClient client = new OpentsdbClient(AuraConfig.getOpents().getString("url"));
        try {
            Map<String, String> tagMap = new HashMap<String, String>();
            tagMap.put("chl", "hqdApp");

            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 12:15:00", "yyyyMMdd HH:mm:ss"), 210l, tagMap);
            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 12:17:00", "yyyyMMdd HH:mm:ss"), 180l, tagMap);
            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 13:20:00", "yyyyMMdd HH:mm:ss"), 180l, tagMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPutData2() {
        OpentsdbClient client = new OpentsdbClient(AuraConfig.getOpents().getString("url"));
        try {
            Map<String, String> tagMap = new HashMap<String, String>();
            tagMap.put("chl", "hqdWechat");

            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 12:25:00", "yyyyMMdd HH:mm:ss"), 120l, tagMap);
            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 12:27:00", "yyyyMMdd HH:mm:ss"), 810l, tagMap);
            client.putData("metric-t", DateTimeUtil.parse("2018-12-19 13:20:00", "yyyyMMdd HH:mm:ss"), 880l, tagMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetData() {
        OpentsdbClient client = new OpentsdbClient(AuraConfig.getOpents().getString("url"));
        try {
            Filter filter = new Filter();
            filter.setType("regexp");
            filter.setTagk("chl");
            filter.setFilter("hqdApp");
            filter.setGroupBy(Boolean.TRUE);
            String resContent = client.getData("metric-t", filter, Aggregator.avg.name(), "1h",
                    "2018-12-19 12:00:00", "2018-12-19 13:00:00");
            System.out.print(">>>" + resContent);
            log.info(">>>" + resContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetData2() {
        OpentsdbClient client = new OpentsdbClient(AuraConfig.getOpents().getString("url"));
        try {
            Filter filter = new Filter();
            String tagk = "chl";
            String tagvFtype = OpentsdbClient.FILTER_TYPE_WILDCARD;
            String tagvFilter = "hqdapp*";

            Map<String, Map<String, Object>> tagsValuesMap = client.getData("metric-t", tagk, tagvFtype, tagvFilter, Aggregator.avg.name(), "1m",
                    "2018-12-19 12:00:00", "2018-12-19 17:00:00", "yyyyMMdd HHmm");

            for (Iterator<String> it = tagsValuesMap.keySet().iterator(); it.hasNext(); ) {
                String tags = it.next();
                System.out.println(">> tags: " + tags);
                Map<String, Object> tvMap = tagsValuesMap.get(tags);
                for (Iterator<String> it2 = tvMap.keySet().iterator(); it2.hasNext(); ) {
                    String time = it2.next();
                    System.out.println("    >> " + time + " <-> " + tvMap.get(time));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetData3() {
        OpentsdbClient client = new OpentsdbClient(AuraConfig.getOpents().getString("opentsdb.url"));
        try {
            Filter filter = new Filter();
            filter.setType(OpentsdbClient.FILTER_TYPE_LITERAL_OR);
            filter.setTagk("chl");
            filter.setFilter("hqdApp|hqdWechat");
            filter.setGroupBy(Boolean.TRUE);
            String resContent = client.getData("metric-t", filter, Aggregator.avg.name(), "1h",
                    "2018-12-19 12:00:00", "2018-12-20 00:00:00");

            log.info(">>>" + resContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
