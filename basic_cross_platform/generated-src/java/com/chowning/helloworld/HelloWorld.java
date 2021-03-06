// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from helloworld.djinni

package com.chowning.helloworld;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class HelloWorld {
    /** function taht returns a string */
    public abstract String getHelloWorld();

    /** return an instance of our C++ object with the methods we outline in this IDL file */
    public static native HelloWorld create();

    private static final class CppProxy extends HelloWorld
    {
        private final long nativeRef;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);

        private CppProxy(long nativeRef)
        {
            if (nativeRef == 0) throw new RuntimeException("nativeRef is zero");
            this.nativeRef = nativeRef;
        }

        private native void nativeDestroy(long nativeRef);
        public void destroy()
        {
            boolean destroyed = this.destroyed.getAndSet(true);
            if (!destroyed) nativeDestroy(this.nativeRef);
        }
        protected void finalize() throws java.lang.Throwable
        {
            destroy();
            super.finalize();
        }

        @Override
        public String getHelloWorld()
        {
            assert !this.destroyed.get() : "trying to use a destroyed object";
            return native_getHelloWorld(this.nativeRef);
        }
        private native String native_getHelloWorld(long _nativeRef);
    }
}
