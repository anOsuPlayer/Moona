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
        Moona::defaultJNIEnv().DeleteLocalRef(c);
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(c);
    }
    JavaClass::JavaClass(const JavaClass& clazz) {
        unsigned int len = strlen(clazz.classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';
        for (unsigned int i = 0; i < len; i++) {
            this->classname[i] = clazz.classname[i];
        }

        this->pack = clazz.pack;
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(clazz.clazz);
    }

    JavaClass::~JavaClass() {
        delete this->classname;
        Moona::defaultJNIEnv().DeleteGlobalRef(this->clazz);
    }

    JavaClass& JavaClass::operator = (const JavaClass& other) {
        unsigned int len = strlen(other.classname);
        this->classname = new char[len+1]; this->classname[len] = '\0';
        for (unsigned int i = 0; i < len; i++) {
            this->classname[i] = other.classname[i];
        }

        this->pack = other.pack;
        this->clazz = (jclass) Moona::defaultJNIEnv().NewGlobalRef(other.clazz);

        return *this;
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
    }
    const JavaPackage& JavaClass::getPackage() const noexcept {
        return *this->pack;
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
}