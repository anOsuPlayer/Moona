#pragma once

#ifndef MOONA_ANY
    #define MOONA_ANY

    #include <iostream>
    #include <typeinfo>
    #include <type_traits>

    #include "../base/object.hpp"
    #include "../exceptions/castexception.hpp"

    namespace moona {

        class Any : public Object<Any> {
            private:
                const void* data;
                const std::type_info* info;

            public:
                Any() = default;
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

                template <typename T> const Any& operator = (const T& ref) {
                    this->data = &ref;
                    this->info = &typeid(T);

                    return *this;
                }

                template <typename T> operator const T() const {
                    if (typeid(T) != *this->info) {
                        throw BadCastException("Illegal cast.");
                    }
                    return *static_cast<const T*>(this->data);
                }
                operator const void*() const noexcept {
                    return this->data;
                }
        };
    }

#endif