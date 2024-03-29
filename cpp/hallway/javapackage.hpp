#pragma once

#include <jni.h>
#include <string.h>

#include "hallwayexception.hpp"
#include "javaclass.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {
    
    class JavaClass;

    class JavaPackage : public Object<JavaPackage> {
        private:
            char* location;

        public:
            JavaPackage() = delete;
            explicit JavaPackage(const char* location);
            JavaPackage(const JavaPackage& pack);
            ~JavaPackage() = default;

            virtual JavaPackage& operator = (const JavaPackage& other) noexcept;
            virtual bool operator == (const JavaPackage& other) const noexcept;

            JavaClass getClass(const char* classname) const;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaPackage& other) const noexcept override final;
    };
}