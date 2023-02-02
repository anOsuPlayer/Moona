#pragma once

#ifndef MOONA_PTRTYPE
    #define MOONA_PTRTYPE

    #include "type.hpp"

    namespace moona {

        template <typename P> class PointerType : public Type<P*> {
            
        };
    }

#endif