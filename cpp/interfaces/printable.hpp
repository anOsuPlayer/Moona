#pragma once

#ifndef MOONA_PRINTABLE_INTERFACE
    #define MOONA_PRINTABLE_INTERFACE

    #include <fstream>

    #include "../base/notation.hpp"

    namespace moona {

        interface Printable {
            protected:
                Printable() = default;
                ~Printable() = default;

            public:
                virtual const char* toString() const noexcept abstract;

                friend std::ostream& operator << (const std::ostream& os, const Printable& obj);
        };
    }

#endif