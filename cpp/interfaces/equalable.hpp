#pragma once

#ifndef MOONA_EQUALABLE_INTERFACE
    #define MOONA_EQUALABLE_INTERFACE

    #include "../base/notation.hpp"

    namespace moona {

        interface Equalable {
            protected:
                Equalable() = default;
                ~Equalable() = default;
            
            public:
                virtual bool equals(const Equalable& eq) const noexcept;
                virtual bool equals(const Equalable* eq) const noexcept;

                virtual bool operator == (const Equalable& eq) const noexcept;
                virtual bool operator == (const Equalable* eq) const noexcept;
        };
    }

#endif