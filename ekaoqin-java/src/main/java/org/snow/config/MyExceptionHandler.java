package org.snow.config;


import org.springframework.web.bind.annotation.ControllerAdvice;

//全局异常捕捉处理，可定制多个捕捉器
@ControllerAdvice
public class MyExceptionHandler {

    //例子
    //@ExceptionHandler(value = Exception.class)
    //public void errorHandler(Exception ex, HttpServletResponse response) throws IOException {
    //    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "custom:"+ex.getMessage());
    //}


}
