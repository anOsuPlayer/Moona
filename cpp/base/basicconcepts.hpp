#pragma once

#ifndef MOONA_BASIC_CONCEPTS
    #define MOONA_BASIC_CONCEPTS

    #include <iostream>
    #include <concepts>

    #include "entity.hpp"
    #include "object.hpp"

    namespace moona {

        template <typename E> concept MoonaEntity = requires {
            std::is_base_of<Entity<E>, E>();
        };

        template <typename O> concept MoonaObject = requires {
            std::is_base_of<Object<O>, O>();
        };
    }

#endif