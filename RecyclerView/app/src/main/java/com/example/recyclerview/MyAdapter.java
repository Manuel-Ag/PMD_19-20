package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Ejemplo a seguir: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] mDataSet;
    private ItemClickListener mClickListener;

    /**
     * Constructor al que le pasamos como parámetro los datos
     * @param mDataSet
     */
    public MyAdapter(String[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    /**
     * El layout manager llama a este método
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /**
     * Este método reemplaza en contenido de las vistas
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    // convenience method for getting data at click position
    public String getItem(int pos) {
        return mDataSet[pos];
    }

    /**
     * Aquí utilizamos toas las referencias a las vistas a mostrar en cada fila
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public MyViewHolder(View v) {
            super(v);
            // Debemos tener acceso a las vistas a través de la vista que nos llega (LinearLayout)
            textView = v.findViewById(R.id.textView);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
