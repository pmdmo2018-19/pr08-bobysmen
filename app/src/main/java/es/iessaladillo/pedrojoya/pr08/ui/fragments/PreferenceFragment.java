package es.iessaladillo.pedrojoya.pr08.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceFragmentCompat;
import es.iessaladillo.pedrojoya.pr08.R;

public class PreferenceFragment extends PreferenceFragmentCompat {

//    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;

    public static PreferenceFragment newInstance(){return new PreferenceFragment();}

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar(requireView());
//        onSharedPreferenceChangeListener = (sharedPreferences, key) -> Toast.makeText(getContext(), "Han clickado en la preferencia", Toast.LENGTH_SHORT).show();
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.settings_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Settings");
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(
//                onSharedPreferenceChangeListener);
//    }
//
//    @Override
//    public void onPause() {
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
//                onSharedPreferenceChangeListener);
//        super.onPause();
//    }
}
