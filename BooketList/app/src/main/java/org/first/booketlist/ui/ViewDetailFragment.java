package org.first.booketlist.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.first.booketlist.db.AppDatabase;
import org.first.booketlist.model.BookInfo;
import org.first.booketlist.R;
import org.first.booketlist.databinding.FragmentViewDetailBinding;
import org.first.booketlist.viewmodel.ViewDetailViewModel;


public class ViewDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewDetailViewModel viewModel = new ViewModelProvider(this).get(ViewDetailViewModel.class);
        FragmentViewDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_detail, container, false);

        binding.setViewModel(viewModel);
        Bundle bundle = getArguments();
        BookInfo BookData = (BookInfo) bundle.getSerializable("bookInfo");

        viewModel.book.set(BookData);

        binding.setBook(viewModel.book);
        View view = binding.getRoot();
        return view;
    }



}