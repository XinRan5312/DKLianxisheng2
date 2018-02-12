package com.xinran.sharelib;

/**
 * Created by houqixin on 2018/2/12.
 * 自定义分享面板 添加生成图片按钮
 */
public class QXShareButton {

    public String platformName;
    public QX_SHARE_MEDIA SHARE_MEDIA;
    public String share_icon_normal_res_name;
    public String share_icon_pressed_res_name;

    private QXShareButton() {}

    /**
     * 分享按钮
     * @param platformName 分享按钮中文名称，即显示名称
     * @param SHARE_MEDIA 分享平台{@link QX_SHARE_MEDIA}
     * @param share_icon_normal_res_name 分享图标正常状态的资源名称
     * @param share_icon_pressed_res_name 分享图标按下状态的资源名称
     */
    public QXShareButton(String platformName, QX_SHARE_MEDIA SHARE_MEDIA, String share_icon_normal_res_name, String share_icon_pressed_res_name) {
        this.platformName = platformName;
        this.SHARE_MEDIA = SHARE_MEDIA;
        this.share_icon_normal_res_name = share_icon_normal_res_name;
        this.share_icon_pressed_res_name = share_icon_pressed_res_name;
    }
}
