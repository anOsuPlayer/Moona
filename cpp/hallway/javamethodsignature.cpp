#include "javamethodsignature.hpp"

namespace moona {

    Signature::Signature(const char* sign) {
        unsigned int len = strlen(sign);
        this->sign = new char[len+1];
        this->sign[0] = sign[0]; this->sign[1] = '\0';
    }
    Signature::Signature(const Signature& s) {
        this->sign = s.sign;
    }

    Signature::~Signature() {
        delete[] this->sign;
    }

    const char* Signature::get() const noexcept {
        return this->sign;
    }

    const Signature Signature::BOOLEAN = Signature("Z");
    const Signature Signature::BYTE = Signature("B");
    const Signature Signature::SHORT = Signature("S");
    const Signature Signature::INT = Signature("I");
    const Signature Signature::LONG = Signature("J");
    const Signature Signature::FLOAT = Signature("F");
    const Signature Signature::DOUBLE = Signature("D");

    const Signature Signature::VOOID = Signature("V");

    ObjectSignature::ObjectSignature(const char* obj) {
        unsigned int len = strlen(obj);
        this->sign = new char[len+3];
        this->sign[0] = 'L'; this->sign[len+1] = ';'; this->sign[len+2] = '\0';

        for (int i = 1; i < len+1; i++) {
            this->sign[i] = obj[i-1];
        }
    }

    ObjectSignature::~ObjectSignature() {
        delete[] this->sign;
    }

    ArraySignature::ArraySignature(const Signature& s) {
        const char* sign = s.get();
        unsigned int len = strlen(sign);
        this->sign = new char[len+2];
        this->sign[0] = '['; this->sign[len+1] = '\0';

        for (int i = 1; i < len+1; i++) {
            this->sign[i] = sign[i-1];
        }
    }
    ArraySignature::ArraySignature(const char* obj) {
        unsigned int len = strlen(sign);
        this->sign = new char[len+4];
        this->sign[0] = '['; this->sign[1] = 'L';
        this->sign[len+2] = ';'; this->sign[len+3] = '\0';

        for (int i = 2; i < len+2; i++) {
            this->sign[i] = obj[i-2];
        }
    }

    const ArraySignature ArraySignature::BOOLEAN_ARRAY = ArraySignature("Z");
    const ArraySignature ArraySignature::BYTE_ARRAY = ArraySignature("B");
    const ArraySignature ArraySignature::SHORT_ARRAY = ArraySignature("S");
    const ArraySignature ArraySignature::INT_ARRAY = ArraySignature("I");
    const ArraySignature ArraySignature::LONG_ARRAY = ArraySignature("J");
    const ArraySignature ArraySignature::FLOAT_ARRAY = ArraySignature("F");
    const ArraySignature ArraySignature::DOUBLE_ARRAY = ArraySignature("D");

    ArraySignature::~ArraySignature() {
        delete[] this->sign;
    }

    MethodSignature::MethodSignature(const Signature& returntype) {
        const char* sign = returntype.get();
        unsigned int len = strlen(sign);
        this->signature = new char[len+3];
        this->signature[0] = '('; this->signature[1] = ')';
        this->signature[len+2] = '\0';

        for (int i = 2; i < len+2; i++) {
            this->signature[i] = sign[i-2];
        }
    }
    MethodSignature::MethodSignature(const Signature& returntype, unsigned int argc, const Signature* args) {
        const char** signs = new const char*[argc];
        unsigned int* lengths = new unsigned int[argc];
        unsigned int totalLength = 0;

        const char* retSign = returntype.get();
        unsigned int retLength = strlen(retSign);

        for (int i = 0; i < argc; i++) {
            signs[i] = args[i].get();
            lengths[i] = strlen(signs[i]);
            totalLength += lengths[i];
        }

        this->signature = new char[totalLength+retLength+3];
        this->signature[0] = '('; this->signature[totalLength+retLength+2] = '\0';

        unsigned int pastLength = 1;
        for (unsigned int i = 0; i < argc; i++) {
            for (unsigned int e = 0; e < lengths[i]; e++) {
                this->signature[pastLength+e] = signs[i][e];
            }
            pastLength += lengths[i];
        }

        this->signature[pastLength++] = ')';

        for (unsigned int i = 0; i < retLength; i++) {
            this->signature[pastLength+i] = retSign[i];
        }

        delete[] signs;
        delete[] lengths;
    }

    MethodSignature::~MethodSignature() {
        delete[] this->signature;
    }

    const char* MethodSignature::get() const noexcept {
        return this->signature;
    }
}