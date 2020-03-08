package ru.kolesnikovdmitry.lesson12notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHANEL_USUAL_ID = "Usual Notify";                                   //ID канала оповещений
    private final int NOTIFY_ID                 = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMainAct(View view) {
        switch (view.getId()) {
            case R.id.buttonUsualNotifyActMain:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANEL_USUAL_ID);
                builder.setContentTitle("Usual Notify");
                builder.setContentText("it is usual notify for test");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setSmallIcon(R.drawable.ic_notifications_usual_black_24dp);
                //builder.setContentIntent();

                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(CHANEL_USUAL_ID, CHANEL_USUAL_ID, NotificationManager.IMPORTANCE_DEFAULT);
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(notificationChannel);
                }*/
                assert notificationManager != null;
                notificationManager.notify(NOTIFY_ID, builder.build());
                break;
            default:
                break;

        }
    }
}
