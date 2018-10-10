package com.tkgd.example.cuson.phuongnambook.ui.activity;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tkgd.example.cuson.phuongnambook.R;
import com.tkgd.example.cuson.phuongnambook.adapter.BillAdapter;
import com.tkgd.example.cuson.phuongnambook.model.Bill;
import com.tkgd.example.cuson.phuongnambook.model.InfoBill;
import com.tkgd.example.cuson.phuongnambook.sqlitedao.BillDAO;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity {
    Toolbar tbBill;

    ListView lvBill;
    public List<Bill> billList = new ArrayList<>();

    BillAdapter adapter = null;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        tbBill = findViewById(R.id.toolbar);
        lvBill = findViewById(R.id.lvBill);

        setSupportActionBar(tbBill);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        tbBill.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tbBill.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//

            }
        });

        billDAO = new BillDAO(BillActivity.this);
        try {
            billList = billDAO.getAllBill();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new BillAdapter(this, billList);
        lvBill.setAdapter(adapter);
        lvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill hoaDon = (Bill) parent.getItemAtPosition(position);
                Intent intent = new Intent(BillActivity.this, InfoBillActivity.class);
                Bundle b = new Bundle();
                b.putString("IDBILL", hoaDon.getIdBill());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        // TextFilter
        lvBill.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edtSeBill);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if(count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    public void addBill(View view) {
        startActivity(new Intent(BillActivity.this, AddBillActivity.class));
    }
}
