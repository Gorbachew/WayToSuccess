package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class Mood extends Fragment {
    Toast toast;
    Button btnMoodWatchAnimals,btnMoodDrinkBeer,btnMoodDrinkVodka,btnMoodGoCinema,btnMoodWatchTV,btnMoodPlayPC,btnMoodgoConcert,btnMoodOrderComedian,btnMoodPersonalComedian;
    SharedPreferences sPref;
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_BUFFCOMIC = "BuffComic";
    final String LOAD_ALCO = "Alco";
    final String LOAD_PRTV = "PropertyTV";
    final String LOAD_PRPC = "PropertyPC";
    final String LOAD_PRWEAPON = "PropertyWeapon";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View MoodFragment = inflater.inflate(R.layout.fragment_mood, container, false);
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
                if(toast != null)toast.cancel();
                ((Game)getActivity()).ChangeParam("SP","-",0,10);
                ((Game)getActivity()).ChangeParam("MP","+",5,15);

                int rand = random.nextInt(10);
                if (rand == 9){
                    ((Game)getActivity()).ChangeParam("SP","+",10,20);
                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.MFprofit1),Toast.LENGTH_LONG);
                    toast.show();
                }
                ((Game)getActivity()).NextDay();
            }
        });
        btnMoodDrinkBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 50) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {

                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    if (var >= 1) {
                        ((Game)getActivity()).ChangeParam("HP", "-", 0, 5);
                        ((Game)getActivity()).ChangeParam("SP", "+", 0, 5);
                        ((Game)getActivity()).ChangeParam("MP", "+", 20, 10);
                        ((Game) getActivity()).transaction("rub", "-", 50);
                        int alco = sPref.getInt(LOAD_ALCO,0);
                        alco += 2;
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putInt(LOAD_ALCO,alco);
                        ed.apply();

                    } else {
                        ((Game) getActivity()).transaction("rub", "-", 50);
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror1), Toast.LENGTH_LONG);toast.show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodDrinkVodka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 200) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {


                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    if (var >= 2) {
                        ((Game)getActivity()).ChangeParam("HP", "-", 10, 30);
                        ((Game)getActivity()).ChangeParam("SP", "-", 10, 30);
                        ((Game)getActivity()).ChangeParam("MP", "+", 40, 40);
                        ((Game) getActivity()).transaction("rub", "-", 200);
                        int alco = sPref.getInt(LOAD_ALCO,0);
                        alco += 4;
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putInt(LOAD_ALCO,alco);
                        ed.apply();

                    } else {
                        ((Game)getActivity()).ChangeParam("HP", "-", 50, 10);
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror2), Toast.LENGTH_LONG);toast.show();
                    }

                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodGoCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 500) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 3) {

                        ((Game)getActivity()).ChangeParam("MP", "+", 30, 30);
                        ((Game) getActivity()).transaction("rub", "-", 500);
                        int rand = random.nextInt(10);
                        if (rand == 10){
                            ((Game)getActivity()).ChangeParam("SP","+",20,10);
                            toast = Toast.makeText(getActivity(),getResources().getString(R.string.MFprofit2),Toast.LENGTH_SHORT);toast.show();
                        }
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror3), Toast.LENGTH_LONG);toast.show();
                    }


                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodWatchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 100) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(LOAD_PRTV, ""));
                    if (var >= 1) {
                        ((Game)getActivity()).ChangeParam("MP", "+", 20, 10);
                        ((Game) getActivity()).transaction("rub", "-", 100);
                        ((Game) getActivity()).NextDay();
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror4), Toast.LENGTH_LONG);toast.show();

                    }

                }
            }
        });
        btnMoodPlayPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 300) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(LOAD_PRPC, ""));
                    if (var >= 1) {
                        ((Game)getActivity()).ChangeParam("MP", "+", 30, 20);
                        ((Game) getActivity()).transaction("rub", "-", 300);
                        ((Game) getActivity()).NextDay();
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror5), Toast.LENGTH_LONG);toast.show();
                    }

                }
            }
        });
        btnMoodgoConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 7000) {
                    ((Game) getActivity()).LowMoney("rub");
                }
                else {
                    ((Game)getActivity()).ChangeParam("MP", "+", 0, 100);
                    ((Game) getActivity()).transaction("rub", "-", 7000);
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodOrderComedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();
                if (sPref.getInt(LOAD_RUB, 0) < 15000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 4) {
                        ((Game)getActivity()).ChangeParam("MP", "+", 100, 10);
                        ((Game) getActivity()).transaction("rub", "-", 15000);
                    } else {
                        toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror6), Toast.LENGTH_LONG);toast.show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnMoodPersonalComedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)toast.cancel();

                    int var = Integer.parseInt(sPref.getString(SAVED_HOLDING, ""));
                    String checkvar = sPref.getString(LOAD_BUFFCOMIC, "");
                    if (checkvar.equals("0")) {
                        if (var >= 6) {
                            if (sPref.getInt(LOAD_RUB, 0) < 50000) {
                                ((Game) getActivity()).LowMoney("rub");
                            } else {

                                ((Game) getActivity()).transaction("rub", "-", 50000);
                                SharedPreferences.Editor ed = sPref.edit();
                                ed.putString(LOAD_BUFFCOMIC, "1");
                                ed.apply();
                            }
                        } else {
                            toast = Toast.makeText(getActivity(), getResources().getString(R.string.MFerror7), Toast.LENGTH_LONG);

                        }
                    } else if (checkvar.equals("1")) {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_BUFFCOMIC, "0");
                        ed.apply();
                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
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
