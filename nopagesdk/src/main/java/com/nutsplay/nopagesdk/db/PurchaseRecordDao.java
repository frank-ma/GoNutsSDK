//package com.nutsplay.nopagesdk.db;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteStatement;
//
//import org.greenrobot.greendao.AbstractDao;
//import org.greenrobot.greendao.Property;
//import org.greenrobot.greendao.internal.DaoConfig;
//import org.greenrobot.greendao.database.Database;
//import org.greenrobot.greendao.database.DatabaseStatement;
//
//// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
///**
// * DAO for table "PURCHASE_RECORD".
//*/
//public class PurchaseRecordDao extends AbstractDao<PurchaseRecord, Void> {
//
//    public static final String TABLENAME = "PURCHASE_RECORD";
//
//    /**
//     * Properties of entity PurchaseRecord.<br/>
//     * Can be used for QueryBuilder and for referencing column names.
//     */
//    public static class Properties {
//        public final static Property TransactionId = new Property(0, String.class, "transactionId", false, "TRANSACTION_ID");
//        public final static Property GoogleId = new Property(1, String.class, "googleId", false, "GOOGLE_ID");
//        public final static Property PurchaseJson = new Property(2, String.class, "purchaseJson", false, "PURCHASE_JSON");
//        public final static Property SkuId = new Property(3, String.class, "skuId", false, "SKU_ID");
//        public final static Property Status = new Property(4, int.class, "status", false, "STATUS");
//    }
//
//
//    public PurchaseRecordDao(DaoConfig config) {
//        super(config);
//    }
//
//    public PurchaseRecordDao(DaoConfig config, DaoSession daoSession) {
//        super(config, daoSession);
//    }
//
//    /** Creates the underlying database table. */
//    public static void createTable(Database db, boolean ifNotExists) {
//        String constraint = ifNotExists? "IF NOT EXISTS ": "";
//        db.execSQL("CREATE TABLE " + constraint + "\"PURCHASE_RECORD\" (" + //
//                "\"TRANSACTION_ID\" TEXT UNIQUE ," + // 0: transactionId
//                "\"GOOGLE_ID\" TEXT," + // 1: googleId
//                "\"PURCHASE_JSON\" TEXT," + // 2: purchaseJson
//                "\"SKU_ID\" TEXT," + // 3: skuId
//                "\"STATUS\" INTEGER NOT NULL );"); // 4: status
//    }
//
//    /** Drops the underlying database table. */
//    public static void dropTable(Database db, boolean ifExists) {
//        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PURCHASE_RECORD\"";
//        db.execSQL(sql);
//    }
//
//    @Override
//    protected final void bindValues(DatabaseStatement stmt, PurchaseRecord entity) {
//        stmt.clearBindings();
//
//        String transactionId = entity.getTransactionId();
//        if (transactionId != null) {
//            stmt.bindString(1, transactionId);
//        }
//
//        String googleId = entity.getGoogleId();
//        if (googleId != null) {
//            stmt.bindString(2, googleId);
//        }
//
//        String purchaseJson = entity.getPurchaseJson();
//        if (purchaseJson != null) {
//            stmt.bindString(3, purchaseJson);
//        }
//
//        String skuId = entity.getSkuId();
//        if (skuId != null) {
//            stmt.bindString(4, skuId);
//        }
//        stmt.bindLong(5, entity.getStatus());
//    }
//
//    @Override
//    protected final void bindValues(SQLiteStatement stmt, PurchaseRecord entity) {
//        stmt.clearBindings();
//
//        String transactionId = entity.getTransactionId();
//        if (transactionId != null) {
//            stmt.bindString(1, transactionId);
//        }
//
//        String googleId = entity.getGoogleId();
//        if (googleId != null) {
//            stmt.bindString(2, googleId);
//        }
//
//        String purchaseJson = entity.getPurchaseJson();
//        if (purchaseJson != null) {
//            stmt.bindString(3, purchaseJson);
//        }
//
//        String skuId = entity.getSkuId();
//        if (skuId != null) {
//            stmt.bindString(4, skuId);
//        }
//        stmt.bindLong(5, entity.getStatus());
//    }
//
//    @Override
//    public Void readKey(Cursor cursor, int offset) {
//        return null;
//    }
//
//    @Override
//    public PurchaseRecord readEntity(Cursor cursor, int offset) {
//        PurchaseRecord entity = new PurchaseRecord( //
//            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // transactionId
//            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // googleId
//            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // purchaseJson
//            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // skuId
//            cursor.getInt(offset + 4) // status
//        );
//        return entity;
//    }
//
//    @Override
//    public void readEntity(Cursor cursor, PurchaseRecord entity, int offset) {
//        entity.setTransactionId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
//        entity.setGoogleId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
//        entity.setPurchaseJson(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
//        entity.setSkuId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
//        entity.setStatus(cursor.getInt(offset + 4));
//     }
//
//    @Override
//    protected final Void updateKeyAfterInsert(PurchaseRecord entity, long rowId) {
//        // Unsupported or missing PK type
//        return null;
//    }
//
//    @Override
//    public Void getKey(PurchaseRecord entity) {
//        return null;
//    }
//
//    @Override
//    public boolean hasKey(PurchaseRecord entity) {
//        // TODO
//        return false;
//    }
//
//    @Override
//    protected final boolean isEntityUpdateable() {
//        return true;
//    }
//
//}
