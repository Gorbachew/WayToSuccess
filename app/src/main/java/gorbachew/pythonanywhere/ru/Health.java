package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Health extends Fragment {

    Button btnHealthGrass,btnHealthRobPharmacy,btnHealthDistrictHospital,btnHealthBuyPharmacy,btnHealthPrivateHospital,btnHealthPersonalDoctor;
    SharedPreferences sPref;
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_BUFFDOCK = "BuffDock";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View healthFragment = inflater.inflate(R.layout.fragment_health, container, false);


        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);



        btnHealthGrass = healthFragment.findViewById(R.id.btnHealthGrass);
        btnHealthRobPharmacy = healthFragment.findViewById(R.id.btnHealthRobPharmacy);
        btnHealthDistrictHospital = healthFragment.findViewById(R.id.btnHealthDistrictHospital);
        btnHealthBuyPharmacy = healthFragment.findViewById(R.id.btnHealthBuyPharmacy);
        btnHealthPrivateHospital = healthFragment.findViewById(R.id.btnHealthPrivateHospital);
        btnHealthPersonalDoctor = healthFragment.findViewById(R.id.btnHealthPersonalDoctor);
        CheckButton();

        btnHealthGrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Game)getActivity()).RandomStats("HP","+",5,20);
                ((Game)getActivity()).RandomStats("SP","-",0,5);
                ((Game)getActivity()).RandomStats("MP","-",0,5);

                ((Game)getActivity()).NextDay();
            }
        });

        btnHealthRobPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                if(var >= 1){
                    ((Game)getActivity()).RandomStats("HP","+",10,25);
                    ((Game)getActivity()).RandomStats("SP","-",0,10);
                    ((Game)getActivity()).RandomStats("MP","-",0,5);
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.FFerror2),Toast.LENGTH_LONG).show();
                }

                ((Game)getActivity()).NextDay();
            }
        });
        btnHealthBuyPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 1000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                    if(var >= 3){
                        ((Game)getActivity()).RandomStats("HP","+",10,30);
                        ((Game)getActivity()).RandomStats("SP","-",0,5);
                        ((Game)getActivity()).RandomStats("MP","-",0,5);
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
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 3000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                    if(var >= 3){
                        ((Game)getActivity()).RandomStats("HP","+",10,30);
                        ((Game)getActivity()).RandomStats("SP","-",0,5);
                        ((Game)getActivity()).RandomStats("MP","-",0,5);
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
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 1000) {
                    ((Game) getActivity()).LowMoney("usd");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
                    if(var >= 2){
                        ((Game)getActivity()).RandomStats("HP","+",20,50);
                        ((Game)getActivity()).RandomStats("SP","-",0,5);
                        ((Game)getActivity()).RandomStats("MP","-",0,5);
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
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 70000) {
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
