#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include "../moona.hpp"

    namespace moona {

        template <typename T> class Type {
            private:
                Type() {
                };
                ~Type() {
                }

            public:
                constexpr Type<T> getType() const {
                    return new Type<T>();
                }

                virtual std::string toString() const {
                    return "Object";
                }
                virtual bool equals(const T* t2) const {
                    return this == t2;
                }

            template <typename O> friend class Object;
        };
    }

#endif