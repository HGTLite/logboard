package com.hgt.hbase.common;

import com.hgt.hbase.config.HBaseConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:hbase查询类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class QueryFromHBase {

    public Configuration configuration;

    public QueryFromHBase() {
        configuration = new HBaseConfig().createHBaseConfiguration();
    }

    /**
     * 根据行键查询一行
     *
     * @param strTableName
     * @param strRowkey
     */
    public void queryByRowkey(String strTableName, String strRowkey) {

        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(tableName);

            Get scan = new Get(strRowkey.getBytes());
            Result result = table.get(scan);
            System.out.println("rowkey是: " + new String(result.getRow()));

            for (Cell rowKV : result.rawCells()) {
//                System.out.print("行键是: " + new String(CellUtil.cloneRow(rowKV)) + " ");
                System.out.print("列族名: " + new String(CellUtil.cloneFamily(rowKV)) + " ");
                System.out.print("列名是:  " + new String(CellUtil.cloneQualifier(rowKV)) + " ");
                System.out.print("列值是: " + new String(CellUtil.cloneValue(rowKV)) + " ");
                System.out.println("时间戳: " + rowKV.getTimestamp() + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据列值进行严格查询     *
     *
     * @param strTableName
     * @param strCF
     * @param strQualifier
     * @param strValue
     */
    public void queryByColumnValue(String strTableName, String strCF, String strQualifier, String strValue) {
        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(tableName);
            // 当列column1的值为aaa时进行查询
            Filter filter = new SingleColumnValueFilter(strCF.getBytes(), strQualifier.getBytes(), CompareFilter.CompareOp.EQUAL, strValue.getBytes());
            Scan s = new Scan();
            s.setFilter(filter);
            ResultScanner rs = table.getScanner(s);

            for (Result result : rs) {
                for (Cell rowKV : result.rawCells()) {
//                    System.out.print("行键是: " + new String(CellUtil.cloneRow(rowKV)) + " ");
                    System.out.print("列族名: " + new String(CellUtil.cloneFamily(rowKV)) + " ");
                    System.out.print("列名是:  " + new String(CellUtil.cloneQualifier(rowKV)) + " ");
                    System.out.print("列值是: " + new String(CellUtil.cloneValue(rowKV)) + " ");
//                    System.out.println("时间戳: " + rowKV.getTimestamp() + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据值对多个列进行查询
     *
     * @param strTableName
     * @param strCFs
     * @param strQualifers
     * @param strValues
     */
    public void queryByMultiColumns(String strTableName, String[] strCFs, String[] strQualifers, String[] strValues) {
        TableName tableName = TableName.valueOf(strTableName);

        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(tableName);

            List<Filter> filters = new ArrayList<Filter>();

            for (int i = 0; i < strQualifers.length; i++) {
                Filter f = new SingleColumnValueFilter(strCFs[i].getBytes(), strQualifers[i].getBytes(), CompareFilter.CompareOp.EQUAL, strValues[i].getBytes());
                filters.add(f);
            }

            FilterList filterList = new FilterList(filters);

            Scan scan = new Scan();
            scan.setFilter(filterList);
            ResultScanner rs = table.getScanner(scan);

            for (Result result : rs) {
                for (Cell rowKV : result.rawCells()) {
                    System.out.print("行键是: " + new String(CellUtil.cloneRow(rowKV)) + " ");
                    System.out.print("列族名: " + new String(CellUtil.cloneFamily(rowKV)) + " ");
                    System.out.print("列名是:  " + new String(CellUtil.cloneQualifier(rowKV)) + " ");
                    System.out.print("列值为: " + new String(CellUtil.cloneValue(rowKV)) + " ");
                    System.out.println("时间戳: " + rowKV.getTimestamp() + " ");
                }
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 打印表所有行的方法，本质是scan
     *
     * @param strTableName
     */
    public void queryAll(String strTableName) {
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(TableName.valueOf(strTableName));
            ResultScanner rs = table.getScanner(new Scan());
            //TO-DO:表为空时要提示
            for (Result result : rs) {
                for (Cell rowKV : result.rawCells()) {
                    System.out.print("行键为: " + new String(CellUtil.cloneRow(rowKV)) + " ");
                    System.out.print("列族名: " + new String(CellUtil.cloneFamily(rowKV)) + " ");
                    System.out.print("列名是:  " + new String(CellUtil.cloneQualifier(rowKV)) + " ");
                    System.out.print("列值是: " + new String(CellUtil.cloneValue(rowKV)) + " ");
                    System.out.println("时间戳: " + rowKV.getTimestamp() + " ");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
