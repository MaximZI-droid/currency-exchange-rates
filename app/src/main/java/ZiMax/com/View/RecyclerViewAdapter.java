package ZiMax.com.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ZiMax.com.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    private List<RecyclerViewItem> arrayList;

    public RecyclerViewAdapter(List<RecyclerViewItem> arrayList) {
        this.arrayList = arrayList;
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewInCard);
            textView1 = itemView.findViewById(R.id.textViewInCard1);
        }
    }

    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewViewHolder holder, int position) {

        RecyclerViewItem recyclerViewItem = arrayList.get(position);

        holder.imageView.setImageResource(recyclerViewItem.getCurrencyFlag());

        String str = recyclerViewItem.getCurrencyName() + " ( "+ recyclerViewItem.getCurrencyTicker() + ")"
                + "\nцена : " + recyclerViewItem.getCurrencyValue() + " RUB";
        holder.textView1.setText(str);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
