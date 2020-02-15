package com.nutsplay.nopagesdk.db;

import com.android.billingclient.api.Purchase;
import com.nutsplay.nopagesdk.kernel.SDKApplication;

import java.util.List;

/**
 * Created by frankma on 2019-09-29 11:56
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class DBManager {

    private static DBManager INSTANCE;

    private PurchaseRecordDao purchaseRecordDao;

    public static DBManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager();
                }
            }
        }
        return INSTANCE;
    }

    private PurchaseRecordDao getRecordDao() {
        if (purchaseRecordDao == null) {
            DaoSession daoSession = SDKApplication.getInstance().getDaoSession();
            return daoSession.getPurchaseRecordDao();
        } else {
            return purchaseRecordDao;
        }
    }

    public void insertOrReplace(PurchaseRecord record) {
        if (record==null)return;
        getRecordDao().insertOrReplace(record);
    }

    public void delete(PurchaseRecord record){
        if (record==null)return;
        getRecordDao().delete(record);
    }

    public void deleteAll(){
        getRecordDao().deleteAll();
    }

    public void update(PurchaseRecord record){
        if (record == null)return;
        getRecordDao().update(record);
    }

    public List<PurchaseRecord> queryAll(){
        return getRecordDao().loadAll();
    }

    public PurchaseRecord query(long Id){
        return getRecordDao().loadByRowId(Id);
    }

    /**
     * 查询所有状态为2的订单：支付但未兑换
     * @return
     */
    public List<Purchase> queryLostOrders(){
//        return getRecordDao().queryRaw("where status=2 order by transaction_id desc limit 50");
        return null;
    }
    /**
     * 查询所有状态为2的订单：支付但未兑换
     * @return
     */
    public List<PurchaseRecord> query(String googleId){
        return getRecordDao().queryRaw("where GOOGLE_ID=?",googleId);
    }
}
