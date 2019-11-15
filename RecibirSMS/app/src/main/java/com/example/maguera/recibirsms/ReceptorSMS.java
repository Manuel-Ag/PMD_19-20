package com.example.maguera.recibirsms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceptorSMS extends BroadcastReceiver {
    private final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private onRecibeSMS respuesta;

    public void setOnRecibeSMSListener(Activity x) {
        respuesta = (onRecibeSMS) x;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();

            SmsMessage[] msgs = null;
            String origen = null;
            String msg = null;

            if (bundle != null) {
                Object[] pdu = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdu.length];
                for (int i = 0; i < msgs.length; i++) {
                    String format = bundle.getString("format");
                    msgs[i] = SmsMessage.createFromPdu((byte[])pdu[i], format);
                    origen = msgs[i].getOriginatingAddress();
                    msg = msgs[i].getMessageBody();
                }

                respuesta.onRecibeSMS(origen, msg);
                //Toast.makeText(context, "Mensaje", Toast.LENGTH_SHORT).show();
                Log.i("Prueba", "Broadcast funciona");
            }
        }
    }

    public interface onRecibeSMS {
        public void onRecibeSMS(String origen, String mensaje);
    }
}
