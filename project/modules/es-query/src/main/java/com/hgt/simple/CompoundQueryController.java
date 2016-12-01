package com.hgt.simple;

import com.hgt.es.common.ESCompoundQuery;
import com.hgt.es.config.ESConfig;
import com.hgt.es.tools.ESLogBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * README: 组合查询
 * Created by Yao on 12/1/16.
 * ==============================================================================
 * CHANGELOG:
 */
@RestController
public class CompoundQueryController {

    private final String BASE_URL = "logb/q";

    String esCname = "es-yao";
    String esChost = "192.168.99.140:9300,192.168.99.141:9300";

    @RequestMapping(value = BASE_URL + "/docid/{strIndex}/{strType}/{strId}", method = RequestMethod.GET)
    public ESLogBean queryDocById(@PathVariable String strIndex, @PathVariable String strType, @PathVariable String strId) {
        ESLogBean resultBean = new ESLogBean();
        ESConfig esConfig = new ESConfig(esCname, esChost);
        ESCompoundQuery esCompoundQuery = new ESCompoundQuery(esConfig);
        resultBean = esCompoundQuery.queryDocById(strIndex, strType, strId);
        return resultBean;
    }

    @RequestMapping(value = BASE_URL + "/s/{strIndex}/{strType}/{strWord}", method = RequestMethod.GET)
    public List<ESLogBean> executeQuery(@PathVariable String strIndex, @PathVariable String strType, @PathVariable String strWord) {

        List<ESLogBean> resultList = new LinkedList<ESLogBean>();
        ESLogBean resultBean = new ESLogBean();
        ESConfig esConfig = new ESConfig(esCname, esChost);
        ESCompoundQuery esCompoundQuery = new ESCompoundQuery(esConfig);
        resultList = esCompoundQuery.simpleStringQuery(strIndex, strType, strWord);
        return resultList;
    }

    @RequestMapping(value = BASE_URL + "/s/{strIndex}/{strType}/{strWord}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public List<ESLogBean> executeQuery(@PathVariable String strIndex, @PathVariable String strType, @PathVariable String strWord, @PathVariable int pageNum, @PathVariable int pageSize) {

        List<ESLogBean> resultList = new LinkedList<ESLogBean>();
        ESLogBean resultBean = new ESLogBean();
        ESConfig esConfig = new ESConfig(esCname, esChost);
        ESCompoundQuery esCompoundQuery = new ESCompoundQuery(esConfig);
        resultList = esCompoundQuery.simpleStringQueryByPages(strIndex, strType, strWord, pageNum, pageSize);
        return resultList;
    }

}
