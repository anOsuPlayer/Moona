#include "mwstring.hpp"

namespace moona {

    WideString::WideString(const wchar_t* str) : RawString<wchar_t>(str) {
    }
}