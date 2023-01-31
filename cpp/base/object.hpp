#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "entity.hpp"

    namespace moona {

        template <typename O> class Object : public Entity<O> {
            public:
                Object() {
                }
                ~Object() {
                }

                virtual bool equals(const Object<O>* obj2) const {
                    return (this == obj2);
                }

                virtual Object* clone() const {
                    return new Object<O>();
                }

                virtual const char* toString() const {
                    std::string_view sw = "Object";
                    return sw.cbegin();
                }
        };
    }

#endif