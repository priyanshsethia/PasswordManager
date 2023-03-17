package com.app.passwordmanager.ui.home.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.database.AppDatabase;
import com.app.passwordmanager.data.database.SecureElement;
import com.app.passwordmanager.utils.AppConstants;
import com.app.passwordmanager.utils.Utility;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment implements DashboardAdapter.Callbacks {

    Context context;
    AppDatabase appDatabase;
    DashboardAdapter adapter;

    EditText editSearch;
    ImageView cancelButton;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    List<SecureElement> passcodeList;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        appDatabase = AppDatabase.getInstance(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editSearch = view.findViewById(R.id.search_bar);
        cancelButton = view.findViewById(R.id.cancel_button);
        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);

        setUpSearch();
        setUpAddButton();
        setUpData();
        setUpAdapter();
    }

    private void setUpSearch() {

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cancelButton.setVisibility(s.toString().trim().isEmpty() ? View.GONE : View.VISIBLE);
                filter(s.toString().trim());
            }
        });

        cancelButton.setOnClickListener(v -> {
            Utility.hideKeyboard(getActivity());
            editSearch.setText("");
            editSearch.clearFocus();
        });

    }

    private void filter(String searchText) {
        List<SecureElement> temp = new ArrayList<>();
        for (SecureElement passcode : passcodeList) {
            if ((passcode.title != null && passcode.title.toLowerCase().contains(searchText.toLowerCase())) ||
                    (passcode.notes != null && passcode.notes.toLowerCase().contains(searchText.toLowerCase())))
                temp.add(passcode);
        }
        adapter.setData(temp);
    }

    private void setUpAddButton() {
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddPreviewScreenActivity.class);
            intent.putExtra("purpose", AppConstants.ADD_PASSCODE);
            startActivity(intent);
        });
    }

    private void setUpData() {
        appDatabase.secureElementDao().getAllPasscodes().observe(getViewLifecycleOwner(),
                passcodeList -> {
                    DashboardFragment.this.passcodeList = passcodeList;
                    adapter.setData(passcodeList);
                });
    }

    private void setUpAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DashboardAdapter();
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        adapter.setCallbacks(this);
    }

    @Override
    public void onClickItem(SecureElement element) {
        Intent intent = new Intent(context, AddPreviewScreenActivity.class);
        intent.putExtra("purpose", AppConstants.EDIT_PASSCODE);
        intent.putExtra("data", element);
        startActivity(intent);
    }

    @Override
    public void onCopyToClipboard(String passcode) {
        Utility.copyToClipboard(context, passcode);
    }
}