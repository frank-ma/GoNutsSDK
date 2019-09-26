package com.nutsplay.nopagesdk.billing;

/**
 * Created by frank-ma on 2019/6/26 5:02 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface BillingProvider {
    BillingManager getBillingManager();
    boolean isPremiumPurchased();
    boolean isGoldMonthlySubscribed();
    boolean isTankFull();
    boolean isGoldYearlySubscribed();
}
