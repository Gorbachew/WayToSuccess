package gorbachew.pythonanywhere.ru;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends AppCompatActivity {
    SharedPreferences sPref;
    final String LOAD_NAME = "Name";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String LOAD_RESPECT = "RESPECT";
    final String LOAD_DAY = "DAY";
    final String LOAD_AGE = "Age";
    final String LOAD_TotalHP = "TotalHP";
    final String LOAD_TotalMP = "TotalMP";
    final String LOAD_TotalSP = "TotalSP";

    final String LOAD_HP = "HP";
    final String LOAD_MP = "MP";
    final String LOAD_SP = "SP";

    final String LOAD_SCRAP = "SCRAP";

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

    final Random random = new Random();

    String NameGamer,TotalRub,TotalUsd,TotalResp,TotalDay,TotalHP,TotalSP,TotalMP,HP,SP,MP,SCRAP,LoadCourseScrap;
    FragmentTransaction fTrans;
    Fragment FragmentPass,FragmentFood,FragmentMood,FragmentHealth,FragmentFreelance,FragmentWork,FragmentBusinessMetal,FragmentBusinessPC,FragmentBusinessCafe,FragmentProperty,FragmentHolding,FragmentEducation;
    ImageView ivimgDay;
    LinearLayout LayoutScrap;
    HorizontalScrollView ScrollBusiness;



    TextView totalrub,totalusd,totalresp,totalday,totaltexthp,totaltextmp,totaltextsp,namefragments,tvHours,textKgScrap,KgScrap,textCourseScrap,CourseScrap;
    ProgressBar ProgBarHP,ProgBarMP,ProgBarSP;
    ImageButton BtnMood,BtnFood,BtnHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        BtnMood = findViewById(R.id.btnMood);
        BtnFood = findViewById(R.id.btnFood);
        BtnHealth = findViewById(R.id.btnHealth);
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




        //Кнопки

        //Копки закончились

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
        ed.commit();
    }


    //Метод работы рабочих на пункте приема метала
    public void BMWorker(){
        int worker = Integer.parseInt(sPref.getString(LOAD_BMW, ""));
        int metal = Integer.parseInt(sPref.getString(LOAD_BMFS, ""));
        int ad = Integer.parseInt(sPref.getString(LOAD_BMA, ""));
        SharedPreferences.Editor ed = sPref.edit();
        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
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
                if(Sign.equals("Plus")){

                    varStatHP = varStatHP + ExactChangeStat + random.nextInt(RandChangeStat);

                    ed.putString(LOAD_HP,String.valueOf(varStatHP));
                    /*
                    ProgBarHP.setProgress(varStatHP);

                    totaltexthp.setText(ProgBarHP.getProgress() + "/" +ProgBarHP.getMax());*/
                }
                if(Sign.equals("Minus")){
                    varStatHP = varStatHP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_HP,String.valueOf(varStatHP));
                    /*
                    ProgBarHP.setProgress(varStatHP);
                    totaltexthp.setText(ProgBarHP.getProgress() + "/" +ProgBarHP.getMax());*/
                }
                break;
            case "MP":
                int varStatMP = ProgBarMP.getProgress();
                if(Sign.equals("Plus")){
                    varStatMP = varStatMP + ExactChangeStat + random.nextInt(RandChangeStat);
                    ed.putString(LOAD_MP,String.valueOf(varStatMP));
                }
                if(Sign.equals("Minus")){
                    varStatMP = varStatMP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_MP,String.valueOf(varStatMP));
                }
                break;
            case "SP":
                int varStatSP = ProgBarSP.getProgress();
                if(Sign.equals("Plus")){
                    varStatSP = varStatSP + ExactChangeStat + random.nextInt(RandChangeStat);
                    ed.putString(LOAD_SP,String.valueOf(varStatSP));
                }
                if(Sign.equals("Minus")){
                    varStatSP = varStatSP - ExactChangeStat - random.nextInt(RandChangeStat);
                    ed.putString(LOAD_SP,String.valueOf(varStatSP));
                }
                break;
        }
        ed.commit();



    }

    public void NextDay(){
        //Методы которые проверяются каждый ход



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

            int load = Integer.parseInt(sPref.getString(LOAD_DAY,""));
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_DAY,String.valueOf(load + 1));
            ed.commit();

            BMWorker();
            BCafe();
        }
        CourseScrap();
        loadGame();
    }



    public void SellScrap(){
//        Toast.makeText(this,CourseScrap.getText().toString() + " " + KgScrap.getText().toString() + " " + totalrub.getText().toString(),Toast.LENGTH_LONG).show();
        int intSellScrap = Integer.parseInt(CourseScrap.getText().toString()) * Integer.parseInt(KgScrap.getText().toString()) + Integer.parseInt(totalrub.getText().toString());

        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_RUB,String.valueOf(intSellScrap));
        ed.putString(LOAD_SCRAP, "0");
        ed.commit();


    }

    public void FindScrup(){
        int Scrap = Integer.parseInt(KgScrap.getText().toString());
        if(Scrap <= 30){
            Scrap = Scrap + random.nextInt(5);

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_SCRAP, String.valueOf(Scrap));
            ed.commit();

        }
        else {
            Toast.makeText(this,getResources().getString(R.string.FrFLotScrap),Toast.LENGTH_LONG).show();
        }


    }
    public void CourseScrap(){
        int Course = 11 + random.nextInt(21 - 10);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_COURSESCRAP,String.valueOf(Course));
        ed.commit();

    }



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

        }
        fTrans.commit();
        //Отображает

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
        textKgScrap.setText(getResources().getString(R.string.FrFSupportScrapQuantity));
        textCourseScrap.setText(getResources().getString(R.string.FrFSupportScrapCourse));
        namefragments.setText(getResources().getString(R.string.NFFreelance));
        KgScrap.setText(SCRAP);
        CourseScrap.setText(LoadCourseScrap);



        String DiffHP = ProgBarHP.getProgress() + "/" + ProgBarHP.getMax();
        String DiffSP = ProgBarSP.getProgress() + "/" + ProgBarSP.getMax();
        String DiffMP = ProgBarMP.getProgress() + "/" + ProgBarMP.getMax();
        totaltexthp.setText(DiffHP);
        totaltextsp.setText(DiffSP);
        totaltextmp.setText(DiffMP);


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
