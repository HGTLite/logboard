package com.hgt.hbase.common;

import com.hgt.hbase.config.HBaseConfig;
import com.hgt.utils.StringHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:工具类，直接与HBase交互的操作
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class HBaseOperations {

    //region 属性+构造+初始化
    public Configuration configuration;

    public HBaseOperations() {
        configuration = new HBaseConfig().createHBaseConfiguration();
    }
    //endregion


    //region 表的插入操作

    /**
     * 插入键值对
     *
     * @param strTableName    表名
     * @param strRowkey       行键
     * @param strColumnFamily 列族名
     * @param strQualifier    列名
     * @param strValue        值名
     */
    public void insertRow(String strTableName, String strRowkey, String strColumnFamily, String[] strQualifier, String[] strValue) {

//        System.out.println("=====开始插入数据到"+strTableName+"=====");
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(TableName.valueOf(strTableName));

            // 一个PUT代表一行数据
            Put put = new Put(strRowkey.getBytes());

            //一次添加一个单元格的kv
            //put.addColumn(strColumnFamily.getBytes(), strQualifier.getBytes(), strValue.getBytes());

            //一次添加一行数据，多个kv
            for (int i = 0; i < strQualifier.length; i++) {
                //列名为空的单独处理
                if (strQualifier[i] == null || strQualifier[i].isEmpty()) {
                    put.addColumn(strColumnFamily.getBytes(), "".getBytes(), strValue[i].getBytes());
                } else {
                    put.addColumn(strColumnFamily.getBytes(), strQualifier[i].getBytes(), strValue[i].getBytes());
                }
            }
            table.put(put);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=====插入数据结束=====");
    }
    //endregion


    //region 表的删除操作
    public void deleteTable(String strTableName) {
        TableName tableName = TableName.valueOf(strTableName);
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin hBaseAdmin = connection.getAdmin();

            if (hBaseAdmin.tableExists(tableName)) {
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println("=====表 " + strTableName + " 已删除完成");
            } else {
                System.out.println("=====所要删除的表：" + strTableName + "不存在，请检查表名");
                return;
            }

            hBaseAdmin.close();
            connection.close();

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRowsByRowkeys(String strTableName, String[] strRowkeys) {
        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(tableName);

            List list = new ArrayList();
            for (String rowkey : strRowkeys) {
                Delete d = new Delete(rowkey.getBytes());
                list.add(d);
            }
            table.delete(list);
            System.out.println("=====删除行 " + strRowkeys + " 完成=====");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteColumnFamilies(String strTableName, String[] strCFNames) {

        if (strCFNames == null) {
            System.out.println("列族名为空,未删除列族");
            return;
        }

        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin hBaseAdmin = connection.getAdmin();
            Table table = connection.getTable(tableName);

            hBaseAdmin.disableTable(tableName);

            HTableDescriptor tableDescriptor = hBaseAdmin.getTableDescriptor(tableName);

            for (String s : strCFNames) {
                try {
                    //TO-DO:如果表中无此列族名，直接结束方法
                    tableDescriptor.removeFamily(s.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("列族删除失败");
                }
            }

            hBaseAdmin.modifyTable(tableName, tableDescriptor);
            hBaseAdmin.enableTable(tableName);

            hBaseAdmin.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion


    //region 创建新表、新列族

    /**
     * 建表方法
     *
     * @param strTableName 表名
     * @param strCFNames   列族名，多个请用逗号分隔
     * @param minV         列族保存的最小版本
     * @param maxV         列族保存的最大版本
     */
    public void createTable(String strTableName, String strCFNames, int minV, int maxV) {

        TableName tableName = TableName.valueOf(strTableName);
        List<String> columnFamilyNames = StringHelper.strTOList(strCFNames);

        if (strCFNames.trim() == "") {
            System.out.println("建表时请指定至少一个列族名。");
            return;
        }

        System.out.println("=====开始建表" + strTableName + "=====");
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin hBaseAdmin = connection.getAdmin();

            // 如果存在要创建的表，那么先删除，再创建
            if (hBaseAdmin.tableExists(tableName)) {
                hBaseAdmin.disableTable(tableName);
                System.out.println(strTableName + "表已存在，正在删除");
                hBaseAdmin.deleteTable(tableName);
            }

            //使用新的命名空间，默认为 NamespaceDescriptor.DEFAULT_NAMESPACE
//            hBaseAdmin.createNamespace(NamespaceDescriptor.create("newns").build());

            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

            for (String cf : columnFamilyNames) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(cf);
                //设置数据保存的版本个数
//                columnDescriptor.setVersions(minV, maxV);
                columnDescriptor.setMinVersions(minV);
                columnDescriptor.setMaxVersions(maxV);
                tableDescriptor.addFamily(columnDescriptor);
            }

            //异步写WAL日志，提高容错率
//            tableDescriptor.setDurability(Durability.ASYNC_WAL);

            hBaseAdmin.createTable(tableDescriptor);
            hBaseAdmin.close();
            connection.close();

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=====" + strTableName + "表创建完成");
    }

    /**
     * 建表时每个列族默认存2个版本
     *
     * @param strTableName
     * @param strCFNames
     */
    public void createTable(String strTableName, String strCFNames) {
        this.createTable(strTableName, strCFNames, 2, 2);
    }

    public void addNewColumnFamilies(String strTableName, String[] strCFNames) {

        if (strCFNames == null) {
            System.out.println("列族名不能为空");
            return;
        }

        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin hBaseAdmin = connection.getAdmin();
            Table table = connection.getTable(tableName);

            hBaseAdmin.disableTable(tableName);

            HTableDescriptor tableDescriptor = hBaseAdmin.getTableDescriptor(tableName);

            for (String s : strCFNames) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(s);
                tableDescriptor.addFamily(columnDescriptor);
            }

            hBaseAdmin.modifyTable(tableName, tableDescriptor);
            hBaseAdmin.enableTable(tableName);

            hBaseAdmin.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion


    //region 表的基本信息查询操作

    /**
     * 查询表的列族名
     *
     * @param strTableName
     */
    public void showColumnFamilies(String strTableName) {

        //返回的列族名数组
        List<String> resultCFs = new ArrayList<String>();

        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(TableName.valueOf(strTableName));

            HTableDescriptor tableDescriptor = table.getTableDescriptor();

            HColumnDescriptor[] columnDescriptors = tableDescriptor.getColumnFamilies();

            for (HColumnDescriptor columnDescriptor : columnDescriptors) {
                resultCFs.add(columnDescriptor.getNameAsString());
                //System.out.println("=====" + strTableName + "的列族有：" + columnDescriptor.getNameAsString());
            }

            System.out.println("=====" + strTableName + "的列族总共有：" + columnDescriptors.length + " 个");
            System.out.println("=====" + strTableName + "的列族分别是：" + resultCFs);

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion


}
