package org.first.booketlist.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.first.booketlist.R;
import org.first.booketlist.db.AppDatabase;

public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "bookinfo-db")
                .allowMainThreadQueries()
                .build();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView textView = (TextView) view.findViewById(R.id.db);
        textView.setText(db.bookInfoDao().getAll().toString());
        // Inflate the layout for this fragment
        return view;
    }
}