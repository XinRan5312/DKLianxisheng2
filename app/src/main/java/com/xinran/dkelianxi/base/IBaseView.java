package com.xinran.dkelianxi.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IBaseView {
    /**
     * 显示进度中
     */
    void showLoading();

    /**
     * 隐藏进度
     */
    void DismissLoading();

    /**
     * 显示请求成功
     */
    void showSuccess();

    /**
     * 失败重试
     */
    void showFailed();

    /**
     * 显示当前网络不可用
     */
    void showNoNet();

    /**
     * 重试
     */
    void onRetry();

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();

}
