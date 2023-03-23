#pragma once

#ifndef MOONA_JVM_IMPL
    #define MOONA_JVM_IMPL

    #include <jni.h>
    #include <windows.h>
    #include <tchar.h>

    namespace moona {

        typedef int (__jvmbuilder)(JavaVM**, void**, void*);
        typedef int (__jvmfinder)(JavaVM**, jint, jint*);

        class JVM {
            private:
                JavaVM* jvm;
                JNIEnv* env;

                typedef int (__jvmbuilder)(JavaVM**, void**, void*);
                typedef int (__jvmfinder)(JavaVM**, jint, jint*);

                inline static __jvmbuilder* jvmbuilder;
                inline static __jvmfinder* jvmfinder;

                static void loadJVMLibraries();

            public:
                JVM();
                ~JVM();

                bool isBuilt() const;

                void buildJVM();

                void destroyJVM();

            friend class Moona;
        };
    }

#endif