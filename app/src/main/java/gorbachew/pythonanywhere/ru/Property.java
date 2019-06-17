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


public class Property extends Fragment {

    Button btnRubberBoots,btnCamera,btnCheapClothes,btnBicycle,btnNormalClothes,btnCar,btnSuit,btnWeapons,btnTV,btnPC,btnHelicopter,btnYacht;
    SharedPreferences sPref;

    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";



    final String LOAD_PRTV = "PropertyTV";
    final String LOAD_PRPC = "PropertyPC";
    final String LOAD_PRWEAPON = "PropertyWeapon";
    final String LOAD_PRCAMERA = "PropertyCamera";

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

        btnRubberBoots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"1");

                ed.commit();
                ((Game)getActivity()).transaction("rub","-",500);
                ((Game)getActivity()).NextDay();
            }
        });
        btnCheapClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"2");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",2000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_PRCAMERA,"1");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",5000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"1");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",5000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnNormalClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"3");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",15000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"2");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",200000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnSuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"4");
                ed.commit();
                ((Game)getActivity()).transaction("usd","-",3000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnWeapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_PRWEAPON,"1");
                ed.commit();
                ((Game)getActivity()).transaction("usd","-",4000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_PRTV,"1");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",70000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(LOAD_PRPC,"1");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-",120000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnHelicopter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"3");
                ed.commit();
                ((Game)getActivity()).transaction("usd","-",550000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnYacht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"4");
                ed.commit();
                ((Game)getActivity()).transaction("usd","-",1500000);
                ((Game)getActivity()).NextDay();
            }
        });

        return PropertyFG;
    }

}
