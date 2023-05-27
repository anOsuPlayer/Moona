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

    template <typename A> concept JArray = requires { std::is_base_of<jarray, A>(); };

    template <JArray A, typename T> class JavaArray : public Object<JavaArray<A, T>> {
        protected:
            T* elements;
            mutable size_t size;

            JavaArray(size_t size) {
                if (!Moona::enableHallwayAccess) {
                    throw HallwayAccessException();
                }
                this->elements = new T[size];
                this->size = size;
            }

        public:
            virtual ~JavaArray() {
                delete[] this->elements;
            }

            virtual JavaArray<A, T>& operator = (const JavaArray<A, T>& arr) noexcept final {
                if (this->elements != nullptr) {
                    delete[] this->elements;
                }
                this->size = arr.length();
                this->elements = new T[size];
                for (size_t i = 0; i < size; i++) {
                    this->elements[i] = arr.elements[i];
                }

                return *this;
            }
            virtual JavaArray<A, T>& operator = (A& arr) noexcept abstract;

            virtual T& operator [] (size_t index) final {
                if (index >= this->length()) {
                    throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaArray.");
                }
                return this->elements[index];
            }

            virtual A getJArray() const noexcept abstract;
            virtual operator A() const noexcept abstract;

            virtual JValue getJValue() const noexcept {
                return JValue(*this);
            }
            virtual operator JValue() const noexcept {
                return JValue(*this);
            }
            
            size_t length() const noexcept {
                return this->size;
            }
    };

    class JavaBooleanArray : public Object<JavaBooleanArray>, public JavaArray<jbooleanArray, jboolean> {
        protected:
            JavaBooleanArray() = default;

        public:
            JavaBooleanArray(jbooleanArray arr);
            JavaBooleanArray(const JavaBooleanArray& arr);
            JavaBooleanArray(size_t size, jboolean* elements = nullptr);
            virtual ~JavaBooleanArray() = default;

            virtual JavaBooleanArray& operator = (jbooleanArray& arr) noexcept override final;

            JavaBooleanArray region(size_t begin, size_t end) const;

            virtual jbooleanArray getJArray() const noexcept override final;
            virtual operator jbooleanArray() const noexcept override final;
    };
    class JavaByteArray : public Object<JavaByteArray>, public JavaArray<jbyteArray, jbyte> {
        protected:
            JavaByteArray() = default;

        public:
            JavaByteArray(jbyteArray arr);
            JavaByteArray(const JavaByteArray& arr);
            JavaByteArray(size_t size, jbyte* elements = nullptr);
            virtual ~JavaByteArray() = default;

            virtual JavaByteArray& operator = (jbyteArray& arr) noexcept override final;

            JavaByteArray region(size_t begin, size_t end) const;

            virtual jbyteArray getJArray() const noexcept override final;
            virtual operator jbyteArray() const noexcept override final;
    };
    class JavaCharArray : public Object<JavaCharArray>, public JavaArray<jcharArray, jchar> {
        protected:
            JavaCharArray() = default;

        public:
            JavaCharArray(jcharArray arr);
            JavaCharArray(const JavaCharArray& arr);
            JavaCharArray(size_t size, jchar* elements = nullptr);
            JavaCharArray(size_t size, const char* elements);
            virtual ~JavaCharArray() = default;

            virtual JavaCharArray& operator = (jcharArray& arr) noexcept override final;

            JavaCharArray region(size_t begin, size_t end) const;

            virtual jcharArray getJArray() const noexcept override final;
            virtual operator jcharArray() const noexcept override final;
    };
    class JavaIntArray : public Object<JavaIntArray>, public JavaArray<jintArray, jint> {
        protected:
            JavaIntArray() = default;

        public:
            JavaIntArray(jintArray arr);
            JavaIntArray(const JavaIntArray& arr);
            JavaIntArray(size_t size, jint* elements = nullptr);
            virtual ~JavaIntArray() = default;

            virtual JavaIntArray& operator = (jintArray& arr) noexcept override final;

            JavaIntArray region(size_t begin, size_t end) const;

            virtual jintArray getJArray() const noexcept override final;
            virtual operator jintArray() const noexcept override final;
    };
    class JavaLongArray : public Object<JavaLongArray>, public JavaArray<jlongArray, jlong> {
        protected:
            JavaLongArray() = default;

        public:
            JavaLongArray(jlongArray arr);
            JavaLongArray(const JavaLongArray& arr);
            JavaLongArray(size_t size, jlong* elements = nullptr);
            virtual ~JavaLongArray() = default;

            virtual JavaLongArray& operator = (jlongArray& arr) noexcept override final;

            JavaLongArray region(size_t begin, size_t end) const;

            virtual jlongArray getJArray() const noexcept override final;
            virtual operator jlongArray() const noexcept override final;
    };
    class JavaFloatArray : public Object<JavaFloatArray>, public JavaArray<jfloatArray, jfloat> {
        protected:
            JavaFloatArray() = default;

        public:
            JavaFloatArray(jfloatArray arr);
            JavaFloatArray(const JavaFloatArray& arr);
            JavaFloatArray(size_t size, jfloat* elements = nullptr);
            virtual ~JavaFloatArray() = default;

            virtual JavaFloatArray& operator = (jfloatArray& arr) noexcept override final;

            JavaFloatArray region(size_t begin, size_t end) const;

            virtual jfloatArray getJArray() const noexcept override final;
            virtual operator jfloatArray() const noexcept override final;
    };
    class JavaDoubleArray : public Object<JavaDoubleArray>, public JavaArray<jdoubleArray, jdouble> {
        protected:
            JavaDoubleArray() = default;

        public:
            JavaDoubleArray(jdoubleArray arr);
            JavaDoubleArray(const JavaDoubleArray& arr);
            JavaDoubleArray(size_t size, jdouble* elements = nullptr);
            virtual ~JavaDoubleArray() = default;

            virtual JavaDoubleArray& operator = (jdoubleArray& arr) noexcept override final;

            JavaDoubleArray region(size_t begin, size_t end) const;

            virtual jdoubleArray getJArray() const noexcept override final;
            virtual operator jdoubleArray() const noexcept override final;
    };
}