#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "entity.hpp"
    #include "moonaclass.hpp"

    namespace moona {

        template <typename O> class Object : public virtual Entity<O> {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual Object* clone() const {
                    return new Object<O>();
                }

                virtual const char* toString() const {
                    return Moona::getDefaultObjectToString().cbegin();
                }

                virtual bool equals(const Object* obj2) const {
                    return this == obj2;
                }
        };
    }

#endif