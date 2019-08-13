package gorbachew.pythonanywhere.ru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Work extends Fragment {
    Button btnFactory,btnAutoService,btnEngineer, btnManagerForSale, btnHeadOfDepartment,btnJob,btnQuit;
    TextView WTworkName,WTrank,WTloyalty,WTsalary;
    ScrollView choiceWork,workTable;
    Toast toast;
    final String LOAD_SCHOOL = "EducationSchool";
    final String LOAD_COLLEGE = "EducationCollage";
    final String LOAD_COURSES = "EducationCourses";
    final String LOAD_UNIVERSITY = "EducationUniversity";
    final String LOAD_OVERSEASUNIVERSITY = "EducationOverseasUniversity";
    SharedPreferences sPref;
    final String LOAD_JOB = "Job";
    final String LOAD_RANKJOB = "RankJob";
    final String LOAD_JOBLOYALTY = "LoyaltyJob";
    final String LOAD_JOBMAXLOYALTY = "MaxLoyaltyJob";
    final String LOAD_ALCO = "Alco";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View WorkFr = inflater.inflate(R.layout.fragment_work, container, false);




        // Inflate the layout for this fragment
        btnFactory = WorkFr.findViewById(R.id.btnWorkFactory);
        btnAutoService = WorkFr.findViewById(R.id.btnWorkAutoService);
        btnEngineer = WorkFr.findViewById(R.id.btnWorkEngineer);
        btnManagerForSale = WorkFr.findViewById(R.id.btnWorkManagerForSale);
        btnHeadOfDepartment = WorkFr.findViewById(R.id.btnWorkHeadOfDepartment);
        choiceWork = WorkFr.findViewById(R.id.choiceWork);
        workTable = WorkFr.findViewById(R.id.workTable);

        WTworkName = WorkFr.findViewById(R.id.WTworkName);
        WTrank = WorkFr.findViewById(R.id.WTrank);
        WTsalary = WorkFr.findViewById(R.id.WTsalary);
        WTloyalty = WorkFr.findViewById(R.id.WTloyalty);

        btnJob = WorkFr.findViewById(R.id.btnWTjob);
        btnQuit = WorkFr.findViewById(R.id.btnWTfired);

        checkWT();


        btnJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int alco = sPref.getInt(LOAD_ALCO, 0);
                if(alco > 0){
                    if(toast != null){
                        toast.cancel();
                    }
                    toast = Toast.makeText(getActivity(),getResources().getString(R.string.WTerror0),Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    int Moneyjob = Integer.parseInt(sPref.getString(LOAD_JOB,"")) * 100;
                    int Moneyrank = Integer.parseInt(sPref.getString(LOAD_RANKJOB,"")) * 100;
                    int plusmoney = (Moneyjob + Moneyrank) * Integer.parseInt(sPref.getString(LOAD_JOB,""));

                    int loyalty = Integer.parseInt(sPref.getString(LOAD_JOBLOYALTY,""));
                    int maxLoyalty = Integer.parseInt(sPref.getString(LOAD_JOBMAXLOYALTY,""));
                    loyalty ++;
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOBLOYALTY,String.valueOf(loyalty));
                    if(loyalty >= maxLoyalty){
                        switch (maxLoyalty){
                            case 100:ed.putString(LOAD_JOBMAXLOYALTY,"300");ed.putString(LOAD_RANKJOB,"2");break;
                            case 300:ed.putString(LOAD_JOBMAXLOYALTY,"1000");ed.putString(LOAD_RANKJOB,"3");break;
                            case 1000:ed.putString(LOAD_JOBMAXLOYALTY,"2000");ed.putString(LOAD_RANKJOB,"4");break;
                            case 2000:ed.putString(LOAD_RANKJOB,"5");break;
                        }
                    }
                    ed.apply();
                    ((Game)getActivity()).transaction("rub","+",plusmoney);
                    checkWT();

                }
                ((Game)getActivity()).NextDay();



            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getResources().getString(R.string.WTquitText))
                        .setPositiveButton(getResources().getString(R.string.yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Подхватывает и запускает страницу создания персонажа

                                        SharedPreferences.Editor ed = sPref.edit();
                                        ed.putString(LOAD_JOB,"0");
                                        ed.putString(LOAD_RANKJOB,"0");
                                        ed.putString(LOAD_JOBMAXLOYALTY,"100");
                                        ed.putString(LOAD_JOBLOYALTY,"0");
                                        ed.apply();
                                        checkWT();
                                        ((Game)getActivity()).NextDay();

                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.no),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();



            }
        });
        btnFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_SCHOOL,"");
                if(!checkEduc.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOB,"1");
                    ed.putString(LOAD_RANKJOB,"1");
                    ed.apply();
                    ((Game)getActivity()).NextDay();
                    checkWT();
                }

            }
        });
        btnAutoService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkEduc = sPref.getString(LOAD_COLLEGE,"");
                if(!checkEduc.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFCollegeError),Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOB,"2");
                    ed.putString(LOAD_RANKJOB,"1");
                    ed.apply();
                    ((Game)getActivity()).NextDay();
                    checkWT();
                }

            }
        });
        btnManagerForSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_COURSES,"");
                if(!checkEduc.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFCoursesError),Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOB,"4");
                    ed.putString(LOAD_RANKJOB,"1");
                    ed.apply();
                    ((Game)getActivity()).NextDay();
                    checkWT();
                }
            }
        });
        btnEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String university = sPref.getString(LOAD_UNIVERSITY,"");
                if(!university.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFUniversityError),Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOB,"3");
                    ed.putString(LOAD_RANKJOB,"1");
                    ed.apply();
                    ((Game)getActivity()).NextDay();
                    checkWT();
                }
            }
        });
        btnHeadOfDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_OVERSEASUNIVERSITY,"");
                if(!checkEduc.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFOverseasUniversityError),Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_JOB,"5");
                    ed.putString(LOAD_RANKJOB,"1");
                    ed.apply();
                    ((Game)getActivity()).NextDay();
                    checkWT();
                }
            }
        });
        return WorkFr;

    }
    @SuppressLint("SetTextI18n")
    private void checkWT(){
        String job = sPref.getString(LOAD_JOB,"");
        String rankJob = sPref.getString(LOAD_RANKJOB,"");
        if(job.equals("0")){
            choiceWork.setVisibility(View.VISIBLE);
            workTable.setVisibility(View.INVISIBLE);
            btnJob.setVisibility(View.INVISIBLE);
            btnQuit.setVisibility(View.INVISIBLE);
        }
        else {
            choiceWork.setVisibility(View.INVISIBLE);
            workTable.setVisibility(View.VISIBLE);
            btnJob.setVisibility(View.VISIBLE);
            btnQuit.setVisibility(View.VISIBLE);
            switch (job){
                case "1":WTworkName.setText(getResources().getString(R.string.PFSjob1));break;
                case "2":WTworkName.setText(getResources().getString(R.string.PFSjob2));break;
                case "3":WTworkName.setText(getResources().getString(R.string.PFSjob3));break;
                case "4":WTworkName.setText(getResources().getString(R.string.PFSjob4));break;
                case "5":WTworkName.setText(getResources().getString(R.string.PFSjob5));break;
            }
            switch (rankJob){
                case "0":WTrank.setText(getResources().getString(R.string.PFSrankJob0));break;
                case "1":WTrank.setText(getResources().getString(R.string.PFSrankJob1));break;
                case "2":WTrank.setText(getResources().getString(R.string.PFSrankJob2));break;
                case "3":WTrank.setText(getResources().getString(R.string.PFSrankJob3));break;
                case "4":WTrank.setText(getResources().getString(R.string.PFSrankJob4));break;
                case "5":WTrank.setText(getResources().getString(R.string.PFSrankJob5));break;
            }
            WTloyalty.setText(sPref.getString(LOAD_JOBLOYALTY,"") + "/" + sPref.getString(LOAD_JOBMAXLOYALTY,""));
            int Moneyjob = Integer.parseInt(sPref.getString(LOAD_JOB,"")) * 100;
            int Moneyrank = Integer.parseInt(sPref.getString(LOAD_RANKJOB,"")) * 100;
            int plusmoney = (Moneyjob + Moneyrank) * Integer.parseInt(sPref.getString(LOAD_JOB,""));
            WTsalary.setText(String.valueOf(plusmoney));


        }
    }
}