#pragma once

#ifndef MOONA_JAVA_PACKAGE
    #define MOONA_JAVA_PACKAGE

    #include "hallwayexception.hpp"
    #include "../base/moonaclass.hpp"
    #include "../base/object.hpp"
    #include "../exceptions/nullptrexception.hpp"

    namespace moona {

        class JavaPackage : public Object<JavaPackage> {
            private:
                const char* package;

            public:
                JavaPackage() = delete;
                JavaPackage(const char* package);
                ~JavaPackage() = default;

                virtual const char* toString() const noexcept override final;
        };
    }

#endif