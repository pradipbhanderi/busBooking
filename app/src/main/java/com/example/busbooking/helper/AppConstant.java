package com.example.busbooking.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AppConstant {
    public static final String BASE_URL ="";
    public static final String COMPANYNAME ="company_name" ;
    public static final String COMPANYEMAIL = "company_email";
    public static final String VEHICLECONFIGURE ="vehicle_configure" ;
    public static final String NAME ="name" ;
    public static final String NUMBER ="number";
    public static final String DATEOFBIRTH = "dateofbirth";
    public static final String GENDER ="gender" ;
    public static final String Email= "email";
    public static final String BOARDINGADDRESS = "boardingaddress";
    public static final String DROPPING ="dropping" ;
    public static final String SOURCE ="source" ;
    public static final String DESTINATION ="destination" ;
    public static final String PASSENGERNAME ="passengername";
    public static final String PASSENGERGENDER="passengergender";
    public static final String PASSENGERAGE ="passengerage";

    //api
    public static String IS_LOGIN = "is_login";
    public static final String LANGUAGE = "user_language";
    public static String TOKEN = "token";
    public static String CoPassenger;
    public static Boolean isLogin;


    public static boolean isOnline(final Context ctx) {
        if (ctx != null) {
            final ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    final NetworkInfo ni = cm.getActiveNetworkInfo();
                    Network activeNetwork = cm.getActiveNetwork();
                    NetworkCapabilities caps = cm.getNetworkCapabilities(activeNetwork);
                    if (caps != null) {
                        boolean vpnInUse = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                        if (vpnInUse) {
                            Toast.makeText(ctx, ctx.getString(R.string.vpn), Toast.LENGTH_SHORT).show();
                            return false;
                        } else {
                            if (ni != null) {
                                if (ni.isConnectedOrConnecting()) {
                                    return true;
                                } else {
                                    Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            } else {
                                Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    } else {
                        Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {
                    NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_VPN);
                    if (networkInfo != null) {
                        if (networkInfo.isConnectedOrConnecting()) {
                            Toast.makeText(ctx, "Please disconnect VPN and try again.", Toast.LENGTH_SHORT).show();
                            return false;
                        } else {
                            final NetworkInfo ni = cm.getActiveNetworkInfo();
                            if (ni != null) {
                                if (ni.isConnectedOrConnecting()) {
                                    return true;
                                } else {
                                    Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            } else {
                                Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    } else {
                        Toast.makeText(ctx, ctx.getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void hideKeyboard(FragmentActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            }
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void removeSpaceListener(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replaceAll(" ", "");
                if (!s.toString().equals(result)) {
                    editText.setText(result);
                    editText.setSelection(result.length());
                    // alert the user
                }
            }
        });
    }

    public static String removePTag(String text) {
        return text.replaceAll("[<p>,</p>]", "");
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static String getddmmDate(String currentDate) {
        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = outputFmt.parse(currentDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" dd\nMMM");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }
    public static String getDateFormate(String currentDate) {
        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("dd/mm/yyyy");
            Date date = outputFmt.parse(currentDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd MMM");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    public static String getddmmyyyyDate(String currentDate) {
        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = outputFmt.parse(currentDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    public static String timeForamte(String currentDate) {
        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("HH:mm:ss");
            Date date = outputFmt.parse(currentDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    public static void runLayoutAnimation(RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.rcy_item_animation);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void setLanguage(Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("LANGUAGE_SETTING", Context.MODE_PRIVATE);
        String languageToLoad;
        if (sharedPreferences.getString(AppConstant.LANGUAGE, "gu").equalsIgnoreCase("gu")) {
            languageToLoad = "gu"; // your language
        } else {
            languageToLoad = "en";
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config,
                activity.getResources().getDisplayMetrics());
    }

public static class URLDrawable extends BitmapDrawable {
    // the drawable that you need to set, you could set the initial drawing
    // with the loading image if you need to
    protected Drawable drawable;

    @Override
    public void draw(Canvas canvas) {
        // override the draw to facilitate refresh function later
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }
}

public static class URLImageParser implements Html.ImageGetter {
    Context context;
    View container;

    public URLImageParser(View container, Context context) {
        this.context = context;
        this.container = container;
    }

    public Drawable getDrawable(String source) {
        if (source.matches("data:image.*base64.*")) {
            String base_64_source = source.replaceAll("data:image.*base64", "");
            byte[] data = Base64.decode(base_64_source, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable image = new BitmapDrawable(context.getResources(), bitmap);
            image.setBounds(0, 0, 0 + image.getIntrinsicWidth(), 0 + image.getIntrinsicHeight());
            return image;
        } else {
            URLDrawable urlDrawable = new URLDrawable();
            ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
            asyncTask.execute(source);
            return urlDrawable; //return reference to URLDrawable where We will change with actual image from the src tag
        }
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(), 0 + result.getIntrinsicHeight()); //set the correct bound according to the result from HTTP call
            urlDrawable.drawable = result; //change the reference of the current drawable to the result from the HTTP call
            URLImageParser.this.container.invalidate(); //redraw the image by invalidating the container
        }

        public Drawable fetchDrawable(String urlString) {
            try {
                InputStream is = (InputStream) new URL(urlString).getContent();
                Drawable drawable = Drawable.createFromStream(is, "src");
                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0 + drawable.getIntrinsicHeight());
                return drawable;
            } catch (Exception e) {
                return null;
            }
        }
    }

}

    public static GradientDrawable[] GradientColor() {
        GradientDrawable[] gradientDrawable = new GradientDrawable[]{
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#cc2b5e"), Color.parseColor("#753a88")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#4568dc"), Color.parseColor("#b06ab3")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#DA5050"), Color.parseColor("#EC9966")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#33469C"), Color.parseColor("#923B8B"), Color.parseColor("#EAA925")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#267ECE"), Color.parseColor("#CF3DF9")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#DA5050"), Color.parseColor("#EC9966")}),
                new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor("#E98E3A"), Color.parseColor("#B133C6")})};
        return gradientDrawable;
    }

    public static final int getSoftColors[] =
            {
                    Color.parseColor("#C24162")
                    , Color.parseColor("#0053DB")
                    , Color.parseColor("#284240")
                    , Color.parseColor("#FDA33C")
                    , Color.parseColor("#F73279")
                    , Color.parseColor("#BB7C3E")
                    , Color.parseColor("#3FBA7B")
            };

    public static String dateYYYYToDD(String enterdate) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(enterdate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String TimeFormate(String time) {
        String inputPattern = "HH:mm:ss";
        String outputPattern = "h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String dateTimeFormate(String enterdate) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(enterdate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String DueDateFormate(String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy  h:mm a");
        String actual_date = "";
        try {
            Date date_really = input.parse(date);
            actual_date = output.format(date_really);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return actual_date;
    }

    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(new Date());
        return time;
    }

    public static String changeDateFormat(String sDate) {
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdfSource.parse(sDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("EEE , MMM dd ");
            return sdfDestination.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getYesterdayDate() {
        Calendar today = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = today.get(Calendar.YEAR);
        mMonth = today.get(Calendar.MONTH);
        mDay = (today.get(Calendar.DAY_OF_MONTH) - 1);

        String day = mDay + "";
        if (mDay < 10) day = "0" + mDay;

        String month = mMonth + "";
        if (mMonth < 10) month = "0" + mMonth;

        return mYear + "-" + month + "-" + day;
    }

    public static String getFormatedDate(String createdAt) {
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date past = format.parse(createdAt);
            Date now = new Date();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());

            if (seconds < 60 || minutes < 2) {
                return "Now";
            } else if (minutes < 60) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(createdAt);
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a");
                return simpleDateFormat1.format(date);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(createdAt);
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                return simpleDateFormat1.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    //     return true when autometic time set else return false
    public static boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return Settings.System.getInt(c.getContentResolver(), Settings.System.AUTO_TIME, 0) == 1;
        }
    }

    public static File getFileFromUri(Context context, Uri uri) {
        File file = null;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            String path = context.getFilesDir().getAbsolutePath() + File.separator + "default";
            OutputStream outFile = null;
            file = new File(path, String.valueOf("tmp" + System.currentTimeMillis()) + "kdahtest.jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            outFile = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outFile);
            outFile.flush();
            outFile.close();
        } catch (Exception e) {
            Log.d("DATA", e.getMessage());
        }
        return file;
    }

}