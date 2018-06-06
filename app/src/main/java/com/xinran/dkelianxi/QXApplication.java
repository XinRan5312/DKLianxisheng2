package com.xinran.dkelianxi;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by houqixin on 2018/2/12.
 */

public class QXApplication extends Application{
   public static UMShareAPI mUMShareAPI;
    {
        // 友盟
        PlatformConfig.setWeixin("wx9d74c515795bb7a4", "fd3971ec2c3b2f53e52dc9bb351d182e");
        PlatformConfig.setQQZone("1106439769", "TGaRcCIGGfef6xI9");
        PlatformConfig.setSinaWeibo("2594271520", "ea4a3dcf07c70ad7d9dea9454ff34b79", "http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
       mUMShareAPI= UMShareAPI.get(this);
    }
}
