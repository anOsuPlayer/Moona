#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "entity.hpp"
    #include "../interfaces/printable.hpp"
    #include "../interfaces/cloneable.hpp"

    namespace moona {

        template <typename O, typename... super> class Object : public Entity<O>, public Printable, public Cloneable {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual Object<O, super...>* clone() const noexcept override {
                    return new Object<O, super...>();
                }

                virtual const char* toString() const noexcept override {
                    return this->type()->name();
                }

                virtual bool equals(const Object* obj2) const noexcept {
                    return this == obj2;
                }
        };
    }

#endif