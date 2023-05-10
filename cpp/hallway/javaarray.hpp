#pragma once

#include <jni.h>
#include <concepts>

#include "javavalue.hpp"
#include "../base/entity.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/object.hpp"
#include "../exceptions/indexexception.hpp"

namespace moona {

    template <typename A> concept JArray = requires { std::is_base_of<_jarray, A>(); };

    template <JArray A, typename T> class JavaArrayElement : public Object<JavaArrayElement<A, T>> {
        
    };
    template <JArray A, typename T> class JavaArrayRegion : public Object<JavaArrayRegion<A, T>>, protected JavaArrayElement<A, T> {
        
    };

    template <JArray A, typename T> class JavaArray : public Object<JavaArray<A, T>> {
        protected:
            A array;

            JavaArray(const A& arr) {
                this->array = Moona::defaultJNIEnv().NewGlobalRef(arr);
            }
            JavaArray() = default;

        public:
            JavaArray(const JavaArray<A, T>& arr) {
                this->array = arr.array;
            }
            virtual ~JavaArray() {
                Moona::defaultJNIEnv().DeleteGlobalRef(this->array);
            }
            
            size_t length() const noexcept {
                return Moona::defaultJNIEnv().GetArrayLength(this->array);
            }
    };

    class JavaBooleanArray : public Object<JavaBooleanArray>, public JavaArray<jbooleanArray, jboolean> {
        protected:
            JavaBooleanArray() = default;

        public:
            JavaBooleanArray(size_t size, const jboolean* elements = nullptr);
            virtual ~JavaBooleanArray() = default;
    };
}