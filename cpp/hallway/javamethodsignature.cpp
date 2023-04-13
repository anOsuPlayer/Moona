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
        this->signature[0] = '['; this->signature[0] = 'L';
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
}