package com.hgt.simple;

import com.hgt.es.common.ESQueryAll;
import com.hgt.es.config.ESConfig;
import com.hgt.es.tools.ESLogBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * README:
 * Created by Yao on 11/28/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class QueryAllController {

    private final String BASE_URL = "logb/q";

    String esCname = "es-yao";
    String esChost = "192.168.99.140:9300,192.168.99.141:9300";

    // TODO: 改成java批量查询接口 
    @RequestMapping(value = BASE_URL + "/all/{strIndex}/{strType}", method = RequestMethod.GET)
    public List<ESLogBean> queryAllByType(@PathVariable String strIndex, @PathVariable String strType) {
        List<ESLogBean> docList = new LinkedList<>();
        ESConfig esConfig = new ESConfig(esCname, esChost);
        ESQueryAll queryFromES = new ESQueryAll(esConfig);
        docList = queryFromES.queryAllByType(strIndex, strType);
        queryFromES.close();
        return docList;
    }

    @RequestMapping(value = BASE_URL + "/all/{strIndex}/{strType}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public List<ESLogBean> queryAllByTypeByPages(@PathVariable String strIndex, @PathVariable String strType, @PathVariable int pageNum, @PathVariable int pageSize) {
        List<ESLogBean> docList = new LinkedList<>();
        ESConfig esConfig = new ESConfig(esCname, esChost);
        ESQueryAll queryFromES = new ESQueryAll(esConfig);
        docList = queryFromES.queryAllByTypeByPages(strIndex, strType, pageNum, pageSize);
        queryFromES.close();
        return docList;
    }





}
