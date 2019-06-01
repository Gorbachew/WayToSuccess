package gorbachew.pythonanywhere.ru;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame,btnContinue,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Game");
                startActivity(intent);
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
