#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include <typeinfo>
    #include <string>

    namespace moona {

        template <typename T> class Type {
            private:
                Type() {
                }
                ~Type() {
                }

            public:
                template <typename S> constexpr bool isBaseOf() const {
                    return std::is_base_of<T, S>();
                }
                template <typename S> constexpr bool isBaseOf(const Type<S>* ts) const {
                    return std::is_base_of<T, S>();
                }

                template <typename S> constexpr bool isDerivedOf() const {
                    return std::is_base_of<S, T>();
                }
                template <typename S> constexpr bool isDerivedOf(const Type<S>* ts) const {
                    return std::is_base_of<S, T>();
                }

                template <typename S> constexpr bool isSameAs() const {
                    return std::is_same<S, T>();
                }
                template <typename S> constexpr bool isSameAs(const Type<S>* ts) const {
                    return std::is_same<S, T>();
                }

                constexpr std::type_info info() const {
                    return typeid(T);
                }
                constexpr std::string name() const {
                    return typeid(T).name();
                }

                constexpr unsigned short int size() const {
                    return sizeof(T);
                }

            template <typename E> friend class Entity;
        };
    }

#endif