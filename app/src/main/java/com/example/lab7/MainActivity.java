package com.example.lab7;

import static com.example.lab7.App.CHANNEL_1_ID;
import static com.example.lab7.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

    }

    public void sendOnChannel1(View v){
        editTextTitle = (EditText) findViewById(R.id.Title);
        editTextMessage = (EditText) findViewById(R.id.Message);
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity
                (this,0,activityIntent,0);

        Intent broadcastIntent = new Intent(this,NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage",message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,
                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
                .build();

        notificationManager.notify(1,notification);
    }

    public void sendOnChannel2(View v){
        editTextTitle = (EditText) findViewById(R.id.Title);
        editTextMessage = (EditText) findViewById(R.id.Message);
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity
                (this,0,activityIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_baseline_bedtime_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notificationManager.notify(2,notification);

    }

}