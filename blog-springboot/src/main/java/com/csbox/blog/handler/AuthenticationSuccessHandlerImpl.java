package com.csbox.blog.handler;

import com.alibaba.fastjson.JSON;
import com.csbox.blog.dao.UserAuthDao;
import com.csbox.blog.dto.UserInfoDTO;
import com.csbox.blog.entity.UserAuth;
import com.csbox.blog.util.BeanCopyUtils;
import com.csbox.blog.util.UserUtils;
import com.csbox.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.csbox.blog.constant.CommonConst.APPLICATION_JSON;


/**
 * Spring Security 登录成功处理器
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final UserAuthDao userAuthDao;

    @Autowired
    public AuthenticationSuccessHandlerImpl(UserAuthDao userAuthDao) {
        this.userAuthDao = userAuthDao;
    }

    /**
     * 返回登录授权成功信息
     *
     * @param httpServletRequest http请求
     * @param httpServletResponse http响应
     * @param authentication 权限信息
     * @throws IOException IO异常
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 返回登录信息
        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserInfoDTO.class);
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok(userLoginDTO)));
        // 更新用户ip，最近登录时间
        updateUserInfo();
    }

    /**
     * 更新用户信息
     */
    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtils.getLoginUser().getId())
                .ipAddress(UserUtils.getLoginUser().getIpAddress())
                .ipSource(UserUtils.getLoginUser().getIpSource())
                .lastLoginTime(UserUtils.getLoginUser().getLastLoginTime())
                .build();
        userAuthDao.updateById(userAuth);
    }

}
