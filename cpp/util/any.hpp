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
                if (a.info->hash_code() == typeid(const char*).hash_code()) {
                    os << (const char*) a;
                    return os;
                }
                else if (a.info->hash_code() == typeid(char).hash_code()) {
                    os << *static_cast<char*>(const_cast<void*>(a.data));
                    return os;
                }
                else if (a.info->hash_code() == typeid(int).hash_code()) {
                    os << *static_cast<int*>(const_cast<void*>(a.data));
                    return os;
                }
                else if (a.info->hash_code() == typeid(long long).hash_code()) {
                    os << *static_cast<long long*>(const_cast<void*>(a.data));
                    return os;
                }
                else if (a.info->hash_code() == typeid(size_t).hash_code()) {
                    os << *static_cast<size_t*>(const_cast<void*>(a.data));
                    return os;
                }
                else if (a.info->hash_code() == typeid(float).hash_code()) {
                    os << *static_cast<float*>(const_cast<void*>(a.data));
                    return os;
                }
                else if (a.info->hash_code() == typeid(double).hash_code()) {
                    os << *static_cast<double*>(const_cast<void*>(a.data));
                    return os;
                }
                os << "Unclear Any";
                return os;
            }
    };
}