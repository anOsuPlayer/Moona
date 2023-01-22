#pragma once

#ifndef MOONA
    #define MOONA

    #include <iostream>
    #include <concepts>

    #include "base/base.h"

    namespace moona {

        template <typename O> concept MoonaObject = std::is_base_of<Object<O>, O>::value;
    }

#endif