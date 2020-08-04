package org.first.booketlist.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.first.booketlist.R;
import org.first.booketlist.adapter.RecyclerAdapter;
import org.first.booketlist.viewmodel.SearchViewModel;
import org.first.booketlist.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

 //  private ObservableArrayList<BookInfo> bookList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        FragmentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setViewModel(viewModel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        binding.recyclerview.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter();
        binding.recyclerview.setAdapter(adapter);
        binding.setBookList(viewModel.bookData);

        View view = binding.getRoot();

        return view;

    }
}