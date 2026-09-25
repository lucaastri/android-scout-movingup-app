package com.example.passaggicompany2k25.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.passaggicompany2k25.R;
import com.example.passaggicompany2k25.bean.Letter;
import java.util.List;

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Letter> data;
    private OnItemButtonClickListener listener;

    public LetterAdapter(Context context, List<Letter> data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemButtonClickListener {
        void onButtonClick(int position);
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(context);
        }
        View view = inflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letter bean = data.get(position);

        if (position == 0) { // se è il primo è sempre visibile
            bean.setVisible(true);
        }

        if (bean.isVisible()) {
            holder.itemView.setVisibility(View.VISIBLE); // mostro
            holder.txtTitle.setText(bean.getTitle());
            int pos = position + 1;
            holder.txtLetter.setText("Lettera " + (pos-1));
            holder.btnPress.setBackgroundColor(Color.RED);
            holder.btnPress.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onButtonClick(position);
                }
            });
        } else {
            // qui nascondo/resetto la view per evitare riuso sporco
            holder.itemView.setVisibility(View.GONE);
            holder.txtTitle.setText("");
            holder.txtLetter.setText("");
            holder.btnPress.setOnClickListener(null);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtLetter,txtTitle;
        public Button btnPress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLetter = itemView.findViewById(R.id.txtLetter);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            btnPress = itemView.findViewById(R.id.btnPress);
        }

    }
}
