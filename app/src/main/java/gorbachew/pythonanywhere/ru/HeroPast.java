package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


public class HeroPast extends Fragment {


    String FatherHistText,MotherHistText, FAM,ChildText,YouthText,AftSchoolText,PurposeText,WhyText;
    TextView History;
    SharedPreferences sPref;
    final String SAVED_POLL1 = "Father";
    final String SAVED_POLL2 = "Mother";
    final String SAVED_POLL3 = "childhood";
    final String SAVED_POLL4 = "youth";
    final String SAVED_POLL5 = "AftSchool";
    final String SAVED_POLL7 = "Why";

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Распознаем id обьектов в фрагменте
        View HeroPastFr = inflater.inflate(R.layout.fragment_hero_past, container, false);



        sPref = getActivity().getSharedPreferences("Saved",MODE_PRIVATE);


        String FatherHistId = sPref.getString(SAVED_POLL1,"");
        String MotherHistId = sPref.getString(SAVED_POLL2,"");
        String ChildId = sPref.getString(SAVED_POLL3,"");
        String YouthId = sPref.getString(SAVED_POLL4,"");
        String AftSchoolId = sPref.getString(SAVED_POLL5,"");

        String WhyId = sPref.getString(SAVED_POLL7,"");


        History = (TextView) HeroPastFr.findViewById(R.id.HistFather);

        switch (FatherHistId){
            case "0":
               FatherHistText = getResources().getString(R.string.FatherHistArmy);
               break;
            case "1":
                FatherHistText = getResources().getString(R.string.FatherHistSci);
                break;
            case "2":
                FatherHistText = getResources().getString(R.string.FatherHistEng);
                break;
            case "3":
                FatherHistText = getResources().getString(R.string.FatherHistWork);
                break;
            case "4":
                FatherHistText = getResources().getString(R.string.FatherHistCri);
                break;
            case "5":
                FatherHistText = getResources().getString(R.string.FatherHistBum);
                break;
            case "6":
                FatherHistText = getResources().getString(R.string.FatherHistNo);
                break;
        }
        switch (MotherHistId){
            case "0":
                MotherHistText = getResources().getString(R.string.MotherHistArmy);
                break;
            case "1":
                MotherHistText = getResources().getString(R.string.MotherHistSci);
                break;
            case "2":
                MotherHistText = getResources().getString(R.string.MotherHistEng);
                break;
            case "3":
                MotherHistText = getResources().getString(R.string.MotherHistWork);
                break;
            case "4":
                MotherHistText = getResources().getString(R.string.MotherHistCri);
                break;
            case "5":
                MotherHistText = getResources().getString(R.string.MotherHistBum);
                break;
            case "6":
                MotherHistText = getResources().getString(R.string.MotherHistNo);
                break;
        }
        switch (ChildId) {
            case "0":
                ChildText = getResources().getString(R.string.ChildHistLove);
                break;
            case "1":
                ChildText = getResources().getString(R.string.ChildHistSil);
                break;
            case "2":
                ChildText = getResources().getString(R.string.ChildHistActive);
                break;
            case "3":
                ChildText = getResources().getString(R.string.ChildHistJoker);
                break;
        }
        switch (YouthId) {
            case "0":
                YouthText = getResources().getString(R.string.YouthSelfEduc);
                break;
            case "1":
                YouthText = getResources().getString(R.string.YouthCri);
                break;
            case "2":
                YouthText = getResources().getString(R.string.YouthVol);
                break;
        }
        switch (AftSchoolId) {
            case "0":
                AftSchoolText = getResources().getString(R.string.AftSchoolSold);
                break;
            case "1":
                AftSchoolText = getResources().getString(R.string.AftSchoolStud);
                break;
            case "2":
                AftSchoolText = getResources().getString(R.string.AftSchoolWork);
                break;
            case "3":
                AftSchoolText = getResources().getString(R.string.AftSchoolLazy);
                break;
        }
//        switch (PurposeId) {
//            case "0":
//                PurposeText = getResources().getString(R.string.PurposeWheal);
//                break;
//            case "1":
//                PurposeText = getResources().getString(R.string.PurposePower);
//                break;
//            case "2":
//                PurposeText = getResources().getString(R.string.PurposeNoEnd);
//                break;
//        }
        switch (WhyId) {
            case "0":
                WhyText = getResources().getString(R.string.WhyDead);
                break;
            case "1":
                WhyText = getResources().getString(R.string.WhyJob);
                break;
            case "2":
                WhyText = getResources().getString(R.string.WhyJob);
                break;
        }



        if (MotherHistId.equals("5") && FatherHistId.equals("5")){
            FAM = getResources().getString(R.string.FAMHistBum);
            History.setText(FAM + " " + ChildText + " " + YouthText + " " + AftSchoolText + " " + PurposeText + " " + WhyText);
        }
        else if (MotherHistId.equals("6") && FatherHistId.equals("6")){
            FAM = getResources().getString(R.string.FAMHistNo);
            History.setText(FAM + " " + ChildText + " " + YouthText + " " + AftSchoolText + " " + PurposeText + " " + WhyText);
        }
        else{
            History.setText(FatherHistText + " " + MotherHistText + " " + ChildText + " " + YouthText + " " + AftSchoolText + " " + PurposeText + " " + WhyText);
        }





        return HeroPastFr;
    }


}
