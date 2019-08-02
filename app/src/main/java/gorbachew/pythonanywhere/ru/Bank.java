package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Bank extends Fragment {



    final String LOAD_CourseUSD = "CourseUSD";
    Spinner spin;
    SharedPreferences sPref;
    TextView BankCourse;
    Button btnBankBuy,btnBankSell;
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View BankFr = inflater.inflate(R.layout.fragment_bank, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
        spin = BankFr.findViewById(R.id.BankSpinner);
        BankCourse = BankFr.findViewById(R.id.BankCourse);

        btnBankBuy = BankFr.findViewById(R.id.btnBankBuy);
        btnBankSell = BankFr.findViewById(R.id.btnBankSell);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.BFspin,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        btnBankSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int choise = Integer.parseInt(String.valueOf(spin.getSelectedItem()));

                if(Integer.parseInt(sPref.getString(LOAD_USD,"")) < choise){
                    ((Game)getActivity()).LowMoney("usd");
                }
                else {
                    int var = choise * Integer.parseInt(sPref.getString(LOAD_CourseUSD,""));

                    ((Game)getActivity()).transaction("rub","+",var);
                    ((Game)getActivity()).transaction("usd","-",choise);


                    ((Game)getActivity()).NextDay();
                    BankCourse.setText(sPref.getString(LOAD_CourseUSD,""));
                }

            }
        });
        btnBankBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choise = Integer.parseInt(String.valueOf(spin.getSelectedItem()));
                int course = Integer.parseInt(sPref.getString(LOAD_CourseUSD,""));
                int mult = choise * course;

                if( mult >= Integer.parseInt(sPref.getString(LOAD_RUB,""))){
                    ((Game)getActivity()).LowMoney("rub");
                }
                else {
                    int var =  choise * Integer.parseInt(sPref.getString(LOAD_CourseUSD,""));

                    ((Game)getActivity()).transaction("rub","-",var);
                    ((Game)getActivity()).transaction("usd","+",choise);
                    Toast.makeText(getActivity(),String.valueOf(choise),Toast.LENGTH_LONG).show();


                    ((Game)getActivity()).NextDay();
                    BankCourse.setText(sPref.getString(LOAD_CourseUSD,""));
                }
            }

        });


        return BankFr;
    }

    @Override
    public void onStart() {
        super.onStart();
        BankCourse.setText(sPref.getString(LOAD_CourseUSD,""));
    }
}
