package com.hgt.single;

import com.hgt.es.common.ESAdminOperations;
import com.hgt.es.common.QueryFromES;
import com.hgt.es.config.ESConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * README:
 * Created by Yao on 11/28/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class GeneralQuery {

    private final String BASE_URL = "logb/es";

    @RequestMapping(value = BASE_URL+"/all",method = RequestMethod.GET)
    public String getAllByIndex() {
        String allDocs = "";
        ESConfig esConfig = new ESConfig("es-yao", "192.168.99.140:9300,192.168.99.141:9300");
        QueryFromES queryFromES = new QueryFromES(esConfig);
        allDocs=queryFromES.queryAllByType("one-index", "one-type");
        queryFromES.close();
        return allDocs;
    }

}
