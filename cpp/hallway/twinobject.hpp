#pragma once

#include <tuple>

#include "javaarray.hpp"
#include "javaclass.hpp"
#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    template <typename... T> class TwinObject {
        private:
            unsigned int header1, header2, header3;

            TwinObject() = default;

        public:
            std::tuple<T...> data = std::tuple<T...>();
            // TwinObject<T...>** jthis;

            ~TwinObject() = default;
            
            static TwinObject<T...>* of(jobject obj) {
                TwinObject<T...>** o = reinterpret_cast<TwinObject<T...>**>(obj);
                return *o;
            }

            // operator jobject() const noexcept {
            //     return reinterpret_cast<jobject>(this->jthis);
            // }
    };
}