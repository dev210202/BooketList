package org.first.booketlist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import org.first.booketlist.databinding.FragmentViewDetailBinding;


public class ViewDetailFragment extends Fragment {
    ObservableField<String> image;
    String title;
    String author;
    String publisher;
    String pubdate;
    String description;

    FragmentViewDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_detail, container, false);
        // setArguments();

        Glide.with(this).load(image).into(binding.imageView);
        binding.titleTextview.setText(title);

        return inflater.inflate(R.layout.fragment_view_detail, container, false);
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