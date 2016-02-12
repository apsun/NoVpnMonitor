package com.crossbowffs.novpnmonitor;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Hook implements IXposedHookLoadPackage {
    private static final String TAG = "NoVpnMonitor";

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (!"com.android.systemui".equals(lpparam.packageName)) {
            return;
        }

        XposedHelpers.findAndHookMethod(
            "com.android.systemui.qs.QSFooter", lpparam.classLoader,
            "handleRefreshState", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.v(TAG, "In QSFooter#handleRefreshState()");
                    param.setResult(null);
                }
            });

        XposedHelpers.findAndHookConstructor(
            "com.android.systemui.qs.QSFooter", lpparam.classLoader,
            "com.android.systemui.qs.QSPanel", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.v(TAG, "In QSFooter#.ctor()");
                    Handler handler = (Handler)XposedHelpers.getObjectField(param.thisObject, "mMainHandler");
                    Runnable runnable = (Runnable)XposedHelpers.getObjectField(param.thisObject, "mUpdateDisplayState");
                    handler.post(runnable);
                }
            });

        Log.i(TAG, "NoVpnMonitor init successful!");
    }
}
