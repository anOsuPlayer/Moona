#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(const char* classname, size_t arrayDimensions) {
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
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(c);
        Moona::defaultJNIEnv().DeleteLocalRef(c);
    }
    JavaClass::JavaClass(const JavaPackage& pack, const char* classname, size_t arrayDimensions) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        const char* packname = pack.toString();
        unsigned int packlen = strlen(packname), classlen = strlen(classname);
        
        this->classname = new char[packlen+classlen+1];

        for (unsigned int i = 0; i < packlen; i++) {
            this->classname[i] = (packname[i] == '.') ? '/' : packname[i];
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

        this->clazz = clazz.clazz;
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

        this->clazz = other.clazz;

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
            throw JVMException();
        }
        return r;
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
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
        return (strcmp(this->classname, other.classname) == 0);
    }
}