package com.hgt.logger.validator;

import java.util.ArrayList;

import static com.hgt.logger.validator.AppInfoValidator.isAppCodeValid;

/******************************************************************************
 * Created by  Yao  on  2016/9/27
 * README: 验证所有的logger输入信息格式是否都正确，集合所有规则，可扩展
 *         暂时只实现验证AppCode，作为示例
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class LoggerInputsValidator {

    //日志参数
    private ArrayList<String> inputs;

    /**
     * 构造函数
     *
     * @param loginfo
     */
    public LoggerInputsValidator(ArrayList<String> loginfo) {
        this.inputs = loginfo;
    }

    /**
     * 整合所有单项验证信息
     *
     * @return 最终有效结果
     */
    public boolean isLogValid() {

        boolean ret = false;

        if (isAppCodeValid(inputs.get(0))) {
            ret = true;
        }

        return ret;
    }

}
