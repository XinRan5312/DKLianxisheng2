package com.xinran.dkelianxi.base;

import android.content.Context;

public abstract class ABasePresenter<VI extends IBaseView> {
    private VI mViewHelper;
    private Context mContext;

    public ABasePresenter(VI viewHelper, Context context) {
        this.mViewHelper = viewHelper;
        this.mContext = context;
    }

    public VI getmViewHelper() {
        return mViewHelper;
    }

    public Context getmContext() {
        return mContext;
    }
}
