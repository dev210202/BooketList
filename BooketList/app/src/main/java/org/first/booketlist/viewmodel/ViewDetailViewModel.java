package org.first.booketlist.viewmodel;


import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.first.booketlist.model.BookInfo;

public class ViewDetailViewModel extends ViewModel {

    public ObservableField<BookInfo> book = new ObservableField<BookInfo>();
}
