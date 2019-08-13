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

import com.google.android.gms.ads.AdRequest;

import java.util.Random;


public class Respect extends Fragment {
    Button RFsing,RFvideoPresident,RFstream,RFtraining,RFbusinesslunch,RFbecameMem,RFmeetingPresident,RFbecameMP;
    SharedPreferences sPref;
    final String LOAD_PRCAMERA = "PropertyCamera";
    final String LOAD_PRPC = "PropertyPC";
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String LOAD_BUFFMP = "BuffMP";
    private Toast toast0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RespectFragment = inflater.inflate(R.layout.fragment_respect, container, false);

        RFsing = RespectFragment.findViewById(R.id.RFsing);
        RFvideoPresident = RespectFragment.findViewById(R.id.RFvideoPresident);
        RFstream = RespectFragment.findViewById(R.id.RFstream);
        RFtraining = RespectFragment.findViewById(R.id.RFtraining);
        RFbusinesslunch = RespectFragment.findViewById(R.id.RFbusinesslunch);
        RFbecameMem = RespectFragment.findViewById(R.id.RFbecameMem);
        RFmeetingPresident = RespectFragment.findViewById(R.id.RFmeetingPresident);
        RFbecameMP = RespectFragment.findViewById(R.id.RFbecameMP);
        final Random random = new Random();
        CheckButton();

        RFsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = random.nextInt(2);
                if(rand == 0){
                    if(toast0!=null){
                        toast0.cancel();
                    }
                    toast0 = Toast.makeText(getActivity(),getResources().getString(R.string.RFerror0),Toast.LENGTH_LONG);
                    toast0.show();
                }
                else {
                    ((Game)getActivity()).ChangeParam("RESP","+",0,3);
                    ((Game)getActivity()).NextDay();
                }

            }
        });
        RFvideoPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = Integer.parseInt(sPref.getString(LOAD_PRCAMERA,""));
                if(var >= 1){
                    ((Game)getActivity()).ChangeParam("RESP","+",2,5);

                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.RFerror1),Toast.LENGTH_LONG).show();
                }
                ((Game)getActivity()).NextDay();
            }
        });
        RFstream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 300) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(LOAD_PRPC, ""));
                    if (var >= 1) {
                        ((Game)getActivity()).ChangeParam("RESP", "+", 5, 5);
                        ((Game) getActivity()).transaction("rub", "-", 300);

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror2), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        RFtraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 10000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 3) {
                        ((Game)getActivity()).ChangeParam("RESP", "+", 50, 5);
                        ((Game) getActivity()).transaction("rub", "-", 10000);

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror3), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        RFbusinesslunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_USD, 0) < 3000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_CLOTCHES, ""));
                    if (var >= 4) {
                        ((Game)getActivity()).ChangeParam("RESP", "+", 200, 100);
                        ((Game) getActivity()).transaction("usd", "-", 3000);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror4), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        RFbecameMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_RUB, 0) < 100000) {
                    ((Game) getActivity()).LowMoney("rub");
                } else {
                    Random random = new Random();
                    int rand = random.nextInt(10);

                    if (rand == 1) {
                        ((Game)getActivity()).ChangeParam("RESP", "+", 1000, 500);
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFsuccess), Toast.LENGTH_LONG).show();
                        ((Game) getActivity()).transaction("rub", "-", 100000);

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror5), Toast.LENGTH_LONG).show();
                        ((Game) getActivity()).transaction("rub", "-", 100000);
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        RFmeetingPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_USD, 0) < 50000) {
                    ((Game) getActivity()).LowMoney("usd");
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT, ""));
                    if (var >= 3) {
                        ((Game)getActivity()).ChangeParam("RESP", "+", 50000, 20000);
                        ((Game) getActivity()).transaction("usd", "-", 50000);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror6), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        RFbecameMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sPref.getInt(LOAD_USD, 0) < 50000) {
                    ((Game) getActivity()).LowMoney("usd");
                }
                else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT, ""));
                    if (var >= 4) {
                        SharedPreferences.Editor ed = sPref.edit();
                        String checkvar = sPref.getString(LOAD_BUFFMP,"");
                        if(checkvar.equals("0")){
                            ((Game)getActivity()).ChangeParam("RESP", "+", 50000, 20000);
                            ((Game) getActivity()).transaction("usd", "-", 50000);
                            ed.putString(LOAD_BUFFMP,"1");
                        }
                        else if(checkvar.equals("1")){
                            ed.putString(LOAD_BUFFMP,"0");
                        }
                        ed.apply();
                    }
                    else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror7), Toast.LENGTH_LONG).show();
                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        return RespectFragment;
    }
    private void CheckButton(){

        String checkvar = sPref.getString(LOAD_BUFFMP,"");
        if(checkvar.equals("0")){
            RFbecameMP.setText(getResources().getString(R.string.RFbecameMP));
        }
        else if(checkvar.equals("1")){
            RFbecameMP.setText(getResources().getString(R.string.RFbecameMPoff));
        }

    }

}
