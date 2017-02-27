package com.bins.annotation.loginin;

/**
 * @author zhaozhou
 * @Description 登录注解枚举
 *  NO:需登录校验;YES:无需登录校验；OPTIONAL；可选，即传token校验，不传就不作校验
 * @date 2017/2/24 19:08
 */
public enum NeedLogInEnum {
    NO, YES, OPTIONAL
}