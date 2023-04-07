#pragma once

#include <string>
#include <string.h>
#include <iostream>

#include "strconcepts.hpp"
#include "../base/object.hpp"
#include "../base/moonaclass.hpp"
#include "../exceptions/indexexception.hpp"
#include "../exceptions/illegalexception.hpp"
#include "../interfaces/assignable.hpp"
#include "../interfaces/deducible.hpp"
#include "../interfaces/comparable.hpp"
#include "../interfaces/indexable.hpp"

namespace moona {

    template <CharacterType C> class RawString : public Object<RawString<C>>, public Comparable, public Indexable<const C&, unsigned int>, public Assignable<const C*>, public Deducible<const C*> {
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
                delete[] this->str;
            }

            virtual RawString<C>& operator = (const C* str) noexcept override final {
                this->str = str;

                return *this;
            }
            virtual operator const C*() const noexcept override final {
                return this->str;
            }

            virtual const C& operator [] (unsigned int i) const override {
                if (i >= this->length()) {
                    throw IndexOutOfBoundsException("The given index goes out of bounds for this String.");
                }
                return this->str[i];
            }

            virtual std::strong_ordering operator <=> (const Comparable& other) const override {
                if (!other.instanceof<RawString<C>>()) {
                    throw IllegalArgumentException("Not a RawString.");
                }

                return dynamic_cast<const RawString&>(other).length() <=> this->length();
            }

            virtual const unsigned int length() const noexcept final {
                if (this->str == nullptr) {
                    return 0;
                }

                unsigned int size;
                for (size = 0; str[size] != '\0'; size++);

                return size;
            }

            virtual RawString<C>* clone() const noexcept override final {
                return new RawString<C>(this->str);
            }

            virtual const char* toString() const noexcept override final {
                return (const char*) this->str;
            }

            virtual bool equals(const Equalable& str) const noexcept override {
                unsigned int len = this->length();

                if (!str.instanceof<RawString<C>>()) {
                    return false;
                }
                else if (const RawString<C>& cast = dynamic_cast<const RawString<C>&>(str); cast.length() != len) {
                    return false;
                }
                else {
                    for (int i = 0; i < len; i++) {
                        if (cast[i] != this->str[i]) {
                            return false;
                        }
                    }

                    return true;
                }
            }
            virtual bool operator == (const Equalable& str) const noexcept override {
                return this->equals(str);
            }
    };
}