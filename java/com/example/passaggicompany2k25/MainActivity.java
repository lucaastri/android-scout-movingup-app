package com.example.passaggicompany2k25;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passaggicompany2k25.adapter.LetterAdapter;
import com.example.passaggicompany2k25.bean.Letter;
import com.example.passaggicompany2k25.business.LogicClass;
import com.example.passaggicompany2k25.commons.Comms;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Letter> list;
    private LetterAdapter adapter;
    private RecyclerView recView;
    private LogicClass business;
    private RelativeLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        }

        recView = findViewById(R.id.recView);
        layout = findViewById(R.id.main);

        business = new LogicClass(this);
        list = business.load();
        adapter = new LetterAdapter(this,list);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
        layout.setBackgroundColor(Color.WHITE);

        adapter.setOnItemButtonClickListener(new LetterAdapter.OnItemButtonClickListener() {
            @Override
            public void onButtonClick(int position) {
                // Qui mostri il dialog
                Letter clicked = list.get(position);
                ShowDialogue(clicked);
            }
        });

    }

    public void ShowDialogue(Letter data) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogue);
        dialog.show();

        TextView txtTitle, txtText;
        EditText edTxtPassword;
        Button btnClose;

        txtTitle = dialog.findViewById(R.id.txtMain);
        txtText = dialog.findViewById(R.id.txtText);
        edTxtPassword = dialog.findViewById(R.id.edTxtAnswer);
        btnClose = dialog.findViewById(R.id.btnClose);

        txtTitle.setText(data.getTitle());
        txtText.setText(data.getText());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                String input = edTxtPassword.getText().toString();
                if (input.isEmpty()) {
                    btnClose.setText("Non è stata inserita una password!");
                    btnClose.setBackgroundColor(Color.RED);
                } else {
                    for (int i = 0; i < Comms.LETTERCOUNT; i++) {
                        if (i+1 < Comms.LETTERCOUNT) {
                            Letter next = list.get(i+1);
                            System.out.println(input);
                            System.out.println(next.getPassword());
                            if (next.check(input)) {
                                next.setVisible(true);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            } else {
                                btnClose.setText("La password è sbagliata!");
                                btnClose.setBackgroundColor(Color.RED);
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    btnClose.setText("Verifica e Chiudi");
                                    btnClose.setBackgroundColor(Color.argb(103,80,164,255));
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}