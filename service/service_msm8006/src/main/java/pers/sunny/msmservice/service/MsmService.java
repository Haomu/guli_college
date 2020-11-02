package pers.sunny.msmservice.service;

import java.util.Map;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-09-19:26
 */
public interface MsmService {
    boolean send(String phone, Map<String, Object> param);
}
