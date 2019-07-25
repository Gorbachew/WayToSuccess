package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
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

    RadioGroup CasinoRadioGroup1,CasinoRadioGroup2,CasinoRadioGroup3,CasinoRadioGroup4,CasinoRadioGroup5,CasinoRadioGroup6;
    String Check1,Check2,Check3,Check4,Check5,Check6;
    TextView CasinoResult;
    Button btnCasinoPlay,btnCasinoClear;
    Spinner CasinoRate;
    SharedPreferences sPref;
    int quantityRates;
    final Random random = new Random();
    final String LOAD_USD = "USD";
    final String SAVED_CLOTCHES = "Clothes";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                    case R.id.rbSpace1and18:Check1 = "1and18";break;
                    case R.id.rbSpaceEVEN:Check1 = "EVEN";break;
                    case R.id.rbSpaceRED:Check1 = "RED";break;
                    case R.id.rbSpaceBLACK:Check1 = "BLACK";break;
                    case R.id.rbSpaceODD:Check1 = "ODD";break;
                    case R.id.rbSpace3and36:Check1 = "3and36";break;
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
        CasinoRadioGroup3 = CasinoFragment.findViewById(R.id.CasinoRadioGroup3);
        CasinoRadioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbSpace1:Check3 = "1";break;
                    case R.id.rbSpace4:Check3 = "4";break;
                    case R.id.rbSpace7:Check3 = "7";break;
                    case R.id.rbSpace10:Check3 = "10";break;
                    case R.id.rbSpace13:Check3 = "13";break;
                    case R.id.rbSpace16:Check3 = "16";break;
                    case R.id.rbSpace19:Check3 = "19";break;
                    case R.id.rbSpace22:Check3 = "22";break;
                    case R.id.rbSpace25:Check3 = "25";break;
                    case R.id.rbSpace28:Check3 = "28";break;
                    case R.id.rbSpace31:Check3 = "31";break;
                    case R.id.rbSpace34:Check3 = "34";break;
                }
            }
        });
        CasinoRadioGroup4 = CasinoFragment.findViewById(R.id.CasinoRadioGroup4);
        CasinoRadioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbSpace0:Check4 = "0";break;
                    case R.id.rbSpace2:Check4 = "2";break;
                    case R.id.rbSpace5:Check4 = "5";break;
                    case R.id.rbSpace8:Check4 = "8";break;
                    case R.id.rbSpace11:Check4 = "11";break;
                    case R.id.rbSpace14:Check4 = "14";break;
                    case R.id.rbSpace17:Check4 = "17";break;
                    case R.id.rbSpace20:Check4 = "20";break;
                    case R.id.rbSpace23:Check4 = "23";break;
                    case R.id.rbSpace26:Check4 = "26";break;
                    case R.id.rbSpace29:Check4 = "29"; break;
                    case R.id.rbSpace32:Check4 = "32";break;
                    case R.id.rbSpace35:Check4 = "35";break;

                }
            }
        });
        CasinoRadioGroup5 = CasinoFragment.findViewById(R.id.CasinoRadioGroup5);
        CasinoRadioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbSpace3:Check5 = "3";break;
                    case R.id.rbSpace6:Check5 = "6";break;
                    case R.id.rbSpace9:Check5 = "7";break;
                    case R.id.rbSpace12:Check5 = "12";break;
                    case R.id.rbSpace15:Check5 = "15";break;
                    case R.id.rbSpace18:Check5 = "18";break;
                    case R.id.rbSpace21:Check5 = "21";break;
                    case R.id.rbSpace24:Check5 = "24";break;
                    case R.id.rbSpace27:Check5 = "27";break;
                    case R.id.rbSpace30:Check5 = "30";break;
                    case R.id.rbSpace33:Check5 = "33";break;
                    case R.id.rbSpace36:Check5 = "36";break;

                }
            }
        });
        CasinoRadioGroup6 = CasinoFragment.findViewById(R.id.CasinoRadioGroup6);
        CasinoRadioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbSpace1and1:Check6 = "1and1";break;
                    case R.id.rbSpace2and1:Check6 = "2and1";break;
                    case R.id.rbSpace3and1:Check6 = "3and1";break;
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
                CasinoRadioGroup3.clearCheck();
                CasinoRadioGroup4.clearCheck();
                CasinoRadioGroup5.clearCheck();
                CasinoRadioGroup6.clearCheck();

                //Очистка результатов
                Check1 = null;Check2 = null;Check3 = null;Check4 = null;Check5 = null;Check6 = null;
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
                case "1and18":if(Result <= 18 && Result != 0)checkHalf = true;else checkHalf = false;break;
                case "EVEN":if(color == "red" && Result != 0)checkHalf = true;else checkHalf = false;break;
                case "RED":if(color == "red" && Result != 0)checkHalf = true;else checkHalf = false;break;
                case "BLACK":if(color == "black" && Result != 0)checkHalf = true;else checkHalf = false;break;
                case "ODD":if(color == "black" && Result != 0)checkHalf = true;else checkHalf = false;break;
                case  "3and36":if(Result > 18 && Result != 0)checkHalf = true;else checkHalf = false;break;
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
        if(Check3 != null){
            switch (Check3){
                case "1":if(Result == 1)checkNumber = true;else checkNumber = false;break;
                case "4":if(Result == 4)checkNumber = true;else checkNumber = false;break;
                case "7":if(Result == 7)checkNumber = true;else checkNumber = false;break;
                case "10":if (Result == 10)checkNumber = true;else checkNumber = false;break;
                case "13":if (Result == 13)checkNumber = true;else checkNumber = false;break;
                case "16":if (Result == 16)checkNumber = true;else checkNumber = false;break;
                case "19":if (Result == 19)checkNumber = true;else checkNumber = false;break;
                case "22":if (Result == 22)checkNumber = true;else checkNumber = false;break;
                case "25":if (Result == 25)checkNumber = true;else checkNumber = false;break;
                case "28":if (Result == 28)checkNumber = true;else checkNumber = false;break;
                case "31":if (Result == 31)checkNumber = true;else checkNumber = false;break;
                case "34": if (Result == 34)checkNumber = true;else checkNumber = false; break;

            }

        }
        if(Check4 != null){
            switch (Check4){
                case "0":if(Result == 0)checkNumber2 = true;else checkNumber2 = false;break;
                case "2":if(Result == 2)checkNumber2 = true;else checkNumber2 = false;break;
                case "5":if(Result == 5)checkNumber2 = true;else checkNumber2 = false;break;
                case "8":if(Result == 8)checkNumber2 = true;else checkNumber2 = false;break;
                case "11":if (Result == 11)checkNumber2 = true;else checkNumber2 = false;break;
                case "14":if (Result == 14)checkNumber2 = true;else checkNumber2 = false;break;
                case "17":if (Result == 17)checkNumber2 = true;else checkNumber2 = false;break;
                case "20":if (Result == 20)checkNumber2 = true;else checkNumber2 = false;break;
                case "23":if (Result == 23)checkNumber2 = true;else checkNumber2 = false;break;
                case "26":if (Result == 26)checkNumber2 = true;else checkNumber2 = false;break;
                case "29":if (Result == 29)checkNumber2 = true;else checkNumber2 = false;break;
                case "32":if (Result == 32)checkNumber2 = true;else checkNumber2 = false;break;
                case "35": if (Result == 35)checkNumber2 = true;else checkNumber2 = false;break;


            }

        }
        if(Check5 != null){
            switch (Check5){
                case "3":if(Result == 3)checkNumber3 = true;else checkNumber3 = false;break;
                case "6":if(Result == 6)checkNumber3 = true;else checkNumber3 = false;break;
                case "9":if(Result == 9)checkNumber3 = true;else checkNumber3 = false;break;
                case "12":if (Result == 12)checkNumber3 = true;else checkNumber3 = false;break;
                case "15":if (Result == 15)checkNumber3 = true;else checkNumber3 = false;break;
                case "18":if (Result == 18)checkNumber3 = true;else checkNumber3 = false;break;
                case "21":if (Result == 21)checkNumber3 = true;else checkNumber3 = false;break;
                case "24":if (Result == 24)checkNumber3 = true;else checkNumber3 = false;break;
                case "27":if (Result == 27)checkNumber3 = true;else checkNumber3 = false;break;
                case "30":if (Result == 30)checkNumber3 = true;else checkNumber3 = false;break;
                case "33":if (Result == 33)checkNumber3 = true;else checkNumber3 = false;break;
                case "36": if (Result == 36)checkNumber3 = true;else checkNumber3 = false;break;
            }
        }
        if(Check6 != null){
            switch (Check6){
                case "1and1":
                    if (Result == 1 || Result == 4 || Result == 7 || Result == 10 || Result == 13 || Result == 16 || Result == 19 || Result == 22 || Result == 25 || Result == 28 || Result == 31 || Result == 34)checkNumber4 = true;
                    else checkNumber4 = false;break;
                case "2and1":
                    if (Result == 2 || Result == 5 || Result == 8 || Result == 11 || Result == 14 || Result == 17 || Result == 20 || Result == 23 || Result == 26 || Result == 29 || Result == 32 || Result == 35)checkNumber4 = true;
                    else checkNumber4 = false;break;
                case "3and1":
                    if (Result == 3 || Result == 6 || Result == 9 || Result == 12 || Result == 15 || Result == 18 || Result == 21 || Result == 24 || Result == 27 || Result == 30 || Result == 33 || Result == 36)checkNumber4 = true;
                    else checkNumber4 = false;break;
            }
            if(checkNumber4 == true)AllRate = AllRate + (Rate * 2);else AllRate -= Rate;
        }

//        Toast.makeText(getActivity(),String.valueOf(checkHalf) + String.valueOf(checkDozen) + String.valueOf(checkNumber) + String.valueOf(checkNumber2) + String.valueOf(checkNumber3),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(),String.valueOf(Result) +String.valueOf(checkNumber11) + String.valueOf(checkNumber21) + String.valueOf(checkNumber11),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(),Check1 +Check2 + Check3 + Check4 + Check5 + Check6,Toast.LENGTH_SHORT).show();

       if(Check3 != null && Check4 != null && Check5 != null){
           if(checkNumber == true || checkNumber2 == true|| checkNumber3 == true)AllRate = AllRate + (Rate * 11);
           else AllRate -= Rate * 3;
//           Toast.makeText(getActivity(),"Прошло на * 3",Toast.LENGTH_SHORT).show();
       }
       else if((Check3 != null && Check4 != null) || (Check3 != null && Check5 != null) || (Check5 != null && Check4 != null)){
           if(checkNumber == true || checkNumber2 == true|| checkNumber3 == true)AllRate = AllRate + (Rate * 17);
           else AllRate -= Rate * 2;
//           Toast.makeText(getActivity(),"Прошло на * 2",Toast.LENGTH_SHORT).show();
       }
       else if(Check3 != null || Check4 != null || Check5 != null){
           if(checkNumber == true || checkNumber2 == true|| checkNumber3 == true)AllRate = AllRate + (Rate * 35);
           else AllRate -= Rate;
//           Toast.makeText(getActivity(),"Прошло на * 1",Toast.LENGTH_SHORT).show();
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


