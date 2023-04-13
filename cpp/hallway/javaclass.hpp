#pragma once

#include <jni.h>

#include "javapackage.hpp"
#include "hallwayexception.hpp"
#include "noclassexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaClass : public Object<JavaClass> {
        private:
            char* classname;
            jclass clazz;

        public:
            JavaClass() = delete;
            explicit JavaClass(const JavaPackage& pack, const char* classname);
            explicit JavaClass(const char* packname, const char* classname);
            ~JavaClass();

            const jclass& getJClass() const noexcept;

            virtual const char* toString() const noexcept override final;
    };
}