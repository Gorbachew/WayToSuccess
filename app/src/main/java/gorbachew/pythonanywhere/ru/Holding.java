package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;


public class Holding extends Fragment {
    SharedPreferences sPref;
    final String SAVED_HOLDING = "Holding";
    final String LOAD_RENTFLAT = "RentFlat";
    final String MAXSCRAP = "MAXSCRAP";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";

    Button btnCardboardBox,btnCamp, btnRentRoom,btnBuyApartment, btnBuyHome, btnBuyMansion;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        checkBuyed();


        btnCardboardBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 300) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING, "1");
                    ed.putString(MAXSCRAP, "50");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 300);
                    ((Game) getActivity()).NextDay();
                }

            }
        });
        btnCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 3000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING, "2");
                    ed.putString(MAXSCRAP, "100");
                    ed.commit();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 3000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnRentRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = sPref.getString(SAVED_HOLDING, "");
                if (check.equals("3")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(getResources().getString(R.string.HoFRentAnswerTitle))
                            .setMessage(getResources().getString(R.string.HoFRentAnswer))
                            .setIcon(R.drawable.holdingico)
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.yes),
                                    new DialogInterface.OnClickListener() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            SharedPreferences.Editor ed = sPref.edit();
                                            ed.putString(MAXSCRAP, "100");
                                            ed.putString(SAVED_HOLDING, "2");
                                            ed.apply();
                                            checkRent();
                                            btnRentRoom.setBackground(getResources().getDrawable(R.drawable.btngame));
                                            ((Game) getActivity()).NextDay();
                                        }
                                    })
                            .setNegativeButton(getResources().getString(R.string.no),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 25000) {
                        ((Game) getActivity()).LowMoney("rub");
                    } else {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(SAVED_HOLDING, "3");
                        ed.putString(MAXSCRAP, "250");
                        ed.apply();
                        ((Game) getActivity()).transaction("rub", "-", 25000);
                        checkRent();
                        ((Game) getActivity()).NextDay();
                    }
                }
                checkBuyed();
            }
        });
        btnBuyApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 1500000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING, "4");
                    ed.putString(MAXSCRAP, "500");
                    ed.apply();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 1500000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnBuyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 3000000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING, "5");
                    ed.putString(MAXSCRAP, "1000");
                    ed.apply();
                    checkBuyed();
                    ((Game) getActivity()).transaction("rub", "-", 3000000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnBuyMansion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 3000000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_HOLDING, "6");
                    ed.putString(MAXSCRAP, "2000");
                    ed.apply();
                    checkBuyed();
                    ((Game) getActivity()).transaction("usd", "-", 3000000);
                    ((Game) getActivity()).NextDay();
                }
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

    @SuppressLint("NewApi")
    public void checkBuyed(){
        int buyed;
        buyed = Integer.parseInt(sPref.getString(SAVED_HOLDING,""));
        if(buyed >= 1){btnCardboardBox.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnCardboardBox.setEnabled(false);}
        if(buyed >= 2){btnCamp.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnCamp.setEnabled(false);}
        if(buyed >= 3){btnRentRoom.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnRentRoom.setEnabled(true);}
        if(buyed >= 4){btnBuyApartment.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnBuyApartment.setEnabled(false);}
        if(buyed >= 5){btnBuyHome.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnBuyHome.setEnabled(false);}
        if(buyed >= 6){btnBuyMansion.setBackground(getResources().getDrawable(R.drawable.btnbuyed));btnBuyMansion.setEnabled(false);}
        }
    }

