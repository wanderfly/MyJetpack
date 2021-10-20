package com.kevin.myjetpack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function1;

/**
 * @author Kevin  2021/4/27
 */
public class Demo {
    private static final String TAG = "Demo";

    public void setOnClick(@NonNull View.OnClickListener listener, Context context) {
        listener.onClick(new View(context));
        //context.sendBroadcast(new Intent(context,Demo.class));
    }

    public <T extends TextView> T getView(Context context) {
        return null;
    }

    public <TK> TK getView(TK nice) {
        return nice;
    }

    public <A> A getPerson(A name) {
        return name;
    }

    public static void is() {
        Log.e(TAG, "is为kotlin中的保留关键字，在kotlin中调用时，方法名要使用反引号，以避免冲突");
    }
}
