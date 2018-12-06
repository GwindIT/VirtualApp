package com.lody.virtual.client.hook.proxies.shortcut;

import android.util.Log;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;

import java.lang.reflect.Method;

import mirror.android.content.pm.IShortcutService;

import static android.content.Context.SHORTCUT_SERVICE;

/**
 * Author: linqiang
 * Date: 12/6/18
 * Email: qiang.lin@singuloid.com
 * LastUpdateTime: 12/6/18
 * LastUpdateBy: linqiang
 *
 * 为了代理掉8.0上的快捷方式新特性
 */

public class ShortcutManagerStub extends BinderInvocationProxy {

    private static final String TAG = "ShortcutManagerStub";

    private static final String SHORTCUT_MANAGER_NAME = SHORTCUT_SERVICE;
    public ShortcutManagerStub() {
        super(IShortcutService.Stub.asInterface, SHORTCUT_MANAGER_NAME);
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new MethodProxy() {
            @Override
            public String getMethodName() {
                return "setDynamicShortcuts";
            }

            @Override
            public Object call(Object who, Method method, Object... args) throws Throwable {
//                return super.call(who, method, args);
                Log.e(TAG, "call: setDynamicShortcuts");
                return false;
            }
        });
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getManifestShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getDynamicShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("addDynamicShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("createShortcutResultIntent"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("disableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("enableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRemainingCallCount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRateLimitResetTime"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getIconMaxDimensions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getMaxShortcutCountPerActivity"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("reportShortcutUsed"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("onApplicationActive"));
    }
}
