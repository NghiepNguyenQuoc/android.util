package com.nghiepnguyen.androidutils.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.android.gms.maps.model.LatLng;

public class Constant {
    public static final int MAX_LOCATION_INPUT = 11;
    public static final int ZOOM_LEVEL_OVER = 4;
    public static final float ZOOM_LEVEL_NEW = 10.8f;
    public static final float ZOOM_LEVEL = 15f;
    public static final int ZOOM_PADDING = 120;
    public static final int ZOOM_PADDING_SMALL = 60;
    public static final LatLng DEFAULT_LATLNG = new LatLng(13.727726, 100.5281952);
    public static final LatLng DEFAULT_LATLNG_ASIA = new LatLng(10.8230989, 106.6296638);
    public static final String TERM_OF_SERVICE_URL = "https://www.deliveree.com/terms-conditions-user";
    public static final String PRIVACY_POLICY_URL = "https://www.deliveree.com/%s/privacy-policy/";
    public static final String CUSTOMER_HELP_URL = "http://deliveree.com/customer_help.html";
    //  public static final String FIREBASE_URL = Urls.FIREBASE_URL; //"https://deliveree.firebaseio.com/production/available_bookings";
    public static final int CODE_CANNOT_CONNECT_INTERNET = 4004;

    public static final float MIN_RATING = (float) 3.0;
    public static final String TITLE = "TITLE";
    public static final String CURRENT_POSITION_IN_LOCATION_ADAPTER = "CURRENT_POSITION_IN_LOCATION_ADAPTER";
    public static final String DIRECTORY_SCALED = "DIRECTORY_SCALED";
    public static final String DIRECTORY_HOLIDAY_IMAGE = "DIRECTORY_HOLIDAY_IMAGE";
    public static final String FILE_SCALED = "FILE_SCALED";

    public static final String DELIVEREE_APP_DEV = "Deliveree Dev";
    public static final String DELIVEREE_APP_PRODUCTION = "Deliveree Production";

    // ================================================
    // AppsFlyer & Flurry tracking variables
    // ================================================

    public static final String FLURRY_API_KEY = "9T73S3YDJZW973NY879D";
    public static final String FLURRY_EMAIL_KEY = "Email";
    public static final String FLURRY_PHONE_KEY = "Phone";
    public static final String FLURRY_BOOKINGTYPE_KEY = "BookingType";
    public static final String APPSFLYER_SIGNUP_EVENT_FROM_LEFT_MENU_NAME = "SignUpFromLeftMenu";
    public static final String APPSFLYER_SIGNUP_EVENT_AFTER_BOOKING_NAME = "SignUpAfterBooking";
    public static final String APPSFLYER_SIGNIN_EVENT_NAME = "SignIn";
    public static final String APPSFLYER_SIGNIN_EVENT_AFTER_BOOKING_NAME = "SignInAfterBooking";
    public static final String APPSFLYER_SIGNOUT_EVENT_NAME = "SignOut";
    public static final String APPSFLYER_PICKUP_EVENT_NAME = "PickupPress";
    public static final String APPSFLYER_DROPOFF_EVENT_NAME = "DropoffPress";
    public static final String APPSFLYER_ADD_DROPOFF_EVENT_NAME = "AddDropoffPress";
    public static final String APPSFLYER_BOOKING_3_CONFIRM_SHOWSUP_EVENT_NAME = "Booking3_ConfirmShowsUp";
    public static final String APPSFLYER_BOOKING_3_CONFIRM_FAIL_EVENT_NAME = "Booking3_ConfirmPressFailed";
    public static final String APPSFLYER_BOOKING_3_CONFIRM_BEFORE_SIGNIN_EVENT_NAME = "Booking3_ConfirmPressBeforeSignedIn";
    public static final String APPSFLYER_BOOKING_3_CONFIRM_AFTER_SIGNIN_EVENT_NAME = "Booking3_ConfirmPressAfterSignedIn";
    public static final String APPSFLYER_MAP_SHOWSUP_EVENT_NAME = "MapShowsUp";
    public static final String APPSFLYER_BOOKING_1_ROUTE_PRESS_EVENT_NAME = "Booking1_RoutePress";
    public static final String APPSFLYER_BOOKING_1_IMMEDIATE_PRESS_EVENT_NAME = "Booking1_ImmediatePress";
    public static final String APPSFLYER_BOOKING_1_SCHEDULE_EVENT_NAME = "Booking1_ScheduledPress";
    public static final String APPSFLYER_BOOKING_2_SERVICE_SHOWSUP_EVENT_NAME = "Booking2_ServiceShowsUp";
    public static final String APPSFLYER_BOOKING_2_SERVICE_PRESS_EVENT_NAME = "Booking2_ServicePress";
    public static final String APPSFLYER_POPUP_SIGNIN_PRESS_EVENT_NAME = "PopupSignInPress";
    public static final String APPSFLYER_POPUP_NOTNOW_PRESS_EVENT_NAME = "PopupNotNowPress";
    public static final String APPSFLYER_LOCATING_DRIVER_EVENT_NAME = "LocatingDriver";
    public static final String APPSFLYER_DRIVER_FOUND_EVENT_NAME = "DriverFound";
    public static final String APPSFLYER_CANCEL_BOOKING_BEFORE_DRIVER_ACCEPT_EVENT_NAME = "CancelBookingPressBeforeDriverAccept";
    public static final String APPSFLYER_CANCEL_BOOKING_AFTER_DRIVER_ACCEPT_EVENT_NAME = "CancelBookingPressAfterDriverAccept";
    public static final String APPSFLYER_RATING_DRIVER_SHOWSUP_EVENT_NAME = "RatingDriverShowsUp";
    public static final String APPSFLYER_RATING_DRIVER_SUBMIT_EVENT_NAME = "RatingDriverSubmit";
    public static final String APPSFLYER_RATING_APP_NOTHANKS_EVENT_NAME = "NoThanksPress";
    public static final String APPSFLYER_RATING_APP_MAYBE_LATER_EVENT_NAME = "MaybeLaterPress";
    public static final String APPSFLYER_RATING_APP_SUBMIT_EVENT_NAME = "RatingAppPress";
    public static final String FLURRY_BOOKING_STEP_2_EVENT_NAME = "BookingLocationPress";
    public static final String APPSFLYER_BOOKING_STEP_2_EVENT_NAME = "Booking Step 2";

    // ===========================================================
    // Cancel Scops
    // ===========================================================

    public static final String CANCELATION_SCOPE_BEFORE_ACCEPTANCE = "customer_cancelation_before_acceptance";
    public static final String CANCELATION_SCOPE_AFTER_ACCEPTANCE = "customer_cancelation";

    // ===========================================================
    // Header
    // ===========================================================

    public static final String HEADER_APP_NAME = "App-Name";
    public static final String HEADER_DEVICE_ID = "Device-Id";
    public static final String HEADER_DEVICE_TYPE = "Device-Type";
    public static final String HEADER_DEVICE_NAME = "Device-Name";
    public static final String HEADER_OS_VERSION = "OS-Version";
    public static final String HEADER_SCREEN_DPI = "Screen-Dpi";
    public static final String HEADER_APP_VERSION = "App-Version";
    public static final String HEADER_DEVICE_TOKEN = "Device-Token";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_COUNTRY_CODE = "Country-Code";
    public static final String HEADER_APPSFLYER_ID = "Appsflyer-UID";
    public static final String HEADER_ETAG = "ETag";
    public static final String HEADER_IF_NONE_MATCH = "If-None-Match";

    // The minimum distance to change Updates in meters
    public static final int MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    public static final int MAX_RECENT_LOCATION_COUNT = 10; // 10 locations

    // The minimum time between updates in milliseconds
    public static final int MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // The delay time to submit tracking event (for AppsFlyer have problem)
    public static final int DELAY_SUBMIT_TRACKING = 1000 * 30; // 30 seconds

    // The repeat time of checking location of Driver
    public static final int TRACKING_DRIVER_INTERVAL = 6000;

    public static final String FORGET_PASSWORD = "ForgetPasswordActivity";
    public static final String CHOOSE_LOCATION = "ChooseLocationActivity";
    public static final String BOOKING_DETAIL = "BookingHistoryDetailActivity";
    public static final String EDIT_PROFILE = "EditProfileActivity";
    public static final String EDIT_PHONE = "EditPhoneActivity";
    public static final String WEB_VIEW = "WebViewActivity";
    public static final String BOOKING_UPCOMING_DETAIL = "BookingUpcomingDetailActivity";
    public static final String COUNTRY_ACTIVITY = "CountryActivity";
    public static final String CHANGE_PASSWORD_ACTIVITY = "ChangePasswordActivity";
    public static final String PASS_CODE_ACTIVITY = "PassCodeActivity";

    public static final String BUNDLE_POSITION = "POSITION";
    public static final String BUNDLE_NAME = "NAME";
    public static final String BUNDLE_CUSTOMER = "CUSTOMER MODEL";
    public static final String BUNDLE_PHONE = "PHONE";
    public static final String BUNDLE_EMAIL = "EMAIL";
    public static final String BUNDLE_PASSWORD = "PASSWORD";
    public static final String BUNDLE_DROP_OFF_LOCATION = "DROP_OFF_LOCATION";
    public static final String BUNDLE_ACCESS_TOKEN = "BUNDLE_ACCESS_TOKEN";
    public static final String BUNDLE_BOOKING = "BUNDLE_BOOKING";
    public static final String BUNDLE_NUMBER_OF_DROPOFF = "BUNDLE_OF_DROPOFF";
    public static final String BUNDLE_BOOKING_VEHICLE_EXTRA = "BUNDLE_BOOKING_VEHICLE_EXTRA";
    public static final String BUNDLE_BOOKING_CURRENCY_SYMBOL = "BUNDLE_BOOKING_CURRENCY_SYMBOL";
    public static final String BUNDLE_BOOKING_ID = "BUNDLE_BOOKING_ID";
    public static final String BUNDLE_COMPANY_ID = "BUNDLE_COMPANY_ID";
    public static final String BUNDLE_BOOK_AGAIN = "BUNDLE_BOOK_AGAIN";
    public static final String BUNDLE_IS_LOCATION_DRIVER_VIEW = "IS_LOCATION_DRIVER_VIEW";
    public static final String BUNDLE_IS_FROM_UPCOMING = "IS_FROM_UPCOMING";
    public static final String BUNDLE_IS_CANCEL_DRIVER = "IS_CANCEL_DRIVER";
    public static final String BUNDLE_IS_FINISH_JOB = "IS_FINISH_JOB";
    public static final String BUNDLE_IS_HISTORY_VIEW = "BUNDLE_IS_HISTORY_VIEW";
    public static final String BUNDLE_FROM_LOCATION = "FROM_LOCATION";
    public static final String BUNDLE_VIA_LOCATION = "VIA_LOCATION";
    public static final String BUNDLE_SIGNUP_FROM = "SIGNUP_FROM";
    public static final String BUNDLE_MANUAL_NOTIFICATION_TITLE = "BUNDLE_MANUAL_NOTIFICATION_TITLE";
    public static final String BUNDLE_MANUAL_NOTIFICATION_MESSAGE = "BUNDLE_MANUAL_NOTIFICATION_MESSAGE";
    public static final String BUNDLE_ON_BACK_FROM_REVIEW = "ON_BACK_FROM_REVIEW";
    public static final String BUNDLE_ENABLE_PARKING_TOLL_FEATURE = "BUNDLE_ENABLE_PARKING_TOLL_FEATURE";
    public static final String BUNDLE_ENABLE_COD_POD_NEW_RULE = "BUNDLE_ENABLE_COD_POD_NEW_RULE";
    public static final String BUNDLE_COMPANY_SETTING = "BUNDLE_COMPANY_SETTING";
    public static final String BUNDLE_FREE_WAITING_TIME = "BUNDLE_FREE_WAITING_TIME";
    public static final String BUNDLE_ATTACHMENT_IMAGE = "BUNDLE_ATTACHMENT_IMAGE";
    public static final String BUNDLE_ATTACHMENT_INDEX = "BUNDLE_ATTACHMENT_INDEX";
    public static final String BUNDLE_ATTACHMENT_URL = "BUNDLE_ATTACHMENT_URL";
    public static final String BUNDLE_ATTACHMENT_PDF = "BUNDLE_ATTACHMENT_PDF";
    public static final String BUNDLE_ATTACHMENT_IS_FILE = "BUNDLE_ATTACHMENT_IS_FILE";

    public static final int KEY_ACTIVITY_RESULT_FROM_VIEW = 40;
    public static final int KEY_ACTIVITY_RESULT_CANCEL_BOOKING = 46;
    public static final int KEY_ACTIVITY_RESULT_SIGN_IN = 47;
    public static final int KEY_ACTIVITY_RESULT_SETTING = 48;
    public static final int PICK_CONTACT = 100;
    public static final int REQUEST_CODE_SEND_EMAIL = 101;

    public static final String SHARED_PREFERENCE_NAME = "NAME_USER";
    public static final String SHARED_PREFERENCE_FIRST_NAME = "FIRST_NAME_USER";
    public static final String SHARED_PREFERENCE_LAST_NAME = "LAST_NAME_USER";
    public static final String SHARED_PREFERENCE_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String SHARED_PREFERENCE_CHANGED_PHONE = "CHANGED_PHONE";
    public static final String SHARED_PREFERENCE_PHONE = "PHONE_USER";
    public static final String SHARED_PREFERENCE_EMAIL = "EMAIL_USER";
    public static final String SHARED_PREFERENCE_MUST_UPDATE_PASSWORD = "MUST_RESET_PASSWORD";
    public static final String SHARED_AVATAR_FILE_NAME = "AVATAR_FILE_NAME";
    public static final String SHARED_TEMP_CAPTURE_IMAGE = "SHARED_TEMP_CAPTURE_IMAGE";
    public static final String SHARED_EVENT_DIALOG_ID = "SHARED_EVENT_DIALOG_ID";
    public static final String SHARED_TRAINING_OVERLAY_STEP_1 = "SHARED_TRAINING_OVERLAY_STEP_1";
    public static final String SHARED_TRAINING_OVERLAY_STEP_3 = "SHARED_TRAINING_OVERLAY_STEP_3";
    public static final String SHARED_SHOW_BUBBLE_DISCOUNT = "SHARED_SHOW_BUBBLE_DISCOUNT";
    public static final String SHARED_SHOW_BUBBLE_DISCOUNT_TIMES = "SHARED_SHOW_BUBBLE_DISCOUNT_TIMES";
    public static final String SHARED_SHARING_DELIVEREE = "SHARED_SHARING_DELIVEREE";
    public static final String SHARED_BACKUP_COUNTRY_CODE = "SHARED_BACKUP_COUNTRY_CODE";
    public static final String SHARED_CUSTOMER_ID = "CUSTOMER_ID";
    public static final String SHARED_DEVICE_LANGUAGE = "DEVICE_LANGUAGE";
    public static final String SHARED_RECENT_LOCATION_LIST = "SHARED_RECENT_LOCATION_LIST";
    public static final String SHARED_SAVE_LOCATION = "SHARED_SAVE_LOCATION";
    public static final String SHARED_DEVICE_NAME = "SHARED_DEVICE_NAME";
    public static final String SHARED_DEVICE_SCREEN_DPI = "SHARED_DEVICE_SCREEN_DPI";
    public static final String SHARED_DEVICE_ANDROID_OS = "SHARED_DEVICE_ANDROID_OS";
    public static final String SHARED_PREFERENCE_CURRENT_USER = "CURRENT USER INFO";
    public static final String SHARED_PREFERENCE_AGAIN_BOOKING = "SHARED_PREFERENCE_AGAIN_BOOKING";
    public static final String SHARED_PREFERENCE_FORGOT_REMAINING_TIME = "FORGOT REMAINING TIME";
    public static final String SHARED_DEVICE_ID = "SHARED_DEVICE_ID";
    public static final String SHARED_COUNTRY_SELECTED = "SHARED_COUNTRY_SELECTED";
    public static final String SHARED_COUNTRY = "SHARED_COUNTRY";
    public static final String SHARED_ETAG = "SHARED_ETAG";
    public static final String SHARED_DEVICE_TOKEN = "SHARED_DEVICE_TOKEN";
    public static final String SHARED_LOGIN_CATCHED_LIST = "SHARED_LOGIN_CATCHED_LIST";
    public static final String SHARED_LATEST_LOGIN_NAME = "SHARED_LATEST_LOGIN_NAME";
    public static final String SHARED_APPLICATION_LAUNCH_TIME = "SHARED_APPLICATION_LAUNCH_TIME";
    public static final String SHARED_APPLICATION_MANUAL_NOTIFICATION = "SHARED_APPLICATION_MANUAL_NOTIFICATION";
    public static final String SHARED_ALARM = "SHARED_ALARM";
    public static final String SHARED_ALARM_NOTIFY_ID = "SHARED_ALARM_NOTIFY_ID";
    public static final String SHARED_SERVICE_SELECTED = "SHARED_SERVICE_SELECTED";
    public static final String SHARED_IN_PROGRESS_BOOKINGID = "SHARED_IN_PROGRESS_BOOKINGID";
    public static final String SHARED_ENABLE_TRACKING_EVENT = "SHARED_ENABLE_TRACKING_EVENT";
    public static final String SHARED_TRACKING_EVENT_LIST = "SHARED_TRACKING_EVENT_LIST";
    public static final String SHARED_SERVICE_AREA_ID = "SHARED_SERVICE_AREA_ID";
    public static final String SHARED_TIME_CLICKED_LATER = "SHARED_TIME_CLICKED_LATER";
    public static final String SHARED_LOCAL_HOST_URL = "SHARED_LOCAL_HOST_URL";
    public static final String SHARED_TERM_OF_SERVICE_URL = "SHARED_TERM_OF_SERVICE_URL";
    public static final String SHARED_PRIVACY_POLICY_URL = "SHARED_PRIVACY_POLICY_URL";
    public static final String SHARED_SEND_SMS_TO_RECIPIENTS = "SHARED_SEND_SMS_TO_RECIPIENTS";
    public static final String SHARED_ALREADY_SEND_SMS_TO_RECIPIENTS = "SHARED_ALREADY_SEND_SMS_TO_RECIPIENTS";
    public static final String SHARED_DEVINA_NAME_LIST = "SHARED_DEVINA_NAME_LIST";

    public static final String TAG_SERVICE_CALLBACK = "ServiceAdapter";
    public static final String TAG_VEHICLE_CALLBACK = "VehicleAdapter";
    public static final String TAG_REQUIREMENT_CALLBACK = "ExtraRequirementAdapter";
    public static final String PARSE_BOOKING_ID = "PARSE_BOOKING_ID";

    public static final String STRING_TYPE_UPDATE_PROFILE = "TYPE_UPDATE_PROFILE";
    public static final String STRING_TYPE_UPDATE_PASSWORD = "TYPE_UPDATE_PASSWORD";
    public static final String STRING_TYPE_UPDATE_PHONE = "TYPE_UPDATE_PHONE";

    public static final String STRING_TIME_TYPE_IMMEDIATE = "now";
    public static final String STRING_TIME_TYPE_SCHEDULE = "schedule";

    public static final String STRING_IMAGE_URL_MISSING = "missing.png";
    public static final int    HTTP_UNAUTHORIZED = 401;
    public static final String HTTP_UNAUTHORIZED_TEXT = "java.io.IOException: No authentication challenges found";

    public static final int MY_DELIVERY_PAGE_SIZE = 25;

    public static final String CHANGE_PASSWORD_HAS_RESET = "CHANGE PASSWORD HAS RESET";

    public static final int SEARCH_INTERVAL = 500;

    public static final String KEY_COUNTRY_CODE = "COUNTRY_CODE_IP";
    public static final String KEY_AREA_ID = "KEY_AREA_ID";

    public static final String EVENT_NOTIFY_BOOKING_LOCATING_DRIVER = "booking_locating_driver";
    public static final String EVENT_NOTIFY_BOOKING_TIMEOUT = "booking_locating_driver_timeout";
    public static final String EVENT_NOTIFY_BOOKING_DRIVER_FOUND = "booking_driver_accept_booking";
    public static final String EVENT_NOTIFY_BOOKING_IN_PROGRESS = "booking_delivery_in_progress";
    public static final String EVENT_NOTIFY_BOOKING_COMPLETE = "booking_delivery_completed";
    public static final String EVENT_NOTIFY_BOOKING_UNDELIVERABLE = "booking_undeliverable";
    public static final String EVENT_NOTIFY_BOOKING_WAITING_FOR_CUSTOMER = "booking_waiting_for_customer";

    public static final String OBSERVER_NOTIFY_BOOKING_COMPLETED = "OBSERVER_NOTIFY_BOOKING_COMPLETED";
    public static final String OBSERVER_NOTIFY_DEVINA = "OBSERVER_NOTIFY_DEVINA";

    public static final int NOTIFY_ACCEPTED_COMPLETED_ID = 2000;
    public static final int NOTIFY_COMMON_ID = 2001;
    public static final int NOTIFY_DEVINA = 3000;
    public static final String APPSFLYER_KEY_EVENT_NAME_GUESTS = "EVENT_NAME_GUESTS";

    public static final long TIME_1_MINUTE = 60 * 1000;
    public static final long TIME_1_HOURS = 60 * 60 * 1000;
    public static final long TIME_24_HOURS = 24 * 60 * 60 * 1000;
    public static final long TIME_120_MINUTE = 2 * 60 * 1000;
    public static final long TIME_3_DAYS = 3 * 24 * 60 * 60 * 1000;


    public static final long SCHEDULE_TIME_ABOUT_TWO_HOURS = 2 * 60 * 60 * 1000;
    public static final int SCHEDULE_NOTIFICATION_ID = 100;
    public static final long IMMEDIATE_TIME_ABOUT_TEN_MINUTE = 10 * 60 * 1000;

    public static final String FORMAT_DATE_TIME = "HH:mm MMMM dd, yyyy";
    public static final String FORMAT_24_HOURS = "H:mm";
    public static final String FORMAT_12_HOURS = "K:mm a";
    public static final String TRACKING_SCREEN_NAME_STEP_1 = "Route";
    public static final String TRACKING_SCREEN_NAME_STEP_2 = "Service";
    public static final String TRACKING_SCREEN_NAME_STEP_3 = "Review";
    // ===========================================================
    // Image upload config
    // ==================================1500=====================
    public static final int IMAGE_MAX_WIDTH = 1024;
    public static final int IMAGE_MAX_HEIGHT = 1024;
    public static final long IMAGE_MAX_FILE_SIZE = 1 * 1024 * 1024; // 1MPx
    public static final long IMAGE_MAX_FILE_SIZE_UPLOAD = 12 * 1024 * 1024; // 12Mb
    public static final int IMAGE_MIN_WIDTH = 400;
    public static final int IMAGE_MIN_HEIGHT = 400;
    public static final long IMAGE_MIN_FILE_SIZE = 1 * 400 * 400; // 1MPx

    public static final int MAX_UPLOAD_FILE = 3;
    public static final String MIME_TYPE_PDF   = "application/pdf";
    public static final String MIME_TYPE_IMAGE = "image/jpeg";

    public static final int CODE_ASK_PERMISSIONS_READ_EXTERNAL_STORAGE = 10;
    public static final int CODE_ASK_PERMISSIONS_LOCATION_SERICE       = 11;
    public static final int CODE_ASK_PERMISSIONS_CALL_PHONE            = 12;
    public static final int CODE_ASK_PERMISSIONS_GET_ACCOUNTS          = 13;
    public static final String OBSERVER_UPDATE_DATA_KEY_PERMISSION_RESULT    = "OBSERVER_UPDATE_DATA_KEY_PERMISSION_RESULT";

    // Convert dp to px
    public static int dpToPx(int dp, Context context) {
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int px = (int) (dp * displayMetrics.density + 0.5);
            return px;
        }
        return dp;
    }

}