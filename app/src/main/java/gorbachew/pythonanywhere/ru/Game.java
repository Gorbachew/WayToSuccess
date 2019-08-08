package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class Game extends AppCompatActivity implements RewardedVideoAdListener {
    //Реклама
    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    //Базаданных
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    SharedPreferences sPref;
    final String LOAD_USERID = "UserId";
    final String LOAD_NAME = "Name";
    final String LOAD_AGE = "Age";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String LOAD_CourseUSD = "CourseUSD";
    final String LOAD_RESPECT = "RESPECT";
    final String LOAD_DAY = "DAY";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_TotalHP = "TotalHP";
    final String LOAD_TotalMP = "TotalMP";
    final String LOAD_TotalSP = "TotalSP";

    final String LOAD_HP = "HP";
    final String LOAD_MP = "MP";
    final String LOAD_SP = "SP";

    final String LOAD_SCRAP = "SCRAP";
    final String LOAD_MAXSCRAP = "MAXSCRAP";

    final String LOAD_COURSESCRAP = "CourseScrap";
    final String LOAD_BMS = "BusinessMetalPoint";

    final String LOAD_BMW = "BMWorker";
    final String LOAD_BMFS = "BMFullStock";
    final String LOAD_BMMS = "BMMaxStock";
    final String LOAD_BMA = "BMAd";

    final String LOAD_BCA = "BCad";

    final String LOAD_BCT = "BCtables";
    final String LOAD_BCP = "BCprofit";
    final String LOAD_BCV = "BCvisitors";
    final String LOAD_BCVLW = "BCvisitorsLastWeek";
    final String LOAD_BCW = "BCwaiter";
    final String LOAD_BCC = "BCcook";

    final String LOAD_BUFFCOOK = "BuffCook";
    final String LOAD_BUFFCOMIC = "BuffComic";
    final String LOAD_BUFFDOCK = "BuffDock";
    final String LOAD_BUFFMP = "BuffMP";

    final String LOAD_ACHBM = "AchivmentBMetal";
    final String LOAD_ACHBC = "AchivmentBCafe";
    final String LOAD_ACHM = "AchivmentMetal";
    final String LOAD_ACHMo = "AchivmentMoney";
    final String LOAD_ACHR = "AchivmentRespect";

    final String LOAD_ANTICHEAT = "AntiCheat";

    Toast toastLowMoney;
    final Random random = new Random();
    int TotalRub, TotalUsd, TotalResp, TotalDay, TotalHP, TotalSP, TotalMP, HP, SP, MP, SCRAP, LoadCourseScrap, AntiCheatHP,AntiCheatSP,AntiCheatMP,AntiCheatTimer;
    String NameGamer;
    FragmentTransaction fTrans;
    Fragment FragmentPass, FragmentFood, FragmentMood, FragmentHealth, FragmentFreelance, FragmentWork, FragmentBusinessMetal, FragmentRespect, FragmentBusinessCafe, FragmentProperty, FragmentHolding, FragmentEducation, FragmentAchivment, FragmentBank, FragmentCasino, FragmentGorbes;
    ImageView ivimgDay;
    LinearLayout LayoutScrap;
    HorizontalScrollView ScrollBusiness;

    TextView totalrub, totalusd, totalresp, totalday, totaltexthp, totaltextmp, totaltextsp, namefragments, tvHours, textKgScrap, KgScrap, textCourseScrap, CourseScrap;
    ProgressBar ProgBarHP, ProgBarMP, ProgBarSP;
    //Таймер, вкл когда активити видно и выкл когда активити не видно
    Timer timer;
    TimerTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Проверка добавления новых переменных в игру, чтобы не вылетало
        UpdateGame();

        sPref = getSharedPreferences("Saved", MODE_PRIVATE);
        //Создает обьект Id элементов
        totalrub = findViewById(R.id.TotalRUB);
        totalusd = findViewById(R.id.TotalUSD);
        totalday = findViewById(R.id.TotalDay);
        totaltexthp = findViewById(R.id.TextProgressBarHP);
        totaltextsp = findViewById(R.id.TextProgressBarSP);
        totaltextmp = findViewById(R.id.TextProgressBarMP);
        totalresp = findViewById(R.id.TotalResp);
        namefragments = findViewById(R.id.NameFragments);
        ProgBarHP = findViewById(R.id.ProgressBarHP);
        ProgBarMP = findViewById(R.id.ProgressBarMP);
        ProgBarSP = findViewById(R.id.ProgressBarSP);

        ivimgDay = findViewById(R.id.imgDay);
        tvHours = findViewById(R.id.TextHours);
        LayoutScrap = findViewById(R.id.LayoutScrap);
        textKgScrap = findViewById(R.id.textKgScrap);
        KgScrap = findViewById(R.id.KgScrap);
        textCourseScrap = findViewById(R.id.textCourseScrap);
        CourseScrap = findViewById(R.id.CourseScrap);
        ScrollBusiness = findViewById(R.id.ScrollBusiness);

        //Создаем обьект фрагментов
        FragmentPass = new Password();
        FragmentMood = new Mood();
        FragmentFood = new Food();
        FragmentHealth = new Health();
        FragmentFreelance = new Freelance();
        FragmentWork = new Work();
        FragmentBusinessMetal = new BusinessMetal();

        FragmentBusinessCafe = new BusinessCafe();
        FragmentHolding = new Holding();
        FragmentEducation = new Education();
        FragmentProperty = new Property();
        FragmentRespect = new Respect();
        FragmentAchivment = new Achivment();
        FragmentBank = new Bank();
        FragmentCasino = new Casino();
        FragmentGorbes = new Gorbes();


        //Видеореклама
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        //Межстраничная реклама
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6876201111676185/2696227248");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        loadRewardedVideoAd();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        //Загрузка сохраненных параметров
        loadGame();


        //Передаем значения фрагменту Паспорт
        Bundle bundle = new Bundle();
        bundle.putString("Name", NameGamer);
        FragmentPass.setArguments(bundle);
        //К моему сожалению, я не смог сделать чтобы первый фрагмент Password работал. По этому есть костыль в виде фрагмента First, которые заменяется фрагментом Password при открытии приложения.
        fTrans = getSupportFragmentManager().beginTransaction();
        namefragments.setText(getResources().getString(R.string.NFPass));
        fTrans.replace(R.id.MainFragmentsWindow, FragmentPass);
        fTrans.commit();


    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-6876201111676185/9057018825",
                new AdRequest.Builder().build());
    }


    public void EndGame() {
        int LoseHP = sPref.getInt("LoseHP", 0), LoseMP = sPref.getInt("LoseMP", 0), LoseSP = sPref.getInt("LoseSP", 0), LoseMoney = sPref.getInt("LoseMoney", 0);
        if (LoseHP >= 4 || LoseMP >= 4 || LoseSP >= 4 || LoseMoney >= 12) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
            builder.setTitle(getResources().getString(R.string.EndTitle))
                    .setMessage(getResources().getString(R.string.EndText))
                    .setIcon(R.drawable.loseico)
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!sPref.getString(LOAD_USERID, "").equals("0")) {
                                        db.collection("Players").document(sPref.getString(LOAD_USERID, "")).update("death", 1);
                                    }
                                    Intent intent = new Intent(Game.this, MainActivity.class);
                                    startActivity(intent);
                                    File file = new File("/data/data/gorbachew.pythonanywhere.ru/shared_prefs/Saved.xml");
                                    file.delete();
                                }
                            })
                    .setNeutralButton(getResources().getString(R.string.EndVideoReward),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (mRewardedVideoAd.isLoaded()) {
                                        mRewardedVideoAd.show();
                                        SharedPreferences.Editor ed = sPref.edit();
                                        ed.putInt("LoseHP", 0);
                                        ed.putInt("LoseMP", 0);
                                        ed.putInt("LoseSP", 0);
                                        ed.putInt("LoseMoney", 0);
                                        ed.commit();
                                    } else {
                                        makeText(Game.this, getResources().getString(R.string.EndNoVideo), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    //Проверяет статистику на низкие показатели
    public void CheckStats() {
        int LoseHP = sPref.getInt("LoseHP", 0), LoseMP = sPref.getInt("LoseMP", 0), LoseSP = sPref.getInt("LoseSP", 0), LoseMoney = sPref.getInt("LoseMoney", 0);
        int HP = sPref.getInt(LOAD_HP, 0);
        int MP = sPref.getInt(LOAD_MP, 0);
        int SP = sPref.getInt(LOAD_SP, 0);
        int Rub = sPref.getInt(LOAD_RUB, 0);
        int Usd = sPref.getInt(LOAD_USD, 0);

        if (HP <= 0) {
            if (LoseHP == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setTitle(getResources().getString(R.string.LoseTitle))
                        .setMessage(getResources().getString(R.string.LoseHP))
                        .setIcon(R.drawable.loseico)
                        .setCancelable(false)
                        .setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            LoseHP += 1;
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseHP", LoseHP);
            ed.commit();
        } else if (LoseHP > 0) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseHP", 0);
            ed.commit();
        }
        if (MP <= 0) {
            if (LoseMP == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setTitle(getResources().getString(R.string.LoseTitle))
                        .setMessage(getResources().getString(R.string.LoseMP))
                        .setIcon(R.drawable.loseico)
                        .setCancelable(false)
                        .setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            LoseMP += 1;
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseMP", LoseMP);
            ed.commit();
        } else if (LoseMP > 0) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseMP", 0);
            ed.commit();
        }
        if (SP <= 0) {
            if (LoseSP == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setTitle(getResources().getString(R.string.LoseTitle))
                        .setMessage(getResources().getString(R.string.LoseSP))
                        .setIcon(R.drawable.loseico)
                        .setCancelable(false)
                        .setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            LoseSP += 1;
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseSP", LoseSP);
            ed.commit();
        } else if (LoseSP > 0) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseSP", 0);
            ed.commit();
        }
        if (Rub < 0 || Usd < 0) {
            if (LoseMoney == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setTitle(getResources().getString(R.string.LoseTitle))
                        .setMessage(getResources().getString(R.string.LoseMoney))
                        .setIcon(R.drawable.loseico)
                        .setCancelable(false)
                        .setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            LoseMoney += 1;
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseMoney", LoseMoney);
            ed.commit();
        } else if (LoseMoney > 0) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("LoseMoney", 0);
            ed.commit();
        }
    }

    //Ачивки
    public void Achivments() {
        int rub = sPref.getInt(LOAD_RUB, 0);
        int usd = sPref.getInt(LOAD_USD, 0);
        int resp = sPref.getInt(LOAD_RESPECT, 0);
        int metal = sPref.getInt(LOAD_SCRAP, 0);
        String cook = sPref.getString(LOAD_BCC, "");
        String businessmetal = sPref.getString(LOAD_BMFS, "");

        if (sPref.getString(LOAD_ACHBM, "").equals("2") && Integer.parseInt(businessmetal) >= 10000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBM, "3");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal31), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHBM, "").equals("1") && Integer.parseInt(businessmetal) >= 5000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBM, "2");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal21), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHBM, "").equals("0") && Integer.parseInt(businessmetal) >= 1000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBM, "1");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal11), Toast.LENGTH_LONG).show();
        }

        if (sPref.getString(LOAD_ACHBC, "").equals("2") && Integer.parseInt(cook) >= 10) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBC, "3");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe31), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHBC, "").equals("1") && Integer.parseInt(cook) >= 6) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBC, "2");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe21), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHBC, "").equals("0") && Integer.parseInt(cook) >= 2) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHBC, "1");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe11), Toast.LENGTH_LONG).show();
        }

        if (sPref.getString(LOAD_ACHR, "").equals("2") && resp >= 100000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHR, "3");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect31), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHR, "").equals("1") && resp >= 20000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHR, "2");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect21), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHR, "").equals("0") && resp >= 1000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHR, "1");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect11), Toast.LENGTH_LONG).show();
        }

        if (sPref.getString(LOAD_ACHMo, "").equals("5") && usd >= 10000000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "6");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney61), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHMo, "").equals("4") && usd >= 1000000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "5");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney51), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHMo, "").equals("3") && usd >= 5000000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "4");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney41), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHMo, "").equals("2") && rub >= 1000000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "3");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney31), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHMo, "").equals("1") && rub >= 500000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "2");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney21), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHMo, "").equals("0") && rub >= 100000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHMo, "1");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney11), Toast.LENGTH_LONG).show();
        }

        if (sPref.getString(LOAD_ACHM, "").equals("2") && metal >= 1000) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHM, "3");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance31), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHM, "").equals("1") && metal >= 500) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHM, "2");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance21), Toast.LENGTH_LONG).show();
        } else if (sPref.getString(LOAD_ACHM, "").equals("0") && metal >= 200) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_ACHM, "1");
            ed.commit();
            makeText(this, getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance11), Toast.LENGTH_LONG).show();
        }
    }

    //Изменение курса в банке
    public void ChangeCourseUSD() {
        int course = sPref.getInt(LOAD_CourseUSD, 0);

        if (course > 60) {
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    course -= 1;
                    break;
                case 1:
                    course -= 2;
                    break;
                case 2:
                    course -= 3;
                    break;
                case 3:
                    course += 1;
                    break;
            }
        } else if (course < 20) {
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    course += 1;
                    break;
                case 1:
                    course += 2;
                    break;
                case 2:
                    course += 3;
                    break;
                case 3:
                    course -= 1;
                    break;
            }
        } else {
            int rand = random.nextInt(6);
            switch (rand) {
                case 0:
                    course += 1;
                    break;
                case 1:
                    course += 2;
                    break;
                case 2:
                    course += 3;
                    break;
                case 3:
                    course -= 1;
                    break;
                case 4:
                    course -= 2;
                    break;
                case 5:
                    course -= 3;
                    break;
            }
        }
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(LOAD_CourseUSD, course);
        ed.commit();
    }

    //Все снятия\прибавления статистики через определенное кол-во времени
    public void CheckBuff() {
        String cook = sPref.getString(LOAD_BUFFCOOK, "");
        String dock = sPref.getString(LOAD_BUFFDOCK, "");
        String comic = sPref.getString(LOAD_BUFFCOMIC, "");
        String mp = sPref.getString(LOAD_BUFFMP, "");
        String rent = sPref.getString(SAVED_HOLDING, "");
        int day = sPref.getInt(LOAD_DAY, 0);
        if (cook.equals("1")) {
            RandomStats("SP", "+", 30, 70);

            if (day % 30 == 0) {
                transaction("rub", "-", 50000);
            }
        }
        if (dock.equals("1")) {
            RandomStats("HP", "+", 30, 70);
            if (day % 30 == 0) {
                transaction("rub", "-", 70000);
            }
        }
        if (comic.equals("1")) {
            RandomStats("MP", "+", 30, 70);
            if (day % 30 == 0) {
                transaction("rub", "-", 70000);
            }
        }
        if (mp.equals("1")) {
            RandomStats("RESP", "+", 0, 3000);
            if (day % 30 == 0) {
                transaction("usd", "-", 50000);
            }
        }
        if (rent.equals("3")) {
            if (day % 30 == 0) {
                transaction("rub", "-", 25000);
                makeText(this, getResources().getString(R.string.HoFRentPayDay), LENGTH_SHORT).show();
            }
        }
        if (day % 365 == 0) {
            int resp = sPref.getInt(LOAD_RESPECT, 0);
            int rub = resp * 7;
            int usd = (int) (resp * 0.2);
            int newresp = (int) (resp * 0.1);
            @SuppressLint("DefaultLocale")
            String text = String.format(getResources().getString(R.string.HappyBirthdayText), rub, usd, newresp);
            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
            builder.setTitle(getResources().getString(R.string.HappyBirthdayTitle))
                    .setMessage(text)
                    .setIcon(R.drawable.moodico)
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();
            transaction("rub", "+", rub);
            transaction("usd", "+", usd);
            RandomStats("RESP", "+", newresp, 1);
            RandomStats("HP", "+", 50, 1);
            RandomStats("SP", "+", 50, 1);
            RandomStats("MP", "+", 50, 1);
        }


    }

    public void BCafe() {

        int table = Integer.parseInt(sPref.getString(LOAD_BCT, ""));

        if (table >= 1) {
            int ad = Integer.parseInt(sPref.getString(LOAD_BCA, ""));
            int saveVisitors = Integer.parseInt(sPref.getString(LOAD_BCV, ""));


            int visitors = random.nextInt(5) + random.nextInt(ad);
            int place = table * 4;
            if (visitors > place) {
                visitors = place;
            }
            saveVisitors += visitors;


            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_BCV, String.valueOf(saveVisitors));

            int profit = 0;

            for (int i = 1; i <= visitors; i += 1) {

                int payvis = 51 + random.nextInt(1001 - 50);
                profit += payvis;
            }

            ed.putString(LOAD_BCP, String.valueOf(profit));
            transaction("rub", "+", profit);

            int day = sPref.getInt(LOAD_DAY, 0);


            if (day % 7 == 0) {
                ed.putString(LOAD_BCV, "0");
                ed.putString(LOAD_BCVLW, String.valueOf(saveVisitors));
            }
            //Зарплата
            if (day % 30 == 0) {
                int cooks = Integer.parseInt(sPref.getString(LOAD_BCC, ""));
                int waiters = Integer.parseInt(sPref.getString(LOAD_BCW, ""));

                int salaryCook = cooks * 5000;
                int salaryWaiter = waiters * 25000;
                transaction("rub", "-", salaryWaiter);
                transaction("usd", "-", salaryCook);

                makeText(this, getResources().getString(R.string.BCPayDay) + " " + salaryCook + "$," + salaryWaiter + "р", Toast.LENGTH_LONG).show();
            }

            ed.commit();
        }

    }

    //Метод вычитания или прибавления рубля\доллара параметры Валюта, Знак, Значение
    public void transaction(String currency, String sign, int sum) {
        SharedPreferences.Editor ed = sPref.edit();
        switch (currency) {
            case "rub":
                int rub = sPref.getInt(LOAD_RUB, 0);
                switch (sign) {
                    case "+":
                        ed.putInt(LOAD_RUB, rub + sum);
                        break;
                    case "-":
                        ed.putInt(LOAD_RUB, rub - sum);
                        break;
                }
                break;
            case "usd":
                int usd = sPref.getInt(LOAD_USD, 0);
                switch (sign) {
                    case "+":
                        ed.putInt(LOAD_USD, usd + sum);
                        break;
                    case "-":
                        ed.putInt(LOAD_USD, usd - sum);
                        break;
                }
                break;
        }
        ed.apply();
    }

    //Делает покраснение денег, если их нехватает
    public void LowMoney(String currency) {
        switch (currency) {
            case "rub":
                new CountDownTimer(200, 200) {
                    @Override
                    public void onTick(long l) {
                        totalrub.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }

                    @Override
                    public void onFinish() {
                        totalrub.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }.start();

                break;
            case "usd":

                new CountDownTimer(200, 200) {
                    @Override
                    public void onTick(long l) {
                        totalusd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }

                    @Override
                    public void onFinish() {
                        totalusd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }.start();

                break;
        }
        if (toastLowMoney != null) {
            toastLowMoney.cancel();
        }
        toastLowMoney = Toast.makeText(Game.this, getResources().getString(R.string.LowMoney), Toast.LENGTH_LONG);
        toastLowMoney.show();
    }

    //Метод работы рабочих на пункте приема метала
    public void BMWorker() {
        int worker = Integer.parseInt(sPref.getString(LOAD_BMW, ""));
        int metal = Integer.parseInt(sPref.getString(LOAD_BMFS, ""));
        int ad = Integer.parseInt(sPref.getString(LOAD_BMA, ""));
        sPref = getSharedPreferences("Saved", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        if (worker >= 1) {
            //Получает информацию из файла сохранения
            int maxMetal = Integer.parseInt(sPref.getString(LOAD_BMMS, ""));
            int day = Integer.parseInt(totalday.getText().toString());
            //Просчитывает рабочих и рандомит сколько они принесли метала
            if (metal >= maxMetal) {
                makeText(this, getResources().getString(R.string.BmFLowStock), Toast.LENGTH_LONG).show();
            } else {
                for (int x = 1; x <= worker; x = x + 1) {
                    metal = metal + random.nextInt(10);
                }
            }
            //Каждые 30 дней списывает по 5к за каждого рабочего
            if (day % 30 == 0) {
                int rub = Integer.parseInt(totalrub.getText().toString());
                int pd = worker * 5000;
                transaction("rub", "-", pd);
                makeText(this, getResources().getString(R.string.BmPayDay) + " " + pd + "р", LENGTH_SHORT).show();
            }
        }
        metal = metal + random.nextInt(ad);
        ed.putString(LOAD_BMFS, String.valueOf(metal));
        ed.commit();
    }

    //Метод случайного привабления или убавления статистики игрока
    //4 входящие переменные 1(HP,MP,SP)что изменяется 2 (Plus,Minus) прибавляем или вычитаем 3 100% прибавка или отнятие значения 4 Рандомная максимальная величина в добавок к основной
    public void RandomStats(String Stat, String Sign, int ExactChangeStat, int RandChangeStat) {
        SharedPreferences.Editor ed = sPref.edit();
        int TestMode = sPref.getInt("TestMode", 0);
        if (TestMode == 0) {
            switch (Stat) {
                case "HP":
                    int varStatHP = ProgBarHP.getProgress();
                    if (Sign.equals("+")) {
                        varStatHP = varStatHP + ExactChangeStat + random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_HP, varStatHP);
                    }
                    if (Sign.equals("-")) {
                        varStatHP = varStatHP - ExactChangeStat - random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_HP, varStatHP);
                    }
                    break;
                case "MP":
                    int varStatMP = ProgBarMP.getProgress();
                    if (Sign.equals("+")) {
                        varStatMP = varStatMP + ExactChangeStat + random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_MP, varStatMP);
                    }
                    if (Sign.equals("-")) {
                        varStatMP = varStatMP - ExactChangeStat - random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_MP, varStatMP);
                    }
                    break;
                case "SP":
                    int varStatSP = ProgBarSP.getProgress();
                    if (Sign.equals("+")) {
                        varStatSP = varStatSP + ExactChangeStat + random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_SP, varStatSP);
                    }
                    if (Sign.equals("-")) {
                        varStatSP = varStatSP - ExactChangeStat - random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_SP, varStatSP);
                    }
                    break;
                case "RESP":
                    int var = sPref.getInt(LOAD_RESPECT, 0);
                    if (Sign.equals("+")) {
                        varStatSP = var + ExactChangeStat + random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_RESPECT, varStatSP);
                    }
                    if (Sign.equals("-")) {
                        varStatSP = var - ExactChangeStat - random.nextInt(RandChangeStat);
                        ed.putInt(LOAD_RESPECT, varStatSP);
                    }
                    break;
            }
            ed.commit();
        }

    }

    //Переход на следующие 6 часов
    public void NextDay() {
        //Методы которые проверяются каждый ход
        SharedPreferences.Editor ed = sPref.edit();

        if (tvHours.getText().equals("6")) {
            ivimgDay.setImageResource(R.drawable.day12);
            tvHours.setText("12");
        } else if (tvHours.getText().equals("12")) {
            ivimgDay.setImageResource(R.drawable.day18);
            tvHours.setText("18");
            int rand = random.nextInt(25);
            switch (rand){
                case 25:Events(0);break;
                case 24:Events(1);break;
            }
        } else if (tvHours.getText().equals("18")) {
            ivimgDay.setImageResource(R.drawable.day24);
            tvHours.setText("0");
        } else if (tvHours.getText().equals("0")) {
            ivimgDay.setImageResource(R.drawable.day6);
            tvHours.setText("6");

            RandomStats("HP", "-", 1, 15);
            RandomStats("SP", "-", 1, 15);
            RandomStats("MP", "-", 1, 15);


            int load = sPref.getInt(LOAD_DAY, 0);
            ed.putInt(LOAD_DAY, load + 1);
            ed.commit();
            AntiCheat();
            BMWorker();
            BCafe();
            CheckBuff();
        }
        EndGame();
        CheckStats();
        Achivments();
        CourseScrap();
        ChangeCourseUSD();
        loadGame();

    }


    //Продажа металла
    public void SellScrap() {
//        Toast.makeText(this,CourseScrap.getText().toString() + " " + KgScrap.getText().toString() + " " + totalrub.getText().toString(),Toast.LENGTH_LONG).show();
        int intSellScrap = sPref.getInt(LOAD_COURSESCRAP, 0) * sPref.getInt(LOAD_SCRAP, 0) + sPref.getInt(LOAD_RUB, 0);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(LOAD_RUB, intSellScrap);
        ed.putInt(LOAD_SCRAP, 0);
        ed.commit();


    }

    //Нахождение металла
    public void FindScrup() {
        int Scrap = sPref.getInt(LOAD_SCRAP, 0);
        int maxScrap = sPref.getInt(LOAD_MAXSCRAP, 0);
        if (Scrap <= maxScrap) {
            Scrap = Scrap + random.nextInt(6);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt(LOAD_SCRAP, Scrap);
            ed.commit();

        } else {
            makeText(this, getResources().getString(R.string.FrFLotScrap), Toast.LENGTH_LONG).show();
        }


    }

    //Курс металла
    public void CourseScrap() {
        SharedPreferences.Editor ed = sPref.edit();
        int Course = 11 + random.nextInt(21 - 10);
        ed.putInt(LOAD_COURSESCRAP, Course);
        ed.commit();

    }


    //Изменение фрагментов игры
    public void ChangeFragments(View view) {
        fTrans = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.btnPass:
                namefragments.setText(getResources().getString(R.string.NFPass));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentPass);
                break;
            case R.id.btnFood:
                namefragments.setText(getResources().getString(R.string.NFFood));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentFood);
                break;
            case R.id.btnMood:
                namefragments.setText(getResources().getString(R.string.NFMood));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentMood);
                break;
            case R.id.btnHealth:
                namefragments.setText(getResources().getString(R.string.NFHealth));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentHealth);
                break;
            case R.id.btnFreelance:
                namefragments.setText(getResources().getString(R.string.NFFreelance));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentFreelance);
                break;
            case R.id.btnWork:
                namefragments.setText(getResources().getString(R.string.NFWork));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentWork);
                break;
            case R.id.btnBusiness:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentBusinessMetal);
                break;
            case R.id.topBtnMetalPoint:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentBusinessMetal);
                break;

            case R.id.topBtnCafe:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentBusinessCafe);
                break;
            case R.id.btnEducation:
                namefragments.setText(getResources().getString(R.string.NFEducation));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentEducation);
                break;
            case R.id.btnProperty:
                namefragments.setText(getResources().getString(R.string.NFProperty));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentProperty);
                break;
            case R.id.btnHolding:
                namefragments.setText(getResources().getString(R.string.NFHolding));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentHolding);
                break;
            case R.id.btnRespect:
                namefragments.setText(getResources().getString(R.string.NFRespect));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentRespect);
                break;
            case R.id.btnBank:
                namefragments.setText(getResources().getString(R.string.NFBank));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentBank);
                break;
            case R.id.btnCasino:
                namefragments.setText(getResources().getString(R.string.NFCasino));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentCasino);
                break;
            case R.id.btnAchivment:
                namefragments.setText(getResources().getString(R.string.NFAchivment));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentAchivment);
                break;
            case R.id.btnGorbes:
                namefragments.setText(getResources().getString(R.string.NFGorbes));
                fTrans.replace(R.id.MainFragmentsWindow, FragmentGorbes);
                break;

        }
        fTrans.commit();
        //Отображает

        if (namefragments.getText().toString() == getResources().getString(R.string.NFFreelance)) {
            LayoutScrap.setVisibility(View.VISIBLE);
        } else {
            LayoutScrap.setVisibility(View.INVISIBLE);
        }
        if (namefragments.getText().toString() == getResources().getString(R.string.NFBusiness)) {
            ScrollBusiness.setVisibility(View.VISIBLE);
        } else {
            ScrollBusiness.setVisibility(View.INVISIBLE);
        }


    }

    //Загрузка игры
    @SuppressLint("SetTextI18n")
    public void loadGame() {

        sPref = getSharedPreferences("Saved", MODE_PRIVATE);
        //Получает информацию из файла сохранения
        NameGamer = sPref.getString(LOAD_NAME, "");
        TotalRub = sPref.getInt(LOAD_RUB, 0);
        TotalUsd = sPref.getInt(LOAD_USD, 0);
        TotalResp = sPref.getInt(LOAD_RESPECT, 0);
        TotalDay = sPref.getInt(LOAD_DAY, 0);
        TotalHP = sPref.getInt(LOAD_TotalHP, 0);
        TotalMP = sPref.getInt(LOAD_TotalMP, 0);
        TotalSP = sPref.getInt(LOAD_TotalSP, 0);
        HP = sPref.getInt(LOAD_HP, 0);
        SP = sPref.getInt(LOAD_SP, 0);
        MP = sPref.getInt(LOAD_MP, 0);
        SCRAP = sPref.getInt(LOAD_SCRAP, 0);
        LoadCourseScrap = sPref.getInt(LOAD_COURSESCRAP, 0);

        //Конвертация цифр
        if (TotalRub >= 1000000) {
            TotalRub /= Math.pow(10, 3);
            totalrub.setText(String.valueOf(TotalRub) + "k");
        } else {
            totalrub.setText(String.valueOf(TotalRub));
        }
        if (TotalUsd >= 1000000) {
            TotalUsd /= Math.pow(10, 3);
            totalusd.setText(String.valueOf(TotalUsd) + "k");
        } else {
            totalusd.setText(String.valueOf(TotalUsd));
        }
        if (TotalResp >= 1000000) {
            TotalResp /= Math.pow(10, 3);
            totalresp.setText(String.valueOf(TotalResp) + "k");
        } else {
            totalresp.setText(String.valueOf(TotalResp));
        }
        //Выгружает инфу в основные параметры
        totalday.setText(String.valueOf(TotalDay));
//        totalrub.setText(String.valueOf(TotalRub));
//        totalresp.setText(String.valueOf(TotalResp));
//        totalusd.setText(String.valueOf(TotalUsd));
        //Задает уровень HP/SP/MP
        ProgBarHP.setMax(TotalHP);
        ProgBarSP.setMax(TotalSP);
        ProgBarMP.setMax(TotalMP);
        ProgBarHP.setProgress(HP);
        ProgBarSP.setProgress(SP);
        ProgBarMP.setProgress(MP);

        //Загрузка Металлолома

        textKgScrap.setText(getResources().getString(R.string.FrFSupportScrapQuantity) + " ");
        textCourseScrap.setText(getResources().getString(R.string.FrFSupportScrapCourse) + " ");
        KgScrap.setText(String.valueOf(SCRAP));
        CourseScrap.setText(String.valueOf(LoadCourseScrap));


        String DiffHP = ProgBarHP.getProgress() + "/" + ProgBarHP.getMax();
        String DiffSP = ProgBarSP.getProgress() + "/" + ProgBarSP.getMax();
        String DiffMP = ProgBarMP.getProgress() + "/" + ProgBarMP.getMax();
        totaltexthp.setText(DiffHP);
        totaltextsp.setText(DiffSP);
        totaltextmp.setText(DiffMP);


    }


    @Override
    public void onRewarded(RewardItem reward) {
//        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
//                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        makeText(this, "onRewardedVideoAdLeftApplication",
                LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
        int rub = sPref.getInt(LOAD_RUB, 0);
        int usd = sPref.getInt(LOAD_USD, 0);
        int hp = sPref.getInt(LOAD_HP, 0);
        int mp = sPref.getInt(LOAD_MP, 0);
        int sp = sPref.getInt(LOAD_SP, 0);

        SharedPreferences.Editor ed = sPref.edit();
        if (hp <= 0) {
            ed.putInt(LOAD_HP, 10);
        }
        if (mp <= 0) {
            ed.putInt(LOAD_MP, 10);
        }
        if (sp <= 0) {
            ed.putInt(LOAD_SP, 10);
        }
        if (rub < 0) {
            rub += 2000;
            ed.putInt(LOAD_RUB, rub);
        }
        if (usd < 0) {
            usd += 500;
            ed.putInt(LOAD_USD, usd);
        }
        ed.commit();

        loadGame();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
//        Toast.makeText(this, getResources().getString(R.string.videoadmessageError), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        makeText(this, getResources().getString(R.string.videoadmessageOk), LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();

    }



    public void Events(int ad) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
        int resp;
        resp = sPref.getInt(LOAD_RESPECT, 0);
        switch (ad) {
            case 0:
                resp = resp * 2;
                if(resp < 10){resp = 10;}
                final int finalresp1 = resp;
                builder.setTitle(getResources().getString(R.string.EventAdTitle))
                        .setMessage(getResources().getString(R.string.EventVideoAdText))
                        .setIcon(R.drawable.holdingico)
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.Agree) + " " + String.valueOf(resp) + "$",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (mRewardedVideoAd.isLoaded()) {
                                            mRewardedVideoAd.show();
                                            transaction("usd","+", finalresp1);
                                            loadGame();
                                        } else {
                                            Toast.makeText(Game.this, getResources().getString(R.string.EventVideoAdError), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.Disagree),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                break;
            case 1:
                resp = resp * 10;
                if(resp < 300){resp = 300;}
                final int finalRub2 = resp;
                builder.setTitle(getResources().getString(R.string.EventAdTitle))
                        .setMessage(getResources().getString(R.string.EventAdText))
                        .setIcon(R.drawable.holdingico)
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.Agree) + " " + String.valueOf(resp)+ "р",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                            transaction("rub","+", finalRub2);
                                            loadGame();
                                        }
                                        else {
                                            Toast.makeText(Game.this, getResources().getString(R.string.EventAdError), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.Disagree),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                break;


        }
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void AntiCheat(){
       if(AntiCheatHP == ProgBarHP.getProgress() || AntiCheatSP == ProgBarSP.getProgress() || AntiCheatMP == ProgBarMP.getProgress()){
//           Toast.makeText(this,"Подумал, что есть читы",LENGTH_SHORT).show();
           AntiCheatTimer++;
           if(AntiCheatTimer >= 5){
               AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
               builder.setTitle(getResources().getString(R.string.YouCheaterTitle))
                       .setMessage(getResources().getString(R.string.YouCheaterText))
                       .setIcon(R.drawable.loseico)
                       .setCancelable(false)
                       .setPositiveButton("Ok",
                               new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       SharedPreferences.Editor ed = sPref.edit();
                                       ed.putInt(LOAD_ANTICHEAT,1);
                                       ed.apply();
                                       if (!sPref.getString(LOAD_USERID, "").equals("0")) {
                                           db.collection("Players").document(sPref.getString(LOAD_USERID, "")).update("cheat", 1);
                                       }
                                       dialog.cancel();
                                   }
                               });

               AlertDialog alert = builder.create();
               alert.show();
           }
       }
        else{
//            Toast.makeText(this,"Нет читов",LENGTH_SHORT).show();
            AntiCheatTimer = 0;
            AntiCheatHP = ProgBarHP.getProgress();
            AntiCheatSP = ProgBarSP.getProgress();
            AntiCheatMP = ProgBarMP.getProgress();
        }
    }
    public void UpdateGame(){
        try {
            sPref.getInt("AntiCheat",0);
        }
        catch(Exception e){
            sPref = getSharedPreferences("Saved", MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("AntiCheat",0);
            ed.apply();
        }

    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
        if(sPref.getInt(LOAD_ANTICHEAT,0) == 0){
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            if (sPref.getString(LOAD_USERID, "").equals("0")) {
                                //                            Toast.makeText(Game.this,getResources().getString(R.string.GFsendError), LENGTH_SHORT).show();
                            } else {
                                final Map<String, Object> user = new HashMap<>();
                                user.put("name", sPref.getString(LOAD_NAME, ""));
                                user.put("age", sPref.getString(LOAD_AGE, ""));
                                user.put("usd", sPref.getInt(LOAD_USD, 0));
                                user.put("rub", sPref.getInt(LOAD_RUB, 0));
                                user.put("resp", sPref.getInt(LOAD_RESPECT, 0));
                                user.put("timestampupdate", FieldValue.serverTimestamp());
                                DocumentReference DocRef = db.collection("Players").document(sPref.getString(LOAD_USERID, ""));
                                DocRef.update(user);
                                Toast.makeText(Game.this, getResources().getString(R.string.GFsendStats), LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(task, 0, 60000);
            //        Log.i("1111111111111111","Активити видно");

        }
        else {
            Toast.makeText(Game.this, getResources().getString(R.string.YouCheaterToast), LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
        timer.cancel();
//        Log.i("1111111111111111","Активити в паузе");
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
        timer.cancel();
//        Log.i("1111111111111111","Не видно");
    }
}


