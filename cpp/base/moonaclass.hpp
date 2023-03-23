#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include <jni.h>

    #include "object.hpp"
    #include "notation.hpp"
    #include "../bridge/jvm.hpp"
    #include "../util/conditional.hpp"

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

                static_field JVM* jvm;

                #ifdef MOONA_MAIN
                    PreMain static void initialize() {
                        
                    }
                    PostMain static void finalize() {
                        Moona::jvm->~JVM();
                    }
                #endif

            public:
                static void init();

                static void jinit(JNIEnv* env);

                template <typename B> struct isMoonaElement : public Conditional {
                    isMoonaElement() {
                        this->value = std::is_base_of<Base<B>, B>();
                    }
                    ~isMoonaElement() {
                    }
                };

                template <typename E, typename... super> struct isMoonaEntity : public Conditional {
                    isMoonaEntity() {
                        this->value = std::is_base_of<Entity<E, super...>, E>();
                    }
                    ~isMoonaEntity() {
                    }
                };

                template <typename O, typename... super> struct isMoonaObject : public Conditional {
                    isMoonaObject() {
                        this->value = std::is_base_of<Object<O, super...>, O>();
                    }
                    ~isMoonaObject() {
                    }
                };
        };
    }

#endif