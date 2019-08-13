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
import vn.ptt.apicontroller.sys.Convert;
import vn.ptt.icaredoctor.R;
import vn.ptt.model.ResultModel;
import vn.ptt.model.acount.AccountDomain;
import vn.ptt.model.acount.UserDomain;
import vn.ptt.myview.dialog.MyDialog;
import vn.ptt.myview.drawable.MyDrawable;
import vn.ptt.utils.Storage;


public class LoginActivity extends BaseActivity {
    private EditText edtIdUser, edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackground(MyDrawable.makeGradientColor(getResources().getColor(R.color.colorMainLight), getResources().getColor(R.color.colorMain)));

        edtIdUser = findViewById(R.id.edtPhone);
        edtIdUser.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));

        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setBackground(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.transparent));


        Button btn = findViewById(R.id.btnOk);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.white),
                MyDrawable.makeGradient(this, R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.red)));
    }

    public void gotoRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void forgotPass(View view) {
        startActivity(new Intent(this, ForgotActivity.class));
        finish();
    }

    public void signIn(View view) {
        final String idUser = edtIdUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(idUser) || TextUtils.isEmpty(password)) {
            showMessage("Vui Lòng Nhập Username hoặc Mật Khẩu");
            return;
        }

        AccountDomain accountDomain = new AccountDomain();
        accountDomain.setIdUser(idUser);
        accountDomain.setPassword(password);

        ApiController.getInstance().signIn(this, accountDomain, new Callback() {
            @Override
            public void response(Object obj) {
                ResultModel resultModel = (ResultModel) obj;
                if (resultModel != null) {
                    if (resultModel.getId() == 1) {
                        Storage.getInstance(LoginActivity.this).setToken(resultModel.getOtherInfo());
                        Storage.getInstance(LoginActivity.this).setIdUser(idUser);
                        findUserById();
                    } else if (resultModel.getId() == 2) {
                        showMessage("Sai Username hoặc Mật Khẩu");
                    } else if (resultModel.getId() == 0) {
                        showMessage("Tài Khoản Chưa Kích Hoạt");
                    }
                }
            }
        });
    }

    private void findUserById() {
        ApiController.getInstance().findUserById(this, Storage.getInstance(this).getIdUser(), new Callback() {
            @Override
            public void response(Object obj) {
                if (obj != null) {
                    UserDomain userDomain = (UserDomain) obj;
                    Storage.getInstance(LoginActivity.this).setUserDomain(userDomain.toString());
                }
                gotoMain();
            }
        });
    }

    private void gotoMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
