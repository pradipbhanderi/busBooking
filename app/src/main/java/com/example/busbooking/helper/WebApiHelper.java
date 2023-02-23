package com.example.busbooking.helper;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.busbooking.Activity.SplashActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class WebApiHelper {
    static KProgressHUD kProgressHUD;

    public static void callPostApi(final Activity activity, String url, RequestParams params, final boolean isLoading, final CallBack callback) {

        url = url.replace(" ", "%20");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Bearer " + SecurePreferences.getStringPreference(activity, AppConstant.TOKEN));
       client.addHeader("Accept", "application/json");
        client.setTimeout(60000);
        client.post(AppConstant.BASE_URL + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (isLoading) {
                    showProgressDialog(activity);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isLoading)
                    hideProgressDialog();
                callback.onResponse(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isLoading)
                    hideProgressDialog();
                if (!Objects.requireNonNull(error.getLocalizedMessage()).equalsIgnoreCase("Unauthorized"))
                {
                    Toast.makeText(activity, Objects.requireNonNull(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                }
                if (statusCode == 401) {
                    SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,false);
                    activity.finish();
                    activity.startActivity(new Intent(activity, SplashActivity.class));
                }
            }
        });
    }


    public static void callGetApi(final Activity activity, String url, final boolean isLoading, final CallBack callBack) {
        url = url.replace(" ", "%20");
        AsyncHttpClient client = new AsyncHttpClient();
//        client.addHeader("Authorization", "Bearer " + SecurePreferences.getStringPreference(activity, AppConstant.TOKEN));
//        client.addHeader("Accept", "application/json");
        client.setTimeout(60000);
        client.get(AppConstant.BASE_URL + url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (isLoading) {
                    showProgressDialog(activity);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isLoading)
                    hideProgressDialog();

                callBack.onResponse(new String(responseBody));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isLoading)
                    hideProgressDialog();
                callBack.onResponse(error.getLocalizedMessage());
                if (!Objects.requireNonNull(error.getLocalizedMessage()).equalsIgnoreCase("Unauthorized"))
                {
                    Toast.makeText(activity, Objects.requireNonNull(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                }
                if (statusCode == 401) {
                    SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,false);
                    activity.finish();
                    activity.startActivity(new Intent(activity, SplashActivity.class));
                }
            }
        });
    }

    public static void callGetMapApi(final Activity activity, String url, final boolean isLoading, final CallBack callBack) {
        url = url.replace(" ", "%20");
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(60000);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (isLoading) {
                    showProgressDialog(activity);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               hideProgressDialog();


               callBack.onResponse (new String(responseBody));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               hideProgressDialog();
                callBack.onResponse(error.getLocalizedMessage());
                Toast.makeText(activity, Objects.requireNonNull(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void showProgressDialog(Activity activity)
    {
        kProgressHUD = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(5)
                .setDimAmount(0.5f);
        if(!kProgressHUD.isShowing())
            kProgressHUD.show();
    }

    public static void hideProgressDialog()
    {
        if (kProgressHUD.isShowing())
            kProgressHUD.dismiss();
    }

    public static void callPostApi(AppCompatActivity activity, String order_now_api, RequestParams params, boolean b) {
    }
}