package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.Buffet;
import com.gmail.jumpercorderosa.planetabuffet.model.EventType;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;

public class ProfileFragment extends Fragment {

    private DBHandler db;

    EditText etNome;
    EditText etTipoFesta;
    EditText etBuffet;
    EditText etBuffetAddress;
    EditText etDataEvento;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etNome = (EditText) view.findViewById(R.id.etNome);
        etTipoFesta = (EditText) view.findViewById(R.id.etTpFesta);
        etBuffet = (EditText) view.findViewById(R.id.etBuffetEscolhido);
        etBuffetAddress = (EditText) view.findViewById(R.id.etBuffetAdress);
        etDataEvento = (EditText) view.findViewById(R.id.etDataEvento);

        setHasOptionsMenu(true);
        db = new DBHandler(getContext());

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        //load user
        final User user = db.getUser(user_id);

        if(user != null) {
            etNome.setText(user.getLogin());

            Buffet buffet = db.getBuffet(user.getBuffetId());

            String desc;
            if(user.getEventTypeId() == 1) {
                desc = "Casamento";
            } else if(user.getEventTypeId() == 2) {
                desc = "Aniversário";
            } else if(user.getEventTypeId() == 3) {
                desc = "Debutante";
            } else if(user.getEventTypeId() == 4) {
                desc = "Festa corporativa";
            } else {
                desc = "";
            }

            String date_format = user.getEventDate().replace("-", "/");

            etTipoFesta.setText(desc);
            etBuffet.setText(buffet.getSubsidiary());
            etBuffetAddress.setText(buffet.getAddress());
            etDataEvento.setText(date_format);
        }

        return view;
    }
}
