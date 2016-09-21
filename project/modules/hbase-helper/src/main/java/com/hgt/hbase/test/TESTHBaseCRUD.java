package com.hgt.hbase.test;

import io.yaooo.logging.common.HBaseOperations;
import io.yaooo.logging.common.QueryFromHBase;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:测试类
 * 注  意：  1.在插入数据时，可直接输入新列名，不能直接添加新列族名；新列族名要先单独添加
 *          2.列名可为空，无列名时要写明""
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTHBaseCRUD {

    public static void main(String[] args) {

        HBaseOperations hBaseOperations = new HBaseOperations();
        QueryFromHBase queryFromHBase = new QueryFromHBase();


        //测试添加行数据
//        String[] key = {"name", "", "code"};
//        String[] value = {"name-003", "第3行的无名列", "20160718"};
//        hBaseOperations.insertRow("table0715", "rowkey003", "usr", key, value);


        //测试删除表
//        hBaseOperations.deleteTable("table3");

//测试删除列族名
//        String[] cfNames = {"co1", "co2"};
//        hBaseOperations.deleteColumnFamilies("table0715", cfNames);

        //测试根据行键删除行
//        String[] rowkeys = {"rowkey002", "rowkey003"};
//        hBaseOperations.deleteRowsByRowkeys("table0715", rowkeys);


        //测试建表
//        hBaseOperations.createTable("table0720", "usr");

        //测试添加列族名
//                String[] cfNames = {"co1", "co2"};
//                hBaseOperations.addNewColumnFamilies("table0715", cfNames);


        //测试显示列族
//        hBaseOperations.showColumnFamilies("table0720");

        //测试显示所有记录
                queryFromHBase.queryAll("table0715");
        //测试根据行键查询
//        queryFromHBase.queryByRowkey("table0715", "rowkey001");
        //测试根据列值进行严格匹配查询
//        queryFromHBase.queryByColumnValue("table0715","usr","name","name-003");

    }

}
