package ru.kolesnikovdmitry.lesson12notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String CHANEL_USUAL_ID = "Usual Notify";                                   //ID канала оповещений
    private final int    NOTIFY_ID              = 1001;
    TextView             mTextViewActMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewActMain = findViewById(R.id.textViewMainAct);
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

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel notificationChannel = new NotificationChannel(CHANEL_USUAL_ID, CHANEL_USUAL_ID, NotificationManager.IMPORTANCE_DEFAULT);
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify(NOTIFY_ID, builder.build());
                    final Snackbar snackbar = Snackbar.make(view, ">= 26 api", Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(getColor(R.color.colorPrimary));
                    snackbar.setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }
                else {
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                    notificationManagerCompat.notify(NOTIFY_ID, builder.build());
                    final Snackbar snackbar = Snackbar.make(view, "<26 api", 100);
                    snackbar.setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }

                break;
            default:
                break;

        }
    }
}
