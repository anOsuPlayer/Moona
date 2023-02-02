#pragma once

#ifndef MOONA_CONST_POINTER_TYPE
    #define MOONA_CONST_POINTER_TYPE

    #include "type.hpp"
    #include "ptrtype.hpp"

    namespace moona {

        template <typename P> class ConstPointerType : public Type<const P*>, public PointerType<const P> {

        };
    }

#endif