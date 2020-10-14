package com.nutsplay.nopagesdk.manager;

/**
 * Created by frankma on 2020/8/26 11:46 AM
 * Email: frankma9103@gmail.com
 * Desc: 应用内评价等
 */
//public class GoogleAPI {
//
//    public static void evaluateInApp(final Activity context){
//        final ReviewManager manager = ReviewManagerFactory.create(context);
//        Task<ReviewInfo> request = manager.requestReviewFlow();
//        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
//            @Override
//            public void onComplete(Task<ReviewInfo> task) {
//                if (task.isSuccessful()){
//                    LogUtil.d("evaluateInApp success:" + task.getResult().toString());
//                    ReviewInfo reviewInfo = task.getResult();
//                    launchReviewFlow(context,reviewInfo,manager);
//                }else {
//                    LogUtil.d("evaluateInApp failed:" + task.getResult().toString());
//                }
//            }
//        });
//    }
//
//    private static void launchReviewFlow(Activity context,ReviewInfo reviewInfo, ReviewManager manager) {
//        Task<Void> flow = manager.launchReviewFlow(context, reviewInfo);
//        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(Task<Void> task) {
//                if (task.isSuccessful()){
//                    LogUtil.d("launchReviewFlow successful");
//                }else {
//                    LogUtil.d("launchReviewFlow failed:" + task.getResult());
//                }
//            }
//        });
//    }
//}
