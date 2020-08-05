package org.first.booketlist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.first.booketlist.model.BookInfo;

@Database(entities = {BookInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookInfoDao bookInfoDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "bookinfo-db").build();
                }
            }
        }
        return INSTANCE;
    }
}
