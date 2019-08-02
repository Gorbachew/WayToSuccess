package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
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

import java.io.File;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;




public class Game extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;
    int advertisement = 0;

    SharedPreferences sPref;
    final String LOAD_NAME = "Name";
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


    final Random random = new Random();

    String NameGamer,TotalRub,TotalUsd,TotalResp,TotalDay,TotalHP,TotalSP,TotalMP,HP,SP,MP,SCRAP,LoadCourseScrap;
    FragmentTransaction fTrans;
    Fragment FragmentPass,FragmentFood,FragmentMood,FragmentHealth,FragmentFreelance,FragmentWork,FragmentBusinessMetal,FragmentRespect,FragmentBusinessCafe,FragmentProperty,FragmentHolding,FragmentEducation,FragmentAchivment,FragmentBank,FragmentCasino;
    ImageView ivimgDay;
    LinearLayout LayoutScrap;
    HorizontalScrollView ScrollBusiness;

    TextView totalrub,totalusd,totalresp,totalday,totaltexthp,totaltextmp,totaltextsp,namefragments,tvHours,textKgScrap,KgScrap,textCourseScrap,CourseScrap;
    ProgressBar ProgBarHP,ProgBarMP,ProgBarSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
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
        bundle.putString("Name",NameGamer);
        FragmentPass.setArguments(bundle);
        //К моему сожалению, я не смог сделать чтобы первый фрагмент Password работал. По этому есть костыль в виде фрагмента First, которые заменяется фрагментом Password при открытии приложения.
        fTrans = getSupportFragmentManager().beginTransaction();
        namefragments.setText(getResources().getString(R.string.NFPass));
        fTrans.replace(R.id.MainFragmentsWindow,FragmentPass);
        fTrans.commit();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-6876201111676185/9057018825",
                new AdRequest.Builder().build());
    }


    public void EndGame(){
        int LoseHP = sPref.getInt("LoseHP",0),LoseMP = sPref.getInt("LoseMP",0),LoseSP= sPref.getInt("LoseSP",0),LoseMoney = sPref.getInt("LoseMoney",0);
        if (LoseHP >= 4 || LoseMP >= 4 || LoseSP >= 4 || LoseMoney >= 12){
            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
            builder.setTitle(getResources().getString(R.string.EndTitle))
                    .setMessage(getResources().getString(R.string.EndText))
                    .setIcon(R.drawable.loseico)
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Game.this,MainActivity.class);
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
                                        ed.putInt("LoseHP",0);
                                        ed.putInt("LoseMP",0);
                                        ed.putInt("LoseSP",0);
                                        ed.putInt("LoseMoney",0);
                                        ed.commit();
                                    }
                                    else {
                                        Toast.makeText(Game.this, getResources().getString(R.string.EndNoVideo), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    //Проверяет статистику на низкие показатели
    public void CheckStats(){
           int LoseHP = sPref.getInt("LoseHP",0),LoseMP = sPref.getInt("LoseMP",0),LoseSP= sPref.getInt("LoseSP",0),LoseMoney = sPref.getInt("LoseMoney",0);
           int HP = Integer.parseInt(sPref.getString(LOAD_HP,""));
           int MP = Integer.parseInt(sPref.getString(LOAD_MP,""));
           int SP = Integer.parseInt(sPref.getString(LOAD_SP,""));
           int Rub = Integer.parseInt(sPref.getString(LOAD_RUB,""));
           int Usd = Integer.parseInt(sPref.getString(LOAD_USD,""));

           if(HP <= 0){
               if(LoseHP == 0){
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
               ed.putInt("LoseHP",LoseHP);
               ed.commit();
           }
           else if(LoseHP > 0){
               SharedPreferences.Editor ed = sPref.edit();
               ed.putInt("LoseHP",0);
               ed.commit();
           }
            if(MP <= 0 ){
                if(LoseMP == 0){
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
                ed.putInt("LoseMP",LoseMP);
                ed.commit();
            }
            else if(LoseMP > 0){
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("LoseMP",0);
                ed.commit();
            }
            if(SP <= 0 ){
                if(LoseSP == 0){
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
                ed.putInt("LoseSP",LoseSP);
                ed.commit();
            }
            else if(LoseSP > 0){
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("LoseSP",0);
                ed.commit();
            }
            if(Rub < 0  || Usd < 0){
                if(LoseMoney == 0){
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
                ed.putInt("LoseMoney",LoseMoney);
                ed.commit();
            }
            else if(LoseMoney > 0){
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("LoseMoney",0);
                ed.commit();
            }
    }

    //Ачивки
    public void Achivments(){
        String rub = sPref.getString(LOAD_RUB,"");
        String usd = sPref.getString(LOAD_USD,"");
        String resp = sPref.getString(LOAD_RESPECT,"");
        String metal = sPref.getString(LOAD_SCRAP,"");
        String cook = sPref.getString(LOAD_BCC,"");
        String businessmetal = sPref.getString(LOAD_BMFS,"");

         if  (sPref.getString(LOAD_ACHBM,"").equals("2") && Integer.parseInt(businessmetal) >= 10000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBM,"3");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal31), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHBM,"").equals("1") && Integer.parseInt(businessmetal) >= 5000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBM,"2");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal21), Toast.LENGTH_LONG).show();
        }
        else if (sPref.getString(LOAD_ACHBM,"").equals("0") && Integer.parseInt(businessmetal) >= 1000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBM,"1");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFbmetal11), Toast.LENGTH_LONG).show();
        }

        if  (sPref.getString(LOAD_ACHBC,"").equals("2") && Integer.parseInt(cook) >= 10){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBC,"3");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe31), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHBC,"").equals("1") && Integer.parseInt(cook) >= 6){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBC,"2");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe21), Toast.LENGTH_LONG).show();
        }
        else if (sPref.getString(LOAD_ACHBC,"").equals("0") && Integer.parseInt(cook) >= 2){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHBC,"1");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFcafe11), Toast.LENGTH_LONG).show();
        }

        if  (sPref.getString(LOAD_ACHR,"").equals("2") && Integer.parseInt(resp) >= 100000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHR,"3");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect31), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHR,"").equals("1") && Integer.parseInt(resp) >= 20000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHR,"2");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect21), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHR,"").equals("0") && Integer.parseInt(resp) >= 1000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHR,"1");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFrespect11), Toast.LENGTH_LONG).show();
        }

        if  (sPref.getString(LOAD_ACHMo,"").equals("5") && Integer.parseInt(usd) >= 10000000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"6");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney61), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHMo,"").equals("4") && Integer.parseInt(usd) >= 1000000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"5");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney51), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHMo,"").equals("3") && Integer.parseInt(rub) >= 5000000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"4");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney41), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHMo,"").equals("2") && Integer.parseInt(rub) >= 1000000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"3");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney31), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHMo,"").equals("1") && Integer.parseInt(rub) >= 500000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"2");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney21), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHMo,"").equals("0") && Integer.parseInt(rub) >= 100000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHMo,"1");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFmoney11), Toast.LENGTH_LONG).show();
        }

        if  (sPref.getString(LOAD_ACHM,"").equals("2") && Integer.parseInt(metal) >= 1000){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHM,"3");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance31), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHM,"").equals("1") && Integer.parseInt(metal) >= 500){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHM,"2");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance21), Toast.LENGTH_LONG).show();
        }
        else if  (sPref.getString(LOAD_ACHM,"").equals("0") && Integer.parseInt(metal) >= 200){
            SharedPreferences.Editor ed = sPref.edit();ed.putString(LOAD_ACHM,"1");ed.commit();
            Toast.makeText(this,getResources().getString(R.string.AFopen) + " " + getResources().getString(R.string.AFfreelance11), Toast.LENGTH_LONG).show();
        }
    }
    //Изменение курса в банке
    public void ChangeCourseUSD(){
        int course = Integer.parseInt(sPref.getString(LOAD_CourseUSD,""));

        if (course > 60){
            int rand = random.nextInt(4);
            switch (rand){
                case 0: course -= 1;break;
                case 1: course -= 2;break;
                case 2: course -= 3;break;
                case 3: course += 1;break;
            }
        }
        else if(course < 20){
            int rand = random.nextInt(4);
            switch (rand){
                case 0: course += 1;break;
                case 1: course += 2;break;
                case 2: course += 3;break;
                case 3: course -= 1;break;
            }
        }
        else {
            int rand = random.nextInt(6);
            switch (rand){
                case 0: course += 1;break;
                case 1: course += 2;break;
                case 2: course += 3;break;
                case 3: course -= 1;break;
                case 4: course -= 2;break;
                case 5: course -= 3;break;
            }
        }
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_CourseUSD,String.valueOf(course));
        ed.commit();
    }
    //Все снятия\прибавления статистики через определенное кол-во времени
    public void CheckBuff(){
        String cook = sPref.getString(LOAD_BUFFCOOK,"");
        String dock = sPref.getString(LOAD_BUFFDOCK,"");
        String comic = sPref.getString(LOAD_BUFFCOMIC,"");
        String mp = sPref.getString(LOAD_BUFFMP,"");
        String rent = sPref.getString(SAVED_HOLDING,"");
        int day = Integer.parseInt(sPref.getString(LOAD_DAY,""));
        if(cook.equals("1")){
            RandomStats("SP","+",30,70);

            if(day % 30 == 0){
                transaction("rub","-",50000);
            }
        }
        if(dock.equals("1")){
            RandomStats("HP","+",30,70);
            if(day % 30 == 0){
                transaction("rub","-",70000);
            }
        }
        if(comic.equals("1")){
            RandomStats("MP","+",30,70);
            if(day % 30 == 0){
                transaction("rub","-",70000);
            }
        }
        if(mp.equals("1")){
            RandomStats("RESP","+",0,3000);
            if(day % 30 == 0){
                transaction("usd","-",50000);
            }
        }
        if(rent.equals("3")){
            if(day % 30 == 0){
                transaction("rub","-",25000);
                Toast.makeText(this,getResources().getString(R.string.HoFRentPayDay),Toast.LENGTH_SHORT).show();
            }
        }
        if(day%1 == 365){
            int resp = Integer.parseInt(sPref.getString(LOAD_RESPECT,""));
            int rub = resp * 7;
            int usd = (int) (resp * 0.2);
            int newresp = (int) (resp * 0.1);
            @SuppressLint("DefaultLocale")
            String text = String.format(getResources().getString(R.string.HappyBirthdayText),rub,usd,newresp);
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
            transaction("rub","+",rub);
            transaction("usd","+",rub);
            RandomStats("RESP","+",newresp,1);
            RandomStats("HP", "+", 50, 1);
            RandomStats("SP", "+", 50, 1);
            RandomStats("MP", "+", 50, 1);
        }



    }

    public void BCafe(){

       int table = Integer.parseInt(sPref.getString(LOAD_BCT,""));

       if (table >= 1){
           int ad = Integer.parseInt(sPref.getString(LOAD_BCA,""));
           int saveVisitors = Integer.parseInt(sPref.getString(LOAD_BCV,""));


           int visitors = random.nextInt(5) + random.nextInt(ad);
           int place = table * 4;
           if (visitors > place){
               visitors = place;
           }
           saveVisitors += visitors;


           SharedPreferences.Editor ed = sPref.edit();
           ed.putString(LOAD_BCV,String.valueOf(saveVisitors));

           int profit = 0;

           for (int i = 1; i <= visitors; i += 1){

               int payvis = 51 + random.nextInt(1001 - 50);
               profit += payvis;
           }

           ed.putString(LOAD_BCP,String.valueOf(profit));
           transaction("rub","+",profit);

           int day = Integer.parseInt(sPref.getString(LOAD_DAY,""));


           if (day % 7 == 0){
              ed.putString(LOAD_BCV,"0");
              ed.putString(LOAD_BCVLW,String.valueOf(saveVisitors));
           }
           //Зарплата
           if(day % 30 == 0){
               int cooks = Integer.parseInt(sPref.getString(LOAD_BCC,""));
               int waiters = Integer.parseInt(sPref.getString(LOAD_BCW,""));

               int salaryCook = cooks * 5000;
               int salaryWaiter = waiters * 25000;
               transaction("rub","-",salaryWaiter);
               transaction("usd","-",salaryCook);

               Toast.makeText(this,getResources().getString(R.string.BCPayDay) + " " + salaryCook + "$," + salaryWaiter + "р",Toast.LENGTH_LONG).show();
           }

           ed.commit();
       }

    }

    //Метод вычитания или прибавления рубля\доллара параметры Валюта, Знак, Значение
    public void transaction(String currency,String sign, int sum ){
        SharedPreferences.Editor ed = sPref.edit();
        switch (currency){
            case "rub":
                int rub = Integer.parseInt(sPref.getString(LOAD_RUB, ""));
                switch (sign){
                    case "+":
                        ed.putString(LOAD_RUB,String.valueOf(rub + sum));
                        break;
                    case "-":
                        ed.putString(LOAD_RUB,String.valueOf(rub - sum));
                        break;
                }
                break;
            case "usd":
                int usd = Integer.parseInt(sPref.getString(LOAD_USD, ""));
                switch (sign){
                    case "+":
                        ed.putString(LOAD_USD,String.valueOf(usd + sum));
                        break;
                    case "-":
                        ed.putString(LOAD_USD,String.valueOf(usd - sum));
                        break;
                }
                break;

        }
        ed.apply();
    }
    //Делает покраснение денег, если их нехватает
    public void LowMoney(String currency){
        switch (currency){
            case "rub":

                new CountDownTimer(200,200){
                    @Override
                    public void onTick(long l) {
                        totalrub.setTextColor(getResources().getColor(R.color.red));
                    }
                    @Override public void onFinish() {
                        totalrub.setTextColor(getResources().getColor(R.color.white));
                    }
                }.start();
                break;
            case "usd":

                new CountDownTimer(200,200){
                    @Override
                    public void onTick(long l) {
                        totalusd.setTextColor(getResources().getColor(R.color.red));
                    }
                    @Override public void onFinish() {
                        totalusd.setTextColor(getResources().getColor(R.color.white));
                    }
                }.start();
                break;
        }
    }

    //Метод работы рабочих на пункте приема метала
    public void BMWorker(){
        int worker = Integer.parseInt(sPref.getString(LOAD_BMW, ""));
        int metal = Integer.parseInt(sPref.getString(LOAD_BMFS, ""));
        int ad = Integer.parseInt(sPref.getString(LOAD_BMA, ""));
        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        if (worker >= 1){
            //Получает информацию из файла сохранения
            int maxMetal = Integer.parseInt(sPref.getString(LOAD_BMMS, ""));
            int day = Integer.parseInt(totalday.getText().toString());
            //Просчитывает рабочих и рандомит сколько они принесли метала
            if(metal >= maxMetal){
                Toast.makeText(this,getResources().getString(R.string.BmFLowStock),Toast.LENGTH_LONG).show();
            }
            else {
                for (int x = 1;x <= worker;x = x + 1){
                    metal = metal + random.nextInt(10);
                }
            }
            //Каждые 30 дней списывает по 5к за каждого рабочего
            if(day % 30 == 0){
                int rub = Integer.parseInt(totalrub.getText().toString());
                int pd = worker * 5000;
                transaction("rub","-",pd);
                Toast.makeText(this,getResources().getString(R.string.BmPayDay) + " " + pd + "р",Toast.LENGTH_SHORT).show();
            }
        }
        metal = metal + random.nextInt(ad);
        ed.putString(LOAD_BMFS,String.valueOf(metal));
        ed.commit();
    }

    //Метод случайного привабления или убавления статистики игрока
    //4 входящие переменные 1(HP,MP,SP)что изменяется 2 (Plus,Minus) прибавляем или вычитаем 3 100% прибавка или отнятие значения 4 Рандомная максимальная величина в добавок к основной
    public void RandomStats(String Stat, String Sign, int ExactChangeStat, int RandChangeStat){
        SharedPreferences.Editor ed = sPref.edit();
        switch (Stat){
            case "HP":
                int varStatHP = ProgBarHP.getProgress();
                if(Sign.equals("+")){

                    varStatHP = varStatHP + ExactChangeStat + random.nextInt(RandChangeStat);

                    ed.putString(LOAD_HP,String.valueOf(varStatHP));
                    /*
                    ProgBarHP.setProgress(varStatHP);

                    totaltexthp.setText(ProgBarHP.getProgress() + "/" +ProgBarHP.getMax());*/
                }
                if(Sign.equals("-")){
                    varStatHP = varStatHP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_HP,String.valueOf(varStatHP));
                    /*
                    ProgBarHP.setProgress(varStatHP);
                    totaltexthp.setText(ProgBarHP.getProgress() + "/" +ProgBarHP.getMax());*/
                }
                break;
            case "MP":
                int varStatMP = ProgBarMP.getProgress();
                if(Sign.equals("+")){
                    varStatMP = varStatMP + ExactChangeStat + random.nextInt(RandChangeStat);
                    ed.putString(LOAD_MP,String.valueOf(varStatMP));
                }
                if(Sign.equals("-")){
                    varStatMP = varStatMP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_MP,String.valueOf(varStatMP));
                }
                break;
            case "SP":
                int varStatSP = ProgBarSP.getProgress();
                if(Sign.equals("+")){
                    varStatSP = varStatSP + ExactChangeStat + random.nextInt(RandChangeStat);
                    ed.putString(LOAD_SP,String.valueOf(varStatSP));
                }
                if(Sign.equals("-")){
                    varStatSP = varStatSP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_SP,String.valueOf(varStatSP));
                }
                break;
            case "RESP":
                int var = Integer.parseInt(sPref.getString(LOAD_RESPECT,""));
                if(Sign.equals("+")){
                    varStatSP = var + ExactChangeStat + random.nextInt(RandChangeStat);
                    ed.putString(LOAD_RESPECT,String.valueOf(varStatSP));
                }
                if(Sign.equals("-")){
                    varStatSP = var - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_RESPECT,String.valueOf(varStatSP));
                }
                break;
        }
        ed.commit();
    }
    //Переход на следующие 6 часов
    public void NextDay(){
        //Методы которые проверяются каждый ход
        SharedPreferences.Editor ed = sPref.edit();


        if(tvHours.getText().equals("6") ){
            ivimgDay.setImageResource(R.drawable.day12);
            tvHours.setText("12");

        }
        else if(tvHours.getText().equals("12")){
            ivimgDay.setImageResource(R.drawable.day18);
            tvHours.setText("18");
        }
        else if(tvHours.getText().equals("18")){
            ivimgDay.setImageResource(R.drawable.day24);
            tvHours.setText("0");
        }
        else if(tvHours.getText().equals("0")){
            ivimgDay.setImageResource(R.drawable.day6);
            tvHours.setText("6");

            RandomStats("HP","-",0,20);
            RandomStats("SP","-",0,20);
            RandomStats("MP","-",0,20);


            int load = Integer.parseInt(sPref.getString(LOAD_DAY,""));
            ed.putString(LOAD_DAY,String.valueOf(load + 1));
            ed.commit();

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
    public void SellScrap(){
//        Toast.makeText(this,CourseScrap.getText().toString() + " " + KgScrap.getText().toString() + " " + totalrub.getText().toString(),Toast.LENGTH_LONG).show();
        int intSellScrap = Integer.parseInt(CourseScrap.getText().toString()) * Integer.parseInt(KgScrap.getText().toString()) + Integer.parseInt(totalrub.getText().toString());
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_RUB,String.valueOf(intSellScrap));
        ed.putString(LOAD_SCRAP, "0");
        ed.commit();


    }
    //Нахождение металла
    public void FindScrup(){
        int Scrap = Integer.parseInt(KgScrap.getText().toString());
        int maxScrap = Integer.parseInt(sPref.getString(LOAD_MAXSCRAP,""));
        if(Scrap <= maxScrap){
            Scrap = Scrap + random.nextInt(6);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_SCRAP, String.valueOf(Scrap));
            ed.commit();

        }
        else {
            Toast.makeText(this,getResources().getString(R.string.FrFLotScrap),Toast.LENGTH_LONG).show();
        }


    }
    //Курс металла
    public void CourseScrap(){
        SharedPreferences.Editor ed = sPref.edit();
        int Course = 11 + random.nextInt(21 - 10);
        ed.putString(LOAD_COURSESCRAP,String.valueOf(Course));
        ed.commit();

    }


    //Изменение фрагментов игры
    public void ChangeFragments(View view){
        fTrans = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btnPass:
                namefragments.setText(getResources().getString(R.string.NFPass));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentPass);
                break;
            case R.id.btnFood:
                namefragments.setText(getResources().getString(R.string.NFFood));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentFood);
                break;
            case R.id.btnMood:
                namefragments.setText(getResources().getString(R.string.NFMood));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentMood);
                break;
            case R.id.btnHealth:
                namefragments.setText(getResources().getString(R.string.NFHealth));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentHealth);
                break;
            case R.id.btnFreelance:
                namefragments.setText(getResources().getString(R.string.NFFreelance));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentFreelance);
                break;
            case R.id.btnWork:
                namefragments.setText(getResources().getString(R.string.NFWork));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentWork);
                break;
            case R.id.btnBusiness:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentBusinessMetal);
                break;
            case R.id.topBtnMetalPoint:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentBusinessMetal);
                break;

            case R.id.topBtnCafe:
                namefragments.setText(getResources().getString(R.string.NFBusiness));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentBusinessCafe);
                break;
            case R.id.btnEducation:
                namefragments.setText(getResources().getString(R.string.NFEducation));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentEducation);
                break;
            case R.id.btnProperty:
                namefragments.setText(getResources().getString(R.string.NFProperty));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentProperty);
                break;
            case R.id.btnHolding:
                namefragments.setText(getResources().getString(R.string.NFHolding));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentHolding);
                break;
            case R.id.btnRespect:
                namefragments.setText(getResources().getString(R.string.NFRespect));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentRespect);
                break;
            case R.id.btnBank:
                namefragments.setText(getResources().getString(R.string.NFBank));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentBank);
                break;
            case R.id.btnCasino:
                namefragments.setText(getResources().getString(R.string.NFCasino));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentCasino);
                break;
            case R.id.btnAchivment:
                namefragments.setText(getResources().getString(R.string.NFAchivment));
                fTrans.replace(R.id.MainFragmentsWindow,FragmentAchivment);
                break;

        }
        fTrans.commit();
        //Отображает

        advertisement += 1;

        if(advertisement >= 30){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            advertisement = 0;
        }

        if(namefragments.getText().toString() == getResources().getString(R.string.NFFreelance)){
            LayoutScrap.setVisibility(View.VISIBLE);
        }
        else{
            LayoutScrap.setVisibility(View.INVISIBLE);
        }
        if(namefragments.getText().toString() == getResources().getString(R.string.NFBusiness)){
            ScrollBusiness.setVisibility(View.VISIBLE);
        }
        else{
            ScrollBusiness.setVisibility(View.INVISIBLE);
        }


    }

    //Загрузка игры
    @SuppressLint("SetTextI18n")
    public void loadGame(){

        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
        //Получает информацию из файла сохранения
        NameGamer = sPref.getString(LOAD_NAME, "");
        TotalRub = sPref.getString(LOAD_RUB, "");
        TotalUsd = sPref.getString(LOAD_USD,"");
        TotalResp = sPref.getString(LOAD_RESPECT,"");
        TotalDay = sPref.getString(LOAD_DAY,"");
        TotalHP = sPref.getString(LOAD_TotalHP,"");
        TotalMP = sPref.getString(LOAD_TotalMP,"");
        TotalSP = sPref.getString(LOAD_TotalSP,"");
        HP = sPref.getString(LOAD_HP,"");
        SP = sPref.getString(LOAD_SP,"");
        MP = sPref.getString(LOAD_MP,"");
        SCRAP = sPref.getString(LOAD_SCRAP,"");
        LoadCourseScrap = sPref.getString(LOAD_COURSESCRAP,"");


        //Выгружает инфу в основные параметры
        totalrub.setText(String.valueOf(TotalRub));
        totalusd.setText(String.valueOf(TotalUsd));
        totalday.setText(String.valueOf(TotalDay));
        totalresp.setText(String.valueOf(TotalResp));
        //Задает уровень HP/SP/MP
        ProgBarHP.setMax(Integer.parseInt(TotalHP));
        ProgBarSP.setMax(Integer.parseInt(TotalSP));
        ProgBarMP.setMax(Integer.parseInt(TotalMP));
        ProgBarHP.setProgress(Integer.parseInt(HP));
        ProgBarSP.setProgress(Integer.parseInt(SP));
        ProgBarMP.setProgress(Integer.parseInt(MP));

        //Загрузка Металлолома

        textKgScrap.setText(getResources().getString(R.string.FrFSupportScrapQuantity) + " ");
        textCourseScrap.setText(getResources().getString(R.string.FrFSupportScrapCourse) + " ");
        KgScrap.setText(SCRAP);
        CourseScrap.setText(LoadCourseScrap);



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
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
        int rub = Integer.parseInt(sPref.getString(LOAD_RUB,""));
        int usd = Integer.parseInt(sPref.getString(LOAD_USD,""));
        int hp = Integer.parseInt(sPref.getString(LOAD_HP,""));
        int mp = Integer.parseInt(sPref.getString(LOAD_MP,""));
        int sp = Integer.parseInt(sPref.getString(LOAD_SP,""));

        SharedPreferences.Editor ed = sPref.edit();
        if(hp <= 0){
            ed.putString(LOAD_HP,"10");
        }
        if(mp <= 0){
            ed.putString(LOAD_MP,"10");
        }
        if(sp <= 0){
            ed.putString(LOAD_SP,"10");
        }
        if(rub < 0){
            rub += 2000;
            ed.putString(LOAD_RUB,String.valueOf(rub));
        }
        if(usd < 0){
            usd += 500;
            ed.putString(LOAD_USD,String.valueOf(usd));
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
        Toast.makeText(this, getResources().getString(R.string.videoadmessageOk), Toast.LENGTH_SHORT).show();
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
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }



    /* Переделал все на локальные загрузки и сейвы
    public void SaveGame(String Load,String Save){
        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        //Сохранение основных значений
        //ed.putString(LOAD_RUB, (String) totalrub.getText());
        ed.putString(LOAD_USD, (String) totalusd.getText());
        ed.putString(LOAD_RESPECT, (String) totalresp.getText());
        ed.putString(LOAD_DAY, (String) totalday.getText());
        ed.putString(LOAD_SCRAP, (String) KgScrap.getText());
        ed.putString(LOAD_COURSESCRAP, (String) CourseScrap.getText());
        //Сохранение состояния персонажа
        //Максимальное значение
        ed.putString(LOAD_TotalHP, String.valueOf(ProgBarHP.getMax()));
        ed.putString(LOAD_TotalSP, String.valueOf(ProgBarSP.getMax()));
        ed.putString(LOAD_TotalMP, String.valueOf(ProgBarMP.getMax()));
        //Значение
        ed.putString(LOAD_HP, String.valueOf(ProgBarHP.getProgress()));
        ed.putString(LOAD_SP, String.valueOf(ProgBarSP.getProgress()));
        ed.putString(LOAD_MP, String.valueOf(ProgBarMP.getProgress()));
        ed.commit();
//        Toast.makeText(this,String.valueOf(ProgBarHP.getProgress()),Toast.LENGTH_SHORT).show();
    }*/
}
