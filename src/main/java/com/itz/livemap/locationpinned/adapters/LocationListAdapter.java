package com.itz.livemap.locationpinned.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.itz.livemap.locationpinned.MapActivity;
import com.itz.livemap.locationpinned.databinding.LocationItemBinding;
import com.itz.livemap.locationpinned.roomdb.LocationDao;
import com.itz.livemap.locationpinned.roomdb.LocationDatabase;
import com.itz.livemap.locationpinned.roomdb.LocationEntity;
import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {
    private List<LocationEntity> arrayList;
    private Context context;

    public LocationListAdapter(Context context, List<LocationEntity> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LocationItemBinding binding;

        public ViewHolder(LocationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LocationItemBinding binding = LocationItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @SuppressLint({"SuspiciousIndentation", "SetTextI18n"})
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        LocationDatabase database = LocationDatabase.getInstance(context);
       LocationDao locationDao = database.locationDao();

        LocationEntity model = arrayList.get(position);
        holder.binding.address.setText(model.getAddress());
        holder.binding.loti.setText( Double.toString(model.getLatitude()));
        holder.binding.longi.setText( Double.toString(model.getLongitude()));
        holder.binding.mainContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("latitude",model.getLatitude());
            intent.putExtra("longitude",model.getLongitude());
            intent.putExtra("address",model.getAddress());
            context.startActivity(intent);
        });
        holder.binding.deleteButton.setOnClickListener(v->{
            locationDao.deleteLocation(model);
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });
    }
}

