package com.kevin.binarystate;

import android.util.Log;

/**
 * @author Kevin  2022/2/23
 * <p>
 * 通过二进制原理实现状态管理
 * </p>
 */
public final class BinaryState {
    private static final String TAG = "BinaryState";

    private static final int STATUS_1 = 0x0001; // 1<<0  (2 的 0 次方)
    private static final int STATUS_2 = 0x0002; // 1<<1  (2 的 1 次方)
    private static final int STATUS_3 = 0x0004; // 1<<2  (2 的 2 次方)
    private static final int STATUS_4 = 0x0008; // 1<<3  (2 的 3 次方)
    private static final int STATUS_5 = 0x0010; // 1<<4  (2 的 4 次方)
    private static final int STATUS_6 = 0x0020; // 1<<5  (2 的 5 次方)
    private static final int STATUS_7 = 0x0040; // 1<<6  (2 的 6 次方)
    private static final int STATUS_8 = 0x0080; // 1<<7  (2 的 7 次方)

    private static final int MODE_A = STATUS_1 | STATUS_2 | STATUS_3;
    private static final int MODE_B = STATUS_1 | STATUS_4 | STATUS_5 | STATUS_6;
    private static final int MODE_C = STATUS_1 | STATUS_7 | STATUS_8;

    ///////////////////////////////////////////////////////////////////////////
    // 对其中的状态集进行如下操作(curStatuses指 --> STATUS_1 ~ STATUS_8 组合而成的)
    //
    // 一:添加状态 (或运算)
    // curStatuses | STATUS_1
    //
    // 二:移除状态 (取反与运算)
    // curStatuses & ~ STATUS_1
    //
    // 三:是否包含 (与运算 --> 结果为0表示无，反之有)
    // curStatuses & STATUS_1
    ///////////////////////////////////////////////////////////////////////////


    private int curStatuses;
    private String tag = TAG;

    public BinaryState() {
        curStatuses = MODE_A;
    }

    public static String getTAG() {
        return TAG;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 判断当前状态集合中是否包含某指定的状态
     *
     * @param status 指定状态
     * @return 是否包含
     */
    private boolean isContains(int status) {
        return (curStatuses & status) != 0;
    }

    public boolean isContains_1() {
        return isContains(STATUS_1);
    }

    public boolean isContains_2() {
        return isContains(STATUS_2);
    }

    public boolean isContains_3() {
        return isContains(STATUS_3);
    }

    public boolean isContains_4() {
        return isContains(STATUS_4);
    }

    public boolean isContains_5() {
        return isContains(STATUS_5);
    }

    public boolean isContains_6() {
        return isContains(STATUS_6);
    }

    public boolean isContains_7() {
        return isContains(STATUS_7);
    }

    public boolean isContains_8() {
        return isContains(STATUS_8);
    }

    /**
     * 设置当前状态集到指定模式A
     *
     * @return 当前类的引用
     */
    public BinaryState setModeA() {
        curStatuses = MODE_A;
        return this;
    }

    public BinaryState setModeB() {
        curStatuses = MODE_B;
        return this;
    }

    public BinaryState setModeC() {
        curStatuses = MODE_C;
        return this;
    }

    /**
     * 在当前状态集中添加状态
     *
     * @param status 需要添加的状态
     * @return 当前类的引用
     */
    private BinaryState addStatus(int status) {
        curStatuses |= status;
        return this;
    }

    public BinaryState addStatus_1() {
        return addStatus(STATUS_1);
    }

    public BinaryState addStatus_2() {
        return addStatus(STATUS_2);
    }

    public BinaryState addStatus_3() {
        return addStatus(STATUS_3);
    }

    public BinaryState addStatus_4() {
        return addStatus(STATUS_4);
    }

    public BinaryState addStatus_5() {
        return addStatus(STATUS_5);
    }

    public BinaryState addStatus_6() {
        return addStatus(STATUS_6);
    }

    public BinaryState addStatus_7() {
        return addStatus(STATUS_7);
    }

    public BinaryState addStatus_8() {
        return addStatus(STATUS_8);
    }

    /**
     * 移除当前集合中的某指定的状态
     *
     * @param status 需要移除的状态
     * @return 当前类的引用
     */
    private BinaryState removeStatus(int status) {
        curStatuses &= ~status;
        return this;
    }

    public BinaryState removeStatus_1() {
        return removeStatus(STATUS_1);
    }

    public BinaryState removeStatus_2() {
        return removeStatus(STATUS_2);
    }

    public BinaryState removeStatus_3() {
        return removeStatus(STATUS_3);
    }

    public BinaryState removeStatus_4() {
        return removeStatus(STATUS_4);
    }

    public BinaryState removeStatus_5() {
        return removeStatus(STATUS_5);
    }

    public BinaryState removeStatus_6() {
        return removeStatus(STATUS_6);
    }

    public BinaryState removeStatus_7() {
        return removeStatus(STATUS_7);
    }

    public BinaryState removeStatus_8() {
        return removeStatus(STATUS_8);
    }

    public BinaryState printBinaryStatus() {
        //MODE_A: 1、2、3
        //MODE_B: 1、4、5、6
        //MODE_C: 1、7、8
        Log.e(tag, "printBinaryStatus: ---------------");
        Log.e(tag, "printBinaryStatus: 包含状态 1 :" + isContains_1());
        Log.e(tag, "printBinaryStatus: 包含状态 2 :" + isContains_2());
        Log.e(tag, "printBinaryStatus: 包含状态 3 :" + isContains_3());
        Log.e(tag, "printBinaryStatus: 包含状态 4 :" + isContains_4());
        Log.e(tag, "printBinaryStatus: 包含状态 5 :" + isContains_5());
        Log.e(tag, "printBinaryStatus: 包含状态 6 :" + isContains_6());
        Log.e(tag, "printBinaryStatus: 包含状态 7 :" + isContains_7());
        Log.e(tag, "printBinaryStatus: 包含状态 8 :" + isContains_8());

        return this;
    }

}
