#pragma once

#ifndef MOONA_BASE
    #define MOONA_BASE

    #include "type.hpp"
    #include "entity.hpp"
    #include "object.hpp"
    #include "assertion.hpp"

    namespace moona {

        template <typename E> concept MoonaEntity = std::is_base_of<Entity<E>, E>::value;

        template <typename T> concept MoonaType = std::is_base_of<Type<T>, T>::value;

        template <typename O> concept MoonaObject = std::is_base_of<Object<O>, O>::value;
    }

#endif