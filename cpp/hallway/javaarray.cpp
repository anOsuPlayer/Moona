#include "javaarray.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(size_t size, jboolean* elements) : JavaArray(size) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, size, elements);
            this->elements = elements;
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
            Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, size, elements);
            this->elements = elements;
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
}