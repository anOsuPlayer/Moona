#pragma once

#include "javaarray.hpp"
#include "javaclass.hpp"
#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    class TwinClass : public JavaClass {
        private:
            int* pattern;

            void setupPattern() noexcept;

        public:
            TwinClass();
            explicit TwinClass(const char* clazz);
            TwinClass(const jclass& clazz);
            TwinClass(const JavaClass& clazz);
            TwinClass(const TwinClass& tc);
            virtual ~TwinClass();

            const int* getPattern() const noexcept;

        friend class Moona;
    };
}