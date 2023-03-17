package com.app.passwordmanager.ui.home.dashboard;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.passwordmanager.R;

public class DashboardViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView title;
    public ImageButton copy;


    public DashboardViewHolder(@NonNull View view) {
        super(view);

        root = view.findViewById(R.id.root);
        title = view.findViewById(R.id.title);
        copy = view.findViewById(R.id.copy);
    }
}
