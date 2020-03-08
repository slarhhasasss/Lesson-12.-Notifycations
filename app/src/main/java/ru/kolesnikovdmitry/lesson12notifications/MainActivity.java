package ru.kolesnikovdmitry.lesson12notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_USUAL_ID     = "Usual Notify";                                   //ID канала оповещений
    private final String CHANNEL_INTENT_ID    = "Intent Notify";
    private final String CHANNEL_MUSIC_ID     = "Site Notify";
    private final String CHANNEL_LARGE_ID     = "Large Picture Notify";
    private final String CHANNEL_INBOX_ID     = "Inbox Notify";
    private final String CHANNEL_MESS_ID      = "Mess Notify";
    private final int    NOTIFY_LARGE_ID      = 1004;
    private final int    NOTIFY_INBOX_ID      = 1005;
    private final int    NOTIFY_USUAL_ID      = 1001;
    private final int    NOTIFY_INTENT_ID     = 1002;
    private final int    NOTIFY_MUSIC_ID      = 1003;
    private final int    NOTIFY_MESS_ID       = 1006;

    TextView             mTextViewActMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewActMain = findViewById(R.id.textViewMainAct);
    }

    @SuppressLint("RestrictedApi")
    public void onClickMainAct(View view) {
        switch (view.getId()) {
            case R.id.buttonUsualNotifyActMain:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_USUAL_ID);
                builder.setContentTitle("Usual Notify");
                builder.setContentText("it is usual notify for test");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setSmallIcon(R.drawable.ic_notifications_usual_black_24dp);

                showNotify(builder, view, CHANNEL_USUAL_ID, NOTIFY_USUAL_ID);                                                          //показываем уведомление
                break;
            case R.id.buttonIntentActMain:
                //Чтобы активность не перезапускалась заново при нажатии на уведомление, если она уже открыта, то в манифесте
                //для этой активности надо прописать android:launchMode="singleTop"
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_INTENT_ID);
                builder1.setContentText("Перейти на другую активность");
                builder1.setContentTitle("Новая активность");
                builder1.setPriority(NotificationCompat.PRIORITY_DEFAULT);                          //приоритет
                builder1.setSmallIcon(R.drawable.ic_pan_tool_black_24dp);
                builder1.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.car));//Большая иконка
                builder1.setAutoCancel(true);                                                       //автоматическое закрытие после нажатия
                builder1.setTicker("lolkek");                                                       //для старых сутройств
                builder1.setContentIntent(pendingIntent);

                showNotify(builder1, view, CHANNEL_INTENT_ID, NOTIFY_INTENT_ID);
                break;
            case R.id.buttonMusicActMain:
                Intent intent2  = new Intent(getApplicationContext(), SecondActivity.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), NOTIFY_MUSIC_ID, intent2, 0);

                String bigText = "lol\nkek\nlolkek\nfdghfgsdhfgsjhfgskjdfgksjgfsdgfhsgdjfgsdhflhsdgfhjsdgfhsdfhsdgfdjsgfshdgfksdgfjkdsgfhsdgfjsgdhdsjfs" +
                        "dsfjsdfhlksdhflsdgfhsgdgfhgsjkdfgshdgfhjsdgfhsdgfhsdgfkhsdgfhsdgfjhsdghjdsgjsdgfjk";

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_MUSIC_ID);
                builder2.setSmallIcon(R.drawable.ic_cast_connected_black_24dp);
                builder2.setAutoCancel(true);
                builder2.setContentTitle("Сайт");
                builder2.setPriority(1);
                builder2.setAutoCancel(true);
                builder2.setColor(Color.GREEN);
                builder2.setDefaults(Notification.DEFAULT_SOUND);
                builder2.addAction(R.drawable.ic_cast_connected_black_24dp, "go to Second Activity", pendingIntent2);    //Кнопка с переходом на вторую активность
                builder2.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));          //длинный текст

                showNotify(builder2, view, CHANNEL_MUSIC_ID, NOTIFY_MUSIC_ID);
                break;


            case R.id.buttonLargePictureActMain:
                NotificationCompat.Builder builder3 = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_LARGE_ID);
                builder3.setContentText("Большая Картинка");
                builder3.setContentTitle("picture");
                builder3.setColor(Color.RED);
                builder3.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.car)));
                builder3.setAutoCancel(true);
                builder3.setSmallIcon(R.drawable.ic_pan_tool_black_24dp);
                builder3.setPriority(0);

                showNotify(builder3, view, CHANNEL_LARGE_ID, NOTIFY_LARGE_ID);
                break;
            case R.id.buttonInboxStyleActMain:
                NotificationCompat.Builder builder4 = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_INBOX_ID);
                builder4.setPriority(0);
                builder4.setSmallIcon(R.drawable.ic_cast_connected_black_24dp);
                builder4.setContentText("InboxStyle");
                builder4.setContentTitle("InboxStyle");
                builder4.setColor(Color.BLUE);
                builder4.setStyle(new NotificationCompat.InboxStyle()
                .addLine("first")
                .addLine("Second")
                .addLine("Therd")
                .setSummaryText("+2 more"));

                showNotify(builder4, view, CHANNEL_INBOX_ID, NOTIFY_INBOX_ID);
                break;

            case R.id.buttonMessangerActMain:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    Person dima  = new Person.Builder().setName("Dima").build();
                    Person alina = new Person.Builder().setName("Alina").build();

                    NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(androidx.core.app.Person.fromAndroidPerson(alina));
                    messagingStyle.addMessage("some questions", System.currentTimeMillis(), androidx.core.app.Person.fromAndroidPerson(alina));
                    messagingStyle.addMessage("some ans", System.currentTimeMillis(), androidx.core.app.Person.fromAndroidPerson(dima));
                    messagingStyle.setConversationTitle("some chat");


                    NotificationCompat.Builder builder5 = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_MESS_ID);
                    builder5.setColor(Color.YELLOW);
                    builder5.setContentTitle("Messanger");
                    builder5.setSmallIcon(R.drawable.ic_notifications_usual_black_24dp);
                    builder5.setStyle(messagingStyle);

                    showNotify(builder5, view, CHANNEL_MESS_ID, NOTIFY_MESS_ID);
                    break;
                }
                else {
                    Snackbar snackbar = Snackbar.make(view, "Вы слишком старый, Сударь", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    break;
                }
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemCleanActMain:
                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                assert notificationManager != null;
                notificationManager.cancelAll();        //Если закрыть какое-то конкретное уведомление, то notificationManager.cancel(NOTIFY_ID);
                break;
            default:
                break;
        }
        return true;
    }

    private void showNotify(NotificationCompat.Builder builder, View view, String CHANNEL_ID, int NOTIFY_ID) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        else {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
            notificationManagerCompat.notify(NOTIFY_ID, builder.build());
        }
    }
}
