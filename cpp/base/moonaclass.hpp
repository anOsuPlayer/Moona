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

                template <typename O> struct isMoonaElement : public Assertion {
                    isMoonaObject() {
                        this->value = std::is_base_of<Base<O>, O>();
                    }
                    ~isMoonaObject() {
                    }
                };

                template <typename E, typename... super> struct isMoonaEntity : public Assertion {
                    isMoonaObject() {
                        this->value = std::is_base_of<Entity<E, super...>, E>();
                    }
                    ~isMoonaObject() {
                    }
                };
        };
    }

#endif