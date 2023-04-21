package com.shen.service;

import com.shen.controller.Weathercontroller;
import com.shen.pojo.Cast;
import com.shen.pojo.Forecast;
import com.shen.pojo.User;
import com.shen.pojo.WeatherDTO;
import com.shen.utils.ConstantSmsUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class SendSmsService {
    @Resource
    Weathercontroller weathercontroller;
    @Resource
    UserService userService;


  public Boolean sendSms(String phone){

        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(ConstantSmsUtils.SECRET_ID, ConstantSmsUtils.SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象l
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {phone};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId(ConstantSmsUtils.SMSSDKAPP_ID);
            req.setSignName(ConstantSmsUtils.SIGN_NAME);
            req.setTemplateId(ConstantSmsUtils.TEMPLATE_ID);
            User user = userService.selectOneByPhone(phone);
            WeatherDTO weatherDTO = weathercontroller.selectWeacher(user.getCity());
            ArrayList<Forecast> forecasts = weatherDTO.getForecasts();
            Forecast weath = forecasts.get(0);
            ArrayList<Cast> casts = weath.getCasts();
            Cast cast = casts.get(0);
            String[] templateParamSet1 = {user.getUser_name(), cast.getDayweather(), weath.getCity(), cast.getNighttemp(), cast.getDaytemp(), cast.getDaypower() + "级" + cast.getDaywind() + "风" +
                    ""};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
            return true;
        } catch (TencentCloudSDKException | IOException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
