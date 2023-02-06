package com.example.chapter13.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
@SuppressLint("DefaultLocale")
public class CollectWork extends Worker {
    private final static String TAG = "CollectWork";
    private Data mInputData;//工作者的输入数据

    public CollectWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mInputData = workerParams.getInputData();
    }

    @NonNull
    @Override
    public Result doWork() {
         String desc = String.format("请求参数包括：姓名 = %s, 身高 = %d, 体重 = %f",
                 mInputData.getString("name"),
                 mInputData.getInt("height", 0),
                 mInputData.getDouble("weight", 0));
        Log.d(TAG, "doWork "+desc);
        Data outputData = new Data.Builder()
                .putInt("resultCode", 0)
                .putString("resultDesc", "处理成功")
                .build();
        return Result.success(outputData); // success表示成功，failure表示失败
    }

    // 然后在活动页面构建并启动工作任务
}
