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

        this->clazz = Moona::getMoonaJVM().getJNIEnv().FindClass(this->classname);

        if (this->clazz == nullptr) {
            throw NoSuchClassException();
        }
        Moona::getMoonaJVM().getJNIEnv().NewGlobalRef(this->clazz);
    }
    JavaClass::JavaClass(const JavaClass& clazz) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->classname = clazz.classname;
        this->pack = clazz.pack;
        this->clazz = clazz.clazz;
    }

    JavaClass::~JavaClass() {
        delete this->classname;
        Moona::getMoonaJVM().getJNIEnv().DeleteGlobalRef(this->clazz);
    }

    JavaClass& JavaClass::operator = (const JavaClass& other) {
        this->classname = other.classname;
        this->pack = other.pack;
        this->clazz = other.clazz;

        return *this;
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
    }
    const JavaPackage& JavaClass::getPackage() const noexcept {
        return *this->pack;
    }

    const char* JavaClass::toString() const noexcept {
        return this->classname;
    }
}