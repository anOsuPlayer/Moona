#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(const JavaPackage& pack, const char* classname) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->pack = &pack;
        const char* packname = pack.toString();
        unsigned int packlen = strlen(packname), classlen = strlen(classname);
        
        this->classname = new char[packlen+classlen+1];

        for (unsigned int i = 0; i < packlen; i++) {
            this->classname[i] = packname[i];
        }
        this->classname[packlen] = '/';
        for (unsigned int i = packlen+1; i < packlen+classlen+1; i++) {
            this->classname[i] = classname[i-packlen-1];
        }
        this->classname[packlen+classlen+1] = '\0';

        jclass c = Moona::defaultJNIEnv().FindClass(this->classname);

        if (c == nullptr) {
            throw NoSuchClassException();
        }
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(c);
        Moona::defaultJNIEnv().DeleteLocalRef(c);
    }
    JavaClass::JavaClass(const JavaClass& clazz) {
        const char* classname = clazz.classname;
        unsigned int len = strlen(classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->classname[i] = classname[i];
        }

        this->pack = clazz.pack;
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(clazz.clazz);
    }

    JavaClass::~JavaClass() {
        delete[] this->classname;
        Moona::defaultJNIEnv().DeleteGlobalRef(this->clazz);
    }

    JavaClass& JavaClass::operator = (const JavaClass& other) {
        const char* classname = other.classname;
        unsigned int len = strlen(classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->classname[i] = classname[i];
        }

        this->pack = other.pack;
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(other.clazz);

        return *this;
    }
    bool JavaClass::operator == (const JavaClass& other) {
        return ((strcmp(this->classname, other.classname) == 0) && this->pack == other.pack);
    }

    JValue JavaClass::call(const JavaStaticMethod& jsm, const jvalue* args) const {
        const PureSignature ret = jsm.getSignature().returnType();

        switch (ret[0]) {
            case 'Z' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticBooleanMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticBooleanMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'B' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticByteMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticByteMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'S' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticShortMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticShortMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'C' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticCharMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticCharMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'I' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticIntMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticIntMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'J' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticLongMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticLongMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'F' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticFloatMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticFloatMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'D' : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticDoubleMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticDoubleMethod(this->clazz, jsm.getJMethod());
                }
            }
            case 'V' : {
                if (args != nullptr) {
                    Moona::defaultJNIEnv().CallStaticVoidMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    Moona::defaultJNIEnv().CallStaticVoidMethod(this->clazz, jsm.getJMethod());
                }
                return JValue();
            }
            default : {
                if (args != nullptr) {
                    return Moona::defaultJNIEnv().CallStaticObjectMethodA(this->clazz, jsm.getJMethod(), args);
                }
                else {
                    return Moona::defaultJNIEnv().CallStaticObjectMethod(this->clazz, jsm.getJMethod());
                }
            }
        }
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
    }
    const JavaPackage& JavaClass::getPackage() const noexcept {
        return *this->pack;
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

    JavaObject JavaClass::newInstance() const {
        jobject obj = Moona::defaultJNIEnv().AllocObject(this->clazz);
        JavaObject jobj = Moona::defaultJNIEnv().NewGlobalRef(obj);
        Moona::defaultJNIEnv().DeleteLocalRef(obj);
        return jobj;
    }

    JavaClass::operator const jclass&() const noexcept {
        return this->clazz;
    }
    JavaClass::operator const char*() const noexcept {
        return this->classname;
    }

    const char* JavaClass::toString() const noexcept {
        return this->classname;
    }
    bool JavaClass::equals(const JavaClass& other) const noexcept {
        return ((strcmp(this->classname, other.classname) == 0) && this->pack == other.pack);
    }
}