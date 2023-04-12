#include "javamethodsignature.hpp"

namespace moona {

    Signature::Signature(const char* sign) {
        this->sign = new char[2];
        this->sign[0] = sign[0]; this->sign[1] = '\0';
    }
    Signature::Signature(const Signature& s) {
        this->sign = s.sign;
    }

    Signature::~Signature() {
        
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

    const Signature Signature::V0ID = Signature("V");

    ObjectSignature::ObjectSignature(const char* obj) {
        
    }

    ObjectSignature::~ObjectSignature() {
        
    }

    ArraySignature::ArraySignature(const Signature& s) {
        
    }
    ArraySignature::ArraySignature(const char* obj) {
        
    }

    ArraySignature::~ArraySignature() {
        
    }

    MethodSignature::MethodSignature(const Signature& returntype) {
        
    }
    MethodSignature::MethodSignature(const Signature& returntype, unsigned int argc, const Signature* args) {
        
    }

    MethodSignature::~MethodSignature() {
        
    }

    const char* MethodSignature::get() const noexcept {
        return this->signature;
    }
}