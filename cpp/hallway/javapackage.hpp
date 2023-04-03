#pragma once

#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/nullptrexception.hpp"

namespace moona {

    class JavaPackage : public Object<JavaPackage> {
        private:
            const char* package;

        public:
            JavaPackage() = delete;
            explicit JavaPackage(const char* package);
            ~JavaPackage() = default;

            virtual const char* toString() const noexcept override final;
    };
}