package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Freelance extends Fragment {
    Button btnScrap,btnSellScrap,btnBegForMoney,btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View FreelanceFr = inflater.inflate(R.layout.fragment_freelance, container, false);
        btnScrap = FreelanceFr.findViewById(R.id.btnFreelanceScrap);
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





        return FreelanceFr;
    }




}
