#pragma once

#ifndef MOONA_STRING
    #define MOONA_STRING

    #include "rawstring.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class String : public Object<String>, public RawString<char> {
            
        };
    }

#endif