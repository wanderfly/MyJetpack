package com.kevin.retrofit.sample3.bean;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kevin  2022/2/10
 */
@IntDef({
        FxRepCode.SUCCESS,
        FxRepCode.FAILED,
        FxRepCode.ERROR,
        FxRepCode.EMPTY
})
@Retention(RetentionPolicy.SOURCE)
public @interface FxRepCode {
    int SUCCESS = 0;
    int FAILED = 1;
    int ERROR = 2;
    int EMPTY = 3;
}
