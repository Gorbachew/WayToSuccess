package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class BusinessMetal extends Fragment {

    Toast toast = null,toast2 = null;
    Button BuyMetalPoint,btnBMWork,btnAd,btnWorker,btnSellMetal,btnUpStock,btnSellBis;
    SharedPreferences sPref;
    LinearLayout Bis;
    TextView FullStock,MaxStock,Ad,Worker,PriceFactrory,CourseScrap,totalrub;
    final String LOAD_BMS = "BusinessMetalPoint";
    final String LOAD_BMFS = "BMFullStock";
    final String LOAD_BMMS = "BMMaxStock";


    final String LOAD_BMA = "BMAd";
    final String LOAD_BMW = "BMWorker";
    final String LOAD_BMPW = "BMPriceWorker";
    final String LOAD_BMPB = "BMPriceBis";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String LOAD_COURSESCRAP = "CourseScrap";
    final String SAVED_BUSINESS = "Business";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View Business = inflater.inflate(R.layout.fragment_business, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);



        BuyMetalPoint = Business.findViewById(R.id.btnBMBuyMetalPoint);
        Bis = Business.findViewById(R.id.BMBis);
        FullStock = Business.findViewById(R.id.BMFStock);
        MaxStock = Business.findViewById(R.id.BMMStock);
        Ad = Business.findViewById(R.id.BMAdvertising);
        Worker = Business.findViewById(R.id.BMWorker);
        PriceFactrory = Business.findViewById(R.id.BMPriceFactory);
        CourseScrap = getActivity().findViewById(R.id.CourseScrap);
        btnBMWork = Business.findViewById(R.id.btnBMWork);
        btnAd = Business.findViewById(R.id.btnBMUpAdvertising);
        btnWorker = Business.findViewById(R.id.BMNewWorker);
        btnSellMetal = Business.findViewById(R.id.btnBMSellFactory);
        btnSellBis = Business.findViewById(R.id.btnBMSellBusiness);

        btnUpStock = Business.findViewById(R.id.btnBMUpStock);
        totalrub = getActivity().findViewById(R.id.TotalRUB);
        final String LOAD_RUB = "RUB";
        final String LOAD_USD = "USD";

        //ВЫвод инфы
        Load_info();
        //Склад






        BuyMetalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = sPref.getString(SAVED_BUSINESS,"");
                if (check.equals("0")) {
                    if (sPref.getInt(LOAD_RUB, 0) < 50000) {
                        ((Game) getActivity()).LowMoney("rub");
                    }
                    else {
                        BuyMetalPoint.setVisibility(View.INVISIBLE);
                        Bis.setVisibility(View.VISIBLE);
                        btnSellBis.setVisibility(View.VISIBLE);
                        btnBMWork.setVisibility(View.VISIBLE);

                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BMS, "1");
                        ed.putString(SAVED_BUSINESS, "1");
                        ed.commit();


                        ((Game) getActivity()).transaction("rub", "-", 50000);
                        Load_info();
                        ((Game)getActivity()).NextDay();
                    }
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.BCdontTime),Toast.LENGTH_LONG).show();
                }

            }
        });
        btnUpStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpStock();
                Load_info();
                ((Game)getActivity()).NextDay();
            }
        });

        btnBMWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStock();
                PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));
                Load_info();
                ((Game)getActivity()).NextDay();

            }
        });
        btnAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdAdvertising();
                PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));
                Load_info();
                ((Game)getActivity()).NextDay();
            }
        });
        btnWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWorker();
                PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));
                Load_info();
                ((Game)getActivity()).NextDay();
            }
        });

        btnSellMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellMetal();
                PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));
                Load_info();
                ((Game)getActivity()).NextDay();

            }
        });
        btnSellBis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Всплывающее окно перед выходом
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Вы действительно хотите продать бизнес?")

                        .setPositiveButton("Продать",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Подхватывает и запускает страницу создания персонажа
                                        int price = Integer.parseInt(sPref.getString(LOAD_BMPB,""));
                                        ((Game)getActivity()).transaction("rub","+",price);


                                        BuyMetalPoint.setVisibility(View.VISIBLE);
                                        Bis.setVisibility(View.INVISIBLE);
                                        btnSellBis.setVisibility(View.INVISIBLE);
                                        btnBMWork.setVisibility(View.INVISIBLE);

                                        SharedPreferences.Editor ed = sPref.edit();
                                        ed.putString("BMFullStock","0");
                                        ed.putString("BMMaxStock","100");
                                        ed.putString("BMAd","1");
                                        ed.putString("BMWorker","0");
                                        ed.putString("BMPriceWorker","1000");
                                        ed.putString("BMPriceBis","0");
                                        ed.putString(SAVED_BUSINESS,"0");
                                        ed.putString(LOAD_BMS,"0");
                                        ed.commit();

                                        ((Game)getActivity()).NextDay();

                                    }
                                })
                        .setNegativeButton("Отложить",
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

        return Business;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sPref.getString(LOAD_BMS,"").equals("1") ){
            BuyMetalPoint.setVisibility(View.INVISIBLE);
            Bis.setVisibility(View.VISIBLE);
            btnSellBis.setVisibility(View.VISIBLE);
            btnBMWork.setVisibility(View.VISIBLE);
        }
        else {
            BuyMetalPoint.setVisibility(View.VISIBLE);
            Bis.setVisibility(View.INVISIBLE);
            btnSellBis.setVisibility(View.INVISIBLE);
            btnBMWork.setVisibility(View.INVISIBLE);
        }
        PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));


        //Проверка на прогресс
        if (MaxStock.getText().toString().equals("100")){
            btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|1000р");
        }
        else if (MaxStock.getText().toString().equals("1000")){
            btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|5000р");
        }
        else if (MaxStock.getText().toString().equals("5000")){
            btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|10000р");
        }
        else if (MaxStock.getText().toString().equals("10000")){
            btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|50000р");
        }
        else if (MaxStock.getText().toString().equals("50000")){
            btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|100000р");
        }
        else if (MaxStock.getText().toString().equals("100000")){
            btnUpStock.setEnabled(false);
            btnUpStock.setText(getResources().getString(R.string.NoUpdate));
        }

        //Цена рабочего
        int PriceWorker = Integer.parseInt(sPref.getString(LOAD_BMPW,""));
        btnWorker.setText(getResources().getString(R.string.BmFNewWorker) + "|" + String.valueOf(PriceWorker) +"р");


    }

    public void AddStock(){

        int Met = Integer.parseInt(FullStock.getText().toString());

        if(Met <= Integer.parseInt(MaxStock.getText().toString())){

            Random random = new Random();
            int randomMetal = random.nextInt(5);
            if (randomMetal == 0) {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(getActivity(), getResources().getString(R.string.BmFDontFindMetal), Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
               Met = Met + randomMetal;
               SharedPreferences.Editor ed = sPref.edit();
               ed.putString(LOAD_BMFS,String.valueOf(Met));
               ed.commit();
            }
        }
        else {
            if (toast2 != null) {
                toast2.cancel();
            }
            toast2 = Toast.makeText(getActivity(),getResources().getString(R.string.BmFLowStock),Toast.LENGTH_LONG);
            toast2.show();
        }
    }

    public void UpStock(){
        int MaxMet = Integer.parseInt(MaxStock.getText().toString());

        if (MaxMet == 100){
            if (sPref.getInt(LOAD_RUB, 0) < 1000) {
                ((Game) getActivity()).LowMoney("rub");
            }
            else {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BMMS, "300");
                ed.commit();
                btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|5000р");
                ((Game) getActivity()).transaction("rub", "-", 1000);
            }
        }
        else if (MaxMet == 300){
            if (sPref.getInt(LOAD_RUB, 0) < 5000) {
                ((Game) getActivity()).LowMoney("rub");
            }
            else {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BMMS, "1000");
                ed.commit();
                btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|10000р");
                ((Game) getActivity()).transaction("rub", "-", 5000);
            }
        }
        else if (MaxMet == 1000){
            if (sPref.getInt(LOAD_RUB, 0) < 10000) {
                ((Game) getActivity()).LowMoney("rub");
            }
            else {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BMMS, "5000");
                ed.commit();
                btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|50000р");
                ((Game) getActivity()).transaction("rub", "-", 10000);
            }
        }
        else if (MaxMet == 5000){
            if (sPref.getInt(LOAD_RUB, 0) < 50000) {
                ((Game) getActivity()).LowMoney("rub");
            }
            else {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BMMS, "10000");
                ed.commit();
                btnUpStock.setText(getResources().getString(R.string.BmFUPStock) + "|100000р");
                ((Game) getActivity()).transaction("rub", "-", 50000);
            }
        }
        else if (MaxMet == 10000){
            if (sPref.getInt(LOAD_RUB, 0) < 100000) {
                ((Game) getActivity()).LowMoney("rub");
            }
            else {
                MaxStock.setText(String.valueOf(MaxMet = 100000));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BMMS, "50000");
                ed.commit();
                btnUpStock.setText(getResources().getString(R.string.NoUpdate));
                btnUpStock.setEnabled(false);
                ((Game) getActivity()).transaction("rub", "-", 100000);
            }
        }
    }

    public void AdAdvertising(){
        if (sPref.getInt(LOAD_RUB, 0) < 500) {
            ((Game) getActivity()).LowMoney("rub");
        }
        else {
            int Advert = Integer.parseInt(Ad.getText().toString());
            String SaveAd = String.valueOf(Advert + 1);
            Ad.setText(SaveAd);

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(LOAD_BMA, SaveAd);
            ed.commit();

            ((Game) getActivity()).transaction("rub", "-", 500);
        }
    }

    public void SellMetal(){
        int rub = sPref.getInt(LOAD_RUB,0);
        int Price = sPref.getInt(LOAD_COURSESCRAP,0);
        int Met = Integer.parseInt(sPref.getString(LOAD_BMFS,""));
        int plusrub = Price * Met + rub;


                SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_BMFS,"0");
        ed.putInt(LOAD_RUB,plusrub);
        ed.commit();
    }

    public void AddWorker(){
        int worker = Integer.parseInt(Worker.getText().toString());
        String SaveWorker = String.valueOf(worker + 1);
        SharedPreferences.Editor ed = sPref.edit();

        int PriceWorker = Integer.parseInt(sPref.getString(LOAD_BMPW,""));
        int SavePriceWorker = PriceWorker + 1000;
        if (sPref.getInt(LOAD_RUB, 0) < PriceWorker) {
            ((Game) getActivity()).LowMoney("rub");
        }
        else {
            ed.putString(LOAD_BMW, SaveWorker);
            ed.putString(LOAD_BMPW, String.valueOf(SavePriceWorker));
            ((Game) getActivity()).transaction("rub", "-", PriceWorker);

            ed.commit();

            btnWorker.setText(getResources().getString(R.string.BmFNewWorker) + "|" + sPref.getString(LOAD_BMPW, "") + "р");
        }
    }

    public void Load_info(){
        FullStock.setText(sPref.getString(LOAD_BMFS, ""));
        MaxStock.setText(sPref.getString(LOAD_BMMS, ""));
        Ad.setText(sPref.getString(LOAD_BMA, ""));
        Worker.setText(sPref.getString(LOAD_BMW,""));
        PriceFactrory.setText(String.valueOf(sPref.getInt(LOAD_COURSESCRAP,0)));

        //Составление Цены продажи бизнеса
        PriceBis();

    }
    public void PriceBis(){

        int ad = Integer.parseInt(sPref.getString(LOAD_BMA,""));
        int worker = Integer.parseInt(sPref.getString(LOAD_BMW,""));
        int stock = Integer.parseInt(sPref.getString(LOAD_BMMS,""));


        int pricead = ad * 50;
        int pricework = worker * 500;
        int allprice = pricework + pricead + 10000 + stock;


        btnSellBis.setText(getResources().getString(R.string.BmFSellBusiness) + "|" + String.valueOf(allprice));

        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LOAD_BMPB,String.valueOf(allprice));
        ed.commit();
    }



}
