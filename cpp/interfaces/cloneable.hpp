#pragma once

#ifndef MOONA_CLONEABLE_INTERFACE
    #define MOONA_CLONEABLE_INTERFACE

    #include "../base/notation.hpp"

    namespace moona {

        interface Cloneable {
            protected:
                Cloneable() = default;
                ~Cloneable() = default;

            public:
                virtual Cloneable* clone() const noexcept abstract;
        };
    }

#endif