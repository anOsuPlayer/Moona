#pragma once

#include "javaarray.hpp"
#include "javaclass.hpp"
#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    template <typename... T> class TwinObject {
        private:
            unsigned int header1;
            unsigned int header2;
            unsigned int header3;

            TwinObject() {
            }

        public:
            static TwinObject<T...> generate(jobject obj) noexcept {
                TwinObject<T...>** o = new TwinObject<T...>*();
                o = reinterpret_cast<TwinObject<T...>**>(obj);
                return **o; 
            }

            TwinObject(const TwinObject<T...>& obj) : TwinObject() {
                this->header1 = obj.header1;
                this->header2 = obj.header2;
                this->header3 = obj.header3;
            }

            operator const jobject() const noexcept {
                return nullptr;
            }
    };
}