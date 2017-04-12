package com.zsoltdavid.weatherlocation.data.local;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.zsoltdavid.weatherlocation.data.local.RxEventBus.RxEvent.RxEventId.GOOGLE_API_CONNECTED;
import static com.zsoltdavid.weatherlocation.data.local.RxEventBus.RxEvent.RxEventId.GOOGLE_API_CONNECTION_ERROR;

/**
 * Created by Admin on 2017.04.06..
 */

public class RxEventBus {
    private static volatile RxEventBus instance;
    private final PublishSubject<RxEvent> bus;

    private RxEventBus() {
        bus = PublishSubject.create();
    }

    public static RxEventBus getInstance() {
        if (instance == null) {
            synchronized (RxEventBus.class) {
                if (instance == null)
                    instance = new RxEventBus();
            }
        }

        return instance;
    }

    public void send(RxEvent event) {
        bus.onNext(event);
    }

    public void register(Consumer<RxEvent> consumer) {
        bus.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(consumer);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

    public static class RxEvent {
        @Retention(RetentionPolicy.SOURCE)
        @IntDef({GOOGLE_API_CONNECTED, GOOGLE_API_CONNECTION_ERROR})
        public @interface RxEventId {
            int GOOGLE_API_CONNECTED = 0;
            int GOOGLE_API_CONNECTION_ERROR = 1;
        }

        @RxEvent.RxEventId
        private int eventId;
        private String message;

        private RxEvent(int eventId) {
            this.eventId = eventId;
        }

        private RxEvent(int eventId, String message) {
            this.eventId = eventId;
            this.message = message;
        }

        @RxEventId
        public int getEventId() {
            return eventId;
        }

        public String getMessage() {
            return message;
        }

        public static RxEvent create(@RxEventId int eventId) {
            return new RxEvent(eventId);
        }

        public static RxEvent create(@RxEventId int eventId, String message) {
            return new RxEvent(eventId, message);
        }
    }
}