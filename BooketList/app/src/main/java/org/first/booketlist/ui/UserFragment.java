package org.first.booketlist.ui;

import android.app.Application;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.first.booketlist.R;
import org.first.booketlist.databinding.FragmentUserBinding;
import org.first.booketlist.db.AppDatabase;
import org.first.booketlist.model.BookData;
import org.first.booketlist.model.BookInfo;
import org.first.booketlist.network.BookService;
import org.first.booketlist.viewmodel.UserViewModel;
import org.first.booketlist.viewmodel.ViewDetailViewModel;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final UserViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(UserViewModel.class);
        FragmentUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);



        viewModel.getDataFromDB();

        getNaverAPIWithRetrofit();

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;
    }


    private void getNaverAPIWithRetrofit() {


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://openapi.naver.com/v1/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        BookService service = retrofit.create(BookService.class);

        Call<BookData> request = service.getBooks("text", 20);
        request.enqueue(new Callback<BookData>() {

            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                if (response.isSuccessful()) {
                    Log.e("RESOPNSE SUCCESS ! ", "1");
                }
                if (response.body() != null) {
                    Log.e("RESPONSE SUCCESS", response.body().getItems().toString());
                }
            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {
                Log.e("RESPONSE FAIL", t.toString());
            }
        });

    }
}