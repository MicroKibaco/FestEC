package com.asiainfo.latte_ec.database;


import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        final DaoSession session = new DaoMaster(db).newSession();
        mDao = session.getUserProfileDao();
    }

    public UserProfileDao getDao() {
        return mDao;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
}
