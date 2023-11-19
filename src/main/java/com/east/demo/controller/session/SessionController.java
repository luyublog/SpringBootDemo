package com.east.demo.controller.session;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Session管理
 * <a href="https://blog.csdn.net/qq_35387940/article/details/84388644">使用参考</a>
 *
 * @author: east
 * @date: 2023/11/19
 */

@RestController
@Api(tags = "Session管理")
@Slf4j
public class SessionController {
    @GetMapping("/setSessionValue")
    @ApiOperation("获取session")
    public String setRedisResult(HttpServletRequest request) {
        request.getSession().setAttribute(request.getSession().getId(), "---测试数据---" + request.getRequestURL());

        System.out.println(request.getSession().getId());
        return "set成功，已经存入session域且redis里面也会有值";
    }

    @GetMapping("/getSessionValue")
    @ApiOperation("设置session")
    public String redisResult(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        String value = String.valueOf(request.getSession().getAttribute(request.getSession().getId()));

        return "取值成功         :" + value;
    }
}
