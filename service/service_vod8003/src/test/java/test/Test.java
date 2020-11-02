package test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import pers.sunny.vod.utils.ConstPropertiesUtils;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-16-22:13
 */
public class Test {
    public static void main(String[] args) throws Exception{
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "LTAI4FzsNoqYAMKC4NzPLqWs", "K7mqWlSvnr9n5KSoZfjGaSxuh5ddbE");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("0945710874a1464bb5134e06a022eccb");
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        String playAuth = response.getPlayAuth();
        System.out.println(playAuth);
    }
}
