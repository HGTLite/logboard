package com.hgt.ienum;

import java.util.EnumMap;
import java.util.Map;

public class TESTEnumMap {

    public static void main(String[] args) {
        EnumMap<ErrMsgEnum, String> errMsgMap = new EnumMap<>(ErrMsgEnum.class);

        errMsgMap.put(ErrMsgEnum.required_item_id, "2,4,6");
        errMsgMap.put(ErrMsgEnum.invalid_app_id, "1,7");
        errMsgMap.put(ErrMsgEnum.invalid_date, "8");

        for (Map.Entry<ErrMsgEnum, String> entry : errMsgMap.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey().getValue()); //2,4,6 商品id为必填项
//            System.out.println(entry.getKey() + " " + entry.getKey().getValue());//required_item_id 商品id为必填项
        }
    }
}