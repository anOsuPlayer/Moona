#pragma once

#ifndef MOONA_RAW_STRING
    #define MOONA_RAW_STRING

    #include <string>

    #include "strconcepts.hpp"
    #include "../base/object.hpp"
    #include "../interfaces/assignable.hpp"

    namespace moona {

        template <CharacterType C> class RawString : public Object<RawString<C>>, public Assignable<const C*> {
            private:
                C* str;

            public:
                virtual RawString<C>& operator = (const C* str) noexcept override final {
                    this->str = str;

                    return const_cast<RawString<C>&>(*this);
                }
        };
    }

#endif