#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(const JavaPackage& pack, const char* classname) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

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
    }

    JavaClass::JavaClass(const char* packname, const char* classname) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

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
    }

    JavaClass::~JavaClass() {
        delete this->classname;
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
    }

    const char* JavaClass::toString() const noexcept {
        return this->classname;
    }
}