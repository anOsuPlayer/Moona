#pragma once

#include <jni.h>
#include <windows.h>
#include <tchar.h>

#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class JVM : public Entity<JVM> {
        private:
            JavaVM* jvm;
            JNIEnv* env;

            jint JNIStatus;

            static_field HMODULE source;

            typedef int _jvmbuilder(JavaVM**, void**, void*);
            typedef int _jvmfinder(JavaVM**, jint, jint*);

            static_field _jvmbuilder* jvmbuilder;
            static_field _jvmfinder* jvmfinder;

        public:
            static void loadJVMLibraries();
            static void unloadJVMLibraries();

            JVM() = default;
            JVM(JNIEnv* env);
            ~JVM();

            void buildJVM();
            void destroyJVM();

            JavaVM& getJavaVM() const noexcept;
            JNIEnv& getJNIEnv() const noexcept;

            bool isSafe() const noexcept;
            bool isBuilt() const noexcept;

            bool isLoaded() const noexcept;

        friend class Moona;
    };
}