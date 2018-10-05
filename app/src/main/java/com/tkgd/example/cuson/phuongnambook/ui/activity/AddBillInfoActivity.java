package com.tkgd.example.cuson.phuongnambook.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tkgd.example.cuson.phuongnambook.R;
import com.tkgd.example.cuson.phuongnambook.adapter.InfoBillAdapter;
import com.tkgd.example.cuson.phuongnambook.model.Bill;
import com.tkgd.example.cuson.phuongnambook.model.Book;
import com.tkgd.example.cuson.phuongnambook.model.InfoBill;
import com.tkgd.example.cuson.phuongnambook.sqlitedao.BookDAO;
import com.tkgd.example.cuson.phuongnambook.sqlitedao.InfoBillDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddBillInfoActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    InfoBillDAO infoBillDAO;
    BookDAO sachDAO;
    public List<InfoBill> dsHDCT = new ArrayList<>();
    ListView lvCart;
    InfoBillAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_billinfo);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        edMaSach = (EditText) findViewById(R.id.edtIDBook);
        edMaHoaDon = (EditText) findViewById(R.id.edtIDType);
        edSoLuong = (EditText) findViewById(R.id.edtAmount);
        lvCart = (ListView) findViewById(R.id.lvBillInfo);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhtien);
        adapter = new InfoBillAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("IDBILL"));
        }
    }


    public void DoneInfoBillInfo(View view) {
        Toast.makeText(this, "Xong", Toast.LENGTH_SHORT).show();
    }

    public void addBillinfo(View view) {
        infoBillDAO = new InfoBillDAO(AddBillInfoActivity.this);
        sachDAO = new BookDAO(AddBillInfoActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Book sach = sachDAO.getBookByID(edMaSach.getText().toString());
                if (sach != null) {
                    int pos = checkMaSach(dsHDCT, edMaSach.getText().toString());
                    Bill hoaDon = new Bill(edMaHoaDon.getText().toString(), new Date());
                    InfoBill hoaDonChiTiet = new InfoBill(1, hoaDon, sach, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getAmountPay();
                        hoaDonChiTiet.setAmountPay(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }

    }

    public void thanhToanHoaDon(View view) {
        infoBillDAO = new InfoBillDAO(AddBillInfoActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (InfoBill hd : dsHDCT) {
                infoBillDAO.inserInfoBill(hd);
                thanhTien = thanhTien + hd.getAmountPay() *
                        hd.getBook().getPrice();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<InfoBill> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            InfoBill hd = lsHD.get(i);
            if (hd.getBook().getIdBook().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if
                (edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() ||
                edMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}
