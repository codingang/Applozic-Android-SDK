package com.applozic.mobicomkit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.applozic.mobicomkit.api.conversation.MessageClientService;

/**
 * Created by devashish on 29/08/15.
 */
public class ConnectivityReceiver extends BroadcastReceiver {

    static final private String TAG = "ConnectivityReceiver";
    static final private String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    Context context;

    @Override
    public void onReceive(final Context context, Intent intent) {
        this.context = context;

        String action = intent.getAction();

        Log.i(TAG,action);

        if (action.equalsIgnoreCase(CONNECTIVITY_CHANGE)) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    new MessageClientService(context).syncPendingMessages();
                }
            }).start();
        }
    }

}
