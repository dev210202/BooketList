package org.first.booketlist.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.first.booketlist.R;
import org.first.booketlist.db.AppDatabase;
import org.first.booketlist.model.BookInfo;
import org.first.booketlist.ui.ViewDetailFragment;

public class DataBindingAdapter {


    @BindingAdapter("item")
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList<BookInfo> dataArray) {
        RecyclerAdapter adapter = (RecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItem(dataArray);
        }
    }

    @BindingAdapter("setimage")
    public static void setImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }

    @BindingAdapter("viewdeatil")
    public static void viewDetail(View view, BookInfo bookInfo) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDetailFragment fragment = new ViewDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("bookInfo", bookInfo);
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction =
                        activity.getSupportFragmentManager().beginTransaction();

                transaction.addToBackStack(null);
                transaction.replace(R.id.framelayout, fragment);
                transaction.commit();
            }
        });
    }

    @BindingAdapter("linkopen")
    public static void linkOpen(ImageButton imageButton, String link) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) imageButton.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                activity.startActivity(intent);
            }
        });

    }

    @BindingAdapter("insertDB")
    public static void insertDB(ImageButton imageButton, ObservableField<BookInfo> bookInfoObservableField) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) imageButton.getContext();
                AppDatabase db = AppDatabase.getDatabase(activity.getApplication());
                db.bookInfoDao().insert(bookInfoObservableField.get());
                Log.e("DBDBD", db.bookInfoDao().getAll().toString());

            }
        });
    }
}
