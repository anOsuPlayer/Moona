#include "../moona.hpp"

namespace moona {

    BasicString::BasicString() {
        this->strptr = new char;
    }

    BasicString::BasicString(const char* ptr) {
        this->strptr = ptr;
        this->strptr = ptr;
    }

    BasicString::BasicString(std::string str) {
        this->strptr = new char;
        this->strptr = str.c_str();
    }

    BasicString::~BasicString() {
        delete strptr;
    }
}