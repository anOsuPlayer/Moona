#include "javaarray.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(size_t size, jboolean* elements) : JavaArray(size) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaBooleanArray JavaBooleanArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaBooleanArray.");
        }
        JavaBooleanArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jbooleanArray& JavaBooleanArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbooleanArray&>(this->array);
    }
    JavaBooleanArray::operator jbooleanArray&() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbooleanArray&>(this->array);
    }

    JavaByteArray::JavaByteArray(size_t size, jbyte* elements) : JavaArray(size) {
        this->array = (jbyteArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaByteArray JavaByteArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaByteArray.");
        }
        JavaByteArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jbyteArray& JavaByteArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbyteArray&>(this->array);
    }
    JavaByteArray::operator jbyteArray&() const noexcept {
        Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbyteArray&>(this->array);
    }

    JavaCharArray::JavaCharArray(size_t size, jchar* elements) : JavaArray(size) {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, size, this->elements);
        }
    }
    JavaCharArray::JavaCharArray(size_t size, const char* elements) : JavaArray(size) {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = jchar(elements[i]);
            }
            Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaCharArray JavaCharArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaCharArray.");
        }
        JavaCharArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jcharArray& JavaCharArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jcharArray&>(this->array);
    }
    JavaCharArray::operator jcharArray&() const noexcept {
        Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jcharArray&>(this->array);
    }

    JavaIntArray::JavaIntArray(size_t size, jint* elements) : JavaArray(size) {
        this->array = (jintArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaIntArray JavaIntArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaIntArray.");
        }
        JavaIntArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jintArray& JavaIntArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jintArray&>(this->array);
    }
    JavaIntArray::operator jintArray&() const noexcept {
        Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jintArray&>(this->array);
    }

    JavaLongArray::JavaLongArray(size_t size, jlong* elements) : JavaArray(size) {
        this->array = (jlongArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaLongArray JavaLongArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaLongArray.");
        }
        JavaLongArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jlongArray& JavaLongArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jlongArray&>(this->array);
    }
    JavaLongArray::operator jlongArray&() const noexcept {
        Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jlongArray&>(this->array);
    }

    JavaFloatArray::JavaFloatArray(size_t size, jfloat* elements) : JavaArray(size) {
        this->array = (jfloatArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaFloatArray JavaFloatArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaFloatArray.");
        }
        JavaFloatArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jfloatArray& JavaFloatArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jfloatArray&>(this->array);
    }
    JavaFloatArray::operator jfloatArray&() const noexcept {
        Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jfloatArray&>(this->array);
    }

    JavaDoubleArray::JavaDoubleArray(size_t size, jdouble* elements) : JavaArray(size) {
        this->array = (jdoubleArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaDoubleArray JavaDoubleArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaDoubleArray.");
        }
        JavaDoubleArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jdoubleArray& JavaDoubleArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jdoubleArray&>(this->array);
    }
    JavaDoubleArray::operator jdoubleArray&() const noexcept {
        Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jdoubleArray&>(this->array);
    }
}