package vn.ptt.icaredoctor.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.ptt.icaredoctor.R;
import vn.ptt.myview.drawable.MyDrawable;

public class ForgotActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        initView();
    }

    private void initView() {

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackground(MyDrawable.makeGradientColor(getResources().getColor(R.color.colorMainLight), getResources().getColor(R.color.colorMain)));

        EditText edtPhone = findViewById(R.id.edtPhone);
        edtPhone.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));

        Button btn = findViewById(R.id.btnOk);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.white),
                MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.red)));
    }

    public void resetPass(View view){
        Toast.makeText(this, "Mật mã mới đã được gửi tới số điện thoại: 090909090", Toast.LENGTH_SHORT).show();
    }
}
