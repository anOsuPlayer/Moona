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

                template <typename B> struct isMoonaElement : public Assertion {
                    isMoonaElement() {
                        this->value = std::is_base_of<Base<B>, B>();
                    }
                    ~isMoonaElement() {
                    }
                };

                template <typename E, typename... super> struct isMoonaEntity : public Assertion {
                    isMoonaEntity() {
                        this->value = std::is_base_of<Entity<E, super...>, E>();
                    }
                    ~isMoonaEntity() {
                    }
                };

                template <typename O, typename... super> struct isMoonaObject : public Assertion {
                    isMoonaObject() {
                        this->value = std::is_base_of<Object<O, super...>, O>();
                    }
                    ~isMoonaObject() {
                    }
                };
        };
    }

#endif