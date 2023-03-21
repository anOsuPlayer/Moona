#pragma once

#ifndef MOONA_DLLUTIL
    #define MOONA_DLLUTIL

    #include <windows.h>

    #define DLL HMODULE

    #define DLLExport extern "C" __declspec(dllexport)
    #define DLLImport extern "C" __declspec(dllinport)

    #define DLLEntryPoint int WINAPI DllEntryPoint

    #define WinMain int WINAPI WinMain

#endif