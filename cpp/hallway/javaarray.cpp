#include "javaarray.hpp"
#include "javaobject.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(jbooleanArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetBooleanArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaBooleanArray::JavaBooleanArray(size_t size, jboolean* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaBooleanArray::JavaBooleanArray(const JavaBooleanArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaBooleanArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaBooleanArray& JavaBooleanArray::operator = (jbooleanArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jboolean[this->size];
        Moona::defaultJNIEnv().GetBooleanArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaBooleanArray JavaBooleanArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaBooleanArray.");
        }
        JavaBooleanArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jbooleanArray JavaBooleanArray::getJArray() const noexcept {
        jbooleanArray arr = Moona::defaultJNIEnv().NewBooleanArray(this->length());
        Moona::defaultJNIEnv().SetBooleanArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaBooleanArray::operator jbooleanArray() const noexcept {
        return this->getJArray();
    }

    JavaByteArray::JavaByteArray(jbyteArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetByteArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaByteArray::JavaByteArray(size_t size, jbyte* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaByteArray::JavaByteArray(const JavaByteArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaByteArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaByteArray& JavaByteArray::operator = (jbyteArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jbyte[this->size];
        Moona::defaultJNIEnv().GetByteArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaByteArray JavaByteArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaByteArray.");
        }
        JavaByteArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jbyteArray JavaByteArray::getJArray() const noexcept {
        jbyteArray arr = Moona::defaultJNIEnv().NewByteArray(this->length());
        Moona::defaultJNIEnv().SetByteArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaByteArray::operator jbyteArray() const noexcept {
        return this->getJArray();
    }

    JavaCharArray::JavaCharArray(jcharArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetCharArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaCharArray::JavaCharArray(size_t size, jchar* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaCharArray::JavaCharArray(size_t size, const char* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaCharArray::JavaCharArray(const JavaCharArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaCharArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaCharArray& JavaCharArray::operator = (jcharArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jchar[this->size];
        Moona::defaultJNIEnv().GetCharArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaCharArray JavaCharArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaCharArray.");
        }
        JavaCharArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jcharArray JavaCharArray::getJArray() const noexcept {
        jcharArray arr = Moona::defaultJNIEnv().NewCharArray(this->length());
        Moona::defaultJNIEnv().SetCharArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaCharArray::operator jcharArray() const noexcept {
        return this->getJArray();
    }

    JavaIntArray::JavaIntArray(jintArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetIntArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaIntArray::JavaIntArray(size_t size, jint* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaIntArray::JavaIntArray(const JavaIntArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaIntArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaIntArray& JavaIntArray::operator = (jintArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jint[this->size];
        Moona::defaultJNIEnv().GetIntArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaIntArray JavaIntArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaIntArray.");
        }
        JavaIntArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jintArray JavaIntArray::getJArray() const noexcept {
        jintArray arr = Moona::defaultJNIEnv().NewIntArray(this->length());
        Moona::defaultJNIEnv().SetIntArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaIntArray::operator jintArray() const noexcept {
        return this->getJArray();
    }

    JavaLongArray::JavaLongArray(jlongArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetLongArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaLongArray::JavaLongArray(size_t size, jlong* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaLongArray::JavaLongArray(const JavaLongArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaLongArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaLongArray& JavaLongArray::operator = (jlongArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jlong[this->size];
        Moona::defaultJNIEnv().GetLongArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaLongArray JavaLongArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaLongArray.");
        }
        JavaLongArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jlongArray JavaLongArray::getJArray() const noexcept {
        jlongArray arr = Moona::defaultJNIEnv().NewLongArray(this->length());
        Moona::defaultJNIEnv().SetLongArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaLongArray::operator jlongArray() const noexcept {
        return this->getJArray();
    }

    JavaFloatArray::JavaFloatArray(jfloatArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetFloatArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaFloatArray::JavaFloatArray(size_t size, jfloat* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaFloatArray::JavaFloatArray(const JavaFloatArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }
    
    JavaObject JavaFloatArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaFloatArray& JavaFloatArray::operator = (jfloatArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jfloat[this->size];
        Moona::defaultJNIEnv().GetFloatArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaFloatArray JavaFloatArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaFloatArray.");
        }
        JavaFloatArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jfloatArray JavaFloatArray::getJArray() const noexcept {
        jfloatArray arr = Moona::defaultJNIEnv().NewFloatArray(this->length());
        Moona::defaultJNIEnv().SetFloatArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaFloatArray::operator jfloatArray() const noexcept {
        return this->getJArray();
    }

    JavaDoubleArray::JavaDoubleArray(jdoubleArray arr) : JavaArray(Moona::defaultJNIEnv().GetArrayLength(arr)) {
        Moona::defaultJNIEnv().GetDoubleArrayRegion(arr, 0, Moona::defaultJNIEnv().GetArrayLength(arr), this->elements);
    }
    JavaDoubleArray::JavaDoubleArray(size_t size, jdouble* elements) : JavaArray(size) {
        if (elements != nullptr) {
            for (size_t i = 0; i < size; i++) {
                this->elements[i] = elements[i];
            }
        }
    }
    JavaDoubleArray::JavaDoubleArray(const JavaDoubleArray& arr) : JavaArray(arr.length()) {
        for (size_t i = 0; i < this->size; i++) {
            this->elements[i] = arr.elements[i];
        }
    }

    JavaObject JavaDoubleArray::asObject() const noexcept {
        return JavaObject(this->getJArray());
    }

    JavaDoubleArray& JavaDoubleArray::operator = (jdoubleArray& arr) noexcept {
        if (this->elements != nullptr) {
            delete[] this->elements;
        }
        this->size = Moona::defaultJNIEnv().GetArrayLength(arr);
        this->elements = new jdouble[this->size];
        Moona::defaultJNIEnv().GetDoubleArrayRegion(arr, 0, this->size, this->elements);

        return *this;
    }

    JavaDoubleArray JavaDoubleArray::region(size_t begin, size_t len) const {
        if (begin >= this->length() || len >= this->length() || begin+len >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaDoubleArray.");
        }
        JavaDoubleArray arr(len);
        for (size_t i = 0; i < len; i++) {
            arr[i] = this->elements[begin+i];
        }

        return arr;
    }

    jdoubleArray JavaDoubleArray::getJArray() const noexcept {
        jdoubleArray arr = Moona::defaultJNIEnv().NewDoubleArray(this->length());
        Moona::defaultJNIEnv().SetDoubleArrayRegion(arr, 0, this->length(), this->elements);
        return arr;
    }
    JavaDoubleArray::operator jdoubleArray() const noexcept {
        return this->getJArray();
    }
}