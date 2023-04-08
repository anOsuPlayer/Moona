#pragma once

#include <iostream>
#include <typeinfo>
#include <type_traits>

#include "../exceptions/castexception.hpp"

namespace moona {

    class Any {
        private:
            mutable const void* data;
            mutable const std::type_info* info;

        public:
            Any() {
                this->data = nullptr;
                this->info = nullptr;
            }
            template <typename T> Any(T value) noexcept {
                this->data = &value;
                this->info = &typeid(T);
            }
            Any(const Any& any) noexcept {
                this->data = any.data;
                this->info = any.info;
            }
            virtual ~Any() noexcept final {
            }

            template <typename T> const Any& operator = (const T& ref) const noexcept {
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

            friend std::ostream& operator << (std::ostream& os, const Any& a) {
                os << "Unclear Any";
                return os;
            }
    };
}