#include "javasignature.hpp"

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

    PureSignature& PureSignature::operator = (const PureSignature& ps2) noexcept {
        const char* sign = ps2.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = sign[i];
        }

        return *this;
    }
    bool PureSignature::operator == (const PureSignature& ps2) const noexcept {
        return (strcmp(this->signature, ps2.signature) == 0);
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
    const Signature Signature::CHAR = Signature("C");
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
        if (base == Signature::V0ID) {
            throw IllegalArgumentException("Cannot create an ArraySignature of a void array.");
        }

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
    const ArraySignature ArraySignature::CHAR_ARRAY = ArraySignature(Signature::CHAR);
    const ArraySignature ArraySignature::SHORT_ARRAY = ArraySignature(Signature::SHORT);
    const ArraySignature ArraySignature::INT_ARRAY = ArraySignature(Signature::INT);
    const ArraySignature ArraySignature::LONG_ARRAY = ArraySignature(Signature::LONG);
    const ArraySignature ArraySignature::FLOAT_ARRAY = ArraySignature(Signature::FLOAT);
    const ArraySignature ArraySignature::DOUBLE_ARRAY = ArraySignature(Signature::DOUBLE);

    MethodSignature::MethodSignature(const PureSignature& returntype) {
        const char* sign = returntype.getSignature();
        unsigned int len = strlen(sign);
        char* fullname = new char[len+3];
        fullname[0] = '('; fullname[1] = ')';
        fullname[len+2] = '\0';

        for (unsigned int i = 2; i < len+2; i++) {
            fullname[i] = sign[i-2];
        }

        this->signature = fullname;
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
            if (args[i] == Signature::V0ID) {
                throw IllegalArgumentException("Cannot build a MethodSignature accepting void as a parameter.");
            }

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
    MethodSignature::MethodSignature(const MethodSignature& ms) {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = sign[i];
        }
    }

    MethodSignature::~MethodSignature() {
        delete[] this->signature;
    }

    MethodSignature& MethodSignature::operator = (const MethodSignature& ms) noexcept {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = sign[i];
        }

        return *this;
    }
    bool MethodSignature::operator == (const MethodSignature& ms) const noexcept {
        return (strcmp(this->signature, ms.signature) == 0);
    }

    MethodSignature::operator const char*() const noexcept {
        return this->signature;
    }
    const char* MethodSignature::getSignature() const noexcept {
        return this->signature;
    }

    const PureSignature MethodSignature::returnType() const noexcept {
        unsigned int len = strlen(this->signature); char* begin = this->signature;
        for (int i = 0; *begin != ')'; i++) { begin++; }
        if (*(begin+1) == '[' || *(begin+1) == 'L') {
            return ObjectSignature("placeholder");
        }
        else {
            switch (*(begin+1)) {
                case 'Z' : return Signature::BOOLEAN;
                case 'B' : return Signature::BYTE;
                case 'S' : return Signature::SHORT;
                case 'C' : return Signature::BYTE;
                case 'I' : return Signature::INT;
                case 'J' : return Signature::LONG;
                case 'F' : return Signature::FLOAT;
                case 'D' : return Signature::DOUBLE;
                case 'V' : return Signature::V0ID;
            }
        }
        return Signature::V0ID;
    }

    ConstructorSignature::ConstructorSignature() : MethodSignature(Signature::V0ID) {
    }
    ConstructorSignature::ConstructorSignature(unsigned int argc, const PureSignature* args) : MethodSignature(Signature::V0ID, argc, args) {
    }
    ConstructorSignature::ConstructorSignature(const ConstructorSignature& cs) : MethodSignature(cs) {
    }

    ConstructorSignature::~ConstructorSignature() {
        delete[] this->signature;
    }

    const ConstructorSignature ConstructorSignature::standard = ConstructorSignature();

    FieldSignature::FieldSignature(const PureSignature& type) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        const char* typeStr = type.getSignature();
        unsigned int len = strlen(type);
        this->signature = new char[len];

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = typeStr[i];
        }
    }
    FieldSignature::FieldSignature(const FieldSignature& fs) {
        this->signature = fs.signature;
    }

    FieldSignature::~FieldSignature() {
        delete[] this->signature;
    }

    FieldSignature& FieldSignature::operator = (const FieldSignature& ms) noexcept {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (unsigned int i = 0; i < len; i++) {
            this->signature[i] = sign[i];
        }

        return *this;
    }
    bool FieldSignature::operator == (const FieldSignature& ms) const noexcept {
        return (strcmp(this->signature, ms.signature) == 0);
    }

    FieldSignature::operator const char*() const noexcept {
        return this->signature;
    }
    const char* FieldSignature::getSignature() const noexcept {
        return this->signature;
    } 
}