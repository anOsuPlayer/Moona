#include "javaarray.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(size_t size, jboolean* elements) : JavaArray(size) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, size, elements);
            this->elements = elements;
        }
    }

    jbooleanArray& JavaBooleanArray::getJArray() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbooleanArray&>(this->array);
    }
    JavaBooleanArray::operator jbooleanArray&() const noexcept {
        Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, this->length(), this->elements);
        return const_cast<jbooleanArray&>(this->array);
    }
}