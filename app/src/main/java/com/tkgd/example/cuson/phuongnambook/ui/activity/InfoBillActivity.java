package com.tkgd.example.cuson.phuongnambook.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tkgd.example.cuson.phuongnambook.R;
import com.tkgd.example.cuson.phuongnambook.adapter.InfoBillAdapter;
import com.tkgd.example.cuson.phuongnambook.model.InfoBill;
import com.tkgd.example.cuson.phuongnambook.model.User;
import com.tkgd.example.cuson.phuongnambook.sqlitedao.InfoBillDAO;
import com.tkgd.example.cuson.phuongnambook.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class InfoBillActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView lvifBill;
    public List<InfoBill> infoBills = new ArrayList<>();
    InfoBillAdapter adapter = null;
    InfoBillDAO infoBillDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        infoBillDAO = new InfoBillDAO(InfoBillActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            infoBills = infoBillDAO.getAllInfoBillByID(b.getString("IDBILL"));
            adapter = new InfoBillAdapter(this, infoBills);
            lvifBill.setAdapter(adapter);
        }

    }

    private void initView() {
        setContentView(R.layout.activity_info_bill);
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


    }


}
