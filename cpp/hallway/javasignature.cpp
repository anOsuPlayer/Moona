#include "javasignature.hpp"

namespace moona {

    PureSignature::PureSignature(const char* signature) {
        unsigned int len = strlen(signature);
        this->signature = new char[len+1];
        this->signature[len] = '\0';

        for (size_t i = 0; i < len; i++) {
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

        for (size_t i = 0; i < len; i++) {
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

        for (size_t i = 1; i < len+1; i++) {
            this->signature[i] = (obj[i-1] == '.') ? '/' : obj[i-1];
        }
    }
    ObjectSignature::ObjectSignature(const ObjectSignature& os) : PureSignature(os) {
    }

    const ObjectSignature ObjectSignature::STRING = ObjectSignature("java/lang/String");

    ArraySignature::ArraySignature(const char* obj, size_t order) {
        if (order == 0) {
            throw IllegalArgumentException("Unable to create a Signature referring to a 0-dimensions array.");
        }

        unsigned int len = strlen(obj);
        this->signature = new char[len+4];
        for (size_t i = 0; i < order; i++) {
            this->signature[i] = '[';
        }
        this->signature[order] = 'L';
        this->signature[len+1+order] = ';'; this->signature[len+2+order] = '\0';

        for (size_t i = 1+order; i < len+1+order; i++) {
            this->signature[i] = (obj[i-1-order] == '.') ? '/' : obj[i-1-order];
        }
    }
    ArraySignature::ArraySignature(const PureSignature& base, size_t order) {
        if (order == 0) {
            throw IllegalArgumentException("Unable to create a Signature referring to a 0-dimensions array.");
        }
        if (base == Signature::V0ID) {
            throw IllegalArgumentException("Cannot create an ArraySignature of a void array.");
        }

        const char* sign = base.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1+order];
        for (size_t i = 0; i < order; i++) {
            this->signature[i] = '[';
        }
        this->signature[len+order] = '\0';

        for (size_t i = order; i < len+order; i++) {
            this->signature[i] = sign[i-order];
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

    const ArraySignature ArraySignature::STRING_ARRAY = ArraySignature(ObjectSignature::STRING);

    PureSignature ArraySignature::getBaseSignature() const noexcept {
        size_t i = 0, len = strlen(this->signature);
        for (const char* ch = this->signature; *ch == '['; ch++) { i++; }
        char sign[len-i+1];
        for (size_t e = 0; e < len-i+1; e++) {
            sign[e] = this->signature[e+i];
        }
        sign[len-i+1] = '\0';

        return PureSignature(sign);
    }

    MethodSignature::MethodSignature(const PureSignature& returntype) {
        const char* sign = returntype.getSignature();
        unsigned int len = strlen(sign);
        char* fullname = new char[len+3];
        fullname[0] = '('; fullname[1] = ')';
        fullname[len+2] = '\0';

        for (size_t i = 2; i < len+2; i++) {
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

        for (size_t i = 0; i < argc; i++) {
            signs[i] = args[i].getSignature();
            lens[i] = strlen(signs[i]);
            totalLength += lens[i];
        }

        const char* retSign = returntype.getSignature();
        unsigned int retLen = strlen(retSign);

        this->signature = new char[totalLength+retLen+3];
        this->signature[0] = '('; this->signature[totalLength+1] = ')'; this->signature[totalLength+retLen+2] = '\0';

        unsigned int passedLen = 0;
        for (size_t i = 0; i < argc; i++) {
            if (args[i] == Signature::V0ID) {
                throw IllegalArgumentException("Cannot build a MethodSignature accepting void as a parameter.");
            }

            for (unsigned int e = 0; e < lens[i]; e++) {
                this->signature[1+passedLen+e] = signs[i][e];
            }
            passedLen += lens[i];
        }

        for (size_t i = totalLength+2; i < totalLength+retLen+2; i++) {
            this->signature[i] = retSign[i-totalLength-2];
        }

        delete[] signs;
        delete[] lens;
    }
    MethodSignature::MethodSignature(const MethodSignature& ms) {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (size_t i = 0; i < len; i++) {
            this->signature[i] = sign[i];
        }
    }

    MethodSignature::~MethodSignature() {
        delete[] this->signature;
    }

    const MethodSignature MethodSignature::BOOLEAN_METHOD = MethodSignature(Signature::BOOLEAN);
    const MethodSignature MethodSignature::BYTE_METHOD = MethodSignature(Signature::BYTE);
    const MethodSignature MethodSignature::SHORT_METHOD = MethodSignature(Signature::SHORT);
    const MethodSignature MethodSignature::CHAR_METHOD = MethodSignature(Signature::CHAR);
    const MethodSignature MethodSignature::INT_METHOD = MethodSignature(Signature::INT);
    const MethodSignature MethodSignature::LONG_METHOD = MethodSignature(Signature::LONG);
    const MethodSignature MethodSignature::FLOAT_METHOD = MethodSignature(Signature::FLOAT);
    const MethodSignature MethodSignature::DOUBLE_METHOD = MethodSignature(Signature::DOUBLE);
    
    const MethodSignature MethodSignature::STRING_METHOD = MethodSignature(ObjectSignature::STRING);

    MethodSignature& MethodSignature::operator = (const MethodSignature& ms) noexcept {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (size_t i = 0; i < len; i++) {
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
            return ObjectSignature("");
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

    const ConstructorSignature ConstructorSignature::DEFAULT = ConstructorSignature();

    FieldSignature::FieldSignature(const PureSignature& type) {
        const char* typeStr = type.getSignature();
        unsigned int len = strlen(type);
        this->signature = new char[len];

        for (size_t i = 0; i < len; i++) {
            this->signature[i] = typeStr[i];
        }
    }
    FieldSignature::FieldSignature(const FieldSignature& fs) {
        this->signature = fs.signature;
    }

    FieldSignature::~FieldSignature() {
        delete[] this->signature;
    }

    const FieldSignature FieldSignature::BOOLEAN_FIELD = FieldSignature(Signature::BOOLEAN);
    const FieldSignature FieldSignature::BYTE_FIELD = FieldSignature(Signature::BYTE);
    const FieldSignature FieldSignature::SHORT_FIELD = FieldSignature(Signature::SHORT);
    const FieldSignature FieldSignature::CHAR_FIELD = FieldSignature(Signature::CHAR);
    const FieldSignature FieldSignature::INT_FIELD = FieldSignature(Signature::INT);
    const FieldSignature FieldSignature::LONG_FIELD = FieldSignature(Signature::LONG);
    const FieldSignature FieldSignature::FLOAT_FIELD = FieldSignature(Signature::FLOAT);
    const FieldSignature FieldSignature::DOUBLE_FIELD = FieldSignature(Signature::DOUBLE);

    const FieldSignature FieldSignature::STRING_FIELD = FieldSignature(ObjectSignature::STRING);

    FieldSignature& FieldSignature::operator = (const FieldSignature& ms) noexcept {
        const char* sign = ms.getSignature();
        unsigned int len = strlen(sign);
        this->signature = new char[len+1]; this->signature[len] = '\0';

        for (size_t i = 0; i < len; i++) {
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