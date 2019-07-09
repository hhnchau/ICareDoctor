package vn.ptt.icaredoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.ptt.icaredoctor.R;
import vn.ptt.myview.drawable.MyDrawable;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackground(MyDrawable.makeGradientColor(getResources().getColor(R.color.colorMainLight), getResources().getColor(R.color.colorMain)));

        EditText edtPhone = findViewById(R.id.edtPhone);
        edtPhone.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));

        EditText edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));


        Button btn = findViewById(R.id.btnOk);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.white),
                MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.red)));
    }

    public void gotoRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
