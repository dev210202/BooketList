package org.first.booketlist.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import org.first.booketlist.db.AppDatabase;
import org.first.booketlist.db.BookInfoDao;
import org.first.booketlist.model.BookInfo;
import org.first.booketlist.repository.BookRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel{

    List<BookInfo> dbBookData;
    BookRepository bookRepository;

    public UserViewModel(Application application) {
        super(application);
        //bookRepository = new BookRepository(application);
        // dbBookData = bookRepository.getAllBooks();
    }

    public void getDataFromDB(){

        Observable<String> observable = Observable.create(emitter -> {
            try {

                bookRepository = new BookRepository(getApplication());
                dbBookData = bookRepository.getAllBooks();
                Log.e("GETDATA", dbBookData.toString());
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        observable.subscribeOn(Schedulers.io())
                .subscribe(observer);

    }

}
