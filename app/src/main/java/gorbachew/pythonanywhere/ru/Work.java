package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class Work extends Fragment {
    Button btnFactory,btnAutoService,btnEngineer, btnManagerForSale, btnHeadOfDepartment;
    final String LOAD_SCHOOL = "EducationSchool";
    final String LOAD_COLLEGE = "EducationCollage";
    final String LOAD_COURSES = "EducationCourses";
    final String LOAD_UNIVERSITY = "EducationUniversity";
    final String LOAD_OVERSEASUNIVERSITY = "EducationOverseasUniversity";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View WorkFr = inflater.inflate(R.layout.fragment_work, container, false);

        final SharedPreferences sPref;
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);

        final String SAVED_EDUCATION = "Education";
        final String SAVED_JOB = "Job";

        // Inflate the layout for this fragment
        btnFactory = WorkFr.findViewById(R.id.btnWorkFactory);
        btnAutoService = WorkFr.findViewById(R.id.btnWorkAutoService);
        btnEngineer = WorkFr.findViewById(R.id.btnWorkEngineer);
        btnManagerForSale = WorkFr.findViewById(R.id.btnWorkManagerForSale);
        btnHeadOfDepartment = WorkFr.findViewById(R.id.btnWorkHeadOfDepartment);
        final Random random = new Random();







        btnFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkEduc = sPref.getString(LOAD_SCHOOL,"");
                if(!checkEduc.equals("2")){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {
                    int var = 250;
                    ((Game)getActivity()).transaction("rub","+", var);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_JOB,"1");
                    ed.commit();
                }

                ((Game)getActivity()).NextDay();
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
                    int var = 251 + random.nextInt(1000 - 250);
                    ((Game)getActivity()).transaction("rub","+", var);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_JOB,"2");
                    ed.commit();
                }
                ((Game)getActivity()).NextDay();
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
                    int var = 11 + random.nextInt(80 - 10);
                    ((Game)getActivity()).transaction("usd","+", var);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_JOB,"4");
                    ed.commit();
                }
                ((Game)getActivity()).NextDay();
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
                    int var = 2001 + random.nextInt(5000 - 2000);
                    ((Game)getActivity()).transaction("rub","+", var);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_JOB,"3");
                    ed.commit();
                }
                ((Game)getActivity()).NextDay();
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
                    int var = 101 + random.nextInt(200 - 100);
                    ((Game)getActivity()).transaction("usd","+", var);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(SAVED_JOB,"5");
                    ed.commit();
                }
                ((Game)getActivity()).NextDay();
            }
        });
        return WorkFr;

    }

}
