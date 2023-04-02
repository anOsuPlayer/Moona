#pragma once

#ifndef MOONA_ANY
    #define MOONA_ANY

    #include <iostream>
    #include <typeinfo>
    #include <type_traits>

    #include "../base/object.hpp"

    namespace moona {

        class Any : public Object<Any> {
            private:
                const void* data;
                const std::type_info* info;

            public:
                template <typename T> Any(const T& value) noexcept {
                    this->data = &value;
                    this->info = &typeid(T);
                }
                Any(const Any& any) noexcept {
                    this->data = any.data;
                    this->info = any.info;
                }
                virtual ~Any() noexcept final {
                }

                template <typename T> operator const T() {
                    if (typeid(T) != *this->info) {
                        throw std::bad_cast();
                    }
                    return *static_cast<const T*>(this->data);
                }
        };
    }

#endif