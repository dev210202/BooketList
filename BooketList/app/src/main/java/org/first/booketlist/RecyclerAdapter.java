package org.first.booketlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    MutableLiveData<ArrayList<BookInfo>> dataList;

    public RecyclerAdapter(MutableLiveData<ArrayList<BookInfo>> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView).load(dataList.getValue().get(position).image).into(holder.imageView);
        holder.titleTextView.setText(dataList.getValue().get(position).title);
        holder.authorTextView.setText(dataList.getValue().get(position).author);
        holder.publisherTextView.setText(dataList.getValue().get(position).publisher);
        holder.pubDateTextView.setText(dataList.getValue().get(position).pubdate);
        holder.descriptionTextView.setText(dataList.getValue().get(position).description);
    }

    @Override
    public int getItemCount() {
        return dataList.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView authorTextView;
        TextView publisherTextView;
        TextView pubDateTextView;
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_textview);
            authorTextView = (TextView) itemView.findViewById(R.id.author_textview);
            publisherTextView = (TextView) itemView.findViewById(R.id.publisher_textview);
            pubDateTextView = (TextView) itemView.findViewById(R.id.pubdate_textview);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_textview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        ViewDetailFragment fragment = new ViewDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("image", dataList.getValue().get(position).image);
                        bundle.putString("title", dataList.getValue().get(position).title);
                        bundle.putString("author", dataList.getValue().get(position).author);
                        bundle.putString("publisher", dataList.getValue().get(position).publisher);
                        bundle.putString("pubdate", dataList.getValue().get(position).pubdate);
                        bundle.putString("description", dataList.getValue().get(position).description);
                        fragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commitAllowingStateLoss();
                    }
                }
            });
        }
    }

}
