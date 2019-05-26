package com.example.myapplication.ui.fragment.reading;


import android.content.Context;
import android.widget.Toast;
import com.example.myapplication.data.Story;
import com.example.myapplication.data.dto.Result;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.ApiUtils;
import com.example.myapplication.ui.activity.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Ominext on 10/2/2017.
 */

public class ReadingPresenter {
    private ApiService mApiService;
    private ReadingView mReadingView;

    public ReadingPresenter(ReadingView mReadingView) {
        this.mApiService = ApiUtils.getIapiService();
        this.mReadingView = mReadingView;
    }

    public void callApiDemo(String id, String nameStory, String contentStory) {
        Toast.makeText(MainActivity.getMainContext(), "callApi", Toast.LENGTH_LONG).show();
        Story story = new Story(id, nameStory, contentStory);
        mApiService.callApi("newstory", story)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFail);
    }

    private void onSuccess(Result result) {
        Toast.makeText(MainActivity.getMainContext(), result.getResult(), Toast.LENGTH_LONG).show();
    }

    private void onSuccess() {
      /*  if(user.getEmail().equals("")){
            mReadingView.onRequestFail(Constants.LOGIN_FAIL);
        }else {
            mReadingView.onSiginSuccess(user);
        }*/
    }

    private void onFail(Throwable throwable) {
      /*  if(String.valueOf(throwable).equals("com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
            mReadingView.onRequestFail(Constants.LOGIN_FAIL);
        }*/

    }
}
