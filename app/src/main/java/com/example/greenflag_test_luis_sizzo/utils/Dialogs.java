package com.example.greenflag_test_luis_sizzo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import com.example.greenflag_test_luis_sizzo.R;
import com.example.greenflag_test_luis_sizzo.SplashActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Dialogs {

    public void showBottomSheetDialog(Context context) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.bsd_warning);

        Button cancel = bottomSheetDialog.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        Button btnYes = bottomSheetDialog.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(v -> {
            Intent intent = new Intent(context, SplashActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        bottomSheetDialog.show();
    }
}
