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
    final String LOAD_RENTFLAT = "RentFlat";

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

        checkRent();

        btnCardboardBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"1");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-", 300);
                ((Game)getActivity()).NextDay();
            }
        });
        btnCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"2");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-", 3000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnRentRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check = sPref.getString(SAVED_HOLDING,"");
                if (check.equals("3")){
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING,"2");
                    ed.commit();
                }
                else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING,"3");
                    ed.commit();
                    ((Game)getActivity()).transaction("rub","-", 25000);

                }

                checkRent();
                ((Game)getActivity()).NextDay();

            }
        });
        btnBuyApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"4");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-", 1500000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnBuyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"5");
                ed.commit();
                ((Game)getActivity()).transaction("rub","-", 3000000);
                ((Game)getActivity()).NextDay();
            }
        });
        btnBuyMansion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_HOLDING,"6");
                ed.commit();
                ((Game)getActivity()).transaction("usd","-", 3000000);
                ((Game)getActivity()).NextDay();
            }
        });


        // Inflate the layout for this fragment
        return HoldingFr;
    }
    public void checkRent(){
        String check = sPref.getString(SAVED_HOLDING,"");
        if (check.equals("3")){
            btnRentRoom.setText(getResources().getString(R.string.HoFRentRoomOff));
        }
        else {
            btnRentRoom.setText(getResources().getString(R.string.HoFRentRoom));
        }
    }

}
