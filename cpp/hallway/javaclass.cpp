#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(jclass clazz) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = (jclass) Moona::defaultJNIEnv().NewWeakGlobalRef(clazz);

        jmethodID getClass = Moona::defaultJNIEnv().GetMethodID(this->clazz, "getClass", MethodSignature(ObjectSignature("java.lang.Class")));
        jclass thisClass = (jclass) Moona::defaultJNIEnv().CallObjectMethod(this->clazz, getClass);
        jmethodID getName = Moona::defaultJNIEnv().GetMethodID(thisClass, "getName", MethodSignature::STRING_METHOD);
        jstring name = (jstring) Moona::defaultJNIEnv().CallObjectMethod(this->clazz, getName);

        const char* str = Moona::defaultJNIEnv().GetStringUTFChars(name, 0);
        size_t len = strlen(str);
        this->classname = new char[len+1]; this->classname[len] = '\0';        
        for (size_t i = 0; i < len; i++) {
            this->classname[i] = str[i];
        }

        Moona::defaultJNIEnv().ReleaseStringUTFChars(name, str);
        Moona::defaultJNIEnv().DeleteLocalRef(name);
        Moona::defaultJNIEnv().DeleteLocalRef(thisClass);
    }
    JavaClass::JavaClass(const char* classname) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        size_t len = strlen(classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';
        for (size_t i = 0; i < len; i++) {
            this->classname[i] = (classname[i] == '.') ? '/' : classname[i];
        }

        jclass c = Moona::defaultJNIEnv().FindClass(this->classname);

        if (c == nullptr) {
            throw NoSuchClassException();
        }
        this->clazz = (jclass) Moona::defaultJNIEnv().NewWeakGlobalRef(c);
        Moona::defaultJNIEnv().DeleteLocalRef(c);
    }
    JavaClass::JavaClass(const JavaPackage& pack, const char* classname) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        const char* packname = pack.toString();
        size_t packlen = strlen(packname), classlen = strlen(classname);
        
        this->classname = new char[packlen+classlen+1];

        for (size_t i = 0; i < packlen; i++) {
            this->classname[i] = (packname[i] == '.') ? '/' : packname[i];
        }
        this->classname[packlen] = '/';
        for (size_t i = packlen+1; i < packlen+classlen+1; i++) {
            this->classname[i] = classname[i-packlen-1];
        }
        this->classname[packlen+classlen+1] = '\0';

        jclass c = Moona::defaultJNIEnv().FindClass(this->classname);

        if (c == nullptr) {
            throw NoSuchClassException();
        }
        this->clazz = (jclass) Moona::defaultJNIEnv().NewWeakGlobalRef(c);

        Moona::defaultJNIEnv().DeleteLocalRef(c);
    }
    JavaClass::JavaClass(const JavaClass& clazz) {
        const char* classname = clazz.classname;
        size_t len = strlen(classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';

        for (size_t i = 0; i < len; i++) {
            this->classname[i] = classname[i];
        }

        this->clazz = (jclass) Moona::defaultJNIEnv().NewWeakGlobalRef(clazz.clazz);
    }

    JavaClass::~JavaClass() {
        if (this->clazz != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->clazz);
        }
        if (this->classname != nullptr) {
            delete[] this->classname;
        }
    }

    JavaClass& JavaClass::operator = (const JavaClass& other) {
        const char* classname = other.classname;
        size_t len = strlen(classname);
        if (this->classname != nullptr) {
            delete[] this->classname;
        }
        this->classname = new char[len+1]; this->classname[len] = '\0';

        for (size_t i = 0; i < len; i++) {
            this->classname[i] = classname[i];
        }

        if (this->clazz != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->clazz);
        }
        this->clazz = (jclass) Moona::defaultJNIEnv().NewWeakGlobalRef(other.clazz);

        return *this;
    }
    bool JavaClass::operator == (const JavaClass& other) {
        return (strcmp(this->classname, other.classname) == 0);
    }

    JValue JavaClass::call(const JavaStaticMethod& jsm, const jvalue* args) const {
        const PureSignature ret = jsm.getSignature().returnType();
        JValue r;

        switch (ret[0]) {
            case 'Z' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticBooleanMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticBooleanMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'B' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticByteMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticByteMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'S' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticShortMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticShortMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'C' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticCharMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticCharMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'I' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticIntMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticIntMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'J' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticLongMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticLongMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'F' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticFloatMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticFloatMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'D' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticDoubleMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticDoubleMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            case 'V' : {
                if (args != nullptr) {
                    Moona::defaultJNIEnv().CallStaticVoidMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    Moona::defaultJNIEnv().CallStaticVoidMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
            default : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallStaticObjectMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallStaticObjectMethod(this->clazz, jsm.getJMethod());
                }
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            Moona::defaultJNIEnv().ExceptionDescribe();
            throw JVMException();
        }
        return r;
    }

    JValue JavaClass::access(const JavaStaticField& jsf) const {
        const char id = jsf.getSignature()[0];
        JValue r;

        switch (id) {
            case 'Z' : {
                r = Moona::defaultJNIEnv().GetStaticBooleanField(this->clazz, jsf.getJField());
                break;
            }
            case 'B' : {
                r = Moona::defaultJNIEnv().GetStaticByteField(this->clazz, jsf.getJField());
                break;
            }
            case 'S' : {
                r = Moona::defaultJNIEnv().GetStaticShortField(this->clazz, jsf.getJField());
                break;
            }
            case 'C' : {
                r = Moona::defaultJNIEnv().GetStaticCharField(this->clazz, jsf.getJField());
                break;
            }
            case 'I' : {
                r = Moona::defaultJNIEnv().GetStaticIntField(this->clazz, jsf.getJField());
                break;
            }
            case 'J' : {
                r = Moona::defaultJNIEnv().GetStaticLongField(this->clazz, jsf.getJField());
                break;
            }
            case 'F' : {
                r = Moona::defaultJNIEnv().GetStaticFloatField(this->clazz, jsf.getJField());
                break;
            }
            case 'D' : {
                r = Moona::defaultJNIEnv().GetStaticDoubleField(this->clazz, jsf.getJField());
                break;
            }
            default : {
                r = Moona::defaultJNIEnv().GetStaticObjectField(this->clazz, jsf.getJField());
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            Moona::defaultJNIEnv().ExceptionDescribe();
            throw JVMException();
        }
        return r;
    }
    void JavaClass::edit(const JavaStaticField& jsf, const jvalue& value) {
        const char id = jsf.getSignature()[0];

        switch (id) {
            case 'Z' : {
                Moona::defaultJNIEnv().SetStaticBooleanField(this->clazz, jsf.getJField(), value.z);
                break;
            }
            case 'B' : {
                Moona::defaultJNIEnv().SetStaticByteField(this->clazz, jsf.getJField(), value.b);
                break;
            }
            case 'S' : {
                Moona::defaultJNIEnv().SetStaticShortField(this->clazz, jsf.getJField(), value.s);
                break;
            }
            case 'C' : {
                Moona::defaultJNIEnv().SetStaticCharField(this->clazz, jsf.getJField(), value.c);
                break;
            }
            case 'I' : {
                Moona::defaultJNIEnv().SetStaticIntField(this->clazz, jsf.getJField(), value.i);
                break;
            }
            case 'J' : {
                Moona::defaultJNIEnv().SetStaticLongField(this->clazz, jsf.getJField(), value.j);
                break;
            }
            case 'F' : {
                Moona::defaultJNIEnv().SetStaticFloatField(this->clazz, jsf.getJField(), value.f);
                break;
            }
            case 'D' : {
                Moona::defaultJNIEnv().SetStaticDoubleField(this->clazz, jsf.getJField(), value.d);
                break;
            }
            default : {
                Moona::defaultJNIEnv().SetStaticObjectField(this->clazz, jsf.getJField(), value.l);
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            Moona::defaultJNIEnv().ExceptionDescribe();
            throw JVMException();
        }
    }

    JavaClass::operator const jclass&() const noexcept {
        return this->clazz;
    }
    jclass JavaClass::getJClass() const noexcept {
        return (jclass) Moona::defaultJNIEnv().NewLocalRef(this->clazz);
    }

    JavaObject JavaClass::asObject() const noexcept {
        return JavaObject(this->clazz);
    }

    JavaMethod JavaClass::getMethod(const char* name, const MethodSignature& ms) const {
        if (name == nullptr) {
            throw NullPointerException("Unable to find any JavaMethod from a nullptr.");
        }
        return JavaMethod(name, *this, ms);
    }
    JavaStaticMethod JavaClass::getStaticMethod(const char* name, const MethodSignature& ms) const {
        if (name == nullptr) {
            throw NullPointerException("Unable to find any JavaStaticMethod from a nullptr.");
        }
        return JavaStaticMethod(name, *this, ms);
    }

    JavaConstructor JavaClass::getConstructor(const ConstructorSignature& cs) const {
        return JavaConstructor(*this, cs);
    }

    JavaField JavaClass::getField(const char* name, const FieldSignature& fs) {
        if (name == nullptr) {
            throw NullPointerException("Unable to find any JavaField from a nullptr.");
        }
        return JavaField(name, *this, fs);
    }
    JavaStaticField JavaClass::getStaticField(const char* name, const FieldSignature& fs) {
        if (name == nullptr) {
            throw NullPointerException("Unable to find any JavaStaticField from a nullptr.");
        }
        return JavaStaticField(name, *this, fs);
    }

    constexpr bool JavaClass::isArray() const noexcept {
        return this->classname[0] == '[';
    }

    bool JavaClass::extends(const jclass& clazz) const noexcept {
        return Moona::defaultJNIEnv().IsAssignableFrom(this->clazz, clazz);
    }

    JavaClass JavaClass::arrayType(size_t level) const {
        if (level == 0) {
            throw IllegalArgumentException("Unable to obtain 0-dimensional arrays.");
        }

        size_t len = strlen(this->classname);
        if (this->classname[0] != '[') {
            char* name = new char[len+3+level];
            
            for (size_t i = 0; i < level; i++) {
                name[i] = '[';
            }
            
            name[level] = 'L', name[len+1+level] = ';', name[len+2+level] = '\0';
            for (size_t i = level+1; i < len+1+level; i++) {
                name[i] = this->classname[i-1-level];
            }

            JavaClass clazz(name);
            delete[] name;

            return clazz;
        }

        char* name = new char[len+1+level];
        
        for (size_t i = 0; i < level; i++) {
            name[i] = '[';
        }

        name[len+level] = '\0';
        for (size_t i = level; i < len+level; i++) {
            name[i] = this->classname[i-level];
        }

        JavaClass clazz(name);
        delete[] name;

        return clazz;
    }
    JavaClass JavaClass::componentType() const noexcept {
        if (this->classname[0] == '[') {
            return *this;
        }

        size_t len = strlen(this->classname);
        char* name = new char[len]; name[len-1] = '\0';
        for (size_t i = 1; i < len; i++) {
            name[i-1] = this->classname[i];
        }

        JavaClass clazz(name);
        delete[] name;
        
        return clazz;
    }

    const char* JavaClass::toString() const noexcept {
        return this->classname;
    }
    bool JavaClass::equals(const JavaClass& other) const noexcept {
        return (strcmp(this->classname, other.classname) == 0);
    }
}