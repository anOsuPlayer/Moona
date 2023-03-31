#pragma once

#ifndef MOONA_WIDE_STRING
    #define MOONA_WIDE_WSTRING

    #include "rawstring.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class WideString : public Object<WideString>, public RawString<wchar_t> {

        };
    }

#endif