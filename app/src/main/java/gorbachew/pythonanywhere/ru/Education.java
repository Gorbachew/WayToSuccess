package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Education extends Fragment {

    Button btnEducationSchool,btnEducationCollege,btnEducationCourses,btnEducationUniversity,btnEducationOverseasUniversity;
    SharedPreferences sPref;
    final String LOAD_SCHOOL = "EducationSchool";
    final String LOAD_SCHOOLHOUR = "EducationSchoolHour";
    final String LOAD_COLLEGE = "EducationCollage";
    final String LOAD_COLLEGEHOUR = "EducationCollageHour";
    final String LOAD_COURSES = "EducationCourses";
    final String LOAD_COURSESHOUR = "EducationCoursesHour";
    final String LOAD_UNIVERSITY = "EducationUniversity";
    final String LOAD_UNIVERSITYHOUR = "EducationUniversityHour";
    final String LOAD_OVERSEASUNIVERSITY = "EducationOverseasUniversity";
    final String LOAD_OVERSEASUNIVERSITYHOUR = "EducationOverseasUniversityHour";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String SAVED_EDUCATION = "Education";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View EducFr = inflater.inflate(R.layout.fragment_education, container, false);
        sPref = this.getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);





        btnEducationSchool = EducFr.findViewById(R.id.btnEducationSchool);
        btnEducationCollege = EducFr.findViewById(R.id.btnEducationCollege);
        btnEducationCourses = EducFr.findViewById(R.id.btnEducationCourses);
        btnEducationUniversity = EducFr.findViewById(R.id.btnEducationUniversity);
        btnEducationOverseasUniversity = EducFr.findViewById(R.id.btnEducationOverseasUniversity);


        CheckButton();

        btnEducationSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_SCHOOL,"");
                if(checkEduc.equals("0")) {
                    if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 10000) {
                        ((Game) getActivity()).LowMoney("rub");
                    }
                    else {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_SCHOOL, "1");
                        ed.commit();
                        ((Game) getActivity()).transaction("rub", "-", 10000);
                    }
                }
                else {
                    int hour = Integer.parseInt(sPref.getString(LOAD_SCHOOLHOUR,""));
                    hour -= 6;

                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(LOAD_SCHOOLHOUR, String.valueOf(hour));
                    ed.commit();
                }
                CheckButton();
                ((Game)getActivity()).NextDay();
            }
        });
        btnEducationCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkEduc = sPref.getString(LOAD_COLLEGE,"");
                int checkShcool = Integer.parseInt(sPref.getString(SAVED_EDUCATION,""));
                if(checkShcool < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {
                    if(checkEduc.equals("0")) {
                        if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 50000) {
                            ((Game) getActivity()).LowMoney("rub");
                        }
                        else {
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_COLLEGE, "1");
                            ed.commit();
                            ((Game) getActivity()).transaction("rub", "-", 50000);
                        }
                    }
                    else {
                        int hour = Integer.parseInt(sPref.getString(LOAD_COLLEGEHOUR,""));
                        hour -= 6;

                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_COLLEGEHOUR, String.valueOf(hour));
                        ed.commit();
                    }
                    CheckButton();
                    ((Game)getActivity()).NextDay();
                }

            }
        });
        btnEducationCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_COURSES,"");
                int checkShcool = Integer.parseInt(sPref.getString(SAVED_EDUCATION,""));
                if(checkShcool < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {
                    if (checkEduc.equals("0")) {
                        if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 10000) {
                            ((Game) getActivity()).LowMoney("usd");
                        }
                        else {
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_COURSES, "1");
                            ed.commit();
                            ((Game) getActivity()).transaction("usd", "-", 10000);
                        }
                    } else {
                        int hour = Integer.parseInt(sPref.getString(LOAD_COURSESHOUR, ""));
                        hour -= 6;

                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_COURSESHOUR, String.valueOf(hour));
                        ed.commit();
                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnEducationUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_UNIVERSITY,"");
                int checkShcool = Integer.parseInt(sPref.getString(SAVED_EDUCATION,""));
                if(checkShcool < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {
                    if (checkEduc.equals("0")) {
                        if (Integer.parseInt(sPref.getString(LOAD_RUB, "")) < 400000) {
                            ((Game) getActivity()).LowMoney("rub");
                        }
                        else {
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_UNIVERSITY, "1");
                            ed.commit();
                            ((Game) getActivity()).transaction("rub", "-", 400000);
                        }
                    } else {
                        int hour = Integer.parseInt(sPref.getString(LOAD_UNIVERSITYHOUR, ""));
                        hour -= 6;

                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_UNIVERSITYHOUR, String.valueOf(hour));
                        ed.commit();
                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
                }
            }
        });
        btnEducationOverseasUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEduc = sPref.getString(LOAD_OVERSEASUNIVERSITY,"");
                int checkShcool = Integer.parseInt(sPref.getString(SAVED_EDUCATION,""));
                if(checkShcool < 1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.EFSchoolError),Toast.LENGTH_SHORT).show();
                }
                else {
                    if (checkEduc.equals("0")) {
                        if (Integer.parseInt(sPref.getString(LOAD_USD, "")) < 100000) {
                            ((Game) getActivity()).LowMoney("usd");
                        }
                        else {
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString(LOAD_OVERSEASUNIVERSITY, "1");
                            ed.commit();
                            ((Game) getActivity()).transaction("usd", "-", 100000);
                        }
                    } else {
                        int hour = Integer.parseInt(sPref.getString(LOAD_OVERSEASUNIVERSITYHOUR, ""));
                        hour -= 6;

                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(LOAD_OVERSEASUNIVERSITYHOUR, String.valueOf(hour));
                        ed.commit();

                    }
                    CheckButton();
                    ((Game) getActivity()).NextDay();
                }
            }
        });


        return EducFr;
    }

    public void CheckButton(){
        String school = sPref.getString(LOAD_SCHOOL,"");
        String college = sPref.getString(LOAD_COLLEGE,"");
        String courses = sPref.getString(LOAD_COURSES,"");
        String university = sPref.getString(LOAD_UNIVERSITY,"");
        String overseasuniversity = sPref.getString(LOAD_OVERSEASUNIVERSITY,"");

        if(school.equals("0")){
            btnEducationSchool.setText(getResources().getString(R.string.EFSchool) + "|360 часов|10000р" );
        }
        else {
            String hour = sPref.getString(LOAD_SCHOOLHOUR,"");
            if(hour.equals("0")){
                btnEducationSchool.setEnabled(false);
                btnEducationSchool.setText(getResources().getString(R.string.EFSchoolEnd));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_EDUCATION, "1");
                ed.putString(LOAD_SCHOOL, "2");
                ed.commit();
            }
            else {
                btnEducationSchool.setText(getResources().getString(R.string.EFSchool) +"|" + hour);
            }
        }
        if(college.equals("0")){
            btnEducationCollege.setText(getResources().getString(R.string.EFCollege) + "|720 часов|50000р" );
        }
        else {
            String hour = sPref.getString(LOAD_COLLEGEHOUR,"");

            if(hour.equals("0")){
                btnEducationCollege.setEnabled(false);
                btnEducationCollege.setText(getResources().getString(R.string.EFCollegeEnd));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_EDUCATION, "2");
                ed.commit();
            }
            else {
                btnEducationCollege.setText(getResources().getString(R.string.EFCollege) +"|" + hour);
            }
        }
        if(courses.equals("0")){
            btnEducationCourses.setText(getResources().getString(R.string.EFCourses) + "|360 часов|10000$" );
        }
        else {
            String hour = sPref.getString(LOAD_COURSESHOUR,"");
            if(hour.equals("0")){
                btnEducationCourses.setEnabled(false);
                btnEducationCourses.setText(getResources().getString(R.string.EFCoursesEnd));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_EDUCATION, "3");
                ed.commit();
            }
            else {
                btnEducationCourses.setText(getResources().getString(R.string.EFCourses) + "|" + hour);

            }
        }
        if(university.equals("0")){
            btnEducationUniversity.setText(getResources().getString(R.string.EFUniversity) + "|1440 часов|400000р" );
        }
        else {
            String hour = sPref.getString(LOAD_UNIVERSITYHOUR,"");
            if(hour.equals("0")){
                btnEducationUniversity.setEnabled(false);
                btnEducationUniversity.setText(getResources().getString(R.string.EFUniversityEnd));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_EDUCATION, "4");
                ed.commit();
            }
            else {
                btnEducationUniversity.setText(getResources().getString(R.string.EFUniversity) + "|" + hour);

            }

        }
        if(overseasuniversity.equals("0")){
            btnEducationOverseasUniversity.setText(getResources().getString(R.string.EFOverseasUniversity) + "|1200 часов|100000$" );
        }
        else {
            String hour = sPref.getString(LOAD_OVERSEASUNIVERSITYHOUR,"");
            if(hour.equals("0")){
                btnEducationOverseasUniversity.setEnabled(false);
                btnEducationOverseasUniversity.setText(getResources().getString(R.string.EFOverseasUniversityEnd));
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(SAVED_EDUCATION, "5");
                ed.commit();
            }
            else {
                btnEducationOverseasUniversity.setText(getResources().getString(R.string.EFOverseasUniversity) + "|" + hour);

            }

        }
    }
}
