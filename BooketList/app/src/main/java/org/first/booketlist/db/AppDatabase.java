package org.first.booketlist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.first.booketlist.model.BookInfo;

@Database(entities = {BookInfo.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract BookInfoDao bookInfoDao();
}
