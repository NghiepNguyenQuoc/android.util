package com.nghiepnguyen.androidutils.utils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by W10-PRO on 29-Sep-16.
 */

public class BaseObservable extends Observable {
    private static BaseObservable _instance;
    private static String key = "";

    public static BaseObservable getInstance(){
        if (_instance == null)
            _instance = new BaseObservable();
        return _instance;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        BaseObservable.key = key;
    }

    /**
     * Notify Observers wherever listening
     * @param object Object has modified
     */
    public void setNotifyObservers(Object object, String key) {
        setKey(key);
        setChanged();
        notifyObservers(object);
    }

    /**
     * Add observer to listener notify from Observable
     * @param observer
     */
    public void register(Observer observer) {
        _instance.addObserver(observer);
    }

    /**
     * Delete observer that listening
     * @param observer
     */
    public void unregister(Observer observer) {
        _instance.deleteObserver(observer);
    }

}

