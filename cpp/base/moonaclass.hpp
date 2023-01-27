#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include "../moona.hpp"

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

            public:
                template <typename T> struct isMoonaObject : public Assertion {
                    public:
                        isMoonaObject() {
                            this->value = std::is_base_of<Object<T>, T>();
                        }
                        ~isMoonaObject() {
                        }
                };
        };
    }

#endif