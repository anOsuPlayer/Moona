#pragma once

#if !defined(MOONA_CONCEPTS) && __cplusplus >= 202002L
    #define MOONA_CONCEPTS

    #include <concepts>

    #include "object.hpp"
    #include "entity.hpp"

    namespace moona {

        template <typename B> concept MoonaElement = requires {
            std::is_base_of<Base<B>, B>();
        };

        template <typename E, typename... super> concept MoonaEntity = requires {
            std::is_base_of<Entity<E, super...>, E>();
        };

        template <typename O, typename... super> concept MoonaObject = requires {
            std::is_base_of<Object<O, super...>, O>();
        };

        template <typename E, typename... super> concept PureEntity = MoonaEntity<E, super...> && !MoonaObject<E, super...>;
    }

#endif