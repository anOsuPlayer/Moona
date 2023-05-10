#include "javaarray.hpp"

namespace moona {

    JavaBooleanArray::JavaBooleanArray(size_t size, const jboolean* elements) {
        this->array = (jbooleanArray) Moona::defaultJNIEnv().NewGlobalRef(Moona::defaultJNIEnv().NewBooleanArray(size));
        if (elements != nullptr) {
            Moona::defaultJNIEnv().SetBooleanArrayRegion(this->array, 0, size, elements);
        }
    }
}