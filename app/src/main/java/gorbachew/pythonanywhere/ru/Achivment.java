package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Achivment extends Fragment {

    FrameLayout Ach1,Ach2,Ach3,Ach4,Ach5,Ach6,Ach7,Ach8,Ach9,Ach10,Ach11,Ach12,Ach13,Ach14,Ach15,Ach16,Ach17,Ach18;
    SharedPreferences sPref;
    final String LOAD_ACHBM = "AchivmentBMetal";
    final String LOAD_ACHBC = "AchivmentBCafe";
    final String LOAD_ACHM = "AchivmentMetal";
    final String LOAD_ACHMo = "AchivmentMoney";
    final String LOAD_ACHR = "AchivmentRespect";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View AchFragment = inflater.inflate(R.layout.fragment_achivment, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
        Ach1 = AchFragment.findViewById(R.id.Ach1);
        Ach2 = AchFragment.findViewById(R.id.Ach2);
        Ach3 = AchFragment.findViewById(R.id.Ach3);
        Ach4 = AchFragment.findViewById(R.id.Ach4);
        Ach5 = AchFragment.findViewById(R.id.Ach5);
        Ach6 = AchFragment.findViewById(R.id.Ach6);
        Ach7 = AchFragment.findViewById(R.id.Ach7);
        Ach8 = AchFragment.findViewById(R.id.Ach8);
        Ach9 = AchFragment.findViewById(R.id.Ach9);
        Ach10 = AchFragment.findViewById(R.id.Ach10);
        Ach11 = AchFragment.findViewById(R.id.Ach11);
        Ach12 = AchFragment.findViewById(R.id.Ach12);
        Ach13 = AchFragment.findViewById(R.id.Ach13);
        Ach14 = AchFragment.findViewById(R.id.Ach14);
        Ach15 = AchFragment.findViewById(R.id.Ach15);
        Ach16 = AchFragment.findViewById(R.id.Ach16);
        Ach17 = AchFragment.findViewById(R.id.Ach17);
        Ach18 = AchFragment.findViewById(R.id.Ach18);

        int bMetal = Integer.parseInt(sPref.getString(LOAD_ACHBM,""));
        int bCafe = Integer.parseInt(sPref.getString(LOAD_ACHBC,""));
        int metal = Integer.parseInt(sPref.getString(LOAD_ACHM,""));

        int money = Integer.parseInt(sPref.getString(LOAD_ACHMo,""));
        int respect = Integer.parseInt(sPref.getString(LOAD_ACHR,""));

        if (metal >= 1)Ach1.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (metal >= 2)Ach2.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (metal >= 3)Ach3.setBackground(getResources().getDrawable(R.xml.achborderon));

        if (bMetal >= 1)Ach4.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (bMetal >= 2)Ach5.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (bMetal >= 3)Ach6.setBackground(getResources().getDrawable(R.xml.achborderon));

        if (bCafe >= 1)Ach7.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (bCafe >= 2)Ach8.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (bCafe >= 3)Ach9.setBackground(getResources().getDrawable(R.xml.achborderon));

        if (money >= 1)Ach10.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (money >= 2)Ach11.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (money >= 3)Ach12.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (money >= 4)Ach13.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (money >= 5)Ach14.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (money >= 6)Ach15.setBackground(getResources().getDrawable(R.xml.achborderon));

        if (respect >= 1)Ach16.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (respect >= 2)Ach17.setBackground(getResources().getDrawable(R.xml.achborderon));
        if (respect >= 3)Ach18.setBackground(getResources().getDrawable(R.xml.achborderon));

        return AchFragment;
    }


}
