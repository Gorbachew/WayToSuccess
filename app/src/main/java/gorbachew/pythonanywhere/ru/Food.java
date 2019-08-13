package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Random;


public class Food extends Fragment {
    Toast toast;
    private RewardedVideoAd mRewardedVideoAd;
    Button BtnTrash,BtnHunt,btnRobKiosk,btnBuyKiosk,btnBuyStore,btnEatCafe,btnEatRest,btnPersChef,btnFoodKrekers;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FoodtFr = inflater.inflate(R.layout.fragment_food, container, false);


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) getActivity());


        BtnTrash = FoodtFr.findViewById(R.id.btnFoodTrash);
        BtnHunt = FoodtFr.findViewById(R.id.btnFoodHunt);
        btnFoodKrekers = FoodtFr.findViewById(R.id.btnFoodKrekers);
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
                if(toast != null)toast.cancel();
                ((Game)getActivity()).ChangeParam("HP","-",0,5);
                ((Game)getActivity()).ChangeParam("SP","+",5,20);
                ((Game)getActivity()).ChangeParam("MP","-",0,5);

                int rand = random.nextInt(10);
                if (rand == 9){
                    ((Game)getActivity()).ChangeParam("HP","+",10,20);

                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFprofit1),Toast.LENGTH_LONG);
                    toast.show();

                }
                ((Game)getActivity()).NextDay();
            }
        });

        BtnHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                if(var >= 1){

                    int rand = random.nextInt(10);
                    if(rand == 9){
                        ((Game)getActivity()).ChangeParam("MP","-",0,20);
                        ((Game)getActivity()).ChangeParam("HP","-",5,20);

                        toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror1_1),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (rand >= 5){
                        ((Game)getActivity()).ChangeParam("MP","-",0,20);

                        toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror1_2),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        ((Game)getActivity()).ChangeParam("HP","-",0,5);
                        ((Game)getActivity()).ChangeParam("SP","+",10,25);
                    }

                }
                else {
                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror1),Toast.LENGTH_LONG);
                    toast.show();
                }

                ((Game)getActivity()).NextDay();

            }
        });
        btnFoodKrekers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 50) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    ((Game)getActivity()).transaction("rub","-",50);
                    ((Game)getActivity()).ChangeParam("SP","+",15,20);
                    ((Game)getActivity()).NextDay();
                }

            }
        });

        btnRobKiosk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                if(var >= 1){
                    int rand = random.nextInt(10);
                    if(rand >= 7){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(getResources().getString(R.string.FFerror2_1title))
                                .setMessage(getResources().getString(R.string.FFerror2_1text))
                                .setIcon(R.drawable.loseico)
                                .setCancelable(false)
                                .setNegativeButton(getResources().getString(R.string.FFerror2_1_1),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                int rand = random.nextInt(2);
                                                if(rand < 1){
                                                    ((Game)getActivity()).ChangeParam("HP","-",50,50);
                                                    ((Game)getActivity()).ChangeParam("MP","-",0,100);
                                                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2_1_1_2),Toast.LENGTH_LONG);
                                                    toast.show();
                                                }
                                                else {
                                                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2_1_1_1),Toast.LENGTH_LONG);
                                                    toast.show();
                                                    dialog.cancel();
                                                }
                                                ((Game)getActivity()).NextDay();
                                            }
                                        })
                                .setNeutralButton(R.string.FFerror2_1_2,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                if (mRewardedVideoAd.isLoaded()) {
                                                    mRewardedVideoAd.show();
                                                    dialog.cancel();
                                                    ((Game)getActivity()).NextDay();
                                                }
                                                else {
                                                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2_1_2_2),Toast.LENGTH_LONG);
                                                    toast.show();
                                                }
                                            }
                                        })
                                .setPositiveButton(R.string.FFerror2_1_3,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ((Game)getActivity()).ChangeParam("MP","-",0,20);
                                                ((Game)getActivity()).transaction("rub","-",1500);
                                                dialog.cancel();
                                                ((Game)getActivity()).NextDay();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else {
                        ((Game)getActivity()).ChangeParam("SP","+",30,30);
                        ((Game)getActivity()).ChangeParam("MP","+",0,5);
                        ((Game)getActivity()).NextDay();
                    }
                }
                else {
                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2),Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });
        btnBuyKiosk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 350) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 2) {
                        ((Game)getActivity()).ChangeParam("SP", "+", 30, 10);
                        ((Game) getActivity()).transaction("rub", "-", 350);
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.FFerror3), Toast.LENGTH_LONG);
                        toast.show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnEatCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 1500) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 3) {
                        ((Game)getActivity()).ChangeParam("SP", "+", 40, 20);
                        ((Game) getActivity()).transaction("rub", "-", 1500);
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.FFerror4), Toast.LENGTH_LONG);
                        toast.show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });

        btnBuyStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 3000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                    if(var >= 2){
                        ((Game)getActivity()).ChangeParam("SP","+",50,20);
                        ((Game)getActivity()).transaction("rub","-",3000);
                    }
                    else {
                        ((Game)getActivity()).transaction("rub","-",1000);
                        toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror5),Toast.LENGTH_LONG);
                        toast.show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });

        btnEatRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 7500) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                    if(var >= 4){
                        ((Game)getActivity()).ChangeParam("SP","+",50,50);
                        ((Game)getActivity()).transaction("rub","-",7500);
                    }
                    else {
                        toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror6),Toast.LENGTH_LONG);
                        toast.show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });
        btnPersChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();


                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                    String checkvar = sPref.getString(LOAD_BUFFCOOK,"");
                    if (checkvar.equals("0")){
                        if(var >= 6){
                            if (sPref.getInt(LOAD_RUB, 0) < 50000) {
                                ((Game) getActivity()).LowMoney("rub");
                            }
                            else {
                                ((Game) getActivity()).transaction("rub", "-", 50000);
                                SharedPreferences.Editor ed = sPref.edit();
                                ed.putString(LOAD_BUFFCOOK, "1");
                                ed.apply();
                            }

                        }
                        else {
                            toast = Toast.makeText(getActivity(),getResources().getString(R.string.FFerror7),Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    else if(checkvar.equals("1")){
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BUFFCOOK,"0");
                        ed.apply();
                    }
                    CheckButton();
                    ((Game)getActivity()).NextDay();
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
    public void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-6876201111676185/9057018825",
                new AdRequest.Builder().build());
    }

}
