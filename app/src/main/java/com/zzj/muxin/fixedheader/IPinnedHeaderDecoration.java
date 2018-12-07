package com.zzj.muxin.fixedheader;

import android.graphics.Rect;

/**
 *@author zzj
 * @desc 星际联系人悬浮分组的装饰接口
 */
public interface IPinnedHeaderDecoration {

	Rect getPinnedHeaderRect();

	int getPinnedHeaderPosition();

}
