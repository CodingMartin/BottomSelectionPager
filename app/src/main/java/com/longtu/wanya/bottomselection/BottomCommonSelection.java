package com.longtu.wanya.bottomselection;

import android.os.Parcelable;

public interface BottomCommonSelection extends Parcelable {
    String getKey();

    int getIcon();

    String getTitle();

    String getSubTitle();
}
