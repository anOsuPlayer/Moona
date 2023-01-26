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
                    std::string r("Object ");
                    r.append(this->typeName());
                    return (r.c_str());
                }
                virtual bool equals(const O& t2) const {
                    return this == &t2;
                }
        };
    }

#endif