package com.nutsplay.nopagesdk.billing;

import com.android.billingclient.api.BillingClient;

import java.util.Arrays;
import java.util.List;

/**
 * Created by frank-ma on 2019/6/26 5:00 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BillingConstants {

    // SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
    public static final String SKU_PREMIUM = "premium";
    public static final String SKU_GAS = "gas";

    // SKU for our subscription (infinite gas)
    public static final String SKU_GOLD_MONTHLY = "gold_monthly";
    public static final String SKU_GOLD_YEARLY = "gold_yearly";

    private static final String[] IN_APP_SKUS = {SKU_GAS, SKU_PREMIUM};
    private static final String[] SUBSCRIPTIONS_SKUS = {SKU_GOLD_MONTHLY, SKU_GOLD_YEARLY};

    private BillingConstants(){}

    /**
     * Returns the list of all SKUs for the billing type specified
     */
    public static final List<String> getSkuList(@BillingClient.SkuType String billingType) {
        return (billingType == BillingClient.SkuType.INAPP) ? Arrays.asList(IN_APP_SKUS)
                : Arrays.asList(SUBSCRIPTIONS_SKUS);
    }
}
