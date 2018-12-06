package mirror.android.content.pm;

import android.os.IBinder;
import android.os.IInterface;

import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefStaticMethod;

/**
 * @author Lody
 */

public class IShortcutService {
    /**
     * 原方法
     */
/*    public static final class Stub {
        public static Class<?> TYPE = RefClass.load(IShortcutService.class, "android.content.pm.IShortcutService$Stub");
        public static RefStaticMethod<IInterface> asInterface;
    }*/

    public static Class<?> TYPE = RefClass.load(IShortcutService.class, "android.content.pm.IShortcutService");

    /**
     * 仿照其他生产的方法
     */
    public static class Stub {
        public static Class<?> TYPE = RefClass.load(IShortcutService.Stub.class, "android.content.pm.IShortcutService$Stub");
        @MethodParams(IBinder.class)
        public static RefStaticMethod<IInterface> asInterface;
    }
}
