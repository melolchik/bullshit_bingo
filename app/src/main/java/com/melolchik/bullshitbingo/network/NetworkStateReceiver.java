package com.melolchik.bullshitbingo.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * The type Network state receiver.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    /**
     * The Listener.
     */
    protected NetworkStateReceiverListener listener;
    /**
     * The Connected.
     */
    protected boolean connected;
    /**
     * The M intent filter.
     */
    protected IntentFilter mIntentFilter = null;


    /**
     * Instantiates a new Network state receiver.
     *
     * @param listener the listener
     */
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

    /**
     * Gets intent filter.
     *
     * @return the intent filter
     */
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


    /**
     * The interface Network state receiver listener.
     */
    public interface NetworkStateReceiverListener {
        /**
         * Network available.
         */
        public void networkAvailable();

        /**
         * Network unavailable.
         */
        public void networkUnavailable();
    }
}