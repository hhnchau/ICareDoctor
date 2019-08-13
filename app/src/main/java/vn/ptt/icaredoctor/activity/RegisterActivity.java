package vn.ptt.icaredoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.ptt.apicontroller.ApiController;
import vn.ptt.apicontroller.sys.Callback;
import vn.ptt.icaredoctor.R;
import vn.ptt.model.ResultModel;
import vn.ptt.model.acount.AccountDomain;
import vn.ptt.myview.drawable.MyDrawable;

public class RegisterActivity extends BaseActivity {
    private EditText edtIdUser, edtNickname, edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackground(MyDrawable.makeGradientColor(getResources().getColor(R.color.colorMainLight), getResources().getColor(R.color.colorMain)));

        edtNickname = findViewById(R.id.edtNickname);
        edtNickname.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));

        edtIdUser = findViewById(R.id.edtUserName);
        edtIdUser.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));

        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));


        Button btn = findViewById(R.id.btnOk);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.white),
                MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.red)));
    }

    public void gotoLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void signUp(View view) {
        String nickname = edtNickname.getText().toString().trim();
        final String idUser = edtIdUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(idUser) || TextUtils.isEmpty(password) || TextUtils.isEmpty(nickname)) {
            showMessage("Vui Lòng Nhập Username, Mật Khẩu hoặc Nickname");
            return;
        }

        AccountDomain accountDomain = new AccountDomain();
        accountDomain.setIdUser(idUser);
        accountDomain.setPassword(password);
        accountDomain.setNickName(nickname);

        ApiController.getInstance().signUp(this, accountDomain, new Callback() {
            @Override
            public void response(Object obj) {
                ResultModel resultModel = (ResultModel) obj;
                if (resultModel != null) {
                    if (resultModel.getId() == 1) {
                        setActiveAccount(idUser);
                    } else {
                        showMessage("Đăng Ký Không Thành Công");
                    }
                }
            }
        });
    }

    private void setActiveAccount(String idUser) {
        AccountDomain accountDomain = new AccountDomain();
        accountDomain.setActive(1);
        accountDomain.setIdUser(idUser);
        ApiController.getInstance().setActiveAccount(this, accountDomain, new Callback() {
            @Override
            public void response(Object obj) {
                gotoLogin();
            }
        });
    }

    private void gotoLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
