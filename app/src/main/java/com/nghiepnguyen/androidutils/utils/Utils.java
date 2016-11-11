package com.nghiepnguyen.androidutils.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nghiepnguyen.androidutils.widgets.MySpannable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.TELEPHONY_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class Utils {
    public static void showOptionDialog(Context context, String[] options,
                                        String title, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(options, onClickListener);
        builder.show();
    }

    // Have internet
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo wifiNetwork = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (wifiNetwork != null && wifiNetwork.isConnected()) {
                    return true;
                }

                NetworkInfo mobileNetwork = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobileNetwork != null && mobileNetwork.isConnected()) {
                    return true;
                }

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Get language device
    public static String getLanguage(Context mContext) {
        Resources res = mContext.getResources();
        Configuration conf = res.getConfiguration();
        String language = conf.locale.getLanguage();
        // Some languages have wrong define, such as Bahasa Indonesia must be "id", but this function return "in", we need to fix it
        if (!TextUtils.isEmpty(language) && language.equals("in")) {
            language = "id";
        }
        return language;
    }

    // Get the T&C url depend on country code & country language
    public static String getTCUrl(String countryCode, String countryLanguage) {
        String tcUrl = Constant.TERM_OF_SERVICE_URL;
        if (!TextUtils.isEmpty(countryCode) && (countryCode.equalsIgnoreCase("th") || countryCode.equalsIgnoreCase("id") || countryCode.equalsIgnoreCase("ph"))) {
            if (countryCode.equalsIgnoreCase("th")) {
                // In case of there is Thai, load th-th or th-en
                if (countryLanguage.equalsIgnoreCase("th")) {
                    // Load th-th
                    tcUrl += "-" + countryCode + "-" + countryLanguage + "/";
                } else {
                    // Load th-en
                    tcUrl += "-" + countryCode + "-en/";
                }
            } else if (countryCode.equalsIgnoreCase("id")) {
                // In case of there is Indo, load id-id or id-en
                if (countryLanguage.equalsIgnoreCase("id")) {
                    // Load id-id
                    tcUrl += "-" + countryCode + "-" + countryLanguage + "/";
                } else {
                    // Load id-en
                    tcUrl += "-" + countryCode + "-en/";
                }
            } else if (countryCode.equalsIgnoreCase("ph")) {
                // special rule
                tcUrl = "https://www.deliveree.com/ph/terms-conditions-for-user/";
            } else {
                // Else, display the International language
                tcUrl += "/";
            }
        } else {
            // Else, display the International language
            tcUrl += "/";
        }
        return tcUrl;
    }

    // Get the privacy policy url depend on country code & country language
    public static String getPrivacyPolicyUrl(String countryCode, String countryLanguage) {
        String privacyPolicyUrl = Constant.PRIVACY_POLICY_URL;
        if (!TextUtils.isEmpty(countryCode) && (countryCode.equalsIgnoreCase("th") || countryCode.equalsIgnoreCase("id") || countryCode.equalsIgnoreCase("ph"))) {
            if (countryCode.equalsIgnoreCase("th")) {
                // In case of there is Thai, load th-th or th-en
                if (countryLanguage.equalsIgnoreCase("th")) {
                    // Load th
                    privacyPolicyUrl = String.format(privacyPolicyUrl, "th");
                } else {
                    // Load en
                    privacyPolicyUrl = String.format(privacyPolicyUrl, "th/en");
                }
            } else if (countryCode.equalsIgnoreCase("id")) {
                // In case of there is Indo, load id-id or id-en
                if (countryLanguage.equalsIgnoreCase("id")) {
                    // Load id
                    privacyPolicyUrl = String.format(privacyPolicyUrl, "id");
                } else {
                    // Load en
                    privacyPolicyUrl = String.format(privacyPolicyUrl, "id/en");
                }
            } else if (countryCode.equalsIgnoreCase("ph")) {
                // special rule
                privacyPolicyUrl = "https://www.deliveree.com/ph/privacy-policy/";
            } else {
                // Else, display the International language
                privacyPolicyUrl = String.format(privacyPolicyUrl, "th/en");
            }
        } else {
            // Else, display the International language
            privacyPolicyUrl = String.format(privacyPolicyUrl, "th/en");
        }
        return privacyPolicyUrl;
    }

    // Convert month to string
    public static String getMonth(int month, Context mContext) {
        //return new DateFormatSymbols().getMonths()[month];
        DateFormatSymbols symbols = new DateFormatSymbols(mContext.getResources().getConfiguration().locale);
        String[] monthNames = symbols.getMonths();
        return monthNames[month];
    }

    // Get millisecond
    public static long getMillisecond(String pattern, String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Date date = formatter.parse(dateString);
            long milli = date.getTime();
            return milli;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Get date
    public static String getDate(long miliTime, Context mContext) {
        return getDate(Constant.FORMAT_DATE_TIME, miliTime, mContext);
    }

    public static String getDate(String pattern, long milliTime, Context mContext) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, mContext.getResources().getConfiguration().locale);
        Date date = new Date();
        if (milliTime != 0) date = new Date(milliTime);
        return sdf.format(date);
    }

    // Get time
    public static String getTime(long miliTime, Context mContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", mContext.getResources().getConfiguration().locale);
        Date date = new Date();
        if (miliTime != 0) date = new Date(miliTime);
        return sdf.format(date);
    }

    // Get day
    public static String getDay(long miliTime, Context mContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", mContext.getResources().getConfiguration().locale);
        Date date = new Date();
        if (miliTime != 0) date = new Date(miliTime);
        return sdf.format(date);
    }

    // Decode poly
    public static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    // Store some type string
    public static void setStoredString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringStored(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(key, MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    // Get compress bitmap
    public static Bitmap getCompressedBitmap(Context context, Uri imageUri, int size) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
            return rotateImageIfNeed(context.getContentResolver(), bitmap, imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Compress file with bitmap
    public static File makeCompressFile(Context context, Bitmap bitmap) {
        if (bitmap == null) return null;

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Constant.DIRECTORY_SCALED, MODE_PRIVATE);
        // Create dir
        File path = new File(directory, "compress_file.png");
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Compress file with bitmap
    public static File makeCompressFileOnCacheDir(Context context, Bitmap bitmap, String fileName) {
        if (bitmap == null) return null;

        File filePrepareToSubmit = new File(context.getCacheDir(), fileName);
        try {
            filePrepareToSubmit.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapData = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(filePrepareToSubmit);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePrepareToSubmit;
    }

    // Rotate image
    public static Bitmap rotateImageIfNeed(ContentResolver resolver, Bitmap img, Uri selectedImage) {

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(getRealPathFromURI(resolver, selectedImage));
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotation = 0;
            if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
                rotation = 90;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
                rotation = 180;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
                rotation = 270;
            }
            if (rotation != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(rotation);
                Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
                img.recycle();
                return rotatedImg;
            } else {
                return img;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return img;
        }
    }

    // Get path image from uri
    private static String getRealPathFromURI(ContentResolver resolver, Uri contentUri) {
        String result;
        Cursor cursor = resolver.query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public static byte[] compressBitmapImage(Bitmap bm, long maxSizeInBytes,
                                             int maxWidth, int maxHeight) {

        // Scale image if image width is larger than maxWidth
        if (bm.getWidth() > maxWidth) {
            bm = Bitmap.createScaledBitmap(bm, maxWidth, (int) (1.0 * maxWidth
                    / bm.getWidth() * bm.getHeight()), false);
        }

        // Scale image if image height is larger than maxHeight
        if (bm.getHeight() > maxHeight) {
            bm = Bitmap.createScaledBitmap(bm, (int) (1.0 * maxHeight
                    / bm.getHeight() * bm.getWidth()), maxHeight, false);
        }

        // Compress image if image size is bigger than max size
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        int quality = 100;
        while (baos.size() > maxSizeInBytes) {
            quality -= 5;
            baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }

        return baos.toByteArray();

    }

    // To load LocationDriver
    public static boolean isTracking(String status) {
        String[] statuses = new String[]{"driver_accept_booking", "delivery_in_progress", "locating_driver",
                "cs_finding_driver", "waiting_for_customer"};
        for (int i = 0; i < statuses.length; i++) {
            if (status.equals(statuses[i]))
                return true;
        }
        return false;
    }

    public static void saveDeviceInfo(Context mContext, String deviceName, int androidOS, float dpi) {
        saveDeviceName(deviceName, mContext);
        saveAndroidOS(androidOS, mContext);
        saveDeviceScreenDPI(dpi, mContext);
    }

    // Device Name
    public static void saveDeviceName(String deviceName, Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constant.SHARED_DEVICE_NAME, deviceName);
        editor.commit();
    }

    // Device OS
    public static void saveAndroidOS(int androidOs, Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constant.SHARED_DEVICE_ANDROID_OS, androidOs + "");
        editor.commit();
    }

    // Device Screen dpi
    public static void saveDeviceScreenDPI(float dpi, Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (dpi == 0.75f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "ldpi");
        }
        if (dpi == 1.0f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "mdpi");
        }
        if (dpi == 1.5f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "hdpi");
        }
        if (dpi == 2.0f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "xhdpi");
        }
        if (dpi == 3.0f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "xxhdpi");
        }
        if (dpi == 4.0f) {
            editor.putString(Constant.SHARED_DEVICE_SCREEN_DPI, "xxxhdpi");
        }
        editor.commit();
    }

    public static String getDeviceId(Context mContext) {
        SharedPreferences driverPrefs = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, MODE_PRIVATE);
        SharedPreferences.Editor editor = driverPrefs.edit();

        String id = driverPrefs.getString(Constant.SHARED_DEVICE_ID, "");

        if (id.equals("")) {

            // Get Device Id
            id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);

            // If it is not available, get from telephone service(IMEI) instead
            if (TextUtils.isEmpty(id)) {
                id = ((TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
            }

            if (!TextUtils.isEmpty(id)) {
                // Write the value out to the Preferences file
                editor.putString(Constant.SHARED_DEVICE_ID, id).apply();
            } else {
                UUID uuid = UUID.randomUUID();
                String mUuid = uuid.toString();

                // Write the value out to the Preferences file
                id = mUuid;
                editor.putString(Constant.SHARED_DEVICE_ID, mUuid).apply();
            }
        }
        return id;
    }

    public static String getDeviceName(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, MODE_PRIVATE);
        return pref.getString(Constant.SHARED_DEVICE_NAME, "");
    }

    public static String getAndroidOS(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, MODE_PRIVATE);
        return pref.getString(Constant.SHARED_DEVICE_ANDROID_OS, "");
    }

    public static String getDeviceScreenDPI(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_SAVE_LOCATION, MODE_PRIVATE);
        return pref.getString(Constant.SHARED_DEVICE_SCREEN_DPI, "");
    }

    public static String uppercaseEachFirstCharacter(String source) {
        StringBuffer res = new StringBuffer();

        String[] strArr = source.split(" ");
        if (strArr.length > 0) {
            for (String str : strArr) {
                char[] stringArray = str.trim().toCharArray();
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                str = new String(stringArray);

                res.append(str).append(" ");
            }

            return res.toString().trim();
        }

        return source;
    }

    // Hide keyboard
    public static void hideKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception ex) {
        }
    }

    /**
     * Start any animation without animation-listener here
     *
     * @param context     The application context
     * @param viewAnim    The view that animated
     * @param animationId The ID of the animation XML
     */
    public static void startAnimation(Context context, View viewAnim, int animationId) {
        Animation animation = AnimationUtils.loadAnimation(context, animationId);
        viewAnim.startAnimation(animation);
    }

    /**
     * Start any animation with animation-listener
     *
     * @param context           The application context
     * @param viewAnim          The view that animated
     * @param animationId       The ID of the animation XML
     * @param animationListener The animation listener
     */
    public static void startAnimation(Context context, View viewAnim, int animationId, Animation.AnimationListener animationListener) {
        Animation animation = AnimationUtils.loadAnimation(context, animationId);
        animation.setAnimationListener(animationListener);
        viewAnim.startAnimation(animation);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static void fillLinearLayoutWithArrayAdapter(LinearLayout layout, BaseAdapter adapter) {
        layout.removeAllViews();
        for (int iAddress = 0; iAddress < adapter.getCount(); iAddress++) {
            View view = adapter.getView(iAddress, null, null);
            layout.addView(view);
        }
    }

    public static void fillLinearLayoutWeightWithArrayAdapter(LinearLayout layout, BaseAdapter adapter) {
        layout.removeAllViews();
        for (int iAddress = 0; iAddress < adapter.getCount(); iAddress++) {
            View view = adapter.getView(iAddress, null, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            layout.addView(view, layoutParams);
        }
    }

    public static void removeCurrentFragment(FragmentActivity fragmentActivity, int framelayout) {
        try {
            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fm.findFragmentById(framelayout)).commit();
        } catch (Exception exp) {
        }
    }

    public static String getCurrentFragmentTag(FragmentActivity fragmentActivity, int frameLayout) {
        return fragmentActivity.getSupportFragmentManager().findFragmentById(frameLayout).getTag();
    }

    /**
     * Moving the Marker (the Driver icon) on the map
     *
     * @param marker
     * @param toPosition
     * @param hideMarker
     * @param map
     */
    public static void animateMarker(final Marker marker, final LatLng toPosition, final int toRotation, final boolean isRotate, final boolean hideMarker, GoogleMap map) {

        if (toPosition == null) return;

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = map.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
//        final long duration = 10 * 1000;

        if (startLatLng == null) return;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float startRotation = marker.getRotation();
                double t = interpolator.getInterpolation((float) elapsed / Constant.TRACKING_DRIVER_INTERVAL); //duration);
                double lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t) * startLatLng.latitude;

                marker.setPosition(new LatLng(lat, lng));

                if (isRotate) {
                    float rot = (float) (t * toRotation + (1 - t) * startRotation);
                    marker.setRotation(-rot > 180 ? rot / 2 : rot);
                }
                // Check the distance between current and the previous
//                Location startLocation = new Location("");
//                startLocation.setLatitude(lat);
//                startLocation.setLongitude(lng);
//                Location endLocation = new Location("");
//                endLocation.setLatitude(toPosition.latitude);
//                endLocation.setLongitude(toPosition.longitude);
//                float distance = endLocation.distanceTo(startLocation);

                if (t < 1.0) { // && distance > 1) {
                    // Post again 16ms later if the distance is big enough to move
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }

    /**
     * Compares two version strings.
     * <p/>
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    public static Integer versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        else {
            return Integer.signum(vals1.length - vals2.length);
        }
    }

    public static String replaceAccents(String string) {
        String result = null;

        if (string != null) {
            result = string;

            result = result.replaceAll("[àáâãåä]", "a");
            result = result.replaceAll("[ç]", "c");
            result = result.replaceAll("[èéêë]", "e");
            result = result.replaceAll("[ìíîï]", "i");
            result = result.replaceAll("[ñ]", "n");
            result = result.replaceAll("[òóôõö]", "o");
            result = result.replaceAll("[ùúûü]", "u");
            result = result.replaceAll("[ÿý]", "y");

            result = result.replaceAll("[ÀÁÂÃÅÄ]", "A");
            result = result.replaceAll("[Ç]", "C");
            result = result.replaceAll("[ÈÉÊË]", "E");
            result = result.replaceAll("[ÌÍÎÏ]", "I");
            result = result.replaceAll("[Ñ]", "N");
            result = result.replaceAll("[ÒÓÔÕÖ]", "O");
            result = result.replaceAll("[ÙÚÛÜ]", "U");
            result = result.replaceAll("[Ý]", "Y");
        }

        return result;
    }

    public static String getFeeAsString(Context context, double fee, String symbolCurrency, boolean isNoMoney) {
        NumberFormat df = DecimalFormat.getCurrencyInstance(getLocale(symbolCurrency));
        df.setMaximumFractionDigits(0);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();

        // Fix bug crash when symbolCurrency null, we set that currency to empty so that it will do with default currency
        if (symbolCurrency == null) {
            symbolCurrency = "";
        }

        if (symbolCurrency.equalsIgnoreCase("₫") || symbolCurrency.equalsIgnoreCase("₱"))
            dfs.setCurrencySymbol("");
        else
            dfs.setCurrencySymbol(symbolCurrency);
        ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
        String totalFee = df.format(fee);
        if (symbolCurrency.equalsIgnoreCase("₫"))
            totalFee += symbolCurrency;

        if (symbolCurrency.equalsIgnoreCase("₱"))
            totalFee = symbolCurrency + totalFee;
        if (fee != 0) {
            return totalFee;
        } else if (isNoMoney) {
            return totalFee;
        } else
            return "-";
    }

    public static Locale getLocale(String symbolCurrency) {
        Locale locale;
        if (symbolCurrency == null) {
            // If there something wrong here, get the default local to fix
            locale = new Locale("th", "TH"); // Thailand
            return locale;
        }
        switch (symbolCurrency) {
            case "₱":
                locale = new Locale("ph", "PH"); // Philippines
                break;
            case "Rp":
                locale = new Locale("in", "ID"); // Indonesian
                break;
            case "฿":
                locale = new Locale("th", "TH"); // Thailand
                break;
            case "₫":
                locale = new Locale("vi", "VN"); // VietName
                break;
            default:
//                locale = Locale.getDefault();
                locale = new Locale("th", "TH"); // Thailand
        }
        return locale;
    }

    // ******************************************************************
    private static DisplayMetrics getCurrentDisplayMetrics(Context context)
    // ******************************************************************
    {
        WindowManager sWindowManager = (WindowManager) context
                .getSystemService(WINDOW_SERVICE);
        Display display = sWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * Get the screen width specification of device
     *
     * @return the screen width specification in pixels
     */
    // ******************************************************************
    public static int getScreenWidth(Context context)
    // ******************************************************************
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics(context);
        return Math.min(displayMetrics.widthPixels,
                displayMetrics.heightPixels);
    }

    /**
     * Get the screen height specification of device
     *
     * @return the screen height specification in pixels
     */
    // ******************************************************************
    public static int getScreenHeight(Context context)
    // ******************************************************************
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics(context);
        return Math.max(displayMetrics.widthPixels,
                displayMetrics.heightPixels);
    }

    // convert inputstream to String
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    // Static class to hide the soft keyboard (used to close keyboard when touch outside)
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    // Check the view to set the hiding action
    public static void setupHidingKeyboard(View view, final Activity activity) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupHidingKeyboard(innerView, activity);
            }
        }
    }

    ///////////////////////////////////////////
    // Nghiep.Nguyen - Nov 18th, 2015
    // Use to add add/less button in textview
    public static void makeTextViewResizable(final Context mContext, final TextView tv, final String content, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(content);
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (tv.getLineCount() > 1) {
                    if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                        int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                        if (lineEndIndex - (expandText.length() + 1) > 0) {
                            String text = content.subSequence(0, lineEndIndex - (expandText.length() + 1)) + " " + expandText;
                            tv.setText(text);
                            tv.setMovementMethod(LinkMovementMethod.getInstance());
                            tv.setText(addClickablePartTextViewResizable(mContext, Html.fromHtml(tv.getText().toString()), tv, content, maxLine, expandText, viewMore), TextView.BufferType.SPANNABLE);
                        } else {
                            tv.setText(content);
                        }
                    } else {
                        int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                        if (lineEndIndex > 0) {
                            String text = content.subSequence(0, lineEndIndex) + " " + expandText;
                            tv.setText(text);
                            tv.setMovementMethod(LinkMovementMethod.getInstance());
                            tv.setText(addClickablePartTextViewResizable(mContext, Html.fromHtml(tv.getText().toString()), tv, content, lineEndIndex, expandText, viewMore), TextView.BufferType.SPANNABLE);
                        } else {
                            tv.setText(content);
                        }
                    }
                } else {
                    tv.setText(content);
                }
            }
        });

    }

    public static SpannableStringBuilder addClickablePartTextViewResizable(final Context mContext, final Spanned strSpanned, final TextView tv, final String content,
                                                                           final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            // Set content & clickable for the spannable texts
            ssb.setSpan(new MySpannable(mContext, false) {
                @Override
                public void onClick(View widget) {
                    // Do nothing
//                    expand_collapse_texts(mContext, tv, content, viewMore);
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

            // Set clickable for content
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand_collapse_texts(mContext, tv, content, viewMore);
                }
            });
        }
        return ssb;
    }

    private static void expand_collapse_texts(Context mContext, TextView tv, String content, boolean viewMore) {
        if (viewMore) {
            tv.setLayoutParams(tv.getLayoutParams());
            tv.setText(content, TextView.BufferType.SPANNABLE);
            makeTextViewResizable(mContext, tv, content, Integer.MAX_VALUE, "less", false);
        } else {
            tv.setLayoutParams(tv.getLayoutParams());
            tv.setText(content, TextView.BufferType.SPANNABLE);
            makeTextViewResizable(mContext, tv, content, 1, " ..." + "more", true);
        }
    }

    ///////////////////////////////////////////
    // Nghiep.Nguyen - Jan 27th, 2016
    // Show dial screen
    public static void showDial(Context mContext, String phoneNumber) {
        Intent intentCs = new Intent(Intent.ACTION_DIAL);
        intentCs.setData(Uri.parse("tel:" + phoneNumber));
        mContext.startActivity(intentCs);
    }

    ///////////////////////////////////////////
    // Nghiep.Nguyen - Feb 18th, 2016
    // create bitmap form view
    public static Bitmap createBitmapFromView(Context mContext, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    public static Bitmap createBitmapFromXMLForNotification(Context mContext, int layoutId) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        View cluster = LayoutInflater.from(mContext).inflate(layoutId, null);
        cluster.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = Constant.dpToPx(300, mContext); //displaymetrics.widthPixels; //Constant.dpToPx(displaymetrics.widthPixels, mContext);
        int height = Constant.dpToPx(150, mContext); //displaymetrics.widthPixels/2; //Constant.dpToPx(256, mContext);
        cluster.layout(0, 0, width, height);
        final Bitmap clusterBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(clusterBitmap);
        cluster.draw(canvas);
        return clusterBitmap;
    }

    public static void convertMilisecondToCountDown(int value, TextView textView1, TextView textView2) {

        if (value >= 10) {
            int vallue1 = value / 10;
            int vallue2 = value % 10;
            textView1.setText(String.valueOf(vallue1));
            textView2.setText(String.valueOf(vallue2));
        } else {
            int vallue2 = value % 10;
            textView1.setText(String.valueOf(0));
            textView2.setText(String.valueOf(vallue2));
        }
    }

    public static String formatMilliSecondsToTime(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        String txtHour = twoDigitString(hours);
        String txtMinutes = twoDigitString(minutes);
        String txtSeconds = twoDigitString(seconds);

        String time = "";
        if (txtHour != null)
            time += txtHour;

        if (txtMinutes != null) {
            if (time.isEmpty())
                time = txtMinutes;
            else
                time += ":" + txtMinutes;
        }

        if (txtSeconds != null) {
            if (time.isEmpty())
                time = txtSeconds;
            else {
                time += ":" + txtSeconds;
            }
        }

        return time;
    }

    public static String twoDigitString(long number) {
        if (number <= 0) {
            return null;
        }
        if (number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    ///////////////////////////////////////////
    // Nghiep.Nguyen - August 17th, 2016
    public static int roundDistanceToValue(int meterValue) {
        int value = 0;
        if (meterValue / 1000 >= 1) {
            value = Math.round((float) meterValue / 1000);
        } else {
            value = meterValue;
        }
        return value;
    }

    /**
     * @param context
     * @param appPackageName
     */
    public static void startGooglePlayStore(Context context, String appPackageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}