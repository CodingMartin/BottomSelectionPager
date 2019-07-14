package com.longtu.wanya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.longtu.wanya.bottomselection.BottomCommonSelection;
import com.longtu.wanya.bottomselection.BottomCommonSelectionDialog;
import com.longtu.wanya.bottomselection.OnBottomCommonSelectionItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBottomCommonSelectionItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onHelloClick(View view) {
        List<TestBean> list = Arrays.asList(
                new TestBean("111", "hhh"),
                new TestBean("222", "hhh"),
                new TestBean("333", "hhh"),
                new TestBean("444", "hhh"),
                new TestBean("555", "hhh"),
                new TestBean("666", "hhh"),
                new TestBean("777", "hhh"),
                new TestBean("888", "hhh"),
                new TestBean("999", "hhh"),
                new TestBean("a", "hhh"),
                new TestBean("b", "hhh"),
                new TestBean("c", "hhh"),
                new TestBean("d", "hhh"),
                new TestBean("e", "hhh"),
                new TestBean("f", "hhh"),
                new TestBean("g", "hhh"),
                new TestBean("h", "hhh"),
                new TestBean("i", "hhh"),
                new TestBean("j", "hhh"),
                new TestBean("k", "hhh"),
                new TestBean("l", "hhh"),
                new TestBean("m", "hhh"),
                new TestBean("n", "hhh"),
                new TestBean("o", "hhh"),
                new TestBean("p", "hhh"),
                new TestBean("q", "hhh"),
                new TestBean("r", "hhh"),
                new TestBean("s", "hhh"),
                new TestBean("t", "hhh"),
                new TestBean("u", "hhh"),
                new TestBean("v", "hhh"),
                new TestBean("w", "hhh"),
                new TestBean("x", "hhh"),
                new TestBean("y", "hhh"),
                new TestBean("z", "hhh")
        );
        ArrayList<TestBean> tt = new ArrayList<>(list);


        BottomCommonSelectionDialog.newInstance()
                .setColumn(4).setRow(3).setDataSet(tt)
                .setHorizontalSpacing(30)
                .setVerticalSpacing(20)
                .setGridHeight(400)
                .setTitle("音效")
                .setItemLayout(R.layout.item_test_bottom_selection).show(getSupportFragmentManager(), "hhhh");

    }

    @Override
    public void onItemClick(View view, BottomCommonSelection item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
