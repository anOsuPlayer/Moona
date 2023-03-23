#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include <jni.h>

    #include "object.hpp"
    #include "notation.hpp"
    #include "../bridge/jvm.hpp"
    #include "../conditions/conditional.hpp"

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

                staticfield JVM* jvm;

                static void initialize() PreMain;
                static void finalize() PostMain;

            public:
                static void init();

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