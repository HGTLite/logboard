package com.hgt.test;

import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * DemoController
 */
@Controller
@RequestMapping(value = "/demo")
public class TESTSwaggerController2 {

    private Logger logger = LoggerFactory.getLogger(TESTSwaggerController2.class);

    /**
     * 可以直接使用@ResponseBody响应JSON
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcount", method = RequestMethod.POST)
    @ApiOperation(value = "测试-getCount", notes = "getCount更多说明")
    public ModelMap getCount(HttpServletRequest request,
                             HttpServletResponse response) {
        logger.info(">>>>>>>> begin getCount >>>>>>>>");
        ModelMap map = new ModelMap();
        map.addAttribute("count", 158);

        // 后台获取的国际化信息
        map.addAttribute("xstest", "测试");
        return map;
    }

    /**
     * 可以直接使用@ResponseBody响应JSON
     *
     * @param request
     * @param response
     * @return
     */
    @ApiIgnore//使用该注解忽略这个API
    @ResponseBody
    @RequestMapping(value = "/jsonTest1", method = RequestMethod.POST)
    public ModelMap jsonTest(HttpServletRequest request,
                             HttpServletResponse response) {
        ModelMap map = new ModelMap();
        map.addAttribute("hello", "你好");
        map.addAttribute("veryGood", "很好");

        return map;
    }

    /**
     * 可以直接使用@ResponseBody响应JSON
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/jsonTest3", method = RequestMethod.POST)
    public List<String> jsonTest3(HttpServletRequest request,
                                  HttpServletResponse response) {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("你好");
        return list;
    }

    /**
     * JSON请求一个对象<br/>
     * （Ajax Post Data：{"name":"名称","content":"内容"}）
     *
     * @param
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/jsonTest2", method = RequestMethod.POST)
//    public ModelMap jsonTest2(@RequestBody Demo demo) {
//        logger.info("demoName：" + demo.getName());
//        logger.info("demoContent：" + demo.getContent());
//        ModelMap map = new ModelMap();
//        map.addAttribute("result", "ok");
//        return map;
//    }

    /**
     * 直接读取URL参数值<br/>
     * /demo/jsonTest6.do?name=Hello&content=World
     *
     * @param demoName
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/jsonTest6", method = RequestMethod.POST)
    public ModelMap jsonTest6(@RequestParam("name") String demoName, @RequestParam String content) {
        logger.info("demoName：" + demoName);
        ModelMap map = new ModelMap();
        map.addAttribute("name", demoName + "AAA");
        map.addAttribute("content", content + "BBB");
        map.addAttribute("date", new java.util.Date());
        return map;
    }

    /**
     * JSON请求一个对象，将RequestBody自动转换为JSONObject对象<br/>
     * （Ajax Post Data：{"name":"名称","content":"内容"}）
     * <p>
     * 使用JSONObject请添加依赖
     * <dependency>
     * <groupId>net.sf.json-lib</groupId>
     * <artifactId>json-lib</artifactId>
     * <version>2.4</version>
     * <!--指定jdk版本 -->
     * <classifier>jdk15</classifier>
     * </dependency>
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/jsonTest5", method = RequestMethod.POST)
    public ModelMap jsonTest5(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        logger.info("demoName：" + name);
        ModelMap map = new ModelMap();
        map.addAttribute("demoName", name);
        return map;
    }


//    /**
//     * 输入 和输出为JSON格式的数据的方式 HttpEntity<?> ResponseEntity<?>
//     *
//     * @param u
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/jsonTest4", method = RequestMethod.POST)
//    public ResponseEntity<String> jsonTest4(HttpEntity<Demo> demo,
//                                            HttpServletRequest request, HttpSession session) {
//        //获取Headers方法
//        HttpHeaders headers = demo.getHeaders();
//
//        // 获取内容
//        String demoContent = demo.getBody().getContent();
//
//        // 这里直接new一个对象（HttpHeaders headers = new HttpHeaders();）
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.add("MyHeaderName", "SHANHY");
//
//        ResponseEntity<String> responseResult = new ResponseEntity<String>(
//                demoContent, responseHeaders, HttpStatus.OK);
//        return responseResult;
//    }

}