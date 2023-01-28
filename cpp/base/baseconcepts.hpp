#pragma once

#ifndef MOONA_BASE_CONCEPTS
    #define MOONA_BASE_CONCEPTS

    #include <concepts>

    #include "entity.hpp"
    #include "object.hpp"

    namespace moona {

        template <typename E> concept MoonaEntity = requires {
            std::is_base_of<Entity<E>, E>();
        };

        template <typename E> concept NonMoonaEntity = !MoonaEntity<E>;

        template <typename O> concept MoonaObject = requires {
            std::is_base_of<Object<O>, O>();
        };

        template <typename O> concept NonMoonaObject = !MoonaObject<O>;

        template <typename N> concept Numeric = requires {
            std::is_arithmetic<N>();
        };

        template <typename I> concept IntegralType = requires {
            std::is_integral<I>();
        };

        template <typename D> concept DecimalType = requires {
            std::is_floating_point<D>();
        };
    }

#endif