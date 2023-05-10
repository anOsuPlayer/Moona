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

    template <JArray A, typename T, typename Alias = void> class JavaArray : public Object<JavaArray<A, T>> {
        protected:
            A array;
            T* elements;

            JavaArray(size_t size) {
                this->elements = new T[size];
            }

        public:
            JavaArray(const JavaArray<A, T>& arr) {
                this->array = arr.array;
            }
            virtual ~JavaArray() {
                Moona::defaultJNIEnv().DeleteGlobalRef(this->array);
                delete[] this->elements;
            }
            
            size_t length() const noexcept {
                return Moona::defaultJNIEnv().GetArrayLength(this->array);
            }

            virtual T& operator [] (size_t index) final {
                if (index >= this->length()) {
                    throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaArray.");
                }
                return this->elements[index];
            }

            virtual A& getJArray() const noexcept abstract;
            virtual operator A&() const noexcept abstract;
    };

    class JavaBooleanArray : public Object<JavaBooleanArray>, public JavaArray<jbooleanArray, jboolean> {
        protected:
            JavaBooleanArray() = default;

        public:
            JavaBooleanArray(size_t size, jboolean* elements = nullptr);
            virtual ~JavaBooleanArray() = default;

            virtual jbooleanArray& getJArray() const noexcept override final;
            virtual operator jbooleanArray&() const noexcept override final;
    };
}