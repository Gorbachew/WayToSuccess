package gorbachew.pythonanywhere.ru;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class Food extends Fragment {

    Button BtnTrash,BtnHunt,btnRobKiosk,btnBuyKiosk,btnBuyStore,btnEatCafe,btnEatRest,btnPersChef;
    TextView RUB;
    ProgressBar PBHP,PBMP,PBSP;
    final Random random = new Random();
    SharedPreferences sPref;
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_BUFFCOOK = "BuffCook";
    final String LOAD_RUB = "RUB";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FoodtFr = inflater.inflate(R.layout.fragment_food, container, false);


        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);



        BtnTrash = FoodtFr.findViewById(R.id.btnFoodTrash);
        BtnHunt = FoodtFr.findViewById(R.id.btnFoodHunt);
        btnRobKiosk = FoodtFr.findViewById(R.id.btnFoodRobKiosk);
        btnBuyKiosk = FoodtFr.findViewById(R.id.btnFoodBuyKiosk);
        btnBuyStore = FoodtFr.findViewById(R.id.btnFoodbuyStore);
        btnEatCafe = FoodtFr.findViewById(R.id.btnFoodEatCoffee);
        btnEatRest = FoodtFr.findViewById(R.id.btnFoodEatRestaurant);
        btnPersChef = FoodtFr.findViewById(R.id.btnFoodPersonalChef);
        CheckButton();




        BtnTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Game)getActivity()).RandomStats("HP","-",0,5);
                ((Game)getActivity()).RandomStats("SP","+",0,20);
                ((Game)getActivity()).RandomStats("MP","-",0,5);

                ((Game)getActivity()).NextDay();

            }
        });
        BtnHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                if(var >= 1){
                    ((Game)getActivity()).RandomStats("HP","-",0,5);
                    ((Game)getActivity()).RandomStats("SP","+",0,25);
                    ((Game)getActivity()).RandomStats("MP","-",0,5);

                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.FFerror1),Toast.LENGTH_LONG).show();
                }

                ((Game)getActivity()).NextDay();

            }
        });
        btnRobKiosk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                if(var >= 1){
                    ((Game)getActivity()).RandomStats("HP","-",0,15);
                    ((Game)getActivity()).RandomStats("SP","+",0,30);
                    ((Game)getActivity()).RandomStats("MP","+",0,5);
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2),Toast.LENGTH_LONG).show();
                }

                ((Game)getActivity()).NextDay();
            }
        });
        btnBuyKiosk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 500) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 2) {
                        ((Game) getActivity()).RandomStats("SP", "+", 10, 40);
                        ((Game) getActivity()).transaction("rub", "-", 500);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.FFerror3), Toast.LENGTH_LONG).show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnEatCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 1500) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 3) {
                        ((Game) getActivity()).RandomStats("SP", "+", 20, 40);
                        ((Game) getActivity()).transaction("rub", "-", 1500);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.FFerror4), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });

        btnBuyStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 3000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                    if(var >= 2){
                        ((Game)getActivity()).RandomStats("SP","+",30,50);
                        ((Game)getActivity()).transaction("rub","-",3000);
                    }
                    else {
                        Toast.makeText(getActivity(),getResources().getString(R.string.FFerror5),Toast.LENGTH_LONG).show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });

        btnEatRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 7500) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                    if(var >= 4){
                        ((Game)getActivity()).RandomStats("SP","+",40,50);
                        ((Game)getActivity()).transaction("rub","-",7500);
                    }
                    else {
                        Toast.makeText(getActivity(),getResources().getString(R.string.FFerror6),Toast.LENGTH_LONG).show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });
        btnPersChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 50000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                    String checkvar = sPref.getString(LOAD_BUFFCOOK,"");
                    if (checkvar.equals("0")){
                        if(var >= 6){

                            ((Game)getActivity()).transaction("rub","-",50000);
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_BUFFCOOK,"1");
                            ed.commit();

                        }
                        else {
                            Toast.makeText(getActivity(),getResources().getString(R.string.FFerror7),Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(checkvar.equals("1")){
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BUFFCOOK,"0");
                        ed.commit();
                    }
                    CheckButton();
                    ((Game)getActivity()).NextDay();
                }
            }
        });
        // Inflate the layout for this fragment
        return FoodtFr;




    }

    public void CheckButton(){

        String checkvar = sPref.getString(LOAD_BUFFCOOK,"");
        if(checkvar.equals("0")){
            btnPersChef.setText(getResources().getString(R.string.FFpersonalChef));
        }
        else if(checkvar.equals("1")){
            btnPersChef.setText(getResources().getString(R.string.FFfiredChef));
        }

    }


}
