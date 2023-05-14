#include "javaarray.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(jbooleanArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetBooleanArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaBooleanArray::JavaBooleanArray(size_t size, jboolean* elements) : JavaArray(size) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaBooleanArray& JavaBooleanArray::operator = (jbooleanArray& arr) noexcept {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jboolean[len];
        Moona::defaultJNIEnv().GetBooleanArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jbooleanArray JavaBooleanArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return (jbooleanArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaBooleanArray::operator jbooleanArray() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaByteArray::JavaByteArray(jbyteArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jbyteArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetByteArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaByteArray::JavaByteArray(size_t size, jbyte* elements) : JavaArray(size) {
        this->array = (jbyteArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewByteArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaByteArray& JavaByteArray::operator = (jbyteArray& arr) noexcept {
        this->array = (jbyteArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jbyte[len];
        Moona::defaultJNIEnv().GetByteArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jbyteArray JavaByteArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, this->length(), this->elements);
        return (jbyteArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaByteArray::operator jbyteArray() const noexcept {
        Moona::defaultJNIEnv().SetByteArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaCharArray::JavaCharArray(jcharArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetCharArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaCharArray::JavaCharArray(size_t size, jchar* elements) : JavaArray(size) {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewCharArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, size, this->elements);
        }
    }
    JavaCharArray::JavaCharArray(size_t size, const char* elements) : JavaArray(size) {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewCharArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = jchar(elements[i]);
            }
            Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaCharArray& JavaCharArray::operator = (jcharArray& arr) noexcept {
        this->array = (jcharArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jchar[len];
        Moona::defaultJNIEnv().GetCharArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jcharArray JavaCharArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, this->length(), this->elements);
        return (jcharArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaCharArray::operator jcharArray() const noexcept {
        Moona::defaultJNIEnv().SetCharArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaIntArray::JavaIntArray(jintArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jintArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetIntArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaIntArray::JavaIntArray(size_t size, jint* elements) : JavaArray(size) {
        this->array = (jintArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewIntArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaIntArray& JavaIntArray::operator = (jintArray& arr) noexcept {
        this->array = (jintArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jint[len];
        Moona::defaultJNIEnv().GetIntArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jintArray JavaIntArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, this->length(), this->elements);
        return (jintArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaIntArray::operator jintArray() const noexcept {
        Moona::defaultJNIEnv().SetIntArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaLongArray::JavaLongArray(jlongArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jlongArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetLongArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaLongArray::JavaLongArray(size_t size, jlong* elements) : JavaArray(size) {
        this->array = (jlongArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewLongArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaLongArray& JavaLongArray::operator = (jlongArray& arr) noexcept {
        this->array = (jlongArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jlong[len];
        Moona::defaultJNIEnv().GetLongArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jlongArray JavaLongArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, this->length(), this->elements);
        return (jlongArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaLongArray::operator jlongArray() const noexcept {
        Moona::defaultJNIEnv().SetLongArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaFloatArray::JavaFloatArray(jfloatArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jfloatArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetFloatArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaFloatArray::JavaFloatArray(size_t size, jfloat* elements) : JavaArray(size) {
        this->array = (jfloatArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewFloatArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaFloatArray& JavaFloatArray::operator = (jfloatArray& arr) noexcept {
        this->array = (jfloatArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jfloat[len];
        Moona::defaultJNIEnv().GetFloatArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jfloatArray JavaFloatArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, this->length(), this->elements);
        return (jfloatArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaFloatArray::operator jfloatArray() const noexcept {
        Moona::defaultJNIEnv().SetFloatArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }

    JavaDoubleArray::JavaDoubleArray(jdoubleArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        this->array = (jdoubleArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        Moona::defaultJNIEnv().GetDoubleArrayRegion(this->array, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaDoubleArray::JavaDoubleArray(size_t size, jdouble* elements) : JavaArray(size) {
        this->array = (jdoubleArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewDoubleArray(size));
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
            Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, size, this->elements);
        }
    }

    JavaDoubleArray& JavaDoubleArray::operator = (jdoubleArray& arr) noexcept {
        this->array = (jdoubleArray) Moona::defaultJNIEnv().NewGlobalRef(arr);
        size_t len = Moona::defaultJNIEnv().GetArrayLength(arr);
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->elements = new jdouble[len];
        Moona::defaultJNIEnv().GetDoubleArrayRegion(this->array, 0, len, this->elements);

        return *this;
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

    jdoubleArray JavaDoubleArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, this->length(), this->elements);
        return (jdoubleArray) Moona::defaultJNIEnv().NewLocalRef(this->array);
    }
    JavaDoubleArray::operator jdoubleArray() const noexcept {
        Moona::defaultJNIEnv().SetDoubleArrayRegion(this->array, 0, this->length(), this->elements);
        return this->array;
    }
}