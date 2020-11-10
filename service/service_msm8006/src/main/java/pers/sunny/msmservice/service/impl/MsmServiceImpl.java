package pers.sunny.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.sunny.msmservice.service.MsmService;

import java.util.Map;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-09-19:27
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, Map<String, Object> param) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "阿里云key", "阿里云秘钥");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //申请阿里云 签名名称
        request.putQueryParameter("SignName", "好多谷粒在线教育网站");
        //申请阿里云 模板code
        request.putQueryParameter("TemplateCode", "SMS_204275336");
        //验证码数据，转换json数据传递
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
