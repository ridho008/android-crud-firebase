package com.a.ridho_crud_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab_add;
    Data_Item data_item;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<Mahasiswa> listMahasiswa;
    RecyclerView rcview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_add = findViewById(R.id.tbadd);
        rcview = findViewById(R.id.rcview);

        RecyclerView.LayoutManager mLayout  = new LinearLayoutManager(this);
        rcview.setLayoutManager(mLayout);
        rcview.setItemAnimator(new DefaultItemAnimator());

        fab_add.setOnClickListener((v) ->  {
            InputForm dialogForm = new InputForm();
            dialogForm.show(getSupportFragmentManager(), "form");
        });
        tampilkanData();
    }

    private void tampilkanData() {
        database.child("Data1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMahasiswa = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    Mahasiswa mhs = item.getValue(Mahasiswa.class);
                    mhs.setKey((item.getKey()));
                    listMahasiswa.add(mhs);
                }

                data_item = new Data_Item(listMahasiswa);
                rcview.setAdapter(data_item);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}