#pragma once

#ifndef MOONA_STRING
    #define MOONA_STRING

    #include "rawstring.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class WideString : public Object<WideString>, public RawString<wchar_t> {

        };
    }

#endif