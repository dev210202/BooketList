package org.first.booketlist.repository;

import android.app.Application;
import android.util.Log;

import org.first.booketlist.db.AppDatabase;
import org.first.booketlist.db.BookInfoDao;
import org.first.booketlist.model.BookInfo;

import java.util.List;

public class BookRepository {

    BookInfoDao bookInfoDao;
    List<BookInfo> bookInfoList;

    public BookRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        bookInfoDao = db.bookInfoDao();
        bookInfoList = bookInfoDao.getAll();
        Log.e("BOOK LIST", bookInfoList.toString());
    }

    public List<BookInfo> getAllBooks(){
        return bookInfoList;
    }
}
