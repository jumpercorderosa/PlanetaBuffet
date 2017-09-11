package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;
import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.trocaFragmento;

public class CalendarFragment extends Fragment {

    private DBHandler db;
    DatePicker simpleDatePicker;
    Button submit;
    EditText date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int day;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        simpleDatePicker = (DatePicker) view.findViewById(R.id.simpleDatePicker);

        setHasOptionsMenu(true);
        db = new DBHandler(getContext());

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        int aux = db.getUsersCount();
        final User user = db.getUser(user_id);

        submit = (Button) view.findViewById(R.id.picDate);

        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                //String day = "Day = " + simpleDatePicker.getDayOfMonth();
                //String month = "Month = " + (simpleDatePicker.getMonth() + 1);
                //String year = "Year = " + simpleDatePicker.getYear();
                // display the values by using a toast
                //Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();

                String date = simpleDatePicker.getDayOfMonth() + "-" + (simpleDatePicker.getMonth() + 1) + "-" + simpleDatePicker.getYear();

                user.setEventDate(date);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CountdownFragment());
            }
        });

        return view;
    }

}

