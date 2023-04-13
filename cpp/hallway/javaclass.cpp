#include "javaclass.hpp"

namespace moona {

    JavaClass::JavaClass(const JavaPackage& pack, const char* classname) {
        const char* packname = pack.toString();
        unsigned int packlen = strlen(packname), classlen = strlen(classname);
        
        unsigned int chardelta = 0;
        if (packname[packlen-1] == '/') {
            chardelta = 1;
            this->classname = new char[packlen+classlen+chardelta];
            this->classname[packlen+classlen] = '\0';
        }
        else {
            chardelta = 2;
            this->classname = new char[packlen+classlen+chardelta];
            this->classname[packlen] = '/'; this->classname[packlen+classlen] = '\0';
        }

        for (unsigned int i = 0; i < packlen; i++) {
            this->classname[i] = packname[i];
        }
        for (unsigned int i = packlen+chardelta-1; i < packlen+classlen+chardelta-1; i++) {
            this->classname[i] = classname[i-(packlen+chardelta-1)];
        }

        this->clazz = Moona::getMoonaJVM().getJNIEnv().FindClass(this->classname);
    }

    JavaClass::JavaClass(const char* packname, const char* classname) {
        unsigned int packlen = strlen(packname), classlen = strlen(classname);
        
        unsigned int chardelta = 0;
        if (packname[packlen-1] == '/') {
            chardelta = 1;
            this->classname = new char[packlen+classlen+chardelta];
            this->classname[packlen+classlen] = '\0';
        }
        else {
            chardelta = 2;
            this->classname = new char[packlen+classlen+chardelta];
            this->classname[packlen] = '/'; this->classname[packlen+classlen] = '\0';
        }

        for (unsigned int i = 0; i < packlen; i++) {
            this->classname[i] = packname[i];
        }
        for (unsigned int i = packlen+chardelta-1; i < packlen+classlen+chardelta-1; i++) {
            this->classname[i] = classname[i-(packlen+chardelta-1)];
        }

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