#pragma once

#ifndef MOONA_CONST_TYPE
    #define MOONA_CONST_TYPE

    #include "type.hpp"

    namespace moona {

        template <typename C> class ConstType : public Type<const C> {

        };
    }

#endif