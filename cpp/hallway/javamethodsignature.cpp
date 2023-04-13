#include "javamethodsignature.hpp"

namespace moona {

    PureSignature::PureSignature(const char* signature) {
        unsigned int len = strlen(signature);
        this->signature = new char[len+1];
        this->signature[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = signature[i];
        }
    }
    PureSignature::PureSignature(const PureSignature& s) : PureSignature(s.getSignature()) {
    }

    PureSignature::~PureSignature() {
        delete[] this->signature;
    }

    PureSignature::operator const char*() const noexcept {
        return this->signature;
    }
    const char* PureSignature::getSignature() const noexcept {
        return this->signature;
    }

    Signature::Signature(const char* signature) : PureSignature(signature) {
    }
    Signature::Signature(const Signature& s) : PureSignature(s) {
    }

    const Signature Signature::BOOLEAN = Signature("Z");
    const Signature Signature::BYTE = Signature("B");
    const Signature Signature::SHORT = Signature("S");
    const Signature Signature::INT = Signature("I");
    const Signature Signature::LONG = Signature("J");
    const Signature Signature::FLOAT = Signature("F");
    const Signature Signature::DOUBLE = Signature("D");

    const Signature Signature::V0ID = Signature("V");

    ObjectSignature::ObjectSignature(const char* obj) {
        unsigned int len = strlen(obj);
        this->signature = new char[len+3];
        this->signature[0] = 'L';
        this->signature[len+1] = ';'; this->signature[len+2] = '\0';

        for (unsigned int i = 1; i < len+1; i++) {
            this->signature[i] = obj[i-1];
        }
    }
    ObjectSignature::ObjectSignature(const ObjectSignature& os) : PureSignature(os) {
    }

    ArraySignature::ArraySignature(const char* obj) {
        unsigned int len = strlen(obj);
        this->signature = new char[len+4];
        this->signature[0] = '['; this->signature[1] = 'L';
        this->signature[len+2] = ';'; this->signature[len+3] = '\0';

        for (unsigned int i = 2; i < len+2; i++) {
            this->signature[i] = obj[i-2];
        }
    }
    ArraySignature::ArraySignature(const PureSignature& base) {
        const char* sign = base.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+2];
        this->signature[0] = '['; this->signature[len+1] = '\0';

        for (unsigned int i = 1; i < len+1; i++) {
            this->signature[i] = sign[i-1];
        }
    }

    const ArraySignature ArraySignature::BOOLEAN_ARRAY = ArraySignature(Signature::BOOLEAN);
    const ArraySignature ArraySignature::BYTE_ARRAY = ArraySignature(Signature::BYTE);
    const ArraySignature ArraySignature::SHORT_ARRAY = ArraySignature(Signature::SHORT);
    const ArraySignature ArraySignature::INT_ARRAY = ArraySignature(Signature::INT);
    const ArraySignature ArraySignature::LONG_ARRAY = ArraySignature(Signature::LONG);
    const ArraySignature ArraySignature::FLOAT_ARRAY = ArraySignature(Signature::FLOAT);
    const ArraySignature ArraySignature::DOUBLE_ARRAY = ArraySignature(Signature::DOUBLE);

    MethodSignature::MethodSignature(const PureSignature& returntype) {
        const char* sign = returntype.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+3];
        this->signature[0] = '('; this->signature[1] = ')';
        this->signature[len+2] = '\0';

        for (unsigned int i = 2; i < len+2; i++) {
            this->signature[i] = sign[i-2];
        }
    }
    MethodSignature::MethodSignature(const PureSignature& returntype, unsigned int argc, const PureSignature* args) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        const char** signs = new const char*[argc];
        unsigned int* lens = new unsigned int[argc];
        unsigned int totalLength = 0;

        for (unsigned int i = 0; i < argc; i++) {
            signs[i] = args[i].getSignature();
            lens[i] = strlen(signs[i]);
            totalLength += lens[i];
        }

        const char* retSign = returntype.getSignature();
        unsigned int retLen = strlen(retSign);

        this->signature = new char[totalLength+retLen+3];
        this->signature[0] = '('; this->signature[totalLength+1] = ')'; this->signature[totalLength+retLen+2] = '\0';

        unsigned int passedLen = 0;
        for (unsigned int i = 0; i < argc; i++) {
            for (unsigned int e = 0; e < lens[i]; e++) {
                this->signature[1+passedLen+e] = signs[i][e];
            }
            passedLen += lens[i];
        }

        for (unsigned int i = totalLength+2; i < totalLength+retLen+2; i++) {
            this->signature[i] = retSign[i-totalLength-2];
        }

        delete[] signs;
        delete[] lens;
    }
    
    MethodSignature::~MethodSignature() {
        delete[] this->signature;
    }

    MethodSignature::operator const char*() const noexcept {
        return this->signature;
    }
    const char* MethodSignature::getSignature() const noexcept {
        return this->signature;
    }
}