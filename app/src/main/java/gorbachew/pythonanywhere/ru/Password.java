package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class Password extends Fragment {



    TextView textName,textAge,actAge,textClothes,textTransport,textHolding,textJob,textRankJob,textBusiness,textWife,textChild;
    SharedPreferences sPref;
    final String SAVED_AGE = "Age";
    final String SAVED_CLOTCHES = "Clothes";
    final String SAVED_TRANSPORT = "Transport";
    final String SAVED_HOLDING = "Holding";
    final String SAVED_JOB = "Job";
    final String SAVED_RANKJOB = "RankJob";
    final String SAVED_BUSINESS = "Business";

    String VarAge,VarClothes,VarTransport, VarHolding, VarJob, VarRankJob, VarBusiness,VarWife, VarChild;
    int RestAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View PassFr = inflater.inflate(R.layout.fragment_password, container, false);
        // Inflate the layout for this fragment

        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
        textName = PassFr.findViewById(R.id.passTextName);
        textAge = PassFr.findViewById(R.id.passAge);
        textClothes = PassFr.findViewById(R.id.passClothes);
        textTransport = PassFr.findViewById(R.id.passTransport);
        textHolding = PassFr.findViewById(R.id.passHolding);
        textJob = PassFr.findViewById(R.id.passJob);
        textRankJob = PassFr.findViewById(R.id.passRank);
        textBusiness = PassFr.findViewById(R.id.passBusiness);

        return PassFr;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Имя
        Bundle bundle = this.getArguments();
        if (bundle != null){

            String NameGamer = bundle.getString("Name", "");
            textName.setText(NameGamer);
        }

        //Возраст
        VarAge = sPref.getString(SAVED_AGE,"");

        actAge = getActivity().findViewById(R.id.TotalDay);

        for (RestAge = Integer.parseInt(actAge.getText().toString()); RestAge >= 365; RestAge = RestAge - 365){
            VarAge = String.valueOf(Integer.parseInt(VarAge)+1);
        }
        textAge.setText(VarAge + " лет и " + String.valueOf(RestAge) + " День");

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
        VarJob = sPref.getString(SAVED_JOB,"");

        switch (VarJob){
            case "0":
                textJob.setText(getResources().getString(R.string.PFSjob0));
                break;
            case "1":
                textJob.setText(getResources().getString(R.string.PFSjob1));
                break;

        }
        //Должность
        VarRankJob = sPref.getString(SAVED_RANKJOB,"");

        switch (VarRankJob){
            case "0":
                textRankJob.setText(getResources().getString(R.string.PFSrankJob0));
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
