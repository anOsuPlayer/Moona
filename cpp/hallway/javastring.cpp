#include "javastring.hpp"

namespace moona {

    JavaString::JavaString() {
        this->text = new char[0];
    }

    JavaString::JavaString(const char* str, size_t init, size_t end) {
        if (init > end) {
            throw IllegalArgumentException("Invalid combination of init and end index.");
        }

        size_t len = strlen(str);
        if (end == 0) { end = len; }
        this->text = new char[end-init+1]; this->text[end-init] = '\0';
        for (size_t i = init; i < end; i++) {
            this->text[i-init] = str[init+i]; 
        }
    }
    JavaString::JavaString(JavaCharArray& charr, size_t init, size_t end) {
        if (init > end) {
            throw IllegalArgumentException("Invalid combination of init and end index.");
        }

        size_t len = charr.length();
        if (end == 0) { end = len; }
        this->text = new char[end-init+1]; this->text[end-init] = '\0';
        for (size_t i = init; i < end; i++) {
            this->text[i-init] = charr[init+i]; 
        }
    }
    JavaString::JavaString(jstring str, size_t init, size_t end) {
        if (init > end) {
            throw IllegalArgumentException("Invalid combination of init and end index.");
        }

        size_t len = Moona::defaultJNIEnv().GetStringLength(str);
        if (end == 0) { end = len; }
        this->text = new char[len+1]; this->text[len] = '\0';
        Moona::defaultJNIEnv().GetStringUTFRegion(str, init, end, this->text);
    }
    JavaString::JavaString(const JavaString& str, size_t init, size_t end) {
        if (init > end) {
            throw IllegalArgumentException("Invalid combination of init and end index.");
        }

        size_t len = strlen(str.text);
        if (end == 0) { end = len; }
        this->text = new char[end-init+1]; this->text[end-init] = '\0';
        for (size_t i = init; i < end; i++) {
            this->text[i-init] = str.text[init+i];
        }
    }

    JavaString::~JavaString() {
        if (this->str != nullptr) {
            Moona::defaultJNIEnv().DeleteLocalRef(this->str);
        }
        delete[] this->text;
    }

    JavaString& JavaString::operator = (const JavaString& str) noexcept {
        delete[] this->text;

        size_t len = str.length();
        this->text = new char[len+1]; this->text[len] = '\0';
        for (size_t i = 0; i < len; i++) {
            this->text[i] = str.text[i];
        }

        return *this;
    }
    JavaString& JavaString::operator = (jstring str) noexcept {
        delete[] this->text;

        size_t len = Moona::defaultJNIEnv().GetStringUTFLength(str);
        this->text = new char[len+1]; this->text[len] = '\0';
        Moona::defaultJNIEnv().GetStringUTFRegion(str, 0, len, this->text);

        return *this;
    }

    bool JavaString::operator == (const JavaString& str) const noexcept {
        size_t thisLen = this->length(), len = str.length();
        if (len != thisLen) { return false; }

        for (size_t i = 0; i < thisLen; i++) {
            if (this->text[i] != str.text[i]) {
                return false;
            }
        }

        return true;
    }
    bool JavaString::operator == (jstring str) const noexcept {
        size_t thisLen = this->length(), len = Moona::defaultJNIEnv().GetStringUTFLength(str);
        if (len != thisLen) { return false; }

        char* s = new char[len+1]; s[len] = '\0';
        Moona::defaultJNIEnv().GetStringUTFRegion(str, 0, len, s);

        bool result = true;
        for (size_t i = 0; i < thisLen; i++) {
            if (this->text[i] != s[i]) {
                result = false;
                break;
            }
        }

        delete[] s;
        return result;
    }

    char& JavaString::operator [] (size_t index) {
        if (index >= this->length()) {
            throw IndexOutOfBoundsException("The given index goes out of bounds for this JavaString.");
        }
        return this->text[index];
    }

    size_t JavaString::length() const noexcept {
        return strlen(this->text);
    }

    JavaCharArray JavaString::toCharArray() const noexcept {
        return JavaCharArray(this->length(), this->text);
    }

    JavaString::operator const jstring&() const noexcept {
        return this->getJString();
    }
    const jstring& JavaString::getJString() const noexcept {
        if (this->str != nullptr) {
            Moona::defaultJNIEnv().DeleteLocalRef(this->str);
        }
        this->str = Moona::defaultJNIEnv().NewStringUTF(this->text);
        return this->str;
    }

    JavaString::operator const char*() const noexcept {
        return this->text;
    }

    const char* JavaString::toString() const noexcept {
        return this->text;
    }
    bool JavaString::equals(const JavaString& str) const noexcept {
        return *this == str;
    }
}