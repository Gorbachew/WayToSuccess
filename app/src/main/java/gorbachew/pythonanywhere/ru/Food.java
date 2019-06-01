package gorbachew.pythonanywhere.ru;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class Food extends Fragment {

    Button BtnTrash,BtnHunt;
    TextView RUB;
    ProgressBar PBHP,PBMP,PBSP;
    final Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FoodtFr = inflater.inflate(R.layout.fragment_food, container, false);

        BtnTrash = FoodtFr.findViewById(R.id.btnFoodTrash);
        BtnHunt = FoodtFr.findViewById(R.id.btnFoodHunt);
        RUB = getActivity().findViewById(R.id.TotalRUB);




        BtnTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AddRub = String.valueOf(Integer.parseInt((String) RUB.getText()) + 1);
                  RUB.setText(AddRub);

                ((Game)getActivity()).RandomStats("HP","Minus",10,10);
                ((Game)getActivity()).RandomStats("SP","Minus",10,10);
                ((Game)getActivity()).RandomStats("MP","Minus",10,10);

                ((Game)getActivity()).NextDay();

            }
        });
        BtnHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Game)getActivity()).RandomStats("HP","Plus",10,10);
                ((Game)getActivity()).RandomStats("SP","Plus",10,10);
                ((Game)getActivity()).RandomStats("MP","Plus",10,10);

                ((Game)getActivity()).NextDay();
            }
        });





        // Inflate the layout for this fragment
        return FoodtFr;
    }



}
