#include "mstring.hpp"

namespace moona {

    String::String(const char* str) : RawString<char>(str) {
    }
}