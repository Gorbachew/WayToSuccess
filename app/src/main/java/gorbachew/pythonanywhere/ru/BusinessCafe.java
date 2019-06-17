package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class BusinessCafe extends Fragment {
    SharedPreferences sPref;

        final String LOAD_BC = "BusinessCafe";
        final String LOAD_BCR = "BCroom";
        final String LOAD_BCA = "BCad";
        final String LOAD_BCW = "BCwaiter";
        final String LOAD_BCC = "BCcook";
        final String LOAD_BCV = "BCvisitors";
        final String LOAD_BCVLW = "BCvisitorsLastWeek";
        final String LOAD_BCT = "BCtables";

        final String LOAD_BCPR = "BCPriceRoom";
        final String LOAD_BCPW = "BCPriceWaiter";
        final String LOAD_BCPC = "BCPriceCook";
        final String LOAD_BCPr = "BCprofit";
        final String SAVED_BUSINESS = "Business";
        final String LOAD_BCP = "BCPriceBusiness";



        Button btnBСBuyCafe,btnRoom,btnWaiter,btnCook,btnTable,btnAd,btnBCSellBusiness;
        LinearLayout BCBis;
        TextView textRoom,textAd,textWaiter,textCook,textVisitors,textVisitorsLastWeek,textTables,textProfit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View BusinessCafe = inflater.inflate(R.layout.fragment_business_cafe, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        btnBСBuyCafe = BusinessCafe.findViewById(R.id.btnBСBuyCafe);
        BCBis = BusinessCafe.findViewById(R.id.BCBis);
        textRoom = BusinessCafe.findViewById(R.id.textBCroom);
        textAd = BusinessCafe.findViewById(R.id.textBCad);
        textWaiter = BusinessCafe.findViewById(R.id.textBCwaiter);
        textCook = BusinessCafe.findViewById(R.id.textBCcook);
        textVisitors = BusinessCafe.findViewById(R.id.BCvisitors);
        textTables = BusinessCafe.findViewById(R.id.textBCtable);
        textVisitorsLastWeek = BusinessCafe.findViewById(R.id.BCvisitorsLastWeek);
        textProfit = BusinessCafe.findViewById(R.id.BCprofit);



//        Button work = BusinessCafe.findViewById(R.id.btnBCWork);
        btnCook = BusinessCafe.findViewById(R.id.btnBCcook);
        btnWaiter = BusinessCafe.findViewById(R.id.btnBCwaiter);
        btnRoom = BusinessCafe.findViewById(R.id.btnBCroom);
        btnTable = BusinessCafe.findViewById(R.id.btnBCtable);
        btnAd = BusinessCafe.findViewById(R.id.btnBCad);
        btnBCSellBusiness = BusinessCafe.findViewById(R.id.btnBCSellBusiness);

//        work.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((Game)getActivity()).NextDay();
//                load_info();
//            }
//        });


        btnBСBuyCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check = sPref.getString(SAVED_BUSINESS,"");
                if (check.equals("0")){
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_BC,"1");
                    ed.putString(SAVED_BUSINESS,"2");
                    ed.commit();
                    btnBСBuyCafe.setVisibility(View.INVISIBLE);
                    BCBis.setVisibility(View.VISIBLE);

                    ((Game)getActivity()).transaction("usd","-",50000);

                    ((Game)getActivity()).NextDay();
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.BCdontTime),Toast.LENGTH_LONG).show();
                }

            }
        });

        btnWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tester = Integer.parseInt(sPref.getString(LOAD_BCC,""));
                int testing = Integer.parseInt(sPref.getString(LOAD_BCW,""));
                int vartest = tester * 3;
                if (testing < vartest) {
                    int var = Integer.parseInt(sPref.getString(LOAD_BCW, ""));
                    int priceVar = Integer.parseInt(sPref.getString(LOAD_BCPW, ""));

                    String saveVar = String.valueOf(var + 1);
                    String savepriceVar = String.valueOf(priceVar + 1000);

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_BCW, saveVar);
                    ed.putString(LOAD_BCPW, savepriceVar);
                    ed.commit();


                    ((Game) getActivity()).transaction("rub", "-", priceVar);

                    ((Game) getActivity()).NextDay();
                    load_info();
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.BCerrorWaiter),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tester = Integer.parseInt(sPref.getString(LOAD_BCR,""));
                int testing = Integer.parseInt(sPref.getString(LOAD_BCC,""));
                int vartest = tester * 2;
                if (testing < vartest) {
                    int var = Integer.parseInt(sPref.getString(LOAD_BCC, ""));
                    int priceVar = Integer.parseInt(sPref.getString(LOAD_BCPC, ""));

                    String saveVar = String.valueOf(var + 1);
                    String savepriceVar = String.valueOf(priceVar * 2);

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_BCC, saveVar);
                    ed.putString(LOAD_BCPC, savepriceVar);
                    ed.commit();


                    ((Game) getActivity()).transaction("usd", "-", priceVar);

                    ((Game) getActivity()).NextDay();
                    load_info();
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.BCerrorCook),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(LOAD_BCR,""));
                int priceVar = Integer.parseInt(sPref.getString(LOAD_BCPR,""));

                String saveVar = String.valueOf(var + 1);
                String savepriceVar = String.valueOf(priceVar + 10000);

                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BCR,saveVar);
                ed.putString(LOAD_BCPR,savepriceVar);
                ed.commit();


                ((Game)getActivity()).transaction("usd","-",priceVar);

                ((Game)getActivity()).NextDay();
                load_info();
            }
        });
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tester = Integer.parseInt(sPref.getString(LOAD_BCW,""));
                int testing = Integer.parseInt(sPref.getString(LOAD_BCT,""));
                int vartest = tester * 2;
                if (testing < vartest) {
                    int tables = Integer.parseInt(sPref.getString(LOAD_BCT,""));

                    String saveTables = String.valueOf(tables + 1);

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_BCT,saveTables);
                    ed.commit();


                    ((Game)getActivity()).transaction("rub","-",5000);

                    ((Game)getActivity()).NextDay();
                    load_info();
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.BCerrorTable),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(LOAD_BCA,""));


                String saveVar = String.valueOf(var + 1);


                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_BCA,saveVar);

                ed.commit();


                ((Game)getActivity()).transaction("usd","-",100);

                ((Game)getActivity()).NextDay();
                load_info();
            }
        });

        btnBCSellBusiness.setOnClickListener(new View.OnClickListener() {
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
                                        int price = Integer.parseInt(sPref.getString(LOAD_BCP,""));
                                        ((Game)getActivity()).transaction("usd","+",price);
                                        btnBСBuyCafe.setVisibility(View.VISIBLE);
                                        BCBis.setVisibility(View.INVISIBLE);





                                        SharedPreferences.Editor ed = sPref.edit();

                                        ed.putString("BusinessCafe","0");
                                        ed.putString("BCroom","1");
                                        ed.putString("BCad","1");
                                        ed.putString("BCwaiter","0");
                                        ed.putString("BCcook","0");
                                        ed.putString("BCvisitors","0");
                                        ed.putString("BCvisitorsLastWeek","0");
                                        ed.putString("BCtables","0");
                                        ed.putString("BCprofit","0");
                                        ed.putString("BCPriceRoom","20000");
                                        ed.putString("BCPriceWaiter","5000");
                                        ed.putString("BCPriceCook","3000");
                                        ed.putString("BCPriceBusiness","0");
                                        ed.putString(SAVED_BUSINESS,"0");


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

        return BusinessCafe;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sPref.getString(LOAD_BC,"").equals("1") ){
            btnBСBuyCafe.setVisibility(View.INVISIBLE);
            BCBis.setVisibility(View.VISIBLE);
        }
        else {
            btnBСBuyCafe.setVisibility(View.VISIBLE);
            BCBis.setVisibility(View.INVISIBLE);
        }

        load_info();




    }
    public void load_info(){
        textRoom.setText(sPref.getString(LOAD_BCR,""));
        textAd.setText(sPref.getString(LOAD_BCA,""));
        textWaiter.setText(sPref.getString(LOAD_BCW,""));
        textCook.setText(sPref.getString(LOAD_BCC,""));
        textVisitors.setText(sPref.getString(LOAD_BCV,""));
        textTables.setText(sPref.getString(LOAD_BCT,""));
        textVisitorsLastWeek.setText(sPref.getString(LOAD_BCVLW,""));
        textProfit.setText(sPref.getString(LOAD_BCPr,""));

        price_business();



        btnRoom.setText(getResources().getString(R.string.BCbuyRoom) + "|" +sPref.getString(LOAD_BCPR,"")+"$");
        btnCook.setText(getResources().getString(R.string.BCbuyCook) + "|" +sPref.getString(LOAD_BCPC,"")+"$");
        btnWaiter.setText(getResources().getString(R.string.BCbuyWaiter) + "|" +sPref.getString(LOAD_BCPW,"")+"р");
    }

    public void price_business(){
       int room = Integer.parseInt(sPref.getString(LOAD_BCR,""));
       int ad = Integer.parseInt(sPref.getString(LOAD_BCA,""));
       int waiter = Integer.parseInt(sPref.getString(LOAD_BCW,""));
       int cook = Integer.parseInt( sPref.getString(LOAD_BCC,""));
       int table = Integer.parseInt( sPref.getString(LOAD_BCT,""));

       int priceRoom = room * 5000;
       int priceAd = ad * 50;
       int priceWaiter = waiter * 2000;
       int priceCook = cook * 5000;
       int priceTable = table * 300;

       String allPrice = String.valueOf(priceAd + priceCook + priceRoom + priceTable + priceWaiter);

       SharedPreferences.Editor ed = sPref.edit();
       ed.putString(LOAD_BCP,allPrice);
       ed.commit();
       btnBCSellBusiness.setText(getResources().getString(R.string.BmFSellBusiness) + "|" + allPrice + "$");

    }

}
