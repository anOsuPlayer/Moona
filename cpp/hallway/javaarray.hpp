#pragma once

#include <jni.h>
#include <concepts>

#include "javavalue.hpp"
#include "hallwayexception.hpp"
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
                if (!Moona::enableHallwayAccess) {
                    throw HallwayAccessException();
                }
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

            virtual jvalue getJValue() const noexcept {
                jvalue val; val.l = this->array;
                return val;
            }
            virtual operator jvalue() const noexcept {
                jvalue val; val.l = this->array;
                return val;
            }
            
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
    class JavaCharArray : public Object<JavaCharArray>, public JavaArray<jcharArray, jchar> {
        protected:
            JavaCharArray() = default;

        public:
            JavaCharArray(size_t size, jchar* elements = nullptr);
            JavaCharArray(size_t size, const char* elements);
            virtual ~JavaCharArray() = default;

            JavaCharArray region(size_t begin, size_t end) const;

            virtual jcharArray& getJArray() const noexcept override final;
            virtual operator jcharArray&() const noexcept override final;
    };
    class JavaIntArray : public Object<JavaIntArray>, public JavaArray<jintArray, jint> {
        protected:
            JavaIntArray() = default;

        public:
            JavaIntArray(size_t size, jint* elements = nullptr);
            virtual ~JavaIntArray() = default;

            JavaIntArray region(size_t begin, size_t end) const;

            virtual jintArray& getJArray() const noexcept override final;
            virtual operator jintArray&() const noexcept override final;
    };
    class JavaLongArray : public Object<JavaLongArray>, public JavaArray<jlongArray, jlong> {
        protected:
            JavaLongArray() = default;

        public:
            JavaLongArray(size_t size, jlong* elements = nullptr);
            virtual ~JavaLongArray() = default;

            JavaLongArray region(size_t begin, size_t end) const;

            virtual jlongArray& getJArray() const noexcept override final;
            virtual operator jlongArray&() const noexcept override final;
    };
    class JavaFloatArray : public Object<JavaFloatArray>, public JavaArray<jfloatArray, jfloat> {
        protected:
            JavaFloatArray() = default;

        public:
            JavaFloatArray(size_t size, jfloat* elements = nullptr);
            virtual ~JavaFloatArray() = default;

            JavaFloatArray region(size_t begin, size_t end) const;

            virtual jfloatArray& getJArray() const noexcept override final;
            virtual operator jfloatArray&() const noexcept override final;
    };
    class JavaDoubleArray : public Object<JavaDoubleArray>, public JavaArray<jdoubleArray, jdouble> {
        protected:
            JavaDoubleArray() = default;

        public:
            JavaDoubleArray(size_t size, jdouble* elements = nullptr);
            virtual ~JavaDoubleArray() = default;

            JavaDoubleArray region(size_t begin, size_t end) const;

            virtual jdoubleArray& getJArray() const noexcept override final;
            virtual operator jdoubleArray&() const noexcept override final;
    };
}