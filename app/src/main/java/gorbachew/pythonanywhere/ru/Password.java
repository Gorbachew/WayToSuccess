package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;




public class Password extends Fragment {



    TextView textAge,textClothes,textTransport,textHolding,textJob,textBusiness,textEducation,textRankJob;
    SharedPreferences sPref;
    final String SAVED_NAME = "Name";
    final String LOAD_USD = "USD";
    final String SAVED_AGE = "Age";
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String LOAD_JOB = "Job";
    final String LOAD_RANKJOB = "RankJob";
    final String SAVED_BUSINESS = "Business";
    final String SAVED_EDUCATION = "Education";
    final String LOAD_DAY = "DAY";
    EditText passEditNameText;
    ImageButton passEditNameBtn;
    String VarClothes,VarTransport, VarHolding, VarJob,VarRankJob, VarBusiness,VarEducation;
    int VarAge,days;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View PassFr = inflater.inflate(R.layout.fragment_password, container, false);

        // Inflate the layout for this fragment

//        textName = PassFr.findViewById(R.id.passTextName);
        textAge = PassFr.findViewById(R.id.passAge);
        textClothes = PassFr.findViewById(R.id.passClothes);
        textTransport = PassFr.findViewById(R.id.passTransport);
        textHolding = PassFr.findViewById(R.id.passHolding);
        textJob = PassFr.findViewById(R.id.passJob);
        textRankJob = PassFr.findViewById(R.id.passRankJob);
        textBusiness = PassFr.findViewById(R.id.passBusiness);
        textEducation = PassFr.findViewById(R.id.passEducation);
        passEditNameBtn = PassFr.findViewById(R.id.passEditNameBtn);
        passEditNameText = PassFr.findViewById(R.id.passEditNameText);


        passEditNameBtn.setVisibility(View.INVISIBLE);

        passEditNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passEditNameText.getText();
                if(!passEditNameText.equals(sPref.getString(SAVED_NAME, ""))){
                    if (sPref.getInt(LOAD_USD, 0) < 1000) {
                        ((Game) getActivity()).LowMoney("usd");
                    }
                    else {
//                        passEditNameText.setVisibility(View.INVISIBLE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(SAVED_NAME, passEditNameText.getText().toString());
                        ed.apply();
                        ((Game) getActivity()).transaction("usd", "-", 1000);
                        ((Game) getActivity()).NextDay();
                        passEditNameBtn.setVisibility(View.INVISIBLE);

                        String NameGamer = sPref.getString(SAVED_NAME, "");
                        passEditNameText.getText().clear();
                        passEditNameText.setHint(NameGamer);

                    }
                }


            }
        });
        passEditNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passEditNameBtn.setVisibility(View.VISIBLE);
            }
        });
        return PassFr;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onStart() {
        super.onStart();
        //Имя
        String NameGamer = sPref.getString(SAVED_NAME, "");
        passEditNameText.setHint(NameGamer);


        //Возраст
        VarAge = Integer.parseInt(sPref.getString(SAVED_AGE,""));
        days = sPref.getInt(LOAD_DAY,0);

        for(int i = days;i >= 365;i -= 365){
            VarAge += 1;
            days -= 365;
        }
        textAge.setText(String.format("%d%s и %d%s", VarAge,getResources().getString(R.string.PFage), days,getResources().getString(R.string.PFdays)));

        //Образование
        VarEducation = sPref.getString(SAVED_EDUCATION,"");

        switch (VarEducation){
            case "0":
                textEducation.setText(getResources().getString(R.string.PFSeducation0));
                break;
            case "1":
                textEducation.setText(getResources().getString(R.string.PFSeducation1));
                break;
            case "2":
                textEducation.setText(getResources().getString(R.string.PFSeducation2));
                break;
            case "3":
                textEducation.setText(getResources().getString(R.string.PFSeducation3));
                break;
            case "4":
                textEducation.setText(getResources().getString(R.string.PFSeducation4));
                break;
            case "5":
                textEducation.setText(getResources().getString(R.string.PFSeducation5));
                break;
        }

        //Одежда
        VarClothes = sPref.getString(SAVED_CLOTCHES,"");

        switch (VarClothes){
            case "0":
                textClothes.setText(getResources().getString(R.string.PFSclothes0));
                break;
            case "1":
                textClothes.setText(getResources().getString(R.string.PFSclothes1));
                break;
            case "2":
                textClothes.setText(getResources().getString(R.string.PFSclothes2));
                break;
            case "3":
                textClothes.setText(getResources().getString(R.string.PFSclothes3));
                break;
            case "4":
                textClothes.setText(getResources().getString(R.string.PFSclothes4));
                break;
        }



        //Транспорт
        VarTransport = sPref.getString(SAVED_TRANSPORT,"");

        switch (VarTransport){
            case "0":
                textTransport.setText(getResources().getString(R.string.PFStransport0));
                break;
            case "1":
                textTransport.setText(getResources().getString(R.string.PFStransport1));
                break;
            case "2":
                textTransport.setText(getResources().getString(R.string.PFStransport2));
                break;
            case "3":
                textTransport.setText(getResources().getString(R.string.PFStransport3));
                break;
            case "4":
                textTransport.setText(getResources().getString(R.string.PFStransport4));
                break;
        }
        //Недвижимость

        VarHolding = sPref.getString(SAVED_HOLDING,"");

        switch (VarHolding){
            case "0":
                textHolding.setText(getResources().getString(R.string.PFSholding0));
                break;
            case "1":
                textHolding.setText(getResources().getString(R.string.PFSholding1));
                break;
            case "2":
                textHolding.setText(getResources().getString(R.string.PFSholding2));
                break;
            case "3":
                textHolding.setText(getResources().getString(R.string.PFSholding3));
                break;
            case "4":
                textHolding.setText(getResources().getString(R.string.PFSholding4));
                break;
            case "5":
                textHolding.setText(getResources().getString(R.string.PFSholding5));
                break;
            case "6":
                textHolding.setText(getResources().getString(R.string.PFSholding6));
                break;
        }
        //Работа
        VarJob = sPref.getString(LOAD_JOB,"");

        switch (VarJob){
            case "0":
                textJob.setText(getResources().getString(R.string.PFSjob0));
                break;
            case "1":
                textJob.setText(getResources().getString(R.string.PFSjob1));
                break;
            case "2":
                textJob.setText(getResources().getString(R.string.PFSjob2));
                break;
            case "3":
                textJob.setText(getResources().getString(R.string.PFSjob3));
                break;
            case "4":
                textJob.setText(getResources().getString(R.string.PFSjob4));
                break;
            case "5":
                textJob.setText(getResources().getString(R.string.PFSjob5));
                break;

        }

        VarRankJob = sPref.getString(LOAD_RANKJOB,"");

        switch (VarRankJob){
            case "0":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob0));
                break;
            case "1":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob1));
                break;
            case "2":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob2));
                break;
            case "3":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob3));
                break;
            case "4":
                textJob.setText(getResources().getString(R.string.PFSrankJob4));
                break;
            case "5":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob5));
                break;

        }
        //Бизнес
        VarBusiness = sPref.getString(SAVED_BUSINESS,"");

        switch (VarBusiness){
            case "0":
                textBusiness.setText(getResources().getString(R.string.PFSbusiness0));
                break;
            case "1":
                textBusiness.setText(getResources().getString(R.string.PFSbusiness1));
                break;
            case "2":
                textBusiness.setText(getResources().getString(R.string.PFSbusiness2));
                break;
        }
        //Жена
        //Ребенок
    }
}
