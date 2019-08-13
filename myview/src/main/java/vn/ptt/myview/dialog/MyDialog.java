package vn.ptt.myview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.ptt.myview.R;

public class MyDialog {
    public static class Builder {
        private Context context;
        private LinearLayout boxTitle, boxMessage, boxButton;
        private String title, message, noButton, yesButton;
        private View.OnClickListener noListener, yesListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setNoButton(String noButton, View.OnClickListener noListener) {
            this.noButton = noButton;
            this.noListener = noListener;
            return this;
        }

        public Builder setYesButton(String yesButton, View.OnClickListener yesListener) {
            this.yesButton = yesButton;
            this.yesListener = yesListener;
            return this;
        }

        public void build() {
            final Dialog dialog = new Dialog(context, R.style.MyDialog);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog);
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();
            }
            boxTitle = dialog.findViewById(R.id.boxTitle);
            boxTitle.setVisibility(View.GONE);

            final EditText edt = dialog.findViewById(R.id.edt);
            TextView tvNo = dialog.findViewById(R.id.tvNo);
            tvNo.setText(noButton);
            TextView tvYes = dialog.findViewById(R.id.tvYes);
            tvYes.setText(yesButton);

            tvNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noListener != null) noListener.onClick(v);
                    dialog.dismiss();
                }
            });

            tvYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (yesListener != null) yesListener.onClick(edt);
                    dialog.dismiss();
                }
            });

        }
    }
}
