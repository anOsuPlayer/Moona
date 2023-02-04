#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "entity.hpp"
    #include "moonaclass.hpp"

    namespace moona {

        template <typename O, typename... super> class Object : public Entity<O> {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual Object<O, super...>* clone() const {
                    return new Object<O, super...>();
                }

                virtual const char* toString() const {
                    return this->type()->name();
                }

                virtual bool equals(const Object* obj2) const {
                    return this == obj2;
                }
        };
    }

#endif