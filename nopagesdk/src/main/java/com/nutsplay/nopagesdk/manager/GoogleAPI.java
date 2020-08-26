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
//            public void onComplete(@NonNull Task<ReviewInfo> task) {
//                if (task.isSuccessful()) {
//                    // We can get the ReviewInfo object
//                    ReviewInfo reviewInfo = task.getResult();
//
//
//                    launchReviewFlow(reviewInfo, manager, context);
//                } else {
//                    // There was some problem, continue regardless of the result.
//                    LogUtil.d("requestReviewFlow failed:" + task.getResult());
//                }
//            }
//        });
//    }
//
//    private static void launchReviewFlow(ReviewInfo reviewInfo, ReviewManager manager, Activity context) {
//        Task<Void> flow = manager.launchReviewFlow(context, reviewInfo);
//        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    LogUtil.d("launchReviewFlow successful");
//                }else {
//                    LogUtil.d("launchReviewFlow failed:" + task.getResult());
//                }
//            }
//        });
//    }
//}
