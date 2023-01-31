#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    namespace moona {

        template <typename E> class Entity {
            protected:
                Entity() {
                }
                ~Entity() {
                }

            public:
                constexpr Type<E> type() const {
                    return Type<E>();
                }

                template <typename T> bool instanceof() const {
                    return std::is_same<T, E>() || std::is_base_of<T, E>();
                }

                bool sameas(const Entity* e) {
                    return this == e;
                }
        };
    }

#endif