#pragma once

#include "rawstring.hpp"
#include "../base/object.hpp"

namespace moona {

    class WideString : public Object<WideString>, public RawString<wchar_t> {
        public:
            WideString() = default;
            WideString(const wchar_t* str);
            virtual ~WideString() = default;
    };
}