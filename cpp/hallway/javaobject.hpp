#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javafield.hpp"
#include "javavalue.hpp"
#include "javastring.hpp"
#include "jvmexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/object.hpp"
#include "../exceptions/indexexception.hpp"

namespace moona {

    class JavaMethod;
    class JavaStaticMethod;
    class JavaField;

    class JavaObject : public Object<JavaObject> {
        protected:
            jobject obj;

            JavaObject() = default;

            static JValue _call(jobject obj, const JavaMethod& jm, const jvalue* args = nullptr);

            static JValue _access(jobject obj, const JavaField& jf);
            static void _edit(jobject obj, const JavaField& jf, const jvalue& value);

        public:
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            virtual ~JavaObject();

            operator const jobject&() const noexcept;
            jobject getJObject() const noexcept;

            virtual JavaObject asObject() const noexcept final;

            virtual JavaObject& operator = (const jobject& obj) noexcept;
            bool operator == (const jobject& obj) const noexcept;

            JValue call(const JavaMethod& jm, const jvalue* args = nullptr) const;

            JValue access(const JavaField& jf) const;
            void edit(const JavaField& jf, const jvalue& value);

            JavaClass getClass() const;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaObject& obj) const noexcept override final;

            bool equals(const jobject& obj) const noexcept;
            bool sameType(const jobject& obj) const noexcept;

            bool isInstanceof(const jclass& clazz) const noexcept;

        friend class JavaMethod;
        friend class JavaField;
    };

    class JavaObjectArray;

    class JavaObjectArrayElement : public Object<JavaObjectArrayElement>, public JavaObject {
        private:
            JavaObjectArray* ref;
            size_t refIndex;

            bool hasChanged = false;

            JavaObjectArrayElement() = default;

            JavaObjectArrayElement(JavaObjectArray* ref, size_t index);

        public:
            JavaObjectArrayElement(const jobject& obj) = delete;
            JavaObjectArrayElement(const JavaObject& obj) = delete;
            virtual ~JavaObjectArrayElement();

            virtual JavaObject& operator = (const jobject& obj) noexcept override final;
        
        friend class JavaObjectArray;
    };

    class JavaObjectArray : public Object<JavaObjectArray> {
        protected:
            jobjectArray arr;
            JavaObjectArrayElement* currentElement;

            static JavaStaticMethod PRINT_ARRAY;
            static JavaStaticMethod ARRAY_EQUALS;

            JavaObjectArray() = default;

            void popCurrent() noexcept;

        public:
            JavaObjectArray(const jobjectArray& arr);
            JavaObjectArray(const JavaObjectArray& arr);
            virtual ~JavaObjectArray();

            operator const jobjectArray&() const noexcept;
            jobjectArray getJObjectArray() const noexcept;

            virtual JavaObject asObject() const noexcept final;

            JavaObjectArrayElement& operator [] (size_t index);

            JavaObjectArray& operator = (const jobjectArray& arr) noexcept;
            bool operator == (const jobjectArray& arr) const noexcept;

            size_t length() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaObjectArray& obj) const noexcept override final;

            bool equals(const jobjectArray& obj) const noexcept;
        
        friend class JavaObjectArrayElement;
        friend class Moona;
    };
}