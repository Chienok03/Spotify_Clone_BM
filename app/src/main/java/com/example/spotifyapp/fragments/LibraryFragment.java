package com.example.spotifyapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotifyapp.R;
import com.example.spotifyapp.adapters.SinhVienAdapter;
import com.example.spotifyapp.databinding.FragmentLibraryBinding;
import com.example.spotifyapp.models.FirebaseHelper;
import com.example.spotifyapp.models.SinhVien;

import java.util.List;


public class LibraryFragment extends Fragment {

    private FragmentLibraryBinding binding;
    private FirebaseHelper firebaseHelper;
    private SinhVienAdapter sinhVienAdapter;


    public LibraryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        firebaseHelper = new FirebaseHelper();
        binding.btnAdd.setOnClickListener(v -> {
            String maSv = binding.etMaSv.getText().toString();
            String hoTen = binding.etHoTen.getText().toString();
            String diaChi = binding.etDiaChi.getText().toString();
            String ngaySinh = binding.etNgaySinh.getText().toString();
            String gioiTinh = binding.etGioiTinh.getText().toString();
            String email = binding.etEmail.getText().toString();
            float diemTb = Float.parseFloat(binding.etDtb.getText().toString());

            SinhVien sinhVien = new SinhVien(maSv, hoTen, diaChi, ngaySinh, gioiTinh, email, diemTb);
            firebaseHelper.themMoiSinhVien(sinhVien);
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        sinhVienAdapter = new SinhVienAdapter(firebaseHelper.hienDanhSachSinhVien());
        binding.recyclerView.setAdapter(sinhVienAdapter);
        return binding.getRoot();
    }
}