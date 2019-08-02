package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class Freelance extends Fragment {
    Button btnScrap,btnSellScrap,btnFreelanceBegForMoney,btnFreelanceHelpOld,btnFreelanceDistributeFlyers,btnFreelanceRepairFlat,btnFreelanceTaxi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View FreelanceFr = inflater.inflate(R.layout.fragment_freelance, container, false);

        final SharedPreferences sPref;
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        final String SAVED_EDUCATION = "Education";
        final String SAVED_RESPECT = "RESPECT";
        final String SAVED_TRANSPORT = "Transport";
        final String SAVED_HOLDING = "Holding";
        final String SAVED_CLOTCHES = "Clothes";

        btnScrap = FreelanceFr.findViewById(R.id.btnFreelanceScrap);
        btnFreelanceBegForMoney = FreelanceFr.findViewById(R.id.btnFreelanceBegForMoney);
        btnFreelanceHelpOld = FreelanceFr.findViewById(R.id.btnFreelanceHelpOld);
        btnFreelanceDistributeFlyers = FreelanceFr.findViewById(R.id.btnFreelanceDistributeFlyers);
        btnFreelanceRepairFlat = FreelanceFr.findViewById(R.id.btnFreelanceRepairFlat);
        btnFreelanceTaxi = FreelanceFr.findViewById(R.id.btnFreelanceTaxi);
        final Random random = new Random();




        btnScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Game)getActivity()).RandomStats("HP","Minus",10,10);

                ((Game)getActivity()).FindScrup();
                ((Game)getActivity()).NextDay();
            }
        });

        btnSellScrap = FreelanceFr.findViewById(R.id.btnFreelanceSellScrap);
        btnSellScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Game)getActivity()).SellScrap();
                ((Game)getActivity()).NextDay();
                ((Game)getActivity()).RandomStats("HP","Minus",10,10);

            }
        });
        btnFreelanceHelpOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = Integer.parseInt(sPref.getString(SAVED_CLOTCHES,""));
                if(check < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.FrFerror2),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 11 + random.nextInt(200 - 10);
                    ((Game)getActivity()).transaction("rub","+", var);
                }
                ((Game)getActivity()).NextDay();
            }
        });
        btnFreelanceBegForMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = Integer.parseInt(sPref.getString(SAVED_RESPECT,""));
                int check2 = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
                if(check < 100 || check2 < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.FrFerror1),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 11 + random.nextInt(500 - 10);
                    ((Game)getActivity()).transaction("rub","+", var);
                    ((Game)getActivity()).NextDay();
                }


            }
        });

        btnFreelanceDistributeFlyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = Integer.parseInt(sPref.getString(SAVED_RESPECT,""));

                if(check < 500){
                    Toast.makeText(getActivity(),getResources().getString(R.string.FrFerror3),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 101 + random.nextInt(1000 - 100);
                    ((Game)getActivity()).transaction("rub","+", var);
                }

                ((Game)getActivity()).NextDay();
            }
        });
        btnFreelanceRepairFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check = Integer.parseInt(sPref.getString(SAVED_RESPECT,""));

                if(check < 2000){
                    Toast.makeText(getActivity(),getResources().getString(R.string.FrFerror4),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 501 + random.nextInt(5000 - 500);
                    ((Game)getActivity()).transaction("rub","+", var);
                    ((Game)getActivity()).NextDay();
                }


            }
        });
        btnFreelanceTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check = Integer.parseInt(sPref.getString(SAVED_TRANSPORT,""));

                if(check < 2){
                    Toast.makeText(getActivity(),getResources().getString(R.string.FrFerror5),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 501 + random.nextInt(10000 - 500);
                    ((Game)getActivity()).transaction("rub","+", var);
                    ((Game)getActivity()).NextDay();
                }


            }
        });


        return FreelanceFr;
    }




}
