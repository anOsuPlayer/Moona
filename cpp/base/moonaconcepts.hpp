#pragma once

#ifndef MOONA_CONCEPTS
    #define MOONA_CONCEPTS

    #include <concepts>

    #include "object.hpp"
    #include "entity.hpp"

    namespace moona {

        template <typename O, typename... super> concept MoonaObject = requires {
            std::is_base_of<Object<O, super...>, O>();
        };

        template <typename E> concept MoonaEntity = requires {
            std::is_base_of<Entity<E>, E>();
        };
        template <typename E, typename... super> concept PureEntity = requires {
            MoonaEntity<E> && !MoonaObject<E, super...>;
        };
    }

#endif