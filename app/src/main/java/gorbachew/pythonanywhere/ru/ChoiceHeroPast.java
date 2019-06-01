package gorbachew.pythonanywhere.ru;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ChoiceHeroPast extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    Spinner intAge,intAge1,Poll1,Poll2,Poll3,Poll4,Poll5,Poll6,Poll7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      /*
       View v = inflater.inflate(R.layout.fragment_choice_hero_past, container,false);

        intAge = (Spinner) v.findViewById(R.id.SpinnerStartAge);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this.ger(),R.array.SpinnerPull1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        intAge.setAdapter(adapter);
        */
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice_hero_past, container, false);
    }


}
