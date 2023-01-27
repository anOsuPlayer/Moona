#pragma once

#ifndef MOONA_STRINGS
    #define MOONA_STRINGS

    #include "../moona.hpp"

    namespace moona {

        class BasicString {
            private:
                const char* strptr;

            public:
                BasicString();
                BasicString(const char* strptr);
                BasicString(std::string str);
                ~BasicString();

                operator const char*() {
                    return this->strptr;
                }
        };
    }

#endif