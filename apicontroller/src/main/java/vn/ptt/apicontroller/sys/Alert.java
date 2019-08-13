package vn.ptt.apicontroller.sys;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import vn.ptt.apicontroller.R;

public class Alert {
    private static Alert instance = null;

    public static Alert getInstance() {
        if (instance == null) {
            instance = new Alert();
        }
        return instance;
    }

    public void show(Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            window.setAttributes(wlp);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.show();
        }

        TextView tvMessage = dialog.findViewById(R.id.api_content);
        TextView btnRetry =  dialog.findViewById(R.id.api_retry);
        TextView btnQuit =  dialog.findViewById(R.id.api_close);

        String content = "Hello";
        String close = "Close";
        String retry = "Retry";

        if (content != null) {
            tvMessage.setText(content);
        }

        if (close == null) {
            View space = dialog.findViewById(R.id.api_space);
            space.setVisibility(View.GONE);
            btnQuit.setVisibility(View.GONE);
        } else {
            btnQuit.setText(close);
        }

        if (retry == null) {
            btnRetry.setVisibility(View.GONE);
        } else {
            btnRetry.setText(retry);
        }

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //callbackApiFail.onPress(true);

            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //callbackApiFail.onPress(false);

            }
        });

    }
}
