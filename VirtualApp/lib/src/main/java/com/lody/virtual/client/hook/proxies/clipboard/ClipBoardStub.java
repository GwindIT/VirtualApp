package com.lody.virtual.client.hook.proxies.clipboard;

import android.content.Context;
import android.os.Build;
import android.os.IInterface;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.helper.compat.BuildCompat;

import mirror.android.content.ClipboardManager;
import mirror.android.content.ClipboardManagerOreo;

/**
 * @author Lody
 * @see ClipboardManager
 */
public class ClipBoardStub extends BinderInvocationProxy {
    public static int ii;
    public ClipBoardStub() {
        super(getInterface(), Context.CLIPBOARD_SERVICE);
    }
    public ClipBoardStub(int i) {
        super(getInterface(i), Context.CLIPBOARD_SERVICE);
        //Log.d("ClipBoardStub", "ClipBoardStub:已启动 ");
    }
    private static IInterface getInterface(int i) {
        ii=i;
        //Log.d("ClipBoardStub", "getInterface:已启动 ");
        if (BuildCompat.isOreo()) {
            android.content.ClipboardManager cm = (android.content.ClipboardManager)
                    VirtualCore.get().getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            return ClipboardManagerOreo.mService.get(cm);
        } else {
            return ClipboardManager.getService.call();
        }
    }
    private static IInterface getInterface() {
        //Log.d("ClipBoardStub", "getInterface:已启动 ");
        if (BuildCompat.isOreo()) {
            android.content.ClipboardManager cm = (android.content.ClipboardManager)
                    VirtualCore.get().getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            return ClipboardManagerOreo.mService.get(cm);
        } else {
            return ClipboardManager.getService.call();
        }
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        if (ii==1){
            //Log.d("onBindMethods", "真");
            addMethodProxy(new SecGetPrimaryClip("getPrimaryClip"));
        }else {
            //Log.d("onBindMethods", "假");
            addMethodProxy(new ReplaceLastPkgMethodProxy("getPrimaryClip"));
        }
        //addMethodProxy(new ReplaceLastPkgMethodProxy("getPrimaryClip"));
        //addMethodProxy(new SecGetPrimaryClip("getPrimaryClip"));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (ii==1){
                addMethodProxy(new SecSetPrimaryClip("setPrimaryClip"));
            }else {
                addMethodProxy(new ReplaceLastPkgMethodProxy("setPrimaryClip"));
            }
            //addMethodProxy(new ReplaceLastPkgMethodProxy("setPrimaryClip"));
            //addMethodProxy(new SecSetPrimaryClip("setPrimaryClip"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("getPrimaryClipDescription"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("hasPrimaryClip"));
            //addMethodProxy(new SecHasPrimaryClip("hasPrimaryClip"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("addPrimaryClipChangedListener"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("removePrimaryClipChangedListener"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("hasClipboardText"));
        }
    }

    @Override
    public void inject() throws Throwable {
        super.inject();
        //Log.d("ClipBoardStub", "inject:已启动 ");
        if (BuildCompat.isOreo()) {
            android.content.ClipboardManager cm = (android.content.ClipboardManager)
                    VirtualCore.get().getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipboardManagerOreo.mService.set(cm, getInvocationStub().getProxyInterface());
        } else {
            ClipboardManager.sService.set(getInvocationStub().getProxyInterface());
        }
    }
}
