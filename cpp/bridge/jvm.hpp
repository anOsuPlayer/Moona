#pragma once

#ifndef MOONA_JVM_IMPL
    #define MOONA_JVM_IMPL

    #include <jni.h>
    #include <windows.h>
    #include <tchar.h>

    namespace moona {

        class JVM {
            private:
                JavaVM* jvm;
                JNIEnv* env;

                static inline HMODULE dll;

                typedef int (_jvmbuilder)(JavaVM**, void**, void*);
                typedef int (_jvmfinder)(JavaVM**, jint, jint*);

                inline static _jvmbuilder* jvmbuilder;
                inline static _jvmfinder* jvmfinder;

                static void loadJVMLibraries();

            public:
                JVM();
                JVM(JNIEnv* env);
                ~JVM();

                bool isBuilt() const;

                void buildJVM();

                void destroyJVM();

            friend class Moona;
        };
    }

#endif