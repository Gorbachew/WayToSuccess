package gorbachew.pythonanywhere.ru;


        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.view.View;

        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;

        import android.widget.Toast;

public class Start extends AppCompatActivity{

    Button btnSave;

    EditText textName;
    SharedPreferences sPref;
    Spinner intAge,Poll1,Poll2,Poll3,Poll4,Poll5,Poll6,Poll7;
    FragmentTransaction fTrans;
    Fragment CHeroP,HeroP;
    //Константы в сохранение
    final String SAVED_NAME = "Name";
    final String SAVED_AGE = "Age";
    final String SAVED_POLL1 = "Father";
    final String SAVED_POLL2 = "Mother";
    final String SAVED_POLL3 = "childhood";
    final String SAVED_POLL4 = "youth";
    final String SAVED_POLL5 = "AftSchool";
    final String SAVED_POLL6 = "Purpose";
    final String SAVED_POLL7 = "Why";
    final String SAVED_HP = "TotalHP";
    final String SAVED_MP = "TotalMP";
    final String SAVED_SP = "TotalSP";
    final String SAVED_RUB = "RUB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        textName = (EditText)findViewById(R.id.StartName);
        intAge = (Spinner)findViewById(R.id.SpinnerStartAge);
        Poll1 = (Spinner)findViewById(R.id.spinnerPoll1);
        Poll2 = (Spinner)findViewById(R.id.spinnerPoll2);
        Poll3 = (Spinner)findViewById(R.id.spinnerPoll3);
        Poll4 = (Spinner)findViewById(R.id.spinnerPoll4);
        Poll5 = (Spinner)findViewById(R.id.spinnerPoll5);
        Poll6 = (Spinner)findViewById(R.id.spinnerPoll6);
        Poll7 = (Spinner)findViewById(R.id.spinnerPoll7);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.SpinnerAge,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        intAge.setAdapter(adapter);
        ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull1,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll1.setAdapter(adapter1);
        ArrayAdapter<?> adapter2 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull2,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll2.setAdapter(adapter2);
        ArrayAdapter<?> adapter3 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull3,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll3.setAdapter(adapter3);
        ArrayAdapter<?> adapter4 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull4,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll4.setAdapter(adapter4);
        ArrayAdapter<?> adapter5 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull5,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll5.setAdapter(adapter5);
        ArrayAdapter<?> adapter6 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull6,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll6.setAdapter(adapter6);
        ArrayAdapter<?> adapter7 = ArrayAdapter.createFromResource(this,R.array.SpinnerPull7,android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Poll7.setAdapter(adapter7);

        CHeroP = new ChoiceHeroPast();
        HeroP = new HeroPast();

        /*
        btnRead = (Button)findViewById(R.id.button);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textTest1 = (TextView)findViewById(R.id.textView2);
                TextView textTest2 = (TextView)findViewById(R.id.textView5);

                String name = textName.getText().toString();
                textTest1.setText((CharSequence) name);


                String age = intAge.getSelectedItem().toString();
                textTest2.setText((CharSequence) age);
                loadText();
            }

        });*/
        btnSave = findViewById(R.id.btnSaveChoice);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveСhoice();
                //Переход на стр игры
                Intent intent = new Intent(".Game");
                startActivity(intent);
            }
        });
    }


//Смена фрагментов
    public void Change(View view){
        fTrans = getSupportFragmentManager().beginTransaction();
        Button varbtnHeroPast = findViewById(R.id.btnHeroPast);
        switch (view.getId()){
            case R.id.btnHeroPast:

                fTrans.add(R.id.fr_place,HeroP);
                saveСhoice();
                varbtnHeroPast.setClickable(false);
                varbtnHeroPast.setBackgroundColor(Color.GRAY);
            break;
            case R.id.btnChoiceHeroPast:
                fTrans.remove(HeroP);
                varbtnHeroPast.setClickable(true);
                varbtnHeroPast.setBackgroundColor(Color.LTGRAY);
                break;
        }
        fTrans.commit();
    }




//Сохранение выбранных настроек
    private void saveСhoice() {
        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.clear();
        if(textName.getText().toString().equals("")){
            ed.putString(SAVED_NAME,getResources().getString(R.string.UserName));
        }
        else{
            ed.putString(SAVED_NAME, textName.getText().toString());
        }
        int TotalHP = 100,TotalMP = 100,TotalSP = 100,RUB = 0;
        String idAge = String.valueOf(intAge.getSelectedItem());
        if(idAge.equals("0")) {
            ed.putString(SAVED_AGE,"18" );
        }
        else {
            ed.putString(SAVED_AGE,idAge );
        }
        String idPoll1 = String.valueOf(Poll1.getSelectedItemId());
        String idPoll2 = String.valueOf(Poll2.getSelectedItemId());
        String idPoll3 = String.valueOf(Poll3.getSelectedItemId());
        String idPoll4 = String.valueOf(Poll4.getSelectedItemId());
        String idPoll5 = String.valueOf(Poll5.getSelectedItemId());
        String idPoll6 = String.valueOf(Poll6.getSelectedItemId());
        String idPoll7= String.valueOf(Poll6.getSelectedItemId());
        switch (idPoll1){
            case "0":
                TotalHP = TotalHP + 5;
                break;
            case "1":
                TotalMP = TotalMP + 5;
                break;
            case "2":
                TotalSP = TotalSP + 5;
                break;
            case "3":

                break;
            case "4":
                RUB = RUB + 500;
                break;
            case "5":
                RUB = RUB - 100;
                break;
            case "6":
                TotalMP = TotalMP - 10;
                break;
        }
        switch (idPoll2){
            case "0":
                TotalHP = TotalHP + 5;
                break;
            case "1":
                TotalMP = TotalMP + 5;
                break;
            case "2":
                TotalSP = TotalSP + 5;
                break;
            case "3":

                break;
            case "4":
                RUB = RUB + 500;
                break;
            case "5":
                RUB = RUB - 100;
                break;
            case "6":
                TotalMP = TotalMP - 10;
                break;
        }
        switch (idPoll3) {
            case "0":
                TotalMP = TotalMP + 10;
                break;
            case "1":

                break;
            case "2":
                TotalHP = TotalHP + 5;
                break;
            case "3":
                TotalMP = TotalMP - 5;
                break;
        }
        switch (idPoll4) {
            case "0":

                break;
            case "1":
                RUB = RUB + 100; TotalHP = TotalHP - 10;
                break;
            case "2":
                TotalMP = TotalMP + 5; TotalHP = TotalHP + 5; TotalSP = TotalSP + 5;
                break;
        }
        switch (idPoll5) {
            case "0":
                TotalSP = TotalSP + 10;
                break;
            case "1":
                TotalSP = TotalSP - 10;
                break;
            case "2":
                TotalHP = TotalHP - 5;
                RUB = RUB + 200;
                break;
            case "3":

                break;
        }
        switch (idPoll7) {
            case "0":
                TotalMP = TotalMP - 10;
                break;
            case "1":
                RUB = RUB - 200;
                break;
            case "2":
                TotalHP = TotalHP - 10;
                break;
        }

        ed.putString(SAVED_POLL1,idPoll1 );
        ed.putString(SAVED_POLL2,idPoll2 );
        ed.putString(SAVED_POLL3,idPoll3 );
        ed.putString(SAVED_POLL4,idPoll4 );
        ed.putString(SAVED_POLL5,idPoll5 );
        ed.putString(SAVED_POLL6,idPoll6 );
        ed.putString(SAVED_POLL7,idPoll7 );
        ed.putString(SAVED_HP,String.valueOf(TotalHP));
        ed.putString(SAVED_MP,String.valueOf(TotalMP));
        ed.putString(SAVED_SP,String.valueOf(TotalSP));
        ed.putString(SAVED_RUB,String.valueOf(RUB));
        ed.putString("USD","0");
        ed.putString("CourseUSD","20");
        ed.putString("RESPECT","0");
        ed.putString("DAY","1");
        ed.putString("HP","100");
        ed.putString("SP","100");
        ed.putString("MP","100");
        ed.putString("SCRAP","0");
        ed.putString("CourseScrap","10");
        ed.putString("BusinessMetalPoint","0");

        ed.putString("BMFullStock","0");
        ed.putString("BMMaxStock","100");

        ed.putString("BMAd","1");
        ed.putString("BMWorker","0");
        ed.putString("BMPriceWorker","1000");
        ed.putString("BMPriceBis","0");


        ed.putString("BusinessPC","0");
        ed.putString("Clothes","0");
        ed.putString("Education","0");

        ed.putString("EducationSchool","0");
        ed.putString("EducationSchoolHour","720");
        ed.putString("EducationCollage","0");
        ed.putString("EducationCollageHour","1440");
        ed.putString("EducationCourses","0");
        ed.putString("EducationCoursesHour","720");
        ed.putString("EducationUniversity","0");
        ed.putString("EducationUniversityHour","2160");
        ed.putString("EducationOverseasUniversity","0");
        ed.putString("EducationOverseasUniversityHour","2400");



        ed.putString("Transport","0");
        ed.putString("Holding","0");
        ed.putString("Job","0");
        ed.putString("RankJob","0");
        ed.putString("Business","0");
        ed.putString("BusinessCafe","0");



        ed.putString("BusinessCafe","0");
        ed.putString("BCroom","1");
        ed.putString("BCad","1");
        ed.putString("BCwaiter","0");
        ed.putString("BCcook","0");
        ed.putString("BCvisitors","0");
        ed.putString("BCvisitorsLastWeek","0");
        ed.putString("BCtables","0");
        ed.putString("BCprofit","0");

        ed.putString("BCPriceRoom","20000");
        ed.putString("BCPriceWaiter","5000");
        ed.putString("BCPriceCook","3000");

        ed.putString("BCPriceBusiness","0");

        ed.putString("BuffCook","0");
        ed.putString("BuffComic","0");
        ed.putString("BuffDock","0");
        ed.putString("BuffMP","0");

        ed.putString("PropertyTV","0");
        ed.putString("PropertyCamera","0");
        ed.putString("PropertyPC","0");
        ed.putString("PropertyWeapon","0");




        ed.commit();
        Toast.makeText(Start.this, "Text saved", Toast.LENGTH_SHORT).show();
    }
    private void loadText() {
        /*
        TextView textTest1 = (TextView)findViewById(R.id.textView2);
        sPref = getSharedPreferences("Saved",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        textTest1.setText(savedText);
        Toast.makeText(Start.this, "Text Loaded", Toast.LENGTH_SHORT).show();
        */
    }

    //Сохраняет при выходе из приложения
    /*
    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveText();
    }
    */
}

