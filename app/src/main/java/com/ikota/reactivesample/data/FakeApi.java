package com.ikota.reactivesample.data;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.ikota.reactivesample.model.Photo;


public class FakeApi {

    public interface ApiCallback {
        void onResult(Photo photo);
    }

    public static void fakeApiCall(ApiCallback callback) {
        new FakeApiCall(callback).execute();
    }

    private static class FakeApiCall extends AsyncTask<Void, Void, Photo> {
        private ApiCallback mCallback;
        public FakeApiCall(ApiCallback callback) {
            mCallback = callback;
        }

        @Override
        protected Photo doInBackground(Void... voids) {
            SystemClock.sleep(3000);  // simulate api call
            return Photo.createFakeData();
        }

        @Override
        protected void onPostExecute(Photo photo) {
            mCallback.onResult(photo);
        }
    }

}
