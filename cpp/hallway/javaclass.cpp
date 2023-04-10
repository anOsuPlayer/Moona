#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(const JavaPackage& pack, const char* classname) {
        this->classname;
        this->pack = new JavaPackage(pack.toString());

        char* location; strcpy(location, pack.toString());
        strcat(location, "/"), strcat(location, classname);

        jclass clazz = Moona::getMoonaJVM().getJNIEnv().FindClass(location);

        if (clazz == nullptr) {
            delete this->pack;
            throw NoSuchClassException();
        }
    }

    JavaClass::JavaClass(const char* packname, const char* classname) {
        this->classname = classname;
        this->pack = new JavaPackage(packname);

        char* location; strcpy(location, packname);
        strcat(location, "/"), strcat(location, classname);

        jclass clazz = Moona::getMoonaJVM().getJNIEnv().FindClass(location);

        if (clazz == nullptr) {
            delete this->pack;
            throw NoSuchClassException();
        }
    }

    JavaClass::~JavaClass() {
        delete this->pack;
    }

    const jclass& JavaClass::getJClass() const noexcept {
        return this->clazz;
    }

    const char* JavaClass::toString() const noexcept {
        return this->classname;
    }
}