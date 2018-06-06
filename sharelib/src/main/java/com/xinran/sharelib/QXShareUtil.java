package com.xinran.sharelib;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.BaseMediaObject;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houqixin on 2018/2/12.
 * 外观工具类
 */

public class QXShareUtil {
    /**
     * 分享图片
     * @param activity
     */
    protected static void shareWithMediaImg(final Activity activity, UMImage img) {
        new ShareAction(activity)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withMedia(img)
                .setCallback(createUMShareListener(activity))
                .open();
    }

    /**
     * 分享链接
     * @param activity 当前Activity
     * @param umWeb {@link UMWeb}
     */
    protected static void shareWeb(Activity activity, UMWeb umWeb) {
        share(activity, umWeb, null,null);
    }

    /**
     * 分享链接
     * @param activity 当前Activity
     * @param umWeb {@link UMWeb}
     * @param qxShareListener 自定义分享回调
     */
    protected static void shareWeb(final Activity activity, UMWeb umWeb, QXShareListener qxShareListener) {
        share(activity, umWeb, qxShareListener, null);
    }
    /**
     * 分享链接
     * @param activity 当前Activity
     * @param umWeb {@link UMWeb}
     * @param qxShareListener 自定义分享回调
     */
    protected static void shareWebWithCustomButton(final Activity activity, UMWeb umWeb, QXShareListener qxShareListener) {
        // 后两个参数是图标资源名称，如果资源名称改了一定要记得改
        QXShareButton imageButton = new QXShareButton(QXPlatformName.IMAGE, QX_SHARE_MEDIA.IMAGE, "umeng_socialize_image", "umeng_socialize_image");
        share(activity, umWeb, qxShareListener, imageButton);
    }

    /**
     * 分享图片
     * @param activity 当前Activity
     * @param umImage {@link UMImage}
     */
    protected static void shareImage(Activity activity, UMImage umImage) {
        shareImage(activity, umImage, null);
    }

    /**
     * 分享图片
     * @param activity 当前Activity
     * @param umImage {@link UMImage}
     * @param qxShareListener 自定义分享回调
     */
    protected static void shareImage(Activity activity, UMImage umImage, QXShareListener qxShareListener) {
        share(activity, umImage, qxShareListener);
    }

    private static void share(Activity activity, BaseMediaObject mediaObject, QXShareListener qxShareListener, QXShareButton... shareButtons) {
        ShareAction shareAction = new ShareAction(activity);
        shareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA);
        if (shareButtons != null) {
            for (QXShareButton sb : shareButtons) {
                // 下面对addButton进行一下解释：http://dev.umeng.com/social/android/quick-integration#2_5_1
                // 第一个参数（umeng_sharebutton_custom）和第二个参数（umeng_sharebutton_custom）
                // 第一个参数是显示的名字，第二个参数是平台名称，用法如下： 在string文件中设置:
                // <string name="umeng_sharebutton_custom">自定义分享按钮</string>\
                // 然后将名字umeng_sharebutton_custom当做参数传入。
                // 第三个参数（info_icon_1）和第四个参数（info_icon_1）： 第三个参数是按钮图标的图片名字，
                // 第四个按钮是置灰情况下按钮图标的图片名字，例如，你在drawable文件夹下有个图片叫做info_icon_1.png，想用该图片做按钮图标，可以将info_icon_1作为参数传入
                shareAction.addButton(sb.platformName, sb.SHARE_MEDIA.getName(), sb.share_icon_normal_res_name, sb.share_icon_pressed_res_name);
            }
        }
        shareAction.setShareboardclickCallback(createShareBoardlistener(activity, mediaObject, qxShareListener));
        shareAction.open();
    }

    /**
     * 分享面板
     */
    private static ShareBoardlistener createShareBoardlistener(final Activity activity, final BaseMediaObject mediaObject, final QXShareListener qxShareListener) {
        return new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                if (share_media == null){
                    //根据key来区分自定义按钮的类型，并进行对应的操作
                    for (QX_SHARE_MEDIA media : QX_SHARE_MEDIA.values()) {
                        if (media.getName().equals(snsPlatform.mKeyword)) {
//                            Toast.makeText(activity, DK_SHARE_MEDIA.IMAGE.getName(), Toast.LENGTH_SHORT)ow();
                            if (qxShareListener != null) {
                                qxShareListener.onClick(media);
                            }
                            break;
                        }
                    }
                }
                else { //社交平台的分享行为
                    ShareAction shareAction = new ShareAction(activity);
                    shareAction.setPlatform(share_media);
                    if (mediaObject != null) {
                        if (mediaObject instanceof UMWeb) {
                            shareAction.withMedia((UMWeb) mediaObject);
                        } else if (mediaObject instanceof UMImage) {
                            shareAction.withMedia((UMImage) mediaObject);
                        }
                    }
                    shareAction
                            .setCallback(createUMShareListener(activity))
                            .share();
                }
            }
        };
    }

    /**
     * 分享回调
     *
     * @param activity 当前Activity
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    protected static UMShareListener createUMShareListener(final Activity activity) {
        return new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                Map<String, String> map = new HashMap<>();
                map.put("platform", platform.toString());
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(activity, activity.getString(R.string.share_success), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Log.e("share error: ", t + "");
                QXShareUtil.handlePlatformNotInstalled(activity, platform);
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {

            }
        };
    }

    /**
     * 处理微信QQ等客户端未安装的情况
     *
     * @param activity 当前Activity
     * @param platform 分享平台
     */
    protected static boolean handlePlatformNotInstalled(Activity activity, SHARE_MEDIA platform) {
        if (!UMShareAPI.get(activity).isInstall(activity, platform)) {
            String appName = "";
            switch (platform) {
                case WEIXIN:
                case WEIXIN_CIRCLE:
                    appName = activity.getString(R.string.share_weixin);
                    break;
                case QQ:
//                case QZONE: // QQ空间有独立的App，包名跟QQ不一样，所以这里暂不提示了
                    appName = activity.getString(R.string.share_qq);
                    break;
            }
            Toast.makeText(activity, String.format(activity.getString(R.string.share_not_installed), appName), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * 退出登录建议取消微信登录授权，否则再点微信登录时不会弹出微信选框，不能切换微信
     *
     * @param activity 当前Activity
     */
    protected static void deleteWeixinAuth(Activity activity) {
        UMShareAPI.get(activity).deleteOauth(activity, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, Map<String, String> map) {
                Log.d("退出登录时删除微信授权 -> ", status + ", " + map);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int status, Throwable throwable) {
                Log.e("退出登录时删除微信授权 -> ", status + ", " + throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int status) {

            }
        });
    }

}
