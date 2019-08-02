package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Casino extends Fragment {

    RadioGroup CasinoRadioGroup1,CasinoRadioGroup2;
    String Check1,Check2;
    TextView CasinoResult;
    Button btnCasinoPlay,btnCasinoClear;
    Spinner CasinoRate;
    SharedPreferences sPref;
    int quantityRates;
    final Random random = new Random();
    final String LOAD_USD = "USD";
    final String SAVED_CLOTCHES = "Clothes";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View CasinoFragment = inflater.inflate(R.layout.fragment_casino, container, false);

        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
        CasinoResult = CasinoFragment.findViewById(R.id.CasinoResult);
        btnCasinoPlay = CasinoFragment.findViewById(R.id.btnCasinoPlay);
        btnCasinoClear = CasinoFragment.findViewById(R.id.btnCasinoClear);
        CasinoRate = CasinoFragment.findViewById(R.id.CasinoRate);
        CasinoRadioGroup1 = CasinoFragment.findViewById(R.id.CasinoRadioGroup1);
        CasinoRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){

                    case R.id.rbSpaceRED:Check1 = "RED";break;
                    case R.id.rbSpaceBLACK:Check1 = "BLACK";break;

                }
            }
        });
        CasinoRadioGroup2 = CasinoFragment.findViewById(R.id.CasinoRadioGroup2);
        CasinoRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbSpace1and12:Check2 = "1and12";break;
                    case R.id.rbSpace2and12:Check2 = "2and12";break;
                    case R.id.rbSpace3and12:Check2 = "3and12";break;
                }
            }
        });

        btnCasinoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.parseInt(sPref.getString(SAVED_CLOTCHES, "")) >= 4 ){
                    if(Integer.parseInt(CasinoRate.getSelectedItem().toString()) > Integer.parseInt(sPref.getString(LOAD_USD, ""))){
                        ((Game)getActivity()).LowMoney("usd");
                    }
                    else {
                       checkPlay();
                    }
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.CFerror),Toast.LENGTH_SHORT).show();
                }

                ((Game)getActivity()).loadGame();
                //Каждые 10 ставок проходит 6 часов игрового времени
                quantityRates++;
                if(quantityRates == 10){
                    ((Game)getActivity()).NextDay();
                }
            }
        });
        btnCasinoClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CasinoRadioGroup1.clearCheck();
                CasinoRadioGroup2.clearCheck();
                //Очистка результатов
                Check1 = null;Check2 = null;
            }
        });
        return CasinoFragment;
    }
    private void checkPlay(){
        int AllRate = 0;
        int Result = random.nextInt(37);

        //Проверка на цвет
        String color;
        if (Result == 0){
            CasinoResult.setBackgroundColor(getResources().getColor(R.color.green));
            color = "green";
        }
        else if (Result % 2 == 0){
            CasinoResult.setBackgroundColor(getResources().getColor(R.color.red));
            color = "red";
        }
        else {
            CasinoResult.setBackgroundColor(getResources().getColor(R.color.black));
            color = "black";
        }
        //Выдача результата на табло
        CasinoResult.setText(String.valueOf(Result));

        //Получение ставки
        int Rate = Integer.parseInt(CasinoRate.getSelectedItem().toString());

        boolean checkHalf = false, checkDozen = false, checkNumber = false,checkNumber2 = false,checkNumber3 = false, checkNumber4 = false;

        if(Check1 != null){
            switch (Check1){
                case "RED":if(color == "red" && Result != 0)checkHalf = true;else checkHalf = false;break;
                case "BLACK":if(color == "black" && Result != 0)checkHalf = true;else checkHalf = false;break;
            }
            if(checkHalf == true)AllRate = AllRate + Rate;
            else AllRate -= Rate;
        }
        if(Check2 != null){
            switch (Check2){
                case "1and12":if (Result <= 12 && Result != 0)checkDozen = true;else checkDozen = false;break;
                case "2and12":if (Result > 12 && Result <= 24 && Result != 0)checkDozen = true;else checkDozen = false;break;
                case "3and12":if (Result > 24 && Result != 0)checkDozen = true;else checkDozen = false;break;
            }
            if(checkDozen == true)AllRate = AllRate + (Rate * 2);
            else AllRate -= Rate;
        }

        if(AllRate == 0){
            Toast.makeText(getActivity(),getResources().getString(R.string.CFyouZero),Toast.LENGTH_SHORT).show();
        }
        else if(AllRate > 0){
            Toast.makeText(getActivity(),getResources().getString(R.string.CFyouWin) + " " + AllRate + " " + getResources().getString(R.string.CFdollar),Toast.LENGTH_SHORT).show();
            ((Game)getActivity()).transaction("usd","+",AllRate);
        }
        else {
            int res = AllRate * -1;
            Toast.makeText(getActivity(),getResources().getString(R.string.CFyouLose) + " " + AllRate + " " + getResources().getString(R.string.CFdollar),Toast.LENGTH_SHORT).show();
            ((Game)getActivity()).transaction("usd","-",res);
        }

    }
}


