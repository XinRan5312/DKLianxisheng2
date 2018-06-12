package com.xinran.dkelianxi.workmanager;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

/**
 * 更加复杂的用法见：
 * https://blog.csdn.net/guiying712/article/details/80386338
 *
 * 使用WorkManager调度任务
 * 首先我们来看下WorkManager的定义，WorkManager API可以轻松地让异步任务延迟执行以及何时运行它们，
 * 这些API可让我们创建任务并将其交给WorkManager，以便立即或在适当的时间运行。例如，应用程序可能需要不时从网络下载新资源，
 * 我们可以使用WorkManager API设置一个任务，然后选择适合它运行的环境（例如“仅在设备充电和联网时”），
 * 并在符合条件时将其交给 WorkManager 运行，即使该应用程序被强制退出或者设备重新启动，这个任务仍然可以保证运行。
 */
public class WorkUtils {

    public static OneTimeWorkRequest createTimeWorkRequest(Class<? extends Worker> cls) {

        return new OneTimeWorkRequest.Builder(cls).build();
    }

    public static PeriodicWorkRequest createPeriodicWorkRequest(Class<? extends Worker> cls, long repeatInterval,
                                                                @NonNull TimeUnit repeatIntervalTimeUnit) {

        return new PeriodicWorkRequest.Builder(cls, repeatInterval, repeatIntervalTimeUnit).build();
    }

    public static void excuteOneRequest(WorkRequest request) {
        WorkManager.getInstance().enqueue(request);
    }

    public static void excuteRequests(WorkRequest... request) {
        WorkManager.getInstance().enqueue(request);
    }

    public static void excuteRequests(List<WorkRequest> requests) {
        WorkManager.getInstance().enqueue(requests);
    }
}
