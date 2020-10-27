package com.a.ridho_crud_firebase;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InputForm {
    String nim, nama, jurusan, semester;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public InputForm(String nim, String nama, String jurusan, String semester) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.semester = semester;
    }

    public InputForm()
    {

    }

    TextView enim;
    TextView enama;
    TextView ejurusan;
    TextView esemester;

    Button btnsimpan;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
//        boolean attachToRoot;
        final View view = inflater.inflate(R.layout.form_input,container, false);

        enim = view.findViewById(R.id.enim);
        enama = view.findViewById(R.id.enama);
        ejurusan = view.findViewById(R.id.ejurusan);
        esemester = view.findViewById(R.id.esemester);

//        enim.setVisibility( );
//        set 8:03

        enim.setText(nim);
        enama.setText(nama);
        ejurusan.setText(jurusan);
        esemester.setText(semester);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
//            private Object EditText;

            @Override
            public void onClick(View v) {
                String nama = enama.getText().toString();
                String nim = enim.getText().toString();
                String jurusan = ejurusan.getText().toString();
                String semester = esemester.getText().toString();

                if(TextUtils.isEmpty(nim)) {
                    input((EditText) enim, "nim");

                } else if(TextUtils.isEmpty(nama)) {
                    input((EditText) enama, "nama");

                } else if(TextUtils.isEmpty(jurusan)) {
                    input((EditText) ejurusan, "Jurusan");

                } else if(TextUtils.isEmpty(semester)) {
                    input((EditText) esemester,  "Semester");

                } else {
                    database.child("Data1").push().setValue(new Mahasiswa(nim, nama, jurusan, semester)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "Berhasil",  Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Gagal",  Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

//    menit 24:42

    protected void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

//    private Dialog getDialog() {
//        return null;
//    }

//    private Dialog getDialog() {
//    }

    private void input(EditText txt, String s) {
        txt.setError(s+"Harus Di Isi");
        txt.requestFocus();
    }


//    public Dialog getDialog() {
//        return dialog;
//    }
//
//    public void setDialog(Dialog dialog) {
//        this.dialog = dialog;
//    }
}
