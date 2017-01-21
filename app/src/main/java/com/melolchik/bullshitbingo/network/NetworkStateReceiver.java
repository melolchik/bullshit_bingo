package com.melolchik.bullshitbingo.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateReceiver extends BroadcastReceiver {

    protected NetworkStateReceiverListener listener;
    protected boolean connected;
    protected IntentFilter mIntentFilter = null;


    public NetworkStateReceiver(NetworkStateReceiverListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        connected = ni != null && ni.getState() == NetworkInfo.State.CONNECTED;
        notifyState(listener);
    }

    public IntentFilter getIntentFilter() {
        if(mIntentFilter == null) {
            mIntentFilter = new IntentFilter();
            mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        }
        return mIntentFilter;
}

    private void notifyState(NetworkStateReceiverListener listener) {
        if (listener != null) {
            if (connected) {
                listener.networkAvailable();
            } else {
                listener.networkUnavailable();
            }
        }
    }


    public interface NetworkStateReceiverListener {
        public void networkAvailable();

        public void networkUnavailable();
    }
}