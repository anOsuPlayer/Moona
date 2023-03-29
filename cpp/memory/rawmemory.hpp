#pragma once

#ifndef MOONA_RAWMEMORY
    #define MOONA_RAWMEMORY

    #include <any>
    #include <iostream>

    #include "../base/entity.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class ChainedPointer : public Entity<ChainedPointer> {
            private:
                mutable void* value;

                explicit ChainedPointer(void* value, ChainedPointer* next);
                explicit ChainedPointer(ChainedPointer* next);
                ChainedPointer();
                
                ~ChainedPointer();

                mutable ChainedPointer* next;

                void setNext(ChainedPointer* next) const;

            public:
                const ChainedPointer* getNext() const;

            friend class RawMemory;
        };

        class RawMemory : public Entity<RawMemory> {
            private:
                explicit RawMemory(ChainedPointer* begin, ChainedPointer* end);

                mutable unsigned int elements = 0;

                mutable ChainedPointer* begin;
                mutable ChainedPointer* end;

            public:
                RawMemory();
                ~RawMemory();

                template <typename T> void allocate(const T& obj) const noexcept {
                    this->end->next = new ChainedPointer((T*)&obj, nullptr);
                    this->end = this->end->next;

                    this->elements++;
                };
                template <typename T> void allocate(const T* obj) const noexcept {
                    this->end->next = new ChainedPointer((T*)obj, nullptr);
                    this->end = this->end->next;

                    this->elements++;
                };

                template <typename T> void deallocate(int at) const noexcept {
                    ChainedPointer* ptr = this->begin;
                    for (int i = 0; i <= at-1; i++) {
                        ptr = ptr->next;
                    }

                    ChainedPointer* del = ptr->next;
                    ptr->next = ptr->next->next;

                    delete del;

                    this->elements--;
                }

                template <typename T> const T& get(int at) const noexcept {
                    ChainedPointer* ptr = this->begin;
                    for (int i = 0; i <= at; i++) {
                        ptr = ptr->next;
                    }
                    return *((T*)(ptr->value));
                };

                int size() const noexcept;

                const unsigned short int thisSize() const noexcept;
        };
    }

#endif