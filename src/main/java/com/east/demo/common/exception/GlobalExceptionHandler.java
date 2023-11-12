package com.east.demo.common.exception;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.json.JSONUtil;
import com.east.demo.pojo.dto.base.resp.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;

/**
 * 全局异常捕捉处理
 *
 * @author: east
 * @date: 2023/10/8
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("未知异常", e);
        return new BaseResp(ErrorEnum.FAIL);
    }

    @ExceptionHandler(BaseException.class)
    public Object handleBaseException(BaseException e) {
        log.error("基本异常", e);
        return new BaseResp(ErrorEnum.FAIL.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(HeaderRespException.class)
    public ResponseEntity<String> handleHeaderRespException(HeaderRespException headerRespException) {
        log.error("特殊响应异常", headerRespException);
        BaseResp baseResp = new BaseResp(ErrorEnum.FAIL.getErrorCode(), headerRespException.getMsg());
        String encodedResp = URLEncodeUtil.encode(JSONUtil.toJsonStr(baseResp), StandardCharsets.UTF_8);
        return ResponseEntity.ok().header("json", encodedResp).contentType(MediaType.TEXT_PLAIN).build();
    }
}
