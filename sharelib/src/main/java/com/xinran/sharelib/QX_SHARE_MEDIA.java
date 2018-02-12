package com.xinran.sharelib;

/**
 * Created by houqixin on 2018/2/12.
 * 自定义分享面板 添加生成图片按钮
 */

public enum QX_SHARE_MEDIA {
    /**
     * 生成图片
     */
    IMAGE("IMAGE");

    private String name;

    QX_SHARE_MEDIA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
