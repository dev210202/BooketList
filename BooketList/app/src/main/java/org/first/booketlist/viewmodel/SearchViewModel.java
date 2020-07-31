package org.first.booketlist.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.first.booketlist.model.BookInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    ObservableField<String> editTextInput;
    public ObservableArrayList<BookInfo> bookData;
    MutableLiveData<String> toastMessage = new MutableLiveData<String>();
    String responseBody;

    public SearchViewModel() {
        this.editTextInput = new ObservableField<String>("text");
        this.bookData = new ObservableArrayList<BookInfo>();
}

    public ObservableField<String> getEditTextInput() {
        return editTextInput;
    }

    public ObservableArrayList<BookInfo> getBookData() {
        return bookData;
    }

    public void searchData() {

        Observable<String> observable = Observable.create(emitter -> {
            try {
                getData();
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        observable.subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    private void getData() {
        bookData.clear();
        JsonArray dataArray = getDataFromNaverAPI();
        if(dataArray.size() == 0){
            toastMessage.postValue("검색된 책이 없습니다.");
        }
        else{
            try {
                filteringData(dataArray);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    private JsonArray getDataFromNaverAPI(){
        String clientId = "OAVpqgXpExdFQglBCJFc";
        String clientSecret = "KTRwVjuTXS";

        String text = editTextInput.get().toString();
        Log.e("text", text);
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        String apiURL = "https://openapi.naver.com/v1/search/book?query=" + text + "&display=20";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        responseBody = get(apiURL, requestHeaders);

        Log.e("responseBody", responseBody.toString());
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(responseBody.toString());
        JsonObject jsonObject = (JsonObject) obj;
        JsonArray getArray = (JsonArray) jsonObject.get("items");
        return getArray;
    }
    private void filteringData(JsonArray dataArray){
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject object = (JsonObject) dataArray.get(i);

            String filter1 = object.get("title").toString().replaceAll("\"", "");
            String filter2 = filter1.replaceAll("<b>", "");
            String title = filter2.replaceAll("</b>", "");

            filter1 = object.get("link").toString().replaceAll("\"","");
            String link = filter1;

            filter1 = object.get("image").toString().replaceAll("\"", "");
            String image = filter1;

            filter1 = object.get("author").toString().replaceAll("\"", "");
            filter2 = filter1.replaceAll("<b>", "");
            String author = filter2.replaceAll("</b>", "");

            filter1 = object.get("publisher").toString().replaceAll("\"", "");
            filter2 = filter1.replaceAll("<b>", "");
            String publisher = filter2.replaceAll("</b>", "");

            filter1 = object.get("pubdate").toString().replaceAll("\"", "");
            filter2 = filter1.replaceAll("<b>", "");
            String pubdate = filter2.replaceAll("</b>", "");

            filter1 = object.get("description").toString().replaceAll("\"", "");
            filter2 = filter1.replaceAll("<b>", "");
            String description = filter2.replaceAll("</b>", "");
            if (description.equals("")) {
                description = "책 상세정보가 없습니다.";
            }
            BookInfo bookInfo = new BookInfo(
                    title,
                    link,
                    image,
                    author,
                    publisher,
                    pubdate,
                    description
            );
            bookData.add(bookInfo);

        }
    }
}
