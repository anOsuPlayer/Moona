#pragma once

#include "rawstring.hpp"
#include "../base/object.hpp"

namespace moona {

    class String : public Object<String>, public RawString<char> {
        
    };
}