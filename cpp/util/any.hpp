#pragma once

#include <iostream>
#include <typeinfo>
#include <type_traits>

#include "../base/object.hpp"
#include "../exceptions/castexception.hpp"

namespace moona {

    class Any {
        private:
            const mutable void* data;
            const mutable std::type_info* info;

        public:
            Any() {
                this->data = nullptr;
                this->info = nullptr;
            }
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

            template <typename T> const Any& operator = (const T& ref) const {
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

            template <typename T> bool operator == (const T& other) const noexcept {
                return (typeid(T) == *this->info && *static_cast<const T*>(this->data) == other);
            }
    };
}