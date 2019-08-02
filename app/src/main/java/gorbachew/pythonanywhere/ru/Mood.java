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


public class Mood extends Fragment {

    Button btnMoodWatchAnimals,btnMoodDrinkBeer,btnMoodDrinkVodka,btnMoodGoCinema,btnMoodWatchTV,btnMoodPlayPC,btnMoodgoConcert,btnMoodOrderComedian,btnMoodPersonalComedian;
    SharedPreferences sPref;
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_BUFFCOMIC = "BuffComic";

    final String LOAD_PRTV = "PropertyTV";
    final String LOAD_PRPC = "PropertyPC";
    final String LOAD_PRWEAPON = "PropertyWeapon";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View MoodFragment = inflater.inflate(R.layout.fragment_mood, container, false);

        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        btnMoodWatchAnimals = MoodFragment.findViewById(R.id.btnMoodWatchAnimals);
        btnMoodDrinkBeer = MoodFragment.findViewById(R.id.btnMoodDrinkBeer);
        btnMoodDrinkVodka = MoodFragment.findViewById(R.id.btnMoodDrinkVodka);
        btnMoodGoCinema = MoodFragment.findViewById(R.id.btnMoodGoCinema);
        btnMoodWatchTV = MoodFragment.findViewById(R.id.btnMoodWatchTV);
        btnMoodPlayPC = MoodFragment.findViewById(R.id.btnMoodPlayPC);
        btnMoodgoConcert = MoodFragment.findViewById(R.id.btnMoodgoConcert);
        btnMoodOrderComedian = MoodFragment.findViewById(R.id.btnMoodOrderComedian);
        btnMoodPersonalComedian = MoodFragment.findViewById(R.id.btnMoodPersonalComedian);
        final String LOAD_RUB = "RUB";
        final String LOAD_USD = "USD";
        CheckButton();
        final Random random = new Random();
        btnMoodWatchAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Game)getActivity()).RandomStats("SP","-",0,10);
                ((Game)getActivity()).RandomStats("MP","+",5,15);

                int rand = random.nextInt(10);
                if (rand == 9){
                    ((Game)getActivity()).RandomStats("SP","+",10,20);
                    Toast.makeText(getActivity(),getResources().getString(R.string.MFprofit1),Toast.LENGTH_LONG).show();
                }
                ((Game)getActivity()).NextDay();
            }
        });
        btnMoodDrinkBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 50) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {

                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    if (var >= 1) {
                        ((Game) getActivity()).RandomStats("HP", "-", 0, 5);
                        ((Game) getActivity()).RandomStats("SP", "+", 0, 10);
                        ((Game) getActivity()).RandomStats("MP", "+", 20, 10);
                        ((Game) getActivity()).transaction("rub", "-", 50);

                    } else {
                        ((Game) getActivity()).transaction("rub", "-", 50);
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror1), Toast.LENGTH_LONG).show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodDrinkVodka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 200) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {


                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    if (var >= 2) {
                        ((Game) getActivity()).RandomStats("HP", "-", 10, 30);
                        ((Game) getActivity()).RandomStats("SP", "-", 10, 30);
                        ((Game) getActivity()).RandomStats("MP", "+", 40, 40);
                        ((Game) getActivity()).transaction("rub", "-", 200);

                    } else {
                        ((Game) getActivity()).RandomStats("HP", "-", 50, 10);
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror2), Toast.LENGTH_LONG).show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodGoCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 500) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 3) {

                        ((Game) getActivity()).RandomStats("MP", "+", 30, 30);
                        ((Game) getActivity()).transaction("rub", "-", 500);
                        int rand = random.nextInt(10);
                        if (rand == 10){
                            ((Game)getActivity()).RandomStats("SP","+",20,10);
                            Toast.makeText(getActivity(),getResources().getString(R.string.MFprofit2),Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror3), Toast.LENGTH_LONG).show();
                    }


                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodWatchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 100) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(LOAD_PRTV, ""));
                    if (var >= 1) {
                        ((Game) getActivity()).RandomStats("MP", "+", 20, 10);
                        ((Game) getActivity()).transaction("rub", "-", 100);
                        ((Game) getActivity()).NextDay();
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror4), Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
        btnMoodPlayPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 300) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(LOAD_PRPC, ""));
                    if (var >= 1) {
                        ((Game) getActivity()).RandomStats("MP", "+", 30, 20);
                        ((Game) getActivity()).transaction("rub", "-", 300);
                        ((Game) getActivity()).NextDay();
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror5), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        btnMoodgoConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 7000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    ((Game) getActivity()).RandomStats("MP", "+", 0, 100);
                    ((Game) getActivity()).transaction("rub", "-", 7000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodOrderComedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 15000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 4) {
                        ((Game) getActivity()).RandomStats("MP", "+", 100, 10);
                        ((Game) getActivity()).transaction("rub", "-", 15000);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.MFerror6), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodPersonalComedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 50000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    String checkvar = sPref.getString(LOAD_BUFFCOMIC, "");
                    if (checkvar.equals("0")) {
                        if (var >= 6) {

                            ((Game) getActivity()).transaction("rub", "-", 50000);
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_BUFFCOMIC, "1");
                            ed.commit();

                        } else {
                            Toast.makeText(getActivity(), getResources().getString(R.string.MFerror7), Toast.LENGTH_LONG).show();
                        }
                    } else if (checkvar.equals("1")) {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BUFFCOMIC, "0");
                        ed.commit();
                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
                }
            }
        });



        return MoodFragment;
    }
    public void CheckButton(){

        String checkvar = sPref.getString(LOAD_BUFFCOMIC,"");
        if(checkvar.equals("0")){
            btnMoodPersonalComedian.setText(getResources().getString(R.string.MFpersonalComedian));
        }
        else if(checkvar.equals("1")){
            btnMoodPersonalComedian.setText(getResources().getString(R.string.MFfiredComedian));
        }

    }

}
