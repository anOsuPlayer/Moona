#pragma once

#ifndef MOONA_CLONEABLE_INTERFACE
    #define MOONA_CLONEABLE_INTERFACE

    #include "../base/notation.hpp"
    #include "../base/entity.hpp"

    namespace moona {

        class Cloneable : public Entity<Cloneable> {
            protected:
                Cloneable() = default;
                ~Cloneable() = default;

            public:
                virtual Cloneable* clone() const noexcept abstract;
        };
    }

#endif