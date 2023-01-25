#pragma once

#ifndef MOONA_BASE
    #define MOONA_BASE

    #include "entity.hpp"
    #include "type.hpp"
    #include "object.hpp"
    #include "moonaclass.hpp"
    #include "benchmark.hpp"

    namespace moona {

        template <typename E> concept MoonaEntity = std::is_base_of<Entity<E>, E>::value;

        template <typename T> concept MoonaType = std::is_base_of<Type<T>, T>::value;

        template <typename O> concept MoonaObject = MoonaEntity<O> && MoonaType<O>;
    }

#endif