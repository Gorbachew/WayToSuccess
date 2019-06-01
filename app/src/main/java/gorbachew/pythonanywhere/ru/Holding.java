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


public class Holding extends Fragment {
    SharedPreferences sPref;
    final String SAVED_HOLDING = "Holding";
    Button btnCardboardBox,btnCamp, btnRentRoom,btnBuyApartment, btnBuyHome, btnBuyMansion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View HoldingFr = inflater.inflate(R.layout.fragment_holding, container, false);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);


        btnCardboardBox = HoldingFr.findViewById(R.id.btnHoldingCardboardBox);
        btnCamp = HoldingFr.findViewById(R.id.btnHoldingCamp);
        btnRentRoom = HoldingFr.findViewById(R.id.btnHoldingRentRoom);
        btnBuyApartment = HoldingFr.findViewById(R.id.btnHoldingbuyApartment);
        btnBuyHome = HoldingFr.findViewById(R.id.btnHoldingbuyHome);
        btnBuyMansion = HoldingFr.findViewById(R.id.btnHoldingbuyMansion);

        btnCardboardBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"1");
                ed.commit();
            }
        });
        btnCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"2");
                ed.commit();
            }
        });
        btnRentRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"3");
                ed.commit();
            }
        });
        btnBuyApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"4");
                ed.commit();
            }
        });
        btnBuyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"5");
                ed.commit();
            }
        });
        btnBuyMansion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"6");
                ed.commit();
            }
        });


        // Inflate the layout for this fragment
        return HoldingFr;
    }


}
