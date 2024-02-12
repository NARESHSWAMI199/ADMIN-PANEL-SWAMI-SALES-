package com.sales.interceptors;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.admin.repositories.UserRepository;
import com.sales.dto.ErrorDto;
import com.sales.entities.User;
import com.sales.jwtUtils.JwtToken;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SalesInterceptor implements HandlerInterceptor {


    JwtToken jwtToken;
    UserRepository userRepository;


    public SalesInterceptor(JwtToken jwtToken, UserRepository userRepository){
        this.jwtToken = jwtToken;
        this.userRepository = userRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && (token.substring(0, 7)).equals("Bearer ")) {
            token = token.substring(7, token.length());
            String slug = jwtToken.getSlugFromToken(token);
            /** get user by slug. */
            User user = userRepository.findUserBySlug(slug);
            if (user.getIsDeleted().equals("Y")) {
                sendError(response,"User is not found.",401);
                return false;
            } else if (user.getStatus().equals("D")) {
                sendError(response,"User is not active.",401);
                return false;
            }
            request.setAttribute("user",user);
            return true;
        }
        sendError(response,"Invalid authorization.",401);
        return false;
    }

    public void sendError(HttpServletResponse response ,String message, Integer status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(status);
        ErrorDto error = new ErrorDto(message,status);
        response.getWriter().write(mapper.writeValueAsString(error));
    }



}
