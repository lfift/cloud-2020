package com.ift.authorization;

/**
 * 授权类型
 *
 * @author 19870
 * @date 2021/2/24
 */
public interface AuthorizedGrantType {

    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 密码模式
     */
    String PASSWORD = "password";

    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 简化模式
     */
    String IMPLICIT = "implicit";

    /**
     * 刷新Token
     */
    String REFRESH_TOKEN = "refresh_token";


}
