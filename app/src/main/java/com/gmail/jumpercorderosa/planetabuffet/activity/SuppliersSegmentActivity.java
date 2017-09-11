package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.jumpercorderosa.planetabuffet.adapter.IconAdapter;
import com.gmail.jumpercorderosa.planetabuffet.adapter.IconData;
import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.SupplierSegment;

import java.util.ArrayList;
import java.util.List;

public class SuppliersSegmentActivity extends AppCompatActivity {

    private DBHandler db;
    public int current_supplier_segment_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_segment);

        //obtem uma instancia do DB
        db = new DBHandler(this);

        //obtem dados da festa do usuário para obtenção dos fornecedores corretos

        //obtem lista de segmento de fornecedores
        List<SupplierSegment> listSeg = db.getAllSuppliersSegment();

        /*
        //Como esta no exemplo
        IconData[] data = new IconData[] {
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert)
        };
        */

        //Como tentei usar e não consigo
        List<IconData> myList = new ArrayList<>();

        //percorre objetos da lista
        for(SupplierSegment obj : listSeg) {

            int x = obj.getSupplierSegmentId();

            myList.add(new IconData(obj.getSupplierSegmentDesc(),
                    getResources().getIdentifier(
                            obj.getSegmentImgName(),
                            "drawable",
                            getApplicationContext().getPackageName()), obj.getSupplierSegmentId(), 0));

        }

        IconData[] data = myList.toArray(new IconData[myList.size()]);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        IconAdapter adapter = new IconAdapter(data, this, null);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Depois preciso passar o seguimento para a outra activity
        //para mostrar apenas os fornecedores daquele seguimento...

        //Intent intent = new Intent(this, SuppliersActivity.class);
        //intent.putExtra("seguiment_id", current_supplier_segment_id);
        //startActivity(intent);


    }
    /*
    public void trocaActivity(int supplier_segment_id) {
        Intent intent = new Intent(this, SuppliersActivity.class);
        intent.putExtra("seguiment_id", supplier_segment_id);
        startActivity(intent);
    }
    */

}
