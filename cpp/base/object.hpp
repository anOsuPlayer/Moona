#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "../moona.hpp"

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