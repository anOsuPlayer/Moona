#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #ifndef MOONA_BASE
        #include "object.hpp"
        #include "entity.hpp"
    #endif

    namespace moona {

        template <typename O> class Object : public Entity<O>, public Type<O> {
            public:
                Object() {
                };
                ~Object() {
                };
        };
    }

#endif