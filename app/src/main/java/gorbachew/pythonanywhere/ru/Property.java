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

    Button btnRubberBoots,btnCheapClothes,btnBicycle,btnNormalClothes,btnCar,btnSuit,btnWeapons,btnTV,btnPC,btnHelicopter,btnYacht;
    SharedPreferences sPref;
    final String SAVED_AGE = "Age";
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";

    final String SAVED_JOB = "Job";
    final String SAVED_RANKJOB = "RankJob";
    final String SAVED_BUSINESS = "Business";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View PropertyFG = inflater.inflate(R.layout.fragment_property, container, false);
        btnRubberBoots = PropertyFG.findViewById(R.id.btnPropertyRubberBoots);
        btnCheapClothes = PropertyFG.findViewById(R.id.btnPropertyCheapClothes);
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
            }
        });
        btnCheapClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"2");
                ed.commit();
            }
        });
        btnBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"1");
                ed.commit();
            }
        });
        btnNormalClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"3");
                ed.commit();
            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"2");
                ed.commit();
            }
        });
        btnSuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_CLOTCHES,"4");
                ed.commit();
            }
        });
        btnWeapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnHelicopter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"3");
                ed.commit();
            }
        });
        btnYacht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_TRANSPORT,"4");
                ed.commit();
            }
        });

        return PropertyFG;
    }

}
