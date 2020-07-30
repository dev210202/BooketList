package org.first.booketlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.first.booketlist.databinding.RecyclerItemBinding;
import org.first.booketlist.model.BookInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<BookInfo> dataList;

    public RecyclerAdapter() {
        this.dataList = new ArrayList<BookInfo>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = dataList.get(position);
        holder.bind(bookInfo);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    void setItem(List<BookInfo> dataList) {
        if (dataList.isEmpty()) {
            return;
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemBinding binding;

        public ViewHolder(RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(BookInfo bookInfo) {
            binding.setBook(bookInfo);
        }
    }


}
