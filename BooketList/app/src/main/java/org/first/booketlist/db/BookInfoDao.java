package org.first.booketlist.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.first.booketlist.model.BookInfo;

import java.util.List;
@Dao
public interface BookInfoDao {
    @Query("SELECT * FROM bookinfo")
    List<BookInfo> getAll();

    @Insert
    void insert(BookInfo bookInfo);

    @Delete
    void delete(BookInfo bookInfo);

}
