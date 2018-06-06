package com.xinran.dkelianxi;

import android.app.Activity;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by houqixin on 2018/2/12.
 */

public class QXThirdLoginManager {

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {



        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {


        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };

    /**
     * 最后在登录所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，如果在fragment中调用登录，在fragment依赖的Activity中实现，如果不实现onActivityResult方法，会导致登录或回调无法正常进行

     onActivityResult实现方法如下：

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

     }

     登录成功后，第三方平台会将用户资料传回， 全部会在Map data中返回 ，由于各个平台对于用户资料的标识不同，因此为了便于开发者使用，我们将一些常用的字段做了统一封装，开发者可以直接获取，不再需要对不同平台的不同字段名做转换，这里列出我们封装的字段及含义

     UShare封装后字段名	QQ原始字段名	微信原始字段名	新浪原始字段名	字段含义	备注
     uid	openid	unionid	id	用户唯一标识	如果需要做跨APP用户打通，QQ需要使用unionID实现
     name	screen_name	screen_name	screen_name	用户昵称
     gender	gender	gender	gender	用户性别	该字段会直接返回男女
     iconurl	profile_image_url	profile_image_url	profile_image_url	用户头像

     1: http://dev.umeng.com/social/android/login-page#1

     * @param activity
     * @param umAuthListener
     */

    public static void weiXinLogin(Activity activity,UMAuthListener umAuthListener){
        //获取用户资料
       QXApplication.mUMShareAPI.getPlatformInfo(activity,SHARE_MEDIA.WEIXIN,umAuthListener);

        //第三方授权
        QXApplication.mUMShareAPI.deleteOauth(activity,SHARE_MEDIA.WEIXIN,umAuthListener);
    }
}
