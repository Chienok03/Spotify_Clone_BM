package com.example.spotifyapp.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private FirebaseDatabase database;

    public FirebaseHelper() {
        this.database = FirebaseDatabase.getInstance();
    }

    public void themMoiSinhVien(SinhVien sinhVien) {
        String id = database.getReference().child("SinhVien").push().getKey();
        database.getReference().child("SinhVien").child(id).setValue(sinhVien);
    }

    public List<SinhVien> hienDanhSachSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        database.getReference().child("SinhVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sinhVienList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SinhVien sinhVien = dataSnapshot.getValue(SinhVien.class);
                    sinhVienList.add(sinhVien);
                }
                // Use sinhVienList
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        return sinhVienList;
    }

    public void locDanhSachSinhVien() {
        database.getReference().child("SinhVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<SinhVien> sinhVienList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SinhVien sinhVien = dataSnapshot.getValue(SinhVien.class);
                    if (sinhVien.getDiemTb() > 7) {
                        sinhVienList.add(sinhVien);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public interface DataStatus {
        void DataIsLoaded(List<SinhVien> sinhViens, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

}