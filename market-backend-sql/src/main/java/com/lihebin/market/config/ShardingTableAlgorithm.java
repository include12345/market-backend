package com.lihebin.market.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * Created by lihebin on 2020/10/25.
 */
public class ShardingTableAlgorithm implements PreciseShardingAlgorithm<Long>{
    @Override
    public String doSharding(Collection<String> tables, PreciseShardingValue<Long> preciseShardingValue) {
        for (String table : tables) {
            if (table.endsWith(preciseShardingValue.getValue() % 2 + "")) {
                return table;
            }
        }
        throw new UnsupportedOperationException();
    }
}
