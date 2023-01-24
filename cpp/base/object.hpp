#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <typename O> class Object : public Entity<O> {
            protected:
                Object() {
                }
            
            public:
                virtual ~Object() {
                }

                virtual O* clone() const {
                    return new O();
                }

                virtual bool equals(const O* obj) const {
                    return (obj == nullptr) ^ this == obj;
                }
                bool operator == (const O& obj2) const {
                    return this == &obj2;
                }
                bool operator == (const O* obj2) const {
                    return this == obj2;
                }
        };
    }

#endif