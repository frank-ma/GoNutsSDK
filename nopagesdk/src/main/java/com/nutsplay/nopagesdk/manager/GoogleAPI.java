package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.nutsplay.nopagesdk.callback.ResultCallBack;

import org.xutils.common.util.LogUtil;

/**
 * Created by frankma on 2020/8/26 11:46 AM
 * Email: frankma9103@gmail.com
 * Desc: 应用内评价等
 * 不建议放置固定的按钮，让用户触发API，
 * 而是当用户达成某项成就的时候，触发API，让用户去评分，因为应用内评价是有配额的，
 * 不是一个每次都会出现的功能
 * 官方文档原话：
 * 为了提供优质用户体验，Google Play 会强制执行一个限时配额，用于规定系统向用户显示评价对话框的频率。由于存在此配额，
 * 在短时间内（例如，不到一个月内）多次调用 launchReviewFlow 方法时可能不会始终显示对话框。
 * 由于配额可能会发生变化，因此请务必应用自己的逻辑并瞄准最佳时机申请审核。例如，您不得采用旨在触发 API 的号召性用语选项（例如按钮），
 * 因为用户可能已经达到其配额，而该流程无法显示，这会导致无法向用户提供流畅的体验。对于此使用场景，请将用户重定向到 Play 商店。
 */
public class GoogleAPI {

    public static void evaluateInApp(final Activity context, final ResultCallBack resultCallBack){
        final ReviewManager manager = ReviewManagerFactory.create(context);
        final Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()){
                    LogUtil.d("evaluateInApp success:" + task.getResult().toString());
                    ReviewInfo reviewInfo = task.getResult();

                    //ReviewInfo对象仅在有限的时间内有效。您的应用应提前请求ReviewInfo对象（预缓存），但只有
                    //在确定应用会启动应用内评价流程后，才可请求
                    if (reviewInfo == null) return;
                    LogUtil.e("请求评价对象成功"+reviewInfo);
                    launchReviewFlow(context,reviewInfo,manager,resultCallBack);
                }else {
                    LogUtil.d("evaluateInApp failed:" + task.getResult().toString());
                }
            }
        });
    }

    /**
     * 启动应用内评价流程
     * 重要提示：如果在应用内评价流程中出现错误，请勿通知用户或更改应用的正常用户流。调用 onComplete 后，继续执行应用的正常用户流。
     * @param context
     * @param reviewInfo
     * @param manager
     */
    public static void launchReviewFlow(Activity context, ReviewInfo reviewInfo, ReviewManager manager, final ResultCallBack resultCallBack) {
        if (manager == null || reviewInfo == null) return;
        Task<Void> flow = manager.launchReviewFlow(context, reviewInfo);
        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    LogUtil.d("评论成功");
                    resultCallBack.onSuccess();
                }else {
                    LogUtil.d("评论失败:" + task.getResult());
                    resultCallBack.onFailure(task.getResult().toString());
                }
            }
        });
    }
}
