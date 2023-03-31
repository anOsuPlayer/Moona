#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "entity.hpp"
    #include "../interfaces/printable.hpp"
    #include "../interfaces/cloneable.hpp"
    #include "../interfaces/equalable.hpp"

    namespace moona {

        template <typename O, typename... super> class Object : public Entity<O>, public Printable, public Cloneable, public Equalable {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual Object<O, super...>* clone() const noexcept override {
                    return new Object<O, super...>();
                }

                virtual const char* toString() const noexcept override {
                    return this->type().name();
                }

                virtual bool equals(const Equalable* obj2) const noexcept override {
                    return this == obj2;
                }
        };
    }

#endif