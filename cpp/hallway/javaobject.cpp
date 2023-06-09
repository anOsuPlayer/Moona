#include "javaobject.hpp"

namespace moona {

    JavaObject::JavaObject(const jobject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(obj);
    }
    JavaObject::JavaObject(const JavaObject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(obj.obj);
    }

    JavaObject::~JavaObject() {
        if (this->obj != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->obj);
        }
    }

    JavaObject& JavaObject::operator = (const jobject& other) noexcept {
        if (this->obj != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->obj);
        }
        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(other);
        
        return *this;
    }
    bool JavaObject::operator == (const jobject& other) const noexcept {
        return this->equals(other);
    }

    JValue JavaObject::_call(jobject obj, const JavaMethod& jm, const jvalue* args) {
        const PureSignature ret = jm.getSignature().returnType();
        JValue r;

        switch (ret[0]) {
            case 'Z' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallBooleanMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallBooleanMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'B' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallByteMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallByteMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'S' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallShortMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallShortMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'C' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallCharMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallCharMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'I' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallIntMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallIntMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'J' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallLongMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallLongMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'F' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallFloatMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallFloatMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'D' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallDoubleMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallDoubleMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'V' : {
                if (args != nullptr) {
                    Moona::defaultJNIEnv().CallVoidMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    Moona::defaultJNIEnv().CallVoidMethod(obj, jm.getJMethod());
                }
                break;
            }
            default : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallObjectMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallObjectMethod(obj, jm.getJMethod());
                }
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }
    JValue JavaObject::call(const JavaMethod& jm, const jvalue* args) const {
        return JavaObject::_call(this->obj, jm, args);
    }

    JValue JavaObject::_access(jobject obj, const JavaField& jf) {
        const char id = jf.getSignature()[0];
        JValue r;

        switch (id) {
            case 'Z' : {
                r = Moona::defaultJNIEnv().GetBooleanField(obj, jf.getJField());
                break;
            }
            case 'B' : {
                r = Moona::defaultJNIEnv().GetByteField(obj, jf.getJField());
                break;
            }
            case 'S' : {
                r = Moona::defaultJNIEnv().GetShortField(obj, jf.getJField());
                break;
            }
            case 'C' : {
                r = Moona::defaultJNIEnv().GetCharField(obj, jf.getJField());
                break;
            }
            case 'I' : {
                r = Moona::defaultJNIEnv().GetIntField(obj, jf.getJField());
                break;
            }
            case 'J' : {
                r = Moona::defaultJNIEnv().GetLongField(obj, jf.getJField());
                break;
            }
            case 'F' : {
                r = Moona::defaultJNIEnv().GetFloatField(obj, jf.getJField());
                break;
            }
            case 'D' : {
                r = Moona::defaultJNIEnv().GetDoubleField(obj, jf.getJField());
                break;
            }
            default : {
                r = Moona::defaultJNIEnv().GetObjectField(obj, jf.getJField());
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }
    JValue JavaObject::access(const JavaField& jf) const {
        return JavaObject::_access(this->obj, jf);
    }

    void JavaObject::_edit(jobject obj, const JavaField& jf, const jvalue& value) {
        const char id = jf.getSignature()[0];

        switch (id) {
            case 'Z' : {
                Moona::defaultJNIEnv().SetBooleanField(obj, jf.getJField(), value.z);
                break;
            }
            case 'B' : {
                Moona::defaultJNIEnv().SetByteField(obj, jf.getJField(), value.b);
                break;
            }
            case 'S' : {
                Moona::defaultJNIEnv().SetShortField(obj, jf.getJField(), value.s);
                break;
            }
            case 'C' : {
                Moona::defaultJNIEnv().SetCharField(obj, jf.getJField(), value.s);
                break;
            }
            case 'I' : {
                Moona::defaultJNIEnv().SetIntField(obj, jf.getJField(), value.i);
                break;
            }
            case 'J' : {
                Moona::defaultJNIEnv().SetLongField(obj, jf.getJField(), value.j);
                break;
            }
            case 'F' : {
                Moona::defaultJNIEnv().SetFloatField(obj, jf.getJField(), value.f);
                break;
            }
            case 'D' : {
                Moona::defaultJNIEnv().SetDoubleField(obj, jf.getJField(), value.d);
                break;
            }
            default : {
                Moona::defaultJNIEnv().SetObjectField(obj, jf.getJField(), value.l);
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
    }
    void JavaObject::edit(const JavaField& jf, const jvalue& value) {
        return JavaObject::_edit(this->obj, jf, value);
    }

    JavaClass JavaObject::getClass() const {
        if (this->getJObject() == nullptr) {
            throw NullPointerException("Unable to call .getClass() method on a null JavaObject.");
        }
        return JavaClass(Moona::defaultJNIEnv().GetObjectClass(this->obj));
    }

    JavaObject::operator const jobject&() const noexcept {
        return this->obj;
    }
    jobject JavaObject::getJObject() const noexcept {
        return Moona::defaultJNIEnv().NewLocalRef(this->obj);
    }

    JavaObject JavaObject::asObject() const noexcept {
        return *this;
    }

    const char* JavaObject::toString() const noexcept {
        jclass clazz = Moona::defaultJNIEnv().GetObjectClass(this->obj);
        jmethodID toString = Moona::defaultJNIEnv().GetMethodID(clazz, "toString", MethodSignature::TO_STRING);

        JavaString str = (jstring) Moona::defaultJNIEnv().CallObjectMethod(this->obj, toString);
        char* res = new char[str.length()]; strcpy(res, str);
        Moona::defaultJNIEnv().DeleteLocalRef(clazz);

        return res;
    }
    bool JavaObject::equals(const JavaObject& obj) const noexcept {
        return this->equals(obj.getJObject());
    }

    bool JavaObject::equals(const jobject& obj) const noexcept {
        jclass clazz = Moona::defaultJNIEnv().GetObjectClass(this->obj);
        jmethodID equals = Moona::defaultJNIEnv().GetMethodID(clazz, "equals", MethodSignature::EQUALS);

        jboolean res = Moona::defaultJNIEnv().CallBooleanMethod(this->obj, equals, obj);
        bool eq = (res == 1) ? true : false;
        Moona::defaultJNIEnv().DeleteLocalRef(clazz);

        return eq;
    }
    bool JavaObject::sameType(const jobject& obj) const noexcept {
        return (Moona::defaultJNIEnv().IsInstanceOf(this->obj, Moona::defaultJNIEnv().GetObjectClass(obj)) == 0 ? false : true);
    }
    bool JavaObject::isInstanceof(const jclass& clazz) const noexcept {
        return (Moona::defaultJNIEnv().IsInstanceOf(this->obj, clazz) == 0 ? false : true);
    }

    JavaObjectArrayElement::JavaObjectArrayElement(JavaObjectArray* ref, size_t refIndex) : JavaObject(Moona::defaultJNIEnv().GetObjectArrayElement(ref->arr, refIndex)) {
        this->ref = ref; this->refIndex = refIndex;
    }
    JavaObjectArrayElement::~JavaObjectArrayElement() {
        if (this->hasChanged) {
            Moona::defaultJNIEnv().SetObjectArrayElement(this->ref->arr, this->refIndex, this->obj);
        }
    }

    JavaObject& JavaObjectArrayElement::operator = (const jobject& obj) noexcept {
        this->hasChanged = true;
        
        if (this->obj != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->obj);
        }
        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(obj);
        
        return *this;
    }

    JavaObjectArray::JavaObjectArray(const jobjectArray& arr) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->arr = (jobjectArray) Moona::defaultJNIEnv().NewWeakGlobalRef(arr);
    }
    JavaObjectArray::JavaObjectArray(const JavaObjectArray& arr) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->arr = (jobjectArray) Moona::defaultJNIEnv().NewWeakGlobalRef(arr.arr);
    }

    JavaObjectArray::~JavaObjectArray() {
        if (this->currentElement != nullptr) {
            delete this->currentElement;
        }
        Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->arr);
    }

    JavaStaticMethod JavaObjectArray::PRINT_ARRAY;
    JavaStaticMethod JavaObjectArray::ARRAY_EQUALS;

    void JavaObjectArray::popCurrent() noexcept {
        if (this->currentElement != nullptr) {
            delete this->currentElement;
        }
    }

    JavaObjectArray::operator const jobjectArray&() const noexcept {
        return this->arr;
    }
    jobjectArray JavaObjectArray::getJObjectArray() const noexcept {
        return (jobjectArray) Moona::defaultJNIEnv().NewLocalRef(this->arr);
    }

    JavaObject JavaObjectArray::asObject() const noexcept {
        return JavaObject(this->arr);
    }

    JavaObjectArrayElement& JavaObjectArray::operator [] (size_t index) {
        if (index >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaObjectArray.");
        }

        if (this->currentElement != nullptr) {
            delete this->currentElement;
        }
        this->currentElement = new JavaObjectArrayElement(this, index);
        
        return *this->currentElement;
    }

    JavaObjectArray& JavaObjectArray::operator = (const jobjectArray& arr) noexcept {
        if (this->arr != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->arr);
        }
        
        this->arr = (jobjectArray) Moona::defaultJNIEnv().NewWeakGlobalRef(arr);

        return *this;
    }
    bool JavaObjectArray::operator == (const jobjectArray& arr) const noexcept {
        return this->equals(arr);
    }

    size_t JavaObjectArray::length() const noexcept {
        return Moona::defaultJNIEnv().GetArrayLength(this->arr);
    }

    const char* JavaObjectArray::toString() const noexcept {
        if (this->PRINT_ARRAY == nullptr) {
            JavaClass clazz("java/util/Arrays");
            JavaObjectArray::PRINT_ARRAY = JavaStaticMethod("toString", clazz, MethodSignature(ObjectSignature::STRING, ArraySignature::OBJECT_ARRAY));
        }

        jvalue* array = new jvalue[1]; array[0].l = this->arr;
        JavaString str = (jstring) JavaObjectArray::PRINT_ARRAY.call(array);
        delete[] array;

        char* res = new char[str.length()]; strcpy(res, str);
        return res;
    }
    bool JavaObjectArray::equals(const JavaObjectArray& arr) const noexcept {
        return this->equals(arr.arr);
    }

    bool JavaObjectArray::equals(const jobjectArray& arr) const noexcept {
        if (arr == nullptr) {
            return false;
        }

        if (this->ARRAY_EQUALS == nullptr) {
            JavaClass clazz("java/util/Arrays");
            JavaObjectArray::ARRAY_EQUALS = JavaStaticMethod("equals", clazz,  MethodSignature(Signature::BOOLEAN, ComposedSignature(ArraySignature::OBJECT_ARRAY).concat(ArraySignature::OBJECT_ARRAY)));
        }

        jvalue* arrays = new jvalue[2]; arrays[0].l = this->arr; arrays[1].l = arr;
        jboolean res = JavaObjectArray::ARRAY_EQUALS.call(arrays);
        delete[] arrays;

        bool eq = (res == 1) ? true : false;
        return eq;
    }
}