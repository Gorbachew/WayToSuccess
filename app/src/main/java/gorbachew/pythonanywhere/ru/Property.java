package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Property extends Fragment {

    Button btnRubberBoots,btnCamera,btnCheapClothes,btnBicycle,btnNormalClothes,btnCar,btnSuit,btnWeapons,btnTV,btnPC,btnHelicopter,btnYacht;
    SharedPreferences sPref;

    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";



    final String LOAD_PRTV = "PropertyTV";
    final String LOAD_PRPC = "PropertyPC";
    final String LOAD_PRWEAPON = "PropertyWeapon";
    final String LOAD_PRCAMERA = "PropertyCamera";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View PropertyFG = inflater.inflate(R.layout.fragment_property, container, false);
        btnRubberBoots = PropertyFG.findViewById(R.id.btnPropertyRubberBoots);
        btnCheapClothes = PropertyFG.findViewById(R.id.btnPropertyCheapClothes);
        btnCamera = PropertyFG.findViewById(R.id.btnPropertyCamera);
        btnBicycle = PropertyFG.findViewById(R.id.btnPropertyBicycle);
        btnNormalClothes = PropertyFG.findViewById(R.id.btnPropertyNormalClothes);
        btnCar = PropertyFG.findViewById(R.id.btnPropertyCar);
        btnSuit = PropertyFG.findViewById(R.id.btnPropertySuit);
        btnWeapons = PropertyFG.findViewById(R.id.btnPropertyWeapons);
        btnTV = PropertyFG.findViewById(R.id.btnPropertyTV);
        btnPC = PropertyFG.findViewById(R.id.btnPropertyPC);
        btnHelicopter = PropertyFG.findViewById(R.id.btnPropertyHelicopter);
        btnYacht = PropertyFG.findViewById(R.id.btnPropertyYacht);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        checkBuyed();

        btnRubberBoots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 500) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_CLOTCHES, "1");

                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 500);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnCheapClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 2000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_CLOTCHES, "2");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 2000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 5000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_PRCAMERA, "1");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 5000);
                    ((Game) getActivity()).NextDay();
                }
            }

        });
        btnBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 5000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_TRANSPORT, "1");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 5000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnNormalClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 15000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_CLOTCHES, "3");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 15000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 200000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_TRANSPORT, "2");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 200000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnSuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 3000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_CLOTCHES, "4");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("usd", "-", 3000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnWeapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 4000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_PRWEAPON, "1");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("usd", "-", 4000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 70000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_PRTV, "1");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 70000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 120000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_PRPC, "1");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 120000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnHelicopter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 550000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_TRANSPORT, "3");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("usd", "-", 550000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnYacht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 1500000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_TRANSPORT, "4");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("usd", "-", 1500000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });

        return PropertyFG;
    }
    @SuppressLint("NewApi")
    public void checkBuyed(){

        int buyed1 = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
        int buyed2 = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));
        int buyed3 = Integer.parseInt(sPref.getString(LOAD_PRCAMERA,""));
        int buyed4 = Integer.parseInt(sPref.getString(LOAD_PRPC,""));
        int buyed5 = Integer.parseInt(sPref.getString(LOAD_PRTV,""));
        int buyed6 = Integer.parseInt(sPref.getString(LOAD_PRWEAPON,""));
        if(buyed1 >= 1){btnRubberBoots.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed1 >= 2){btnCheapClothes.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed1 >= 3){btnNormalClothes.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed1 >= 4){btnSuit.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed2 >= 1){btnBicycle.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed2 >= 2){btnCar.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed2 >= 3){btnHelicopter.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed2 >= 4){btnYacht.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed3 >= 1){btnCamera.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed4 >= 1){btnPC.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed5 >= 1){btnTV.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
        if(buyed6 >= 1){btnWeapons.setBackground(getResources().getDrawable(R.drawable.btnbuyed));}
    }
}

