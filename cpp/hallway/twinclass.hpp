#pragma once

#include "javaclass.hpp"
#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    class TwinClass : public Object<TwinClass> {
        private:
            JavaClass clazz;

        public:
            TwinClass() = default;
            explicit TwinClass(const char* clazz);
            TwinClass(const jclass& clazz);
            TwinClass(const JavaClass& clazz);
            TwinClass(const TwinClass& tc);
            ~TwinClass() = default;

            TwinClass& operator = (const TwinClass& tc) noexcept;

            bool operator == (const TwinClass& tc) const noexcept;

            const char* toString() const noexcept;
            bool equals(const TwinClass& tc) const noexcept;
    };
}