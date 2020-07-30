package org.first.booketlist.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.first.booketlist.model.BookInfo;
import org.first.booketlist.R;
import org.first.booketlist.databinding.FragmentViewDetailBinding;


public class ViewDetailFragment extends Fragment {

    ObservableField<BookInfo> book = new ObservableField<BookInfo>();
    FragmentViewDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_detail, container, false);

        Bundle bundle = getArguments();
        BookInfo asd = (BookInfo) bundle.getSerializable("bookInfo");

        book.set(asd);
        binding.setBook(book);
//        // setArguments();
//
//        Glide.with(this).load(image).into(binding.imageView);
//        binding.titleTextview.setText(title);
        View view = binding.getRoot();
        return view;
    }

//    public void setArguments(){
//        image = getArguments().getString("image");
//        title = getArguments().getString("title");
//        author = getArguments().getString("author");
//        publisher = getArguments().getString("publisher");
//        pubdate = getArguments().getString("pubdate");
//        description = getArguments().getString("description");
//    }


}