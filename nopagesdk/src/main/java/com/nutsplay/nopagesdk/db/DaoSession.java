package com.nutsplay.nopagesdk.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nutsplay.nopagesdk.db.PurchaseRecord;

import com.nutsplay.nopagesdk.db.PurchaseRecordDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig purchaseRecordDaoConfig;

    private final PurchaseRecordDao purchaseRecordDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        purchaseRecordDaoConfig = daoConfigMap.get(PurchaseRecordDao.class).clone();
        purchaseRecordDaoConfig.initIdentityScope(type);

        purchaseRecordDao = new PurchaseRecordDao(purchaseRecordDaoConfig, this);

        registerDao(PurchaseRecord.class, purchaseRecordDao);
    }
    
    public void clear() {
        purchaseRecordDaoConfig.clearIdentityScope();
    }

    public PurchaseRecordDao getPurchaseRecordDao() {
        return purchaseRecordDao;
    }

}
