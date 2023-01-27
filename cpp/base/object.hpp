#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <typename O> class Object : public Entity<O>, public Type<O> {
            public:
                Object() {
                }
                virtual ~Object() {
                }

                Type<O>* getType() const {
                    return new Type<O>();
                }

                virtual const char* toString() const {
                    const char* r = "Object";
                    return r;
                }
                virtual bool equals(const O& t2) const {
                    return this == &t2;
                }
        };
    }

#endif