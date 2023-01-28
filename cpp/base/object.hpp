#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include <string>

    #include "entity.hpp"

    namespace moona {

        template <typename O> class Object : public Entity<O> {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual constexpr std::string toString() const {
                    return "Object";
                }

                virtual bool equals(const Object<O>* o) const {
                    return (this == o);
                }
        };
    }

#endif