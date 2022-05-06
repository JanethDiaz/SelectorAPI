package com.danfoss.api.Utils;


import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {

    /**
     * Obtener el contexto http
     * */
    public static HttpServletRequest getHttpServletRequest() {

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            ServletRequestAttributes servlet = (ServletRequestAttributes) attributes;
            return servlet.getRequest();
        }
        return null;
    }

}

