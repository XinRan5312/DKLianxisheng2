package com.xinran.dkelianxi.base;

import android.content.Context;

public abstract class ABasePresenter<VI extends IBaseView> {
    protected VI mViewHelper;
    protected Context mContext;

    public ABasePresenter(VI viewHelper, Context context) {
        this.mViewHelper = viewHelper;
        this.mContext = context;
    }
    public abstract void destroyView();
}
