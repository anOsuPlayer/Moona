#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include "object.hpp"
    #include "../conditional/assertion.hpp"

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

            public:
                static void init();

                template <typename O> struct isMoonaObject : public Assertion {
                    isMoonaObject() {
                        this->value = std::is_base_of<Base<O>, O>();
                    }
                    ~isMoonaObject() {
                    }
                };
        };
    }

#endif