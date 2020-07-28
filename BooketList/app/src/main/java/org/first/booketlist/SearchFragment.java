package org.first.booketlist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.first.booketlist.databinding.FragmentSearchBinding;

import java.util.ArrayList;


public class SearchFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        FragmentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setViewModel(viewModel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        binding.recyclerview.setLayoutManager(layoutManager);

        viewModel.bookData.observe(getViewLifecycleOwner(), new Observer<ArrayList<BookInfo>>() {
            @Override
            public void onChanged(ArrayList<BookInfo> bookInfos) {
                RecyclerAdapter adapter = new RecyclerAdapter(viewModel.bookData);
                binding.recyclerview.setAdapter(adapter);

            }
        });


        View view = binding.getRoot();

        return view;

    }
}