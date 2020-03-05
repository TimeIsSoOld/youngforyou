package com.example.youngforyou.config;

import com.example.youngforyou.entity.model.UmsAdmin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        try {
//            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
//            UmsAdmin user=(UmsAdmin)request.getSession().getAttribute("USER");
//            if(user!=null){
//                return true;
//            }
//            response.sendRedirect(request.getContextPath()+"你的登陆页地址");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return true;
    }


}
