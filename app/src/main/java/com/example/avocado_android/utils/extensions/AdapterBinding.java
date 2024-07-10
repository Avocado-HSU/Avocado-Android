package com.example.avocado_android.utils.extensions;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avocado_android.domain.model.HomeDaysItem;
import com.example.avocado_android.domain.model.RecentWordItem;
import com.example.avocado_android.ui.home.CheckDaysAdapter;
import com.example.avocado_android.ui.search.RecentWordAdapter;

import java.util.List;

public class AdapterBinding {

    @BindingAdapter("app:listData")
    public static void bindRecyclerView(RecyclerView recyclerView, LiveData<List<HomeDaysItem>> data) {
        CheckDaysAdapter adapter = (CheckDaysAdapter) recyclerView.getAdapter();
        if (adapter != null && data != null) {
            adapter.submitList(data.getValue());
        }
    }

    @BindingAdapter("app:items")
    public static void bindSearchRecyclerView(RecyclerView recyclerView, LiveData<List<RecentWordItem>> data) {
        RecentWordAdapter adapter = (RecentWordAdapter) recyclerView.getAdapter();
        if (adapter != null && data != null) {
            adapter.submitList(data.getValue());
        }
    }
}
