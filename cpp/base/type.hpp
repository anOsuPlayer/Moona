#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include "../moona.hpp"

    namespace moona {

        template <typename T> class Type {
            private:
                Type() {
                }
                virtual ~Type() {
                }

            public:
                constexpr std::type_info typeInfo() const {
                    return typeid(*this);
                }

                constexpr const char* typeName() const {
                    return typeid(*this).name();
                }

            template <typename O> friend class Object;
        };
    }

#endif