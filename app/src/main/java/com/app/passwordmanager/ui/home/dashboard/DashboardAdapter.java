package com.app.passwordmanager.ui.home.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.database.SecureElement;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    Callbacks callbacks;
    List<SecureElement> passcodeList = new ArrayList<>();

    public void setData(List<SecureElement> passcodeList) {
        this.passcodeList = passcodeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.passcode_item_view, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        SecureElement item = passcodeList.get(position);

        holder.title.setText(item.title);
        holder.copy.setOnClickListener(v -> {
            callbacks.onCopyToClipboard(item.password);
        });
        holder.root.setOnClickListener(v -> {
            callbacks.onClickItem(item);
        });

    }

    @Override
    public int getItemCount() {
        return passcodeList.size();
    }

    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    public interface Callbacks {

        void onClickItem(SecureElement element);

        void onCopyToClipboard(String passcode);
    }
}
