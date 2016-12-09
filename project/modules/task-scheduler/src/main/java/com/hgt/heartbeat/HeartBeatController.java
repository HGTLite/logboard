package com.hgt.heartbeat;

import com.hgt.entity.LogBeatsNormal;
import com.hgt.mapper.LogBeatsNormalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * README:
 * Created by Yao on 12/9/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class HeartBeatController {

    @Autowired
    LogBeatsNormalMapper logBeatsNormalMapper;

    public int storeToMySQL(LogBeatsNormal normal) {

        int count = logBeatsNormalMapper.insert(normal);

        return 0;
    }

}
