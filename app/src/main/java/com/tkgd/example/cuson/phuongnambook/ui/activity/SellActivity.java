package com.tkgd.example.cuson.phuongnambook.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tkgd.example.cuson.phuongnambook.R;

public class SellActivity extends AppCompatActivity {
    Toolbar tbSell;

    ListView lvSell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        tbSell = findViewById(R.id.toolbar);
        lvSell = findViewById(R.id.lvSell);

        setSupportActionBar(tbSell);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        tbSell.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tbSell.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//

            }
        });

    }

    public void addType(View view) {
        startActivity(new Intent(SellActivity.this,AddBookActivity.class));

    }

    public void fltsearch(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Nhập tháng");

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewDialog = inflater.inflate(R.layout.dialog_search, null);
        EditText search = viewDialog.findViewById(R.id.edtSearch);

        builder.setView(viewDialog);

        builder.setPositiveButton("Tìm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SellActivity.this, "Tim", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
