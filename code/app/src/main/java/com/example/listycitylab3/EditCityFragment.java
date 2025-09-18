package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    private EditCityDialogListener listener;
    private City city;

    interface EditCityDialogListener {
        void editCity(City city, int position);
    }

    public EditCityFragment() {
        super();
    }

    public EditCityFragment(City city) {
        this.city = city;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityFragment.AddCityDialogListener) {
            listener = (EditCityFragment.EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_edit_city_text);
        editCityName.setText(city.getName());
        EditText editProvinceName = view.findViewById(R.id.edit_text_edit_province_text);
        editProvinceName.setText(city.getProvince());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.editCity(new City(cityName, provinceName), which);
                })
                .create();
    }
}
