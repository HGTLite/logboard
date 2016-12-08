/**
 * 打印对象
 */
function writeObj(obj) {
    var description = "";
    for (var i in obj) {
        var property = obj[i];
        description += i + " = " + property + "\n";
    }
    alert(description);
}
/**
 * 对象转字符串
 * @param o
 * @returns {*}
 */
function obj2string(o) {
    var r = [];
    if (typeof o == "string") {
        return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
    }
    if (typeof o == "object") {
        if (!o.sort) {
            for (var i in o) {
                r.push(i + ":" + obj2string(o[i]));
            }
            if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                r.push("toString:" + o.toString.toString());
            }
            r = "{" + r.join() + "}";
        } else {
            for (var i = 0; i < o.length; i++) {
                r.push(obj2string(o[i]))
            }
            r = "[" + r.join() + "]";
        }
        return r;
    }
    return o.toString();
}
/**
 * 克隆对象
 * @param obj
 * @returns {*}
 */
function cloneObj(obj) {
    var o, i, j, k;
    if (typeof(obj) != "object" || obj === null)return obj;
    if (obj instanceof (Array)) {
        o = [];
        i = 0;
        j = obj.length;
        for (; i < j; i++) {
            if (typeof(obj[i]) == "object" && obj[i] != null) {
                o[i] = arguments.callee(obj[i]);
            }
            else {
                o[i] = obj[i];
            }
        }
    }
    else {
        o = {};
        for (i in obj) {
            if (typeof(obj[i]) == "object" && obj[i] != null) {
                o[i] = arguments.callee(obj[i]);
            }
            else {
                o[i] = obj[i];
            }
        }
    }

    return o;
}


/**
 * 将js的时间对象转换为字符串yyyy-MM-dd HH:ss:mm
 * @param jsTime
 * @returns {string}
 */
function convertJsTimeToStr(jsTime) {

    var year = jsTime.getFullYear();
    var month = jsTime.getMonth() + 1;
    var day = jsTime.getDate();
    var hour = jsTime.getHours();
    var minute = jsTime.getMinutes();
    var second = jsTime.getSeconds();

    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minute < 10) {
        minute = "0" + minute;
    }
    if (second < 10) {
        second = "0" + second;
    }
    var strTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    return strTime;
}


/**
 * 将字符串转换为js的时间对象,字符串格式yyyy-MM-dd HH:ss:mm
 * @param timeStr
 * @returns {Date}
 * @private
 */
function convertStrToJsTime(timeStr) {

    var y = timeStr.substring(0, 4);
    var m = timeStr.substring(5, 7) - 1;
    var d = timeStr.substring(8, 10);
    var h = timeStr.substring(11, 13);
    var mm = timeStr.substring(14, 16);
    var ss = timeStr.substring(17, 19);
    var time = new Date(y, m, d, h, mm, ss, 0);
    return time;

}

function operateDatetimeByHour(strBaseDate, delta) {

    var baseDate = convertStrToJsTime(strBaseDate);
    // console.log("转换后的baseDate：");
    // console.log(baseDate);
    var postDate=new Date();
     postDate.setTime(baseDate.getTime() + delta * 60 * 60 * 1000);
    return convertJsTimeToStr(postDate);

}
/**
 * 判断对象为空的方法
 * @param obj
 * @returns {boolean}
 */
function isEmpty(obj)
{
    for (var name
        in obj)
    {
        return false;
    }
    return true;
};