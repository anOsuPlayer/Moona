#pragma once

#ifndef MOONA_REFTYPE
    #define MOONA_REFTYPE

    #include "type.hpp"

    namespace moona {

        template <typename R> class RefType : public Type<R&> {

        };
    }

#endif