#pragma once

#ifndef MOONA_CONST_REFTYPE
    #define MOONA_CONST_REFTYPE

    #include "type.hpp"
    #include "consttype.hpp"

    namespace moona {

        template <typename R> class ConstRefType : public Type<const R&>, public ConstType<const R> {

        };
    }

#endif