#pragma once

#include <jni.h>
#include <concepts>

#include "javavalue.hpp"
#include "../base/entity.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/object.hpp"
#include "../exceptions/indexexception.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    template <typename A> concept JArray = requires { std::is_base_of<_jarray, A>(); };

    template <JArray A, typename T> class JavaArray : public Object<JavaArray<A, T>> {
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

            virtual T& operator [] (size_t index) final {
                if (index >= this->length()) {
                    throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaArray.");
                }
                return this->elements[index];
            }

            virtual A& getJArray() const noexcept abstract;
            virtual operator A&() const noexcept abstract;
            
            size_t length() const noexcept {
                return Moona::defaultJNIEnv().GetArrayLength(this->array);
            }
    };

    class JavaBooleanArray : public Object<JavaBooleanArray>, public JavaArray<jbooleanArray, jboolean> {
        protected:
            JavaBooleanArray() = default;

        public:
            JavaBooleanArray(size_t size, jboolean* elements = nullptr);
            virtual ~JavaBooleanArray() = default;

            JavaBooleanArray region(size_t begin, size_t end) const;

            virtual jbooleanArray& getJArray() const noexcept override final;
            virtual operator jbooleanArray&() const noexcept override final;
    };
    class JavaByteArray : public Object<JavaByteArray>, public JavaArray<jbyteArray, jbyte> {
        protected:
            JavaByteArray() = default;

        public:
            JavaByteArray(size_t size, jbyte* elements = nullptr);
            virtual ~JavaByteArray() = default;

            JavaByteArray region(size_t begin, size_t end) const;

            virtual jbyteArray& getJArray() const noexcept override final;
            virtual operator jbyteArray&() const noexcept override final;
    };
}