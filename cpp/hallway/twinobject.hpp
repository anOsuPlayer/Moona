#pragma once

#include "javaarray.hpp"
#include "javaclass.hpp"
#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"
#include "../exceptions/indexexception.hpp"

namespace moona {

    template <typename... T> class TwinObject {
        private:
            unsigned int header1, header2, header3;
            TwinObject<T...>** jthis;

            TwinObject() = default;

        public:
            TwinObject(jobject obj) {
                this->jthis = reinterpret_cast<TwinObject<T...>**>(obj);
            }
            TwinObject(const TwinObject<T...>& obj) {
                this->header1 = obj.header1; this->header2 = obj.header2; this->header3 = obj.header3;
                this->jthis = obj.jthis;
            }
            ~TwinObject() = default;

            template <typename S> S atDistance(const size_t dist) const noexcept {
                return *reinterpret_cast<S*>(reinterpret_cast<char*>(*this->jthis)+16+dist);
            }
            template <typename S> S atSafeDistance(const size_t dist) const {
                size_t sizes[] = { sizeof(T)... }; size_t sum;
                for (size_t i = 0; i < sizeof...(T); i++) { sum += sizes[i]; }
                if (dist + sizeof(S) > dist) {
                    throw IllegalArgumentException("The given distance exceeds this TwinObject's bounds.");
                }
                return atDistance<S>(dist);
            }

            template <typename S> S atIndex(const size_t index) const noexcept {
                if (index > sizeof...(T)) {
                    throw IndexOutOfBoundsException("The given index exceeds this TwinObject's declared number of elements.");
                }
                
            }

            operator jobject() const noexcept {
                return reinterpret_cast<jobject>(this->jthis);
            }
    };
}