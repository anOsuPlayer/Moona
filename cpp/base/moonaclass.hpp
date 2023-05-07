#pragma once

#include <jni.h>

#include "object.hpp"
#include "notation.hpp"
#include "moonacore.hpp"
#include "moonasetting.hpp"
#include "moonahandlingexception.hpp"
#include "../hallway/hallwayexception.hpp"
#include "../hallway/javanotation.hpp"
#include "../hallway/jvm.hpp"
#include "../util/conditional.hpp"

#define DefaultJVM Moona::jvm
#define DefaultENV Moona::jvm->env

namespace moona {

    class JVM;

    class Moona : private MoonaCore {
        private:
            Moona() = delete;
            ~Moona() = delete;
            
            static_field JVM* jvm;

            static void commonInit();

        public:
            const static_field MoonaSetting enableHallwayAccess = MoonaSetting(false);

            static void init();
            static void jinit(JNIEnv* env);

            static void interrupt();

            static bool isOn() noexcept;
            static bool isJVMinitialized() noexcept;

            static const JVM& getMoonaJVM();
            static JNIEnv& defaultJNIEnv();

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