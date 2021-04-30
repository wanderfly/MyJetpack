package com.kevin.myjetpack;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author Kevin  2021/4/27
 */
public class Demo {
    public void setOnClick(@NonNull View.OnClickListener listener, Context context) {
        listener.onClick(new View(context));
        //context.sendBroadcast(new Intent(context,Demo.class));
    }
}
