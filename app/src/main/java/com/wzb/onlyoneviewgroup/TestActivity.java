package com.wzb.onlyoneviewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wzb on 2018/4/19.
 */

public class TestActivity extends AppCompatActivity {

    private OnlyOneViewGroup mOnlyOneViewGroup;
    private TextView tvLayout2;
    private final int [] layouts={R.layout.layout_test_view_1,R.layout.layout_test_view_2,R.layout.layout_test_view_3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mOnlyOneViewGroup=this.findViewById(R.id.test_only_one_view_group);
        mOnlyOneViewGroup.addLayoutRes(layouts);
        mOnlyOneViewGroup.setAddToBackTask(true);

        tvLayout2=mOnlyOneViewGroup.findViewById(R.id.test_layout2_tv);
        tvLayout2.setText("Activity setText");

        findViewById(R.id.test_show_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnlyOneViewGroup.showNextCirculation();
            }
        });
        findViewById(R.id.test_show_index_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnlyOneViewGroup.showIndexView(5);
            }
        });


    }
}
