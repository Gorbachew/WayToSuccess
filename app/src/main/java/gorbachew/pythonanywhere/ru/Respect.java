package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RespectFragment = inflater.inflate(R.layout.fragment_respect, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        RFsing = RespectFragment.findViewById(R.id.RFsing);
        RFvideoPresident = RespectFragment.findViewById(R.id.RFvideoPresident);
        RFstream = RespectFragment.findViewById(R.id.RFstream);
        RFtraining = RespectFragment.findViewById(R.id.RFtraining);
        RFbusinesslunch = RespectFragment.findViewById(R.id.RFbusinesslunch);
        RFbecameMem = RespectFragment.findViewById(R.id.RFbecameMem);
        RFmeetingPresident = RespectFragment.findViewById(R.id.RFmeetingPresident);
        RFbecameMP = RespectFragment.findViewById(R.id.RFbecameMP);

        RFsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Game)getActivity()).RandomStats("RESP","+",0,2);
                ((Game)getActivity()).NextDay();
            }
        });
        RFvideoPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = Integer.parseInt(sPref.getString(LOAD_PRCAMERA,""));
                if(var >= 1){
                    ((Game)getActivity()).RandomStats("RESP","+",2,5);

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
                        ((Game) getActivity()).RandomStats("RESP", "+", 5, 5);
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
                        ((Game) getActivity()).RandomStats("RESP", "+", 50, 5);
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
                        ((Game) getActivity()).RandomStats("RESP", "+", 200, 100);
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
                        ((Game) getActivity()).RandomStats("RESP", "+", 1000, 500);
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
                        ((Game) getActivity()).RandomStats("RESP", "+", 50000, 20000);
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
                } else {
                    int var = Integer.parseInt(sPref.getString(SAVED_TRANSPORT, ""));
                    if (var >= 4) {
                        ((Game) getActivity()).RandomStats("RESP", "+", 50000, 20000);
                        ((Game) getActivity()).transaction("usd", "-", 50000);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.RFerror7), Toast.LENGTH_LONG).show();
                    }
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        return RespectFragment;
    }

}
