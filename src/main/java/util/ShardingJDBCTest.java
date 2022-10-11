package util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

//测试代码 shardingJDBC  无需spring测试
public class ShardingJDBCTest {
    public static void main(String[] args) {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        DruidDataSource druidDataSource1 = new DruidDataSource();
        druidDataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource1.setUrl("jdbc:mysql://localhost:3306/db_test_1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        druidDataSource1.setUsername("root");
        druidDataSource1.setPassword("123456");
        dataSourceMap.put("ds1", druidDataSource1);

        // 配置第二个数据源
        DruidDataSource druidDataSource2 = new DruidDataSource();
        druidDataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource2.setUrl("jdbc:mysql://localhost:3306/db_test_2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        druidDataSource2.setUsername("root");
        druidDataSource2.setPassword("123456");
        dataSourceMap.put("ds2", druidDataSource2);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("orders","ds${1..2}.order_${1..2}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("userid", "ds${userid % 2 + 1}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("orderid", "order_${orderid % 2 + 1}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...
        // ...

        // 获取数据源对象
        try {
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

            // 获取数据库连接
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into orders values(?,?,?,?)");
            for (int i = 0; i < 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);
                preparedStatement.setInt(3, new Random().nextInt(10));
                preparedStatement.setDouble(4, i * 100.0);
                preparedStatement.execute();
            }
            //清空测试数据SQL备注
            /*
                delete from db_test_1.order_1;
                delete from db_test_1.order_2;
                delete from db_test_2.order_1;
                delete from db_test_2.order_2;
             */
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
