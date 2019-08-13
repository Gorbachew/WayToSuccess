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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Это очень тяжелая часть моей работы, я попробую ее здесь разобрать 07.08.2019 она работает но на массовые баги не проверяли
public class Gorbes extends Fragment {
    //Обьявление переменных
    SharedPreferences sPref;
    Button GFbtn;
    ImageButton GFbtnRefresh;
    EditText GFedit;
    ListView GFlist;
    //Обьявление констант для работы с внутренней БД
    final String LOAD_USERID = "UserId";
    final String LOAD_NAME = "Name";
    final String LOAD_AGE = "Age";
    final String LOAD_RUB = "RUB";
    final String LOAD_USD = "USD";
    final String LOAD_RESPECT = "RESPECT";
    final String LOAD_ANTICHEAT = "AntiCheat";

    //Соединение с бд firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getActivity().getSharedPreferences("Saved",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Обьявление визуальной части XML фрагмента и соединение с внутренней бд
        final View GorbesFr = inflater.inflate(R.layout.fragment_gorbes, container, false);

        //Find находим элементы XML
        GFbtn = GorbesFr.findViewById(R.id.GFbtn);
        GFlist = GorbesFr.findViewById(R.id.GFlist);
        GFbtnRefresh = GorbesFr.findViewById(R.id.GFbtnRefresh);

        //Обьявление метода обновления кнопки GFbtnRefresh
        UpdateList();
        //Обьявление метода проверки на регистрацию, если игрок не зарегистрировался, то user_id = 0
        CheckButton(0);
        //Обработка нажатия кнопки GFbtn
        GFbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId = sPref.getString(LOAD_USERID,"");
                //Если юзер ид не существует
                if(userId.equals("0")){
                    //Проверка на соответсвие ников, ищет никнейм такой же как у игрока, если ник находит и результат не пустой, то работает 1 иф, если пусто то регистрирует игрока в разделе Елсе
                    db.collection("Players")
                            .whereEqualTo("name",sPref.getString(LOAD_NAME,""))
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if ( !Objects.requireNonNull(task.getResult()).getDocuments().isEmpty()) {
                                        Toast.makeText(getActivity(),getResources().getString(R.string.GFnickError),Toast.LENGTH_LONG).show();

                                    } else {
                                        if (sPref.getInt(LOAD_USD, 0) < 1000) {
                                            ((Game) getActivity()).LowMoney("rub");
                                        }
                                        else {
                                            final Map<String, Object> user = new HashMap<>();
                                            user.put("name", sPref.getString(LOAD_NAME,""));
                                            user.put("age", sPref.getString(LOAD_AGE,""));
                                            user.put("usd", sPref.getInt(LOAD_USD,0));
                                            user.put("rub", sPref.getInt(LOAD_RUB,0));
                                            user.put("resp", sPref.getInt(LOAD_RESPECT,0));
                                            user.put("death", 0);
                                            user.put("cheat", 0);
                                            user.put("timestampcreate", FieldValue.serverTimestamp());
                                            user.put("timestampupdate", FieldValue.serverTimestamp());
                                            DocumentReference addedDocRef = db.collection("Players").document();
                                            //Добавление строки
                                            addedDocRef.set(user);
                                            SharedPreferences.Editor ed = sPref.edit();
                                            ed.putString(LOAD_USERID,addedDocRef.getId());
                                            ed.apply();
                                            CheckButton(0);
                                            ((Game)getActivity()).transaction("usd","-",1000);
                                            ((Game)getActivity()).NextDay();

                                        }
                                        UpdateList();
                                    }
                                }
                            });
                }
                //Если существует, показывает место
                else {
                    CheckButton(0);
                }
            }
        });
        GFbtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateList();
            }
        });
        return GorbesFr;
    }
    ArrayList arrayPlayers = new ArrayList();

    private void UpdateList(){
        final ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        db.collection("Players")
                .orderBy("usd", Query.Direction.DESCENDING)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String userId = sPref.getString(LOAD_USERID,"");

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HashMap<String, Object> players;
                                players = (HashMap<String, Object>) document.getData();
                                arrayList.add(players);
                                //Делаем адаптер
                                SimpleAdapter adapter = new SimpleAdapter(getActivity(), arrayList, R.layout.list_item,
                                        new String[]{"name", "age","usd","resp"},
                                        new int[]{R.id.LVGName, R.id.LVGAge,R.id.LVGUsd,R.id.LVGResp});
                                GFlist.setAdapter(adapter);
                                //Поиск места игрока, выписывает Аррейлист и сверяет по ид
                                arrayPlayers.add(document.getId());
                                int indexPlayer = arrayPlayers.indexOf(userId) + 1;
                                CheckButton(indexPlayer);
                            }
                            arrayPlayers.clear();


                        } else {
                            Toast.makeText(getActivity(),"Not load, not connections or bug",Toast.LENGTH_LONG).show();
                        }
                    }

                });


    }
    @SuppressLint("CommitPrefEdits")
    private void CheckButton(int indexPlayer){
        SharedPreferences.Editor ed = sPref.edit();
        String userId = sPref.getString(LOAD_USERID,"");
        int anticheat = sPref.getInt(LOAD_ANTICHEAT,0);

        if(anticheat == 1){
            GFbtn.setEnabled(false);
        }
        else if(userId.equals("0")){
            GFbtn.setText(getResources().getString(R.string.GFbtnJoin));
        }
        else {
            GFbtn.setText(String.format(getResources().getString(R.string.GFplace),indexPlayer));

        }
    }


}
