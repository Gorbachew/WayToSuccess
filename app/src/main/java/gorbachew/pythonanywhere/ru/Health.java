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
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Random;


public class Health extends Fragment {

    Button btnHealthGrass,btnHealthRobPharmacy,btnHealthDistrictHospital,btnHealthBuyPharmacy,btnHealthPrivateHospital,btnHealthPersonalDoctor,btnHealthExpired,btnHealthGomeopat;
    SharedPreferences sPref;
    private RewardedVideoAd mRewardedVideoAd;
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_BUFFDOCK = "BuffDock";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final Random random = new Random();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View healthFragment = inflater.inflate(R.layout.fragment_health, container, false);


        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) getActivity());



        btnHealthGrass = healthFragment.findViewById(R.id.btnHealthGrass);
        btnHealthRobPharmacy = healthFragment.findViewById(R.id.btnHealthRobPharmacy);
        btnHealthDistrictHospital = healthFragment.findViewById(R.id.btnHealthDistrictHospital);
        btnHealthBuyPharmacy = healthFragment.findViewById(R.id.btnHealthBuyPharmacy);
        btnHealthPrivateHospital = healthFragment.findViewById(R.id.btnHealthPrivateHospital);
        btnHealthPersonalDoctor = healthFragment.findViewById(R.id.btnHealthPersonalDoctor);
        btnHealthExpired = healthFragment.findViewById(R.id.btnHealthExpired);
        btnHealthGomeopat = healthFragment.findViewById(R.id.btnHealthGomeopat);

        CheckButton();

        btnHealthGrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Game)getActivity()).RandomStats("HP","+",5,20);
                ((Game)getActivity()).RandomStats("SP","-",0,5);
                ((Game)getActivity()).RandomStats("MP","-",0,5);


                int rand = random.nextInt(10);
                if (rand == 9){
                    ((Game)getActivity()).RandomStats("MP","+",10,20);

                    Toast.makeText(getActivity(),getResources().getString(R.string.HFprofit1),Toast.LENGTH_LONG).show();
                }
                ((Game)getActivity()).NextDay();
            }
        });
        btnHealthExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sPref.getInt(LOAD_RUB, 0) < 50) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    ((Game)getActivity()).transaction("rub","-",50);
                    ((Game)getActivity()).RandomStats("HP","+",15,20);
                    ((Game)getActivity()).NextDay();
                }
            }
        });
        btnHealthRobPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT, ""));
                int rand = random.nextInt(10);
                if(var >= 1){
                    if (rand >= 7) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(getResources().getString(R.string.FFerror2_1title))
                                .setMessage(getResources().getString(R.string.HFerror5text))
                                .setIcon(R.drawable.loseico)
                                .setCancelable(false)
                                .setNegativeButton(getResources().getString(R.string.FFerror2_1_1),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                int rand = random.nextInt(2);
                                                if (rand < 1) {
                                                    ((Game) getActivity()).RandomStats("HP", "-", 50, 50);
                                                    ((Game)getActivity()).RandomStats("MP","-",0,100);
                                                    Toast.makeText(getActivity(), getResources().getString(R.string.HFerror5_1_2), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(getActivity(), getResources().getString(R.string.HFerror5_1_1), Toast.LENGTH_LONG).show();
                                                    dialog.cancel();
                                                }
                                                ((Game) getActivity()).NextDay();
                                            }
                                        })
                                .setNeutralButton(R.string.FFerror2_1_2,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                if (mRewardedVideoAd.isLoaded()) {
                                                    mRewardedVideoAd.show();
                                                    dialog.cancel();
                                                    ((Game) getActivity()).NextDay();
                                                } else {
                                                    Toast.makeText(getActivity(), getResources().getString(R.string.FFerror2_1_2_2), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        })
                                .setPositiveButton(R.string.FFerror2_1_3,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ((Game) getActivity()).RandomStats("MP", "-", 0, 20);
                                                ((Game) getActivity()).transaction("rub", "-", 1500);
                                                dialog.cancel();
                                                ((Game) getActivity()).NextDay();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        ((Game)getActivity()).RandomStats("HP","+",30,30);
                        ((Game)getActivity()).RandomStats("MP","+",0,10);
                        ((Game) getActivity()).NextDay();
                    }
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2),Toast.LENGTH_LONG).show();
                }


            }
        });

        btnHealthGomeopat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 500) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 2) {
                        ((Game) getActivity()).RandomStats("HP", "+", 30, 10);
                        ((Game) getActivity()).transaction("rub", "-", 500);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.HFerror5), Toast.LENGTH_LONG).show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnHealthBuyPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 1000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                    if(var >= 3){
                        ((Game)getActivity()).RandomStats("HP","+",30,30);
                        ((Game)getActivity()).transaction("rub","-",1000);
                    }
                    else {
                        Toast.makeText(getActivity(),getResources().getString(R.string.HFerror1),Toast.LENGTH_LONG).show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });
        btnHealthDistrictHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 300) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                    if(var >= 3){
                        ((Game)getActivity()).RandomStats("HP","+",15,40);
                        ((Game)getActivity()).RandomStats("MP","-",0,10);
                        ((Game)getActivity()).transaction("rub","-",300);
                    }
                    else {
                        Toast.makeText(getActivity(),getResources().getString(R.string.HFerror2),Toast.LENGTH_LONG).show();
                    }

                    ((Game)getActivity()).NextDay();
                }

            }
        });


        btnHealthPrivateHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_USD, 0) < 1000) {
                    ((Game) getActivity()).LowMoney("usd");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                    if(var >= 2){
                        ((Game)getActivity()).RandomStats("HP","+",50,50);
                        ((Game)getActivity()).RandomStats("SP","+",0,30);
                        ((Game)getActivity()).RandomStats("MP","+",0,50);
                        ((Game)getActivity()).transaction("usd","-",1000);
                    }
                    else {
                        Toast.makeText(getActivity(),getResources().getString(R.string.HFerror3),Toast.LENGTH_LONG).show();
                    }
                    ((Game)getActivity()).NextDay();
                }

            }
        });

        btnHealthPersonalDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 70000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                    String checkvar = sPref.getString(LOAD_BUFFDOCK,"");
                    if (checkvar.equals("0")){
                        if(var >= 6){

                            ((Game)getActivity()).transaction("rub","-",70000);
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_BUFFDOCK,"1");
                            ed.commit();

                        }
                        else {
                            Toast.makeText(getActivity(),getResources().getString(R.string.HFerror4),Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(checkvar.equals("1")){
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BUFFDOCK,"0");
                        ed.commit();
                    }
                    CheckButton();
                    ((Game)getActivity()).NextDay();
                }

            }
        });
        // Inflate the layout for this fragment
        return healthFragment;
    }

    public void CheckButton(){

        String checkvar = sPref.getString(LOAD_BUFFDOCK,"");
        if(checkvar.equals("0")){
            btnHealthPersonalDoctor.setText(getResources().getString(R.string.HFpersonalDoctor));
        }
        else if(checkvar.equals("1")){
            btnHealthPersonalDoctor.setText(getResources().getString(R.string.HFfiredDoctor));
        }

    }



}
