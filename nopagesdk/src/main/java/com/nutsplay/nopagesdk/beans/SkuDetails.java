/* Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Represents an in-app product's listing details.
 */
public class SkuDetails implements Serializable {
    private String sku;
    private String type;
    private String price;
    private long priceAmountMicros;
    private String priceCurrencyCode;
    private String title;
    private String description;
    private String originalJson;
    private String originalPrice;
    private long originalPriceAmountMicros;
    private String subscriptionPeriod;
    private String freeTrialPeriod;
    private String introductoryPrice;
    private long introductoryPriceAmountMicros;
    private String introductoryPricePeriod;
    private String introductoryPriceCycles;
    private String iconUrl;
    private boolean rewarded;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getPriceAmountMicros() {
        return priceAmountMicros;
    }

    public void setPriceAmountMicros(long priceAmountMicros) {
        this.priceAmountMicros = priceAmountMicros;
    }

    public String getPriceCurrencyCode() {
        return priceCurrencyCode;
    }

    public void setPriceCurrencyCode(String priceCurrencyCode) {
        this.priceCurrencyCode = priceCurrencyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalJson() {
        return originalJson;
    }

    public void setOriginalJson(String originalJson) {
        this.originalJson = originalJson;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public long getOriginalPriceAmountMicros() {
        return originalPriceAmountMicros;
    }

    public void setOriginalPriceAmountMicros(long originalPriceAmountMicros) {
        this.originalPriceAmountMicros = originalPriceAmountMicros;
    }

    public String getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(String subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public String getFreeTrialPeriod() {
        return freeTrialPeriod;
    }

    public void setFreeTrialPeriod(String freeTrialPeriod) {
        this.freeTrialPeriod = freeTrialPeriod;
    }

    public String getIntroductoryPrice() {
        return introductoryPrice;
    }

    public void setIntroductoryPrice(String introductoryPrice) {
        this.introductoryPrice = introductoryPrice;
    }

    public long getIntroductoryPriceAmountMicros() {
        return introductoryPriceAmountMicros;
    }

    public void setIntroductoryPriceAmountMicros(long introductoryPriceAmountMicros) {
        this.introductoryPriceAmountMicros = introductoryPriceAmountMicros;
    }

    public String getIntroductoryPricePeriod() {
        return introductoryPricePeriod;
    }

    public void setIntroductoryPricePeriod(String introductoryPricePeriod) {
        this.introductoryPricePeriod = introductoryPricePeriod;
    }

    public String getIntroductoryPriceCycles() {
        return introductoryPriceCycles;
    }

    public void setIntroductoryPriceCycles(String introductoryPriceCycles) {
        this.introductoryPriceCycles = introductoryPriceCycles;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isRewarded() {
        return rewarded;
    }

    public void setRewarded(boolean rewarded) {
        this.rewarded = rewarded;
    }

    @Override
    public String toString() {
        return "SkuDetails{" +
                ", sku='" + sku + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", priceAmountMicros=" + priceAmountMicros +
                ", priceCurrencyCode='" + priceCurrencyCode + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", originalJson='" + originalJson + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", originalPriceAmountMicros=" + originalPriceAmountMicros +
                ", subscriptionPeriod='" + subscriptionPeriod + '\'' +
                ", freeTrialPeriod='" + freeTrialPeriod + '\'' +
                ", introductoryPrice='" + introductoryPrice + '\'' +
                ", introductoryPriceAmountMicros='" + introductoryPriceAmountMicros + '\'' +
                ", introductoryPricePeriod='" + introductoryPricePeriod + '\'' +
                ", introductoryPriceCycles='" + introductoryPriceCycles + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", rewarded=" + rewarded +
                '}';
    }
}
