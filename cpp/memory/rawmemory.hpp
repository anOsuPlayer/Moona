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
                mutable std::any* value;

                explicit ChainedPointer(std::any* value, ChainedPointer* next, ChainedPointer* prev);
                explicit ChainedPointer(ChainedPointer* next, ChainedPointer* prev);
                ChainedPointer();
                
                ~ChainedPointer();

                mutable ChainedPointer* prev;

                void setPrev(ChainedPointer* prev) const;

                mutable ChainedPointer* next;

                void setNext(ChainedPointer* next) const;

            public:
                const std::any& get() const;

                const ChainedPointer* getNext() const;
                const ChainedPointer* getPrev() const;

            friend class RawMemory;
        };

        class RawMemory : public Object<RawMemory> {
            private:
                explicit RawMemory(ChainedPointer* begin, ChainedPointer* end);

                mutable unsigned int size = 0;

                mutable ChainedPointer* begin;
                mutable ChainedPointer* end;

            public:
                RawMemory();
                ~RawMemory();

                template <typename T> void allocate(const T& obj) const noexcept {
                    this->end->prev->next = new ChainedPointer(new std::any(obj), this->end, this->end->prev);
                    this->end->prev = this->end->prev->next;

                    this->size++;
                };
                template <typename T> void allocate(const T* obj) const noexcept {
                    this->allocate(*obj);
                };

                template <typename T> const T& get(int at) const noexcept {
                    ChainedPointer* ptr = this->begin;
                    for (int i = 0; i <= at; i++) {
                        ptr = ptr->next;
                    }
                    return *std::any_cast<T>(ptr->value);
                };

                int getSize() const noexcept;

                virtual RawMemory* clone() const noexcept override final {
                    return new RawMemory(this->begin, this->end);
                }
        };
    }

#endif