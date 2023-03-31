#pragma once

#ifndef MOONA_RAW_STRING
    #define MOONA_RAW_STRING

    #include <string>
    #include <string.h>

    #include "strconcepts.hpp"
    #include "../base/object.hpp"
    #include "../interfaces/assignable.hpp"
    #include "../interfaces/deducible.hpp"

    namespace moona {

        template <CharacterType C> class RawString : public Object<RawString<C>>, public Assignable<const C*>, public Deducible<const C*> {
            protected:
                const C* str;

            public:
                RawString() = default;
                RawString(const C* str) {
                    unsigned int size;
                    for (size = 0; str[size] != '\0'; size++);
                    C* newstr = new C[++size];

                    for (int i = 0; i < size; i++) {
                        newstr[i] = str[i];
                    }

                    this->str = newstr;
                }
                ~RawString() {
                    delete this->str;
                }

                const unsigned int length() const noexcept {
                    unsigned int size;
                    for (size = 0; str[size] != '\0'; size++);

                    return size;
                }

                virtual RawString<C>& operator = (const C* str) noexcept override final {
                    this->str = str;

                    return *this;
                }
                virtual operator const C*() const noexcept override final {
                    return this->str;
                }

                virtual RawString<C>* clone() const noexcept override final {
                    return new RawString<C>(this->str);
                }

                virtual const char* toString() const noexcept override final {
                    return this->str;
                }

                virtual bool equals(const Equalable& str) const noexcept override {
                    return this == &str;
                }
                virtual bool operator == (const Equalable& str) const noexcept override {
                    return this == &str;
                }
        };
    }

#endif