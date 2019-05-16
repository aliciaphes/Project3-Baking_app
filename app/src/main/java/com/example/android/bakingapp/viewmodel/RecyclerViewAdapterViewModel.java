package com.example.android.bakingapp.viewmodel;

import com.example.android.bakingapp.adapters.GenericAdapter;

public abstract class RecyclerViewAdapterViewModel {

    public int adapterPosition = -1;
    public GenericAdapter.OnListItemViewClickListener onListItemViewClickListener;
}
