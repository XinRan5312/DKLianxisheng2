package com.xinran.sharelib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;

/**
 * Created by houqixin on 2018/2/12.
 */

public class QXShareManager {
    /**
     *
     * @param activity
     * @param resId 图片资源ID
     */
    public static void shareWithMediaImg(final Activity activity, @IdRes int resId) {
        UMImage umImage=new UMImage(activity,resId);
        QXShareUtil.shareWithMediaImg(activity,umImage);
    }

    /**
     *
     * @param activity
     * @param imgUrl 网络图片url
     */
    public static void shareWithMediaImg(final Activity activity, @NonNull String imgUrl) {
        UMImage umImage=new UMImage(activity,imgUrl);

        QXShareUtil.shareWithMediaImg(activity,umImage);
    }

    /**
     *
     * @param activity
     * @param file 本地图片file
     */
    public static void shareWithMediaImg(final Activity activity, @NonNull File file) {
        UMImage umImage=new UMImage(activity,file);

        QXShareUtil.shareWithMediaImg(activity,umImage);
    }

    /**
     *
     * @param activity
     * @param bitmap 图片bimap
     */
    public static void shareWithMediaImg(final Activity activity, @NonNull Bitmap bitmap) {
        UMImage umImage=new UMImage(activity,bitmap);

        QXShareUtil.shareWithMediaImg(activity,umImage);
    }

    public static void shareWithMediaWeb(final Activity activity,@NonNull ShareBean bean,QXShareListener shareListener) {
        UMImage umImage=null;
       if(bean.shareTag!=null){
           umImage=new UMImage(activity,bean.shareTag);
       }else if(bean.shareTagId!=-1){
           umImage=new UMImage(activity,bean.shareTag);
       }else if(bean.shareTagFile!=null){
           umImage=new UMImage(activity,bean.shareTagFile);
       }else{
           throw new QXShareException("参数有误!");
       }
        UMWeb umWeb=new UMWeb(bean.ShareUrl);
        umWeb.setTitle(checkString(bean.title));
        umWeb.setDescription(checkString(bean.description));
        umWeb.setThumb(umImage);

        QXShareUtil.shareWeb(activity,umWeb,shareListener);
    }
    public static void shareWithMediaWeb(final Activity activity,@NonNull ShareBean bean){
        shareWithMediaWeb(activity,bean,null);
    }
    public static void shareWithMediaWebWithButton(final Activity activity,@NonNull ShareBean bean,QXShareListener shareListener){
        UMImage umImage=null;
        if(bean.shareTag!=null){
            umImage=new UMImage(activity,bean.shareTag);
        }else if(bean.shareTagId!=-1){
            umImage=new UMImage(activity,bean.shareTag);
        }else if(bean.shareTagFile!=null){
            umImage=new UMImage(activity,bean.shareTagFile);
        }else{
            throw new QXShareException("参数有误!");
        }
        UMWeb umWeb=new UMWeb(bean.ShareUrl);
        umWeb.setTitle(checkString(bean.title));
        umWeb.setDescription(checkString(bean.description));
        umWeb.setThumb(umImage);

        QXShareUtil.shareWebWithCustomButton(activity,umWeb,shareListener);

    }
    public static void shareWithMediaWebWithButton(final Activity activity,@NonNull ShareBean bean){
        shareWithMediaWebWithButton(activity,bean,null);
    }
    private static String checkString(String str){
        if(str==null)throw new QXShareException("参数有误!");
        return str;
    }
}
