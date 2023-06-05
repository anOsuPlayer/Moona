#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "nomethodexception.hpp"
#include "javaclass.hpp"
#include "javasignature.hpp"
#include "javavalue.hpp"
#include "javaobject.hpp"
#include "javaarray.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/unsupportedexception.hpp"

namespace moona {

    class JavaClass;
    class JavaObject;
    class JavaObjectArray;

    class JavaMethod : public Object<JavaMethod> {
        protected:
            const char* name;
            const JavaClass* clazz;
            MethodSignature sign;
            jmethodID method;

        public:
            JavaMethod() = default;
            explicit JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaMethod(const JavaMethod& meth);
            virtual ~JavaMethod() = default;

            const jmethodID& getJMethod() const noexcept;        
            operator const jmethodID&() const noexcept;

            virtual JavaMethod& operator = (const JavaMethod& other) noexcept;
            virtual bool operator == (const JavaMethod& other) const noexcept;

            const MethodSignature& getSignature() const noexcept;

            virtual JValue callOn(const JavaObject& obj, const jvalue* args = nullptr) const;
            virtual JValue callOn(jobject obj, const jvalue* args = nullptr) const;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaMethod& other) const noexcept override final;
    };

    class JavaStaticMethod : public Object<JavaStaticMethod>, public JavaMethod {
        public:
            JavaStaticMethod() = default;
            explicit JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaStaticMethod(const JavaStaticMethod& meth);
            virtual ~JavaStaticMethod() = default;

            virtual JValue callOn(const JavaObject& obj, const jvalue* args = nullptr) const override final;
            virtual JValue callOn(jobject obj, const jvalue* args = nullptr) const override final;
            
            JValue call(const jvalue* args = nullptr) const;
    };

    class JavaConstructor : public Object<JavaConstructor>, protected JavaMethod {
        public:
            JavaConstructor() = default;
            explicit JavaConstructor(const JavaClass& clazz, const ConstructorSignature& cs);
            explicit JavaConstructor(const JavaClass& clazz);
            JavaConstructor(const JavaConstructor& con);
            ~JavaConstructor() = default;

            JavaObject newInstance(const jvalue* args = nullptr) const;
            JavaObjectArray newArray(size_t length) const;
    };
}