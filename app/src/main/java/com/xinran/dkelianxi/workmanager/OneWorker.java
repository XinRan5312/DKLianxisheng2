package com.xinran.dkelianxi.workmanager;

import android.support.annotation.NonNull;

import androidx.work.Worker;

public class OneWorker extends Worker{
    @NonNull
    @Override
    public WorkerResult doWork() {

        //做些背后的工作  比如压缩图片
        compressImg();
        return WorkerResult.SUCCESS;
    }

    private void compressImg() {

    }
}
