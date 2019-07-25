package gorbachew.pythonanywhere.ru;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame,btnContinue,btnExit,btnAuthors,btnDonate,btnBtnMessageAuthor;


    private static final String TAG = "MainActivity";
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //Кнопка Начала Игры
        btnStartGame = findViewById(R.id.BtnStartGame);
        btnStartGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Всплывающее окно перед выходом
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Вы действительно хотите начать новую игру?")

                        .setPositiveButton("Поехали!",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Подхватывает и запускает страницу создания персонажа
                                        Intent intent = new Intent(".Start");
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("Я ошибся",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        btnContinue = findViewById(R.id.BtnMenuContGame);
        File file = new File("/data/data/gorbachew.pythonanywhere.ru/shared_prefs/Saved.xml");
        if(file.exists()){
            btnContinue.setEnabled(true);
            btnContinue.setTextColor(getResources().getColor(R.color.white));
        }
        else {
            btnContinue.setTextColor(getResources().getColor(R.color.darkGrey));
            btnContinue.setEnabled(false);
        }
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Game");
                startActivity(intent);
            }
        });
        btnAuthors = findViewById(R.id.BtnMenuAuthors);
        btnAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".AuthorsActivity");
                startActivity(intent);
            }
        });
        btnDonate = findViewById(R.id.BtnMenuDonate);
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.donationalerts.com/r/gorbachew"));
                startActivity(browserIntent);
            }
        });
        btnBtnMessageAuthor = findViewById(R.id.BtnMessageAuthor);
        btnBtnMessageAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.donationalerts.com/r/gorbachew"));
                startActivity(browserIntent);
            }
        });

        //Кнопка выхода
        btnExit = findViewById(R.id.BtnMenuExit);
        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //Всплывающее окно перед выходом
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Вы действительно желаете выйти?")
                        .setNegativeButton("Нет",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });
    }
}
