package es.iessaladillo.pedrojoya.pr08.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.utils.FragmentUtils;

public class MainFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private FloatingActionButton fabDetails;
    private SharedPreferences settings;
    private TextView textLorem;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textLorem = ViewCompat.requireViewById(getView(), R.id.textLorem);
        setupFab(requireView());
        setupToolbar(requireView());
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        showLoremText();
    }

    @Override
    public void onResume() {
        super.onResume();
        settings.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_main, menu);
    }


    private void setupFab(View view) {
        fabDetails = ViewCompat.requireViewById(view, R.id.details_fab);

        fabDetails.setOnClickListener(v -> navigateDetails());
    }


    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.inflateMenu(R.menu.fragment_main);
        toolbar.setTitle(getString(R.string.main_fragment_titleToolbar));
        toolbar.setOnMenuItemClickListener(item ->{
            if(item.getItemId() == R.id.mnuSettings){
                navigateSettings();
                return true;
            }else{
                return false;
            }
        });
    }

    private void navigateDetails() {
        FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(), R.id.flContent,
                DetailsFragment.newInstance(), DetailsFragment.class.getSimpleName(), DetailsFragment.class.getSimpleName(),
                FragmentTransaction.TRANSIT_NONE);
    }

    private void navigateSettings() {
        FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(), R.id.flContent,
                PreferenceFragment.newInstance(), PreferenceFragment.class.getSimpleName(),
                PreferenceFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_NONE);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, getString(R.string.pref_listKey))) { showLoremText(); }
    }

    private void showLoremText() {
        textLorem.setText(TextUtils.equals(settings.getString(getString(R.string.pref_listKey), getString(R.string.pref_listDefaultValue)), "latin") ? getString(R.string.main_latin_ipsum) : getString(R.string.main_chiquito_ipsum));
    }
}
